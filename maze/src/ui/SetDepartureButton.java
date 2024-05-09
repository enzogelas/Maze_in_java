package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * Bouton qui permet de placer ou replacer la case Depart dans le labyrinthe
 * @author enzog
 */
public class SetDepartureButton extends JButton implements ActionListener{
	
	private final MazeApp mazeApp;
	/**
	 * Cree un bouton dans l'application specifiee
	 * @param mazeApp L'application
	 */
	public SetDepartureButton(MazeApp mazeApp) {
		super("Placer la case Depart");
		this.mazeApp = mazeApp;
		addActionListener(this);
		setBackground(Color.WHITE);
	}
	/**
	 * Quand on clique sur le bouton
	 */
	public void actionPerformed(ActionEvent e) {
		mazeApp.getMazeAppModel().clickDeparture();
	}
	/**
	 * Informe le bouton du raffraichissement de l'affichage
	 */
	public void notifyForUpdate() {
		if(mazeApp.getMazeAppModel().getSetDeparture())
			setBackground(new Color(0,200,0));
		else
			setBackground(Color.WHITE);
	}
}
