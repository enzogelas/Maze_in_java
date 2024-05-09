package ui;

import javax.swing.*;

import exception.BoxException;
import exception.NoPathException;
import modelmaze.Maze;

import java.awt.*;
/**
 * La zone ou se dessine le labyrinthe dans la fenetre de l'application
 * @author enzog
 */
public class MazePanel extends JPanel {
	
	private final MazeApp mazeApp ;
	/**
	 * Cree une zone vide ou se dessinera le labyrinthe dans la fenetre de l'application
	 * @param mazeApp L'application
	 */
	public MazePanel(MazeApp mazeApp) {
		super();
		this.mazeApp = mazeApp ;
		
		Mouse mouse = new Mouse(mazeApp);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		setBackground(Color.WHITE) ;
		
		// for pack() instructions
		setPreferredSize(new Dimension(300,300));
	}
	/**
	 * Utilise les dimensions de la zone pour caluler les dimensions ideales des hexagones
	 * @return La taille ideale pour les hexagones
	 */
	private int calculHexagonSize() { // CHANGER EN PRIVATE ???
		Maze maze = mazeApp.getMazeAppModel().getMaze();
		if(maze.getLargeur()==0 || maze.getHauteur()==0)
			return 0;
		int hexagonSize = 0;
		int comp1 = (int)(this.getWidth()/(0.87*maze.getLargeur()+0.5));
		int	comp2 = (int)(this.getHeight()/(0.75*maze.getHauteur()+0.25));		
		if (maze.getHauteur()==1) {
			comp1 = (int)(this.getWidth()/(0.87*maze.getLargeur()));
			comp2 = this.getHeight()/maze.getHauteur();
		}
		if (comp1<comp2)
			hexagonSize = comp1;
		else
			hexagonSize = comp2;
		return hexagonSize;
	}
	/**
	 * Methode qui dessine le labyrinthe 
	 * Elle est appelee a chaque raffraichissement de l'affichage.
	 */
	@Override
	protected void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		mazeApp.getMazeAppModel().paintMaze(graphics,calculHexagonSize());
	}
	/**
	 * Informe la zone du raffraichissement de l'affichage.
	 */
	public void notifyForUpdate() {
		repaint();
	}
}
