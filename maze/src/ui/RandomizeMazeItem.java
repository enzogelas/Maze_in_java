package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 * Bouton permettant d'attribuer aleatoirement les cases du labyrinthe actuel
 * @author enzog
 */
public class RandomizeMazeItem extends JMenuItem implements ActionListener {
	
	private final MazeApp mazeApp;
	/**
	 * Cree un bouton dans l'application specifiee
	 * @param mazeApp L'application
	 */
	public RandomizeMazeItem(MazeApp mazeApp) {
		super("Attribuer les cases aleatoirement");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	/**
	 * Quand on clique sur le bouton, un page s'ouvre pour qu'on puisse choisir la frequence d'apparition des murs de notre nouveau labyrinthe
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String swallFrequency = JOptionPane.showInputDialog(mazeApp, "Entrez la frequence d'apparition des murs", "Choix de la frequence d'apparition des murs", JOptionPane.QUESTION_MESSAGE);
		double wallFrequency = Double.parseDouble(swallFrequency);
		mazeApp.getMazeAppModel().createNewRandomMaze(mazeApp.getMazeAppModel().getMaze().getLargeur(), mazeApp.getMazeAppModel().getMaze().getHauteur(), wallFrequency);
	}
	
}
