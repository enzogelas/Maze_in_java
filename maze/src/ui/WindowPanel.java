package ui;

import javax.swing.*;
import java.awt.*;
/**
 * La zone principale de l'application
 * @author enzog
 */
public class WindowPanel extends JPanel {
	
	private final MazePanel mazePanel ;
	private final ButtonsPanel buttonsPanel ;
	/**
	 * Cree la zone principale de l'application, et ajoute des emplacements pour le labyrinthe et les boutons
	 * @param mazeApp
	 */
	public WindowPanel(MazeApp mazeApp) {
		setLayout(new BorderLayout());
		
		add(mazePanel = new MazePanel(mazeApp), BorderLayout.CENTER);
		add(buttonsPanel = new ButtonsPanel(mazeApp), BorderLayout.SOUTH);		
	}
	/**
	 * Informe la zone principale du raffrachissement de la fenetre
	 */
	public void notifyForUpdate() {
		mazePanel.notifyForUpdate();
		buttonsPanel.notifyForUpdate();
	}
}
