package ui;

import javax.swing.*;
/**
 * Le menu de l'application (dans la barre de menu)
 * @author enzog
 */
public class FileMenu extends JMenu{
	private final QuitMenuItem quitMenuItem ;
	private final OpenMazeMenuItem openMazeMenuItem;
	private final SaveMazeMenuItem saveMazeMenuItem;
	/**
	 * Cree un menu dans la barre de menu, avec les items voulus.
	 * @param mazeApp L'application
	 */
	public FileMenu(MazeApp mazeApp) {
		super("Menu");
		add(openMazeMenuItem = new OpenMazeMenuItem(mazeApp));
		add(saveMazeMenuItem = new SaveMazeMenuItem(mazeApp));
		add(quitMenuItem = new QuitMenuItem(mazeApp)) ;
	}
}
