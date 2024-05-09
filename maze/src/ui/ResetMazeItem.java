package ui;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 * Bouton qui efface tous les murs, case Depart et case Arrive du labyrinthe actuel
 * @author enzog
 */
public class ResetMazeItem extends JMenuItem implements ActionListener{
	
	private final MazeApp mazeApp;
	/**
	 * Cree un bouton dans l'application specifiee
	 * @param mazeApp L'application
	 */
	public ResetMazeItem(MazeApp mazeApp) {
		super ("Reinitialiser le labyrinthe");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	/**
	 * Quand on clique sur le bouton, une page s'ouvre pour demander un confirmation
	 */
	public void actionPerformed(ActionEvent e) {
		int response = JOptionPane.showConfirmDialog(mazeApp,"Voulez-vous vraiment reinitialiser votre labyrinthe ?","Reinitialiser le labyrinthe", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
		switch(response) {
		case(JOptionPane.YES_OPTION):
			mazeApp.getMazeAppModel().resetMaze();
		case(JOptionPane.NO_OPTION):
			return;
		}
		
	}
}
