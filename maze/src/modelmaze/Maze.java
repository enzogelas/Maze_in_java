package modelmaze;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import exception.BoxException;
import exception.MazeReadingException;
import graphmaze.Distance;
import graphmaze.Graph;
import graphmaze.Vertex;
import ui.FileCalculator;
/** 
 * Represente un labyrinthe
 * @author enzog
 */
public class Maze implements Graph,Distance{
	private final int largeur ; // largeur du labyrinthe
	private final int hauteur ; // hauteur du labyrinthe
	private final Vertex[][] boxes ;
	
	/**
	 * Creer un labyrinthe avec une largeur, une hauteur et un tableau de cases specifies.
	 * @param largeur La largeur du labyrinthe
	 * @param hauteur La hauteur du labyrinthe
	 * @param boxes Les cases du labyrinthe
	 */
	public Maze(int largeur, int hauteur, Vertex[][] boxes) {
		this.largeur = largeur ;
		this.hauteur = hauteur ;
		this.boxes = boxes ;
	}
	/**
	 * Renvoie les cases du labyrinthe.
	 * @return Un tableau de cases representant le labyrinthe
	 */
	public Vertex[][] getBoxes(){
		return boxes;
	}
	/**
	 * Renvoie la largeur du labyrinthe.
	 * @return Un entier : la largeur du labyrinthe
	 */
	public int getLargeur() {
		return largeur;
	} 
	/**
	 * Renvoie la hauteur du labyrinthe.
	 * @return Un entier : la hauteur du labyrinthe
	 */
	public int getHauteur() {
		return hauteur;
	}
	/**
	 * Renvoie le nombre de cases du labyrinthe.
	 * @return Un entier : le nombre de cases du labyrinthe
	 */
	public int getSize() {
		return largeur*hauteur;
	}
	/**
	 * Renvoie les cases du labyrinthe sur lesquelles on peut se deplacer : Depart, Arrivee et cases vides.
	 * @return Une liste contenant toutes les cases du labyrinthe
	 */
	public List<Vertex> getAllVertexes(){  
		List<Vertex> allvertexes = new ArrayList<Vertex>() ;
		for (int i=0 ; i<largeur ; i++)
			for (int j=0 ; j<hauteur ; j++)
				if (!boxes[i][j].isWall()) 
					allvertexes.add(boxes[i][j]);
		return allvertexes ;
	}
	/**
	 * Renvoie les voisins d'une case dans le labyrinthe.
	 * @param v La case dont on veut avoir les voisins
	 * @return Une liste qui contient les voisins de la case v
	 */
	public List<Vertex> getSuccessors(Vertex v){ // on cherche les voisins du sommet v
		int x = v.getX();
		int y = v.getY();
		List<Vertex> successors = new ArrayList<Vertex>();
		/*on s'occupe d'abord des cases a gauche et a droite*/
		if (x>0)
			if (!boxes[x-1][y].isWall())
				successors.add(boxes[x-1][y]);
		if (x < largeur-1)
			if (!boxes[x+1][y].isWall())
				successors.add(boxes[x+1][y]);
		/* on s'occupe des cases au-dessus et en dessous, et la numerotation va etre differente en fonction de la parite de la ligne*/
		if (y%2 ==0) { // cas ligne paire
			/*cases au-dessus*/
			if (y>0) {
				if (!boxes[x][y-1].isWall())
					successors.add(boxes[x][y-1]);
				if (x>0)
					if (!boxes[x-1][y-1].isWall())
						successors.add(boxes[x-1][y-1]);
			}
			/*cases en dessous*/
			if (y<hauteur-1) {
				if (!boxes[x][y+1].isWall())
					successors.add(boxes[x][y+1]);
				if (x>0)
					if (!boxes[x-1][y+1].isWall())
						successors.add(boxes[x-1][y+1]);			
			}
		} 
		if (y%2 != 0) { // cas ligne impaire
			/* cases au-dessus */
			if (y>0) { // ce if est toujours realise, mais il permet d'avoir un code plus clair
				if (x< largeur-1)
					if (!boxes[x+1][y-1].isWall())
						successors.add(boxes[x+1][y-1]);
				if (!boxes[x][y-1].isWall())
					successors.add(boxes[x][y-1]);
			}
			/* cases en dessous */
			if (y<hauteur-1) {
				if (x< largeur-1)
					if (!boxes[x+1][y+1].isWall())
						successors.add(boxes[x+1][y+1]);
				if (!boxes[x][y+1].isWall())
					successors.add(boxes[x][y+1]);			
			}
		}
		return successors;
	}
	/**
	 * Renvoie la distance entre deux cases du labyrinthe : ici deux cases voisines sont a distance 1.
	 * @param src Une case du labyrinthe
	 * @param dst Une autre case du labyrinthe
	 * @return Un entier : la distance entre les cases src et dst		
	 */
	public int getDistance(Vertex src, Vertex dst) { // si deux sommets sont voisins, la distance entre les deux est 1, sinon infinie (représentée par 1000000)
		if (getSuccessors(src).contains(dst))
			return 1;
		else
			return 1000000;
	}
	/**
	 * Renvoie la case Depart du labyrinthe.
	 * @return La case Depart du labyrinthe
	 * @throws BoxException Une exception se declenche s'il n'y a pas une et une seule case Depart.
	 */
	public Vertex getDeparture() throws BoxException {
		Vertex departure = null;
		int comptD = 0;
		for(int i = 0 ; i<largeur ; i++) {
			for(int j = 0 ; j<hauteur ; j++) {
				if (boxes[i][j].isDeparture()) {
					comptD++;
					departure = boxes[i][j];
				}
			}
		}
		if(comptD == 1)
			return departure;	
		throw new BoxException("Pas le bon nombre de cases Depart");
	}
	/**
	 * Renvoie la case Arrivee du labyrinthe.
	 * @return La case Arrivee du labyrinthe
	 * @throws BoxException Une exception se declenche s'il n'y a pas une et une seule case Arrivee.
	 */
	public Vertex getArrival() throws BoxException {
		Vertex arrival = null;
		int comptA = 0;
		for(int i = 0 ; i<largeur ; i++) {
			for(int j = 0 ; j<hauteur ; j++) {
				if (boxes[i][j].isArrival()) {
					comptA++;
					arrival =  boxes[i][j];
				}
			}
		}
		if (comptA == 1)
			return arrival;
		throw new BoxException("Pas le bon nombre de cases Arrivee");
	}
	/**
	 * Initialise un labyrinthe a partir d'un fichier texte le representant.
	 * 'D' = case Depart; 'A' = case Arrivee ; 'E' = case vide ; 'W' = mur
	 * @param file Le Fichier pour initier la labyrinthe
	 * @throws FileNotFoundException Si le fichier est introuvable
	 * @throws IOException En cas de probleme de lecture avec le BufferedReader
	 * @throws MazeReadingException Si le fichier ne represente pas un format correct de labyrinthe (voir MazeReadingException)
	 * @throws BoxException Si le fichier ne contient pas exactement une case Depart et une case Arrivee
	 */
	public final void initFromTextFile(File file) throws FileNotFoundException, IOException, MazeReadingException, BoxException {
		BufferedReader br = null;	
		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);  // on le  déclare ici pour pouvoir le close() plus tard
			String line;
			int comptA = 0; //entier pour compter le nombre de cases de départ
			int comptD = 0; //entier pour compter le nombre de cases d'arrivée
			Dimension d = FileCalculator.getFileDimension(file);
			int newLarg = (int)d.getWidth();
			int newHaut = (int)d.getHeight();
			int j = 0;
			while ((line = br.readLine()) != null) {
				if(line.length() != newLarg)
					throw new MazeReadingException("Le fichier " + file.getName() + " n'a pas le bon format");
				for (int i =0; i<line.length(); i++) {
					char c = line.charAt(i);
					if(c == 'D') {
						comptD ++;
						boxes[i][j] = new DepartureBox(this,i,j);
					}
					else if (c == 'A') {
						comptA ++;
						boxes[i][j] = new ArrivalBox(this,i,j);
					}
					else if (c == 'E')						
						boxes[i][j] = new EmptyBox(this,i,j);
					else if (c == 'W')				
						boxes[i][j] = new WallBox(this,i,j);					
				}
				j++;
			}
			if(j != newHaut)
				throw new MazeReadingException("Le fichier " + file.getName() + " n'a pas le bon format");
			if(comptD != 1)
				throw new BoxException("Nombre incoherent de cases Depart dans " + file.getName());
			if(comptA != 1)
				throw new BoxException("Nombre incoherent de cases Arrivee dans " + file.getName());
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier n'existe pas");
		} catch (IOException e) {
			System.err.println("Impossible de lire le fichier" + file.getName() );
		}
		try {
			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Sauvegarde le labyrinthe dans un fichier texte.
	 * 'D' = case Depart; 'A' = case Arrivee ; 'E' = case vide ; 'W' = mur
	 * @param file Le fichier ou sauvegarder le labyrinthe
	 * @throws BoxException Si le labyrinthe ne contient pas une et une seule cases Depart et Arrivee
	 */
	public final void saveToTextFile(File file) throws BoxException { 
		getDeparture();
		getArrival();
		PrintWriter pw = null;
		try {
		pw = new PrintWriter(file);
		for (int j = 0 ; j<hauteur ; j++) {
			String line = new String();
			for (int i = 0 ; i<largeur ; i++) { 
				if (boxes[i][j].isDeparture())				
					line += "D";
				else if (boxes[i][j].isArrival()) 
					line += "A";
				else if (boxes[i][j].isEmpty())
					line += "E";
				else if (boxes[i][j].isWall())
					line += "W";
				}				
			pw.println(line);
		}
		}catch (IOException ex) {
			System.err.println("Erreur de PrintWriter");
		}finally {
			pw.close();
		}
	}	
}
