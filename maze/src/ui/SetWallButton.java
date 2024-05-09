package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * Bouton qui permet de placer un mur dans le labyrinthe
 * @author enzog
 */
public class SetWallButton extends JButton implements ActionListener{
	
	private final MazeApp mazeApp;
	/**
	 * Cree un bouton dans l'application specifiee
	 * @param mazeApp L'application
	 */
	public SetWallButton(MazeApp mazeApp) {
		super("Placer un mur");
		this.mazeApp = mazeApp;
		addActionListener(this);
		setBackground(Color.WHITE);
	}
	/**
	 * Quand on clique sur le bouton
	 */
	public void actionPerformed(ActionEvent e) {
		mazeApp.getMazeAppModel().clickWall();
	}
	/**
	 * Informe le bouton du raffraichissement de l'affichage
	 */
	public void notifyForUpdate() {
		if(mazeApp.getMazeAppModel().getSetWall())
			setBackground(Color.BLACK);
		else
			setBackground(Color.WHITE);
	}
}
