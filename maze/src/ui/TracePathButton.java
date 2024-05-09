package ui;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;
/**
 * Bouton permettant l'affichage du chemin dans l'application
 * @author enzog
 *
 */
public class TracePathButton extends JButton implements ActionListener {
	
	private final MazeApp mazeApp;
	/**
	 * Cree un bouton dans l'application specifiee
	 * @param mazeApp L'application
	 */
	public TracePathButton(MazeApp mazeApp) {
		super("Afficher/Enlever le chemin");
		this.mazeApp = mazeApp;
		addActionListener(this);
		setBackground(Color.WHITE);
	}
	/**
	 * Quand on clique sur le bouton
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		mazeApp.getMazeAppModel().clickTracePath();	
	}
	/**
	 * Informe le bouton du raffraichissement de l'affichage
	 */
	public void notifyForUpdate() {
		if(mazeApp.getMazeAppModel().getTracePath())
			setBackground(Color.YELLOW);
		else
			setBackground(Color.WHITE);
	}

}
