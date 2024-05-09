package ui;

import java.awt.event.*;
/**
 * Classe qui sert a ecouter la souris 
 * @author enzog
 */
public class Mouse extends MouseAdapter{
	
	private MazeApp mazeApp;
	/**
	 * Cree un mouseAdapter pour l'application specifiee
	 * @param mazeApp L'application
	 */
	public Mouse(MazeApp mazeApp) {
		super();
		this.mazeApp = mazeApp;
	}
	/**
	 * Quand la souris bouge
	 * @param e Pour reperer la position de la souris
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getPoint() != null)
			mazeApp.getMazeAppModel().selectHexagon(e.getPoint());
	}
	/**
	 * Quand la souris est enfoncee
	 * @param e Pour reperer la position de la souris
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getPoint() != null)
			mazeApp.getMazeAppModel().changeHexagon(e.getPoint());
	}
	/**
	 * Quand la souris bouge avec le click enfonce
	 * @param e Pour reperer la position de la souris
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if(e.getPoint() != null) {
			mazeApp.getMazeAppModel().selectHexagon(e.getPoint());
			mazeApp.getMazeAppModel().changeHexagon(e.getPoint());
		}
	}
}
