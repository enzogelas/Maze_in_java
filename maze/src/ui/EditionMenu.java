package ui;

import javax.swing.*;
/**
 * Le menu de l'application pour modifier le labyrinthe
 * @author enzog
 */
public class EditionMenu extends JMenu{
	private final NewEmptyMazeItem newEmptyMazeItem ;
	private final RandomizeMazeItem randomizeMazeItem ;
	private final ResetMazeItem resetMazeItem ; 
	/**
	 * Cree un menu dans la barre de menu, avec les items voulus.
	 * @param mazeApp L'application
	 */
	public EditionMenu(MazeApp mazeApp) {
		super("Edition");
		add(newEmptyMazeItem = new NewEmptyMazeItem(mazeApp));
		add(randomizeMazeItem = new RandomizeMazeItem(mazeApp));
		add(resetMazeItem = new ResetMazeItem(mazeApp)) ;  
	}
}