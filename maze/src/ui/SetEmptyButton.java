package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * Bouton qui permet de placer une case vide dans le labyrinthe
 * @author enzog
 */
public class SetEmptyButton extends JButton implements ActionListener {
	
	private final MazeApp mazeApp ;
	/**
	 * Cree un bouton dans l'application specifiee
	 * @param mazeApp L'application
	 */
	public SetEmptyButton(MazeApp mazeApp) {
		super("Placer une case vide");
		this.mazeApp = mazeApp;
		addActionListener(this);
		setBackground(Color.WHITE);
	}
	/**
	 * Quand on clique sur le bouton
	 */
	public void actionPerformed(ActionEvent e) {
		mazeApp.getMazeAppModel().clickEmpty();
	}
	/**
	 * Informe le bouton du raffraichissement de l'affichage
	 */
	public void notifyForUpdate() {
		if(mazeApp.getMazeAppModel().getSetEmpty())
			setBackground(Color.LIGHT_GRAY);
		else
			setBackground(Color.WHITE);
	}

}
