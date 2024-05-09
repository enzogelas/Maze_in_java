package ui;

import java.awt.*;

import modelmaze.*;
/**
 * Represente un hexagone
 * @author enzog
 */
public class MazeHexagon extends Polygon{
	
	static final double r3 = Math.sqrt(3.0);
	private Color color; 
	private MazeBox mazeBox;
	/**
	 * Cree un hexagone se referant a la case mazeBox du labyrinthe, et avec la couleur specifiee.
	 * Les dimensions de l'hexagone ne sont pas encore initialisee. Elle le seront dans la methode setSize
	 * @param mazeBox La case du labyrinthe que represente l'hexagone
	 * @param color La couleur de l'hexagone voulue
	 */
	public MazeHexagon(MazeBox mazeBox, Color color) {
		super(new int[6], new int[6], 6);
		this.mazeBox = mazeBox;
		this.color = color;
	}
	/**
	 * Dimensionne l'hexagone a la taille voulue.
	 * @param size La taille voulue (size represente le diametre d'un cercle dans lequel on aurait fait l'hexagone)
	 */
	public void setSize(int size) { 
		xpoints[0] = 0 ;
		xpoints[1] = -(int)(r3*size/4);
		xpoints[2] = -(int)(r3*size/4);
		xpoints[3] = 0 ;
		xpoints[4] = (int)(r3*size/4);
		xpoints[5] = (int)(r3*size/4);
		ypoints[0] = - size/2;
		ypoints[1] = - size/4;
		ypoints[2] = size/4;
		ypoints[3] = size/2 ;
		ypoints[4] = size/4;
		ypoints[5] = - size/4;
		this.translate((int)(r3*size/2)*mazeBox.getX()+(int)(r3*size/4)*(mazeBox.getY()%2) + size/2,(3*size/4)*mazeBox.getY() + size/2); // on positionne l'hexagone par rapport a ses coordonnees
	}
	/**
	 * Renvoie la couleur de l'hexagone
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Modifie la couleur d'un hexagone
	 * @param color La nouvelle couleur
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * Renvoie la case du labyrinthe associee a l'hexagone
	 */
	public MazeBox getMazeBox() {
		return mazeBox;
	}
	/**
	 * Attribue a l'hexagone la case mazeBox
	 * @param mazeBox La case a attribuer
	 */
	public void setMazeBox(MazeBox mazeBox) {
		this.mazeBox = mazeBox;
	}
}
