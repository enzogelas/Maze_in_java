package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.event.*;

import exception.BoxException;
import exception.MazeReadingException;
import exception.NoPathException;
import graphmaze.Dijkstra;
import graphmaze.Vertex;
import modelmaze.*;
/**
 * Le modele de l'application.
 * C'est elle qui effectue les modifications sur le labyrinthe et la grilled'hexagones.
 * @author enzog
 */
public class MazeAppModel {
	
	private final MazeApp mazeApp;
	private Maze maze;
	private HexagonGrid hexagonGrid;
	private boolean setArrival;
	private boolean setDeparture;
	private boolean setEmpty;
	private boolean setWall;
	private boolean tracePath;
	private MazeHexagon currentPointedHexagon; // indique l'hexagon pointe par la souris
	private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();
	/**
	 * Cree un modele pour l'application specifiee
	 * @param mazeApp L'application
	 */
	public MazeAppModel(MazeApp mazeApp) {
		this.maze = new Maze(0,0,new MazeBox[0][0]);
		this.mazeApp = mazeApp;
		createHexagonGrid(); 
	}
	/**
	 * Cree une grille d'hexagone en se basant sur l'etat actuel.
	 * Elle tient compte de l'hexagone pointe par la souris et du choix de l'utilisateur d'afficher le chemin ou non
	 */
	private void createHexagonGrid() { // CHANGER EN PRIVATE ???
		int l = maze.getLargeur();
		int h = maze.getHauteur();
		MazeHexagon[][] futureGrid = new MazeHexagon[l][h];
		List<Vertex> path = new ArrayList<Vertex>();
		if(tracePath)
			path = calculPath();
		for(int i=0 ; i<l ; i++)
			for(int j=0 ; j<h ; j++) {
				MazeBox mazeBox = (MazeBox) maze.getBoxes()[i][j];	
				if(mazeBox.isDeparture())
					futureGrid[i][j] = new MazeHexagon(mazeBox,new Color(0,200,0));
				else if(mazeBox.isArrival())
					futureGrid[i][j] = new MazeHexagon(mazeBox, new Color(230,0,0));	
				else if(path.contains(mazeBox))
					futureGrid[i][j] = new MazeHexagon(mazeBox, Color.YELLOW);
				else if(mazeBox.isEmpty())
					futureGrid[i][j] = new MazeHexagon(mazeBox,Color.WHITE);	
				else if(mazeBox.isWall())
					futureGrid[i][j] = new MazeHexagon(mazeBox,Color.BLACK);
				if(currentPointedHexagon != null)
					if(mazeBox == currentPointedHexagon.getMazeBox())
						futureGrid[i][j] = new MazeHexagon(mazeBox, Color.CYAN);
			}								
		this.hexagonGrid = new HexagonGrid(l,h,futureGrid);
	}
	/**
	 * Change la couleur des cases du labyrinthe en fonction du labyrinthe actuel.
	 * Tient compte du choix de l'utilisateur d'afficher le chemin ou pas
	 */
	private void changeHexagonGrid() { // CHANGER EN PRIVATE ???
		int l = hexagonGrid.getLargeur();
		int h = hexagonGrid.getHauteur();
		MazeHexagon[][] hexagons = hexagonGrid.getHexagons();
		List<Vertex> path = new ArrayList<Vertex>();
		if(tracePath)
			path = calculPath();
		for(int i=0 ; i<l ; i++)
			for(int j=0 ; j<h ; j++) {
				MazeBox mazeBox = (MazeBox) maze.getBoxes()[i][j];
				hexagons[i][j].setMazeBox(mazeBox);
				if(mazeBox.isDeparture())
					hexagons[i][j].setColor(new Color(0,200,0));
				else if(mazeBox.isArrival())
					hexagons[i][j].setColor(new Color(230,0,0));	
				else if(path.contains(mazeBox))
					hexagons[i][j].setColor(Color.YELLOW);
				else if(mazeBox.isEmpty())
					hexagons[i][j].setColor(Color.WHITE);	
				else if(mazeBox.isWall())
					hexagons[i][j].setColor(Color.BLACK);
				
			}
	}
	/**
	 * Rajoute des observateurs (ici il n'y aura que la fenetre de l'application)
	 * @param listener Un observateur 
	 */
	public void addObserver(ChangeListener listener) {
		listeners.add(listener);
	}
	/**
	 * Informe les observateurs (ici uniquement la fenetre de l'application) qu'il y a un changement
	 */
	public void stateChanged() {
		ChangeEvent evt = new ChangeEvent(this);
		
		for (ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}
	/**
	 * Calcul le plus court chemin entre de la case Depart a la case Arrivee.
	 * Affiche des messages d'erreurs si une de ces cases et manquante ou si un tel chemin n'existe pas.
	 * @return Une liste representant le chemin entre les cases Depart et Arrivee, si il existe
	 */
	public List<Vertex> calculPath(){ // CHANGER EN PRIVATE ???
		Vertex startVertex = null;
		Vertex endVertex = null;
		List<Vertex> path = new ArrayList<Vertex>();
		try {
		startVertex = maze.getDeparture();
		endVertex = maze.getArrival();
		path = Dijkstra.dijkstra(maze, startVertex, endVertex).getShortestPaths(endVertex);
		} catch(BoxException bex){ 
			JOptionPane.showMessageDialog(mazeApp, "Il manque une case Depart ou Arrivee", "Erreur lors de la recherche du chemin", JOptionPane.WARNING_MESSAGE);
			tracePath = false;
		} catch(NoPathException npex) { 
			JOptionPane.showMessageDialog(mazeApp, "Il n'y a pas de chemin entre les cases Depart et Arrivee", "Erreur lors de la recherche du chemin", JOptionPane.WARNING_MESSAGE);
			tracePath = false;
		}
		return path;	
	}
	/**
	 * Methode qui dessine le labyrinthe
	 * @param graphics Objet qui permet de dessiner les hexagones
	 * @param hexagonSize La taille des hexagones, definie comme dans MazeHexagon
	 */
	public void paintMaze(Graphics graphics, int hexagonSize) { 
		for(int i=0 ; i<hexagonGrid.getLargeur() ; i++)
			for(int j=0 ; j<hexagonGrid.getHauteur() ; j++) {
				MazeHexagon mh = hexagonGrid.getHexagons()[i][j];
				mh.setSize(hexagonSize);
				graphics.setColor(mh.getColor());
				graphics.fillPolygon(mh);
				((Graphics2D)graphics).setStroke(new BasicStroke((float)0.02*hexagonSize)); // taille du contour des hexagones
				graphics.setColor(Color.BLACK); // couleur du contour des hexagones
				graphics.drawPolygon(mh);
				
			}
	}
	/**
	 * Renvoie le labyrinthe actuel
	 */
	public Maze getMaze() {
		return maze;
	}
	/**
	 * Permet de savoir si le bouton pour placer la case Arrivee est enclenche
	 * @return Un booleen : true si et seulement si le bouton est enclenche
	 */
	public boolean getSetArrival() {
		return setArrival;
	}
	/**
	 * Permet de savoir si le bouton pour placer la case Depart est enclenche
	 * @return Un booleen : true si et seulement si le bouton est enclenche
	 */
	public boolean getSetDeparture() {
		return setDeparture;
	}
	/**
	 * Permet de savoir si le bouton pour placer une case vide est enclenche
	 * @return Un booleen : true si et seulement si le bouton est enclenche
	 */
	public boolean getSetEmpty() {
		return setEmpty;
	}
	/**
	 * Permet de savoir si le bouton pour placer un mur est enclenche
	 * @return Un booleen : true si et seulement si le bouton est enclenche
	 */
	public boolean getSetWall() {
		return setWall;
	}
	/**
	 * Permet de savoir si le bouton pour afficher le chemin est enclenche
	 * @return Un booleen : true si et seulement si le bouton est enclenche
	 */
	public boolean getTracePath() {
		return tracePath;
	}
	/**
	 * Enclenche/Desenclenche le bouton pour placer la case Arrivee
	 */
	public void clickArrival() { 
		setArrival = !setArrival;
		setDeparture = false;
		setEmpty = false;
		setWall = false;
		stateChanged();		
	}
	/**
	 * Enclenche/Desenclenche le bouton pour placer la case Arrivee
	 */
	public void clickDeparture() {
		setDeparture = !setDeparture;
		setArrival = false;
		setEmpty = false;
		setWall = false;
		stateChanged();
	}
	/**
	 * Enclenche/Desenclenche le bouton pour placer une case vide
	 */
	public void clickEmpty() { 
		setEmpty = !setEmpty;
		setArrival = false;
		setDeparture = false;
		setWall = false;
		stateChanged();
	}
	/**
	 * Enclenche/Desenclenche le bouton pour placer un mur
	 */
	public void clickWall() { 
		setWall = !setWall;
		setArrival = false;
		setEmpty = false;
		setDeparture = false;
		stateChanged();
	}
	/**
	 * Ajoute une ligne vide au labyrinthe
	 */
	public void clickAddLine() {
		int largeur = maze.getLargeur();
		int hauteur = maze.getHauteur();
		Vertex[][] boxes = maze.getBoxes();
		maze = new Maze(largeur,hauteur+1,new Vertex[largeur][hauteur+1]);
		for(int i=0 ; i<largeur ; i++)
			for(int j=0 ; j<hauteur ; j++)
				maze.getBoxes()[i][j] = boxes[i][j];
		for(int iadd=0 ; iadd<largeur ; iadd++)
			maze.getBoxes()[iadd][hauteur] = new EmptyBox(maze,iadd,hauteur);
		createHexagonGrid();
		stateChanged();
	}
	/**
	 * Ajoute une colonne vide au labyrinthe
	 */
	public void clickAddColumn() {
		int largeur = maze.getLargeur();
		int hauteur = maze.getHauteur();
		Vertex[][] boxes = maze.getBoxes();
		maze = new Maze(largeur+1,hauteur,new Vertex[largeur+1][hauteur]);
		for(int i=0 ; i<largeur ; i++)
			for(int j=0 ; j<hauteur ; j++)
				maze.getBoxes()[i][j] = boxes[i][j];
		for(int jadd=0 ; jadd<hauteur ; jadd++)
			maze.getBoxes()[largeur][jadd] = new EmptyBox(maze,largeur,jadd);
		createHexagonGrid();
		stateChanged();
	}
	/**
	 * Supprime une ligne du labyrinthe
	 */
	public void clickDeleteLine() { 
		int largeur = maze.getLargeur();
		int hauteur = maze.getHauteur();
		Vertex[][] boxes = maze.getBoxes();
		maze = new Maze(largeur,hauteur-1,new Vertex[largeur][hauteur-1]);
		for(int i=0 ; i<largeur ; i++)
			for(int j=0 ; j<hauteur-1 ; j++)
				maze.getBoxes()[i][j] = boxes[i][j];
		createHexagonGrid();
		stateChanged();
	}
	/**
	 * Supprime une colonne du labyrinthe
	 */
	public void clickDeleteColumn() { 
		int largeur = maze.getLargeur();
		int hauteur = maze.getHauteur();
		Vertex[][] boxes = maze.getBoxes();
		maze = new Maze(largeur-1,hauteur,new Vertex[largeur-1][hauteur]);
		for(int i=0 ; i<largeur-1 ; i++)
			for(int j=0 ; j<hauteur ; j++)
				maze.getBoxes()[i][j] = boxes[i][j];
		createHexagonGrid();
		stateChanged();
	}
	/**
	 * Enclenche/Desenclenche le bouton pour afficher le chemin
	 */
	public void clickTracePath() {
		tracePath = !tracePath;
		changeHexagonGrid();
		stateChanged();
	}
	/**
	 * Permet de savoir quel hexagone pointe la souris, si elle en pointe un.
	 * Elle affecte a l'attribut currentPointedHexagon cet hexagone, ou null si la souris ne pointe pas d'hexagone
	 * @param p Point ou se trouve la souris
	 */
	public void selectHexagon(Point p) {
		currentPointedHexagon = null;
		for (int i=0 ; i<hexagonGrid.getLargeur() ; i++) {
			for (int j=0 ; j<hexagonGrid.getHauteur() ; j++) {
				MazeHexagon mh = hexagonGrid.getHexagons()[i][j];
				if(mh.contains(p)) 	{					
					currentPointedHexagon = mh;
				}
			}
		}
		createHexagonGrid();
		stateChanged();
	}
	/**
	 * Permet de modifier la case du labyrinthe pointee par la souris en fonction du choix de l'utilisateur.
	 * Elle est declenchee quand on appuie sur la souris ou que l'on bouge la souris en restant appuye.
	 * @param p Point ou se trouve la souris
	 */
	public void changeHexagon(Point p) {
		if (currentPointedHexagon != null) {
				if(setArrival) {
					int xCurrent = currentPointedHexagon.getMazeBox().getX();
					int yCurrent = currentPointedHexagon.getMazeBox().getY();
					if(currentPointedHexagon.getMazeBox().isEmpty()) { // ON NE PEUT PLACER UNE CASE ARRIVEE QUE SUR UNE CASE VIDE
						try {
							int xArrival = maze.getArrival().getX();
							int yArrival = maze.getArrival().getY();
							maze.getBoxes()[xArrival][yArrival] = new EmptyBox(maze,xArrival,yArrival);
						} catch (BoxException ex) {}
						maze.getBoxes()[xCurrent][yCurrent] = new ArrivalBox(maze,xCurrent,yCurrent);
					}
				}
				else if(setDeparture) {	
					int xCurrent = currentPointedHexagon.getMazeBox().getX();
					int yCurrent = currentPointedHexagon.getMazeBox().getY();
					if(currentPointedHexagon.getMazeBox().isEmpty()) { // ON NE PEUT PLACER UNE CASE DEPART QUE SUR UNE CASE VIDE
						try {
						int xDeparture = maze.getDeparture().getX();
						int yDeparture = maze.getDeparture().getY();
						maze.getBoxes()[xDeparture][yDeparture] = new EmptyBox(maze,xDeparture,yDeparture);
						} catch (BoxException ex) {}
						maze.getBoxes()[xCurrent][yCurrent] = new DepartureBox(maze,xCurrent,yCurrent);
					}
				}
				else if(setEmpty) {
					int xCurrent = currentPointedHexagon.getMazeBox().getX();
					int yCurrent = currentPointedHexagon.getMazeBox().getY();
					maze.getBoxes()[xCurrent][yCurrent] = new EmptyBox(maze,xCurrent,yCurrent);
				}
				else if(setWall) {
					int xCurrent = currentPointedHexagon.getMazeBox().getX();
					int yCurrent = currentPointedHexagon.getMazeBox().getY();
					maze.getBoxes()[xCurrent][yCurrent] = new WallBox(maze,xCurrent,yCurrent);
				}
		changeHexagonGrid();
		stateChanged();
		}
	}
	/**
	 * Change toutes les cases du labyrinthe en cases vides
	 */
	public void resetMaze() {
		for (int i = 0 ; i<maze.getLargeur() ; i++) {
			for(int j = 0 ; j<maze.getHauteur() ; j++) {
				maze.getBoxes()[i][j] = new EmptyBox(maze,i,j);
			}
		}
		changeHexagonGrid();
		stateChanged();
  	}
	/**
	 * Ouvre le labyrinthe decrit par le fichier selectionne
	 * @param selectedFile Le fichier selectionne
	 * @param d La dimension de ce fichier (sert a verifier le format du fichier)
	 * @throws FileNotFoundException Si le fichier est inexistant
	 * @throws IOException En cas de problemes de lecture avec le BufferedReader
	 * @throws MazeReadingException Si le fichier ne respecte pas le format attendu pour un labyrinthe
	 * @throws BoxException Si il y a un nombre incoherent de cases Depart et Arrivee dans le fichier
	 */
	public void openMaze(File selectedFile,Dimension d) throws FileNotFoundException, IOException, MazeReadingException, BoxException {
		int newLarg = (int)d.getWidth();
		int newHaut = (int)d.getHeight();
		maze = new Maze(newLarg,newHaut,new MazeBox[newLarg][newHaut]);
		maze.initFromTextFile(selectedFile);
		createHexagonGrid();
		stateChanged();
	}
	/**
	 * Enregistre le labyrintha actuel dans le fichier selectionne
	 * @param selectedFile Le fichier selectionne
	 * @throws IOException En cas de probleme d'ecriture avec le PrintWriter
	 */
	public void saveMaze(File selectedFile) throws IOException {
		try {
			maze.saveToTextFile(selectedFile);
		} catch(BoxException ex) {
			JOptionPane.showMessageDialog(mazeApp,"Verifiez que vous avez bien une case Depart et une case Arrivee" ,"Erreur lors de la sauvegarde",JOptionPane.WARNING_MESSAGE);
		}
	}
	/**
	 * Remplace le labyrinthe actuel par un nouveau labyrinthe vide, de dimensions specifiees.
	 * @param mazeLarg La largeur du nouveau llabyrinthe
	 * @param mazeHaut La hauteur du nouveau labyrinthe
	 */
	public void createNewEmptyMaze(int mazeLarg, int mazeHaut) {
		maze = new Maze(mazeLarg,mazeHaut,new MazeBox[mazeLarg][mazeHaut]);
		for (int i=0 ; i<mazeLarg ; i++)
			for (int j=0 ; j<mazeHaut ; j++)
				maze.getBoxes()[i][j] = new EmptyBox(maze,i,j);
		createHexagonGrid();
		stateChanged();
	}
	/**
	 * Remplace le labyrinthe actuel par un nouveau labyrinthe, de dimensions specifiees.
	 * Les types de cases sont attribuees aleatoirement, selon une frequence d'apparition de murs choisie par l'utilisateur
	 * @param mazeLarg La largeur du nouveau llabyrinthe
	 * @param mazeHaut La hauteur du nouveau labyrinthe
	 * @param wallFrequency La frequence choisie par l'utilisateur
	 */
	public void createNewRandomMaze(int mazeLarg, int mazeHaut, double wallFrequency) {
		maze = new Maze(mazeLarg,mazeHaut,new MazeBox[mazeLarg][mazeHaut]);
		Random rd = new Random();
		int xD = rd.nextInt(mazeLarg);
		int yD = rd.nextInt(mazeHaut);
		maze.getBoxes()[xD][yD] = new DepartureBox(maze,xD,yD);
		int xA = rd.nextInt(mazeLarg);
		int yA = rd.nextInt(mazeHaut);
		while(xD==xA && yD==yA){
			xA = rd.nextInt(mazeLarg);
			yA = rd.nextInt(mazeHaut);
		}
		maze.getBoxes()[xA][yA] = new ArrivalBox(maze,xA,yA);
		double wall = 0.0;
		for(int i=0 ; i<mazeLarg ; i++)
			for(int j=0 ; j<mazeHaut ; j++)
				if(maze.getBoxes()[i][j] == null) {
					wall = Math.random();
					if (wall<wallFrequency)
						maze.getBoxes()[i][j] = new WallBox(maze,i,j);
					else
						maze.getBoxes()[i][j] = new EmptyBox(maze,i,j);
				}
		createHexagonGrid();
		stateChanged();
	}
	
}
