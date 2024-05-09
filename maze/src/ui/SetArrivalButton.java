package ui;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;
/**
 * Bouton qui permet de placer ou replacer la case Arrivee dans le labyrinthe
 * @author enzog
 */
public class SetArrivalButton extends JButton implements ActionListener{
	
	private final MazeApp mazeApp ;
	/**
	 * Cree un bouton dans l'application specifiee
	 * @param mazeApp L'application
	 */
	public SetArrivalButton(MazeApp mazeApp) {
		super("Placer la case Arrivee");
		this.mazeApp = mazeApp;
		addActionListener(this);
		setBackground(Color.WHITE);
	}
	/**
	 * Quand on clique sur le bouton
	 */
	public void actionPerformed(ActionEvent e) { 
		mazeApp.getMazeAppModel().clickArrival();
	}
	/**
	 * Informe le bouton du raffraichissement de l'affichage
	 */
	public void notifyForUpdate() {
		if(mazeApp.getMazeAppModel().getSetArrival())
			setBackground(new Color(240,0,0));
		else
			setBackground(Color.WHITE);
	}

}
