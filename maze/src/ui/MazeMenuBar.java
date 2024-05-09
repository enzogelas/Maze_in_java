package ui;

import javax.swing.*;
/**
 * La barre de menu de l'application
 * @author enzog
 */
public class MazeMenuBar extends JMenuBar {
	private final FileMenu fileMenu ;
	private final EditionMenu editionMenu ;
	/**
	 * Cree une barre de menu dans l'application, avec les menus voulus
	 * @param mazeApp L'application
	 */
	public MazeMenuBar(MazeApp mazeApp) {
	super() ;
	
	add(fileMenu = new FileMenu(mazeApp));
	add(editionMenu = new EditionMenu(mazeApp));
	}
}
