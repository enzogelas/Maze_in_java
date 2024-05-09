package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * Bouton permettant de supprimer un ligne du labyrinthe
 * @author enzog
 */
public class DeleteLineButton extends JButton implements ActionListener{
	
	private final MazeApp mazeApp;
	/**
	 * Cree un bouton dans l'application specifiee.
	 * @param mazeApp L'application
	 */
	public DeleteLineButton(MazeApp mazeApp) {
		super("Supprimer une ligne");		
		this.mazeApp = mazeApp;	
		addActionListener(this);
		setBackground(Color.WHITE);
	}
	/**
	 * Quand on clique sur le bouton
	 */
	public void actionPerformed(ActionEvent e) {
		mazeApp.getMazeAppModel().clickDeleteLine();
		
	}
	/**
	 * Informe le bouton du raffraichissement de la fenêtre
	 */
	public void notifyForUpdate() {
	}

}
