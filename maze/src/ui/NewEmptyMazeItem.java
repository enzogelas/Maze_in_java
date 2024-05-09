package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 * Bouton pour creer un nouveau labyrinthe vide de dimensions quelqonques
 * @author enzog
 */
public class NewEmptyMazeItem extends JMenuItem implements ActionListener{
	
	private final MazeApp mazeApp;
	/**
	 * Cree un bouton dans l'application specifiee
	 * @param mazeApp L'application
	 */
	public NewEmptyMazeItem(MazeApp mazeApp) {
		super("Creer un nouveau labyrinthe vide");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	/**
	 * Quand on clique sur le bouton, une page s'ouvre pour choisir les dimensions du nouveau labyrinthe
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String sLargeur1 = JOptionPane.showInputDialog(mazeApp, "Entrez la largeur du labyrinthe","Choix de la largeur",JOptionPane.QUESTION_MESSAGE);
		int mazeLarg1 = Integer.parseInt(sLargeur1);
		String sHauteur1 = JOptionPane.showInputDialog(mazeApp, "Entrez la hauteur du labyrinthe","Choix de la hauteur",JOptionPane.QUESTION_MESSAGE);
		int mazeHaut1 = Integer.parseInt(sHauteur1);
		mazeApp.getMazeAppModel().createNewEmptyMaze(mazeLarg1,mazeHaut1);		
	}

}
