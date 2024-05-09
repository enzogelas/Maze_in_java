package ui;

import java.awt.BorderLayout;

import javax.swing.*;
/**
 * Zone pour les boutons d'édition du labyrinthe
 * @author enzog
 */
public class ButtonsPanel extends JPanel {
	
	private final ColorButtonsPanel colorButtonsPanel ;
	private final SizeButtonsPanel sizeButtonsPanel ;
	/**
	 * Cree une zone dans l'application specifiee et y ajoute les zones des boutons voulus.
	 * @param mazeApp L'application
	 */
	public ButtonsPanel(MazeApp mazeApp) {
		setLayout(new BorderLayout());
		
		add(colorButtonsPanel = new ColorButtonsPanel(mazeApp), BorderLayout.NORTH);
		add(sizeButtonsPanel = new SizeButtonsPanel(mazeApp), BorderLayout.SOUTH);		
	}

	public void notifyForUpdate() {
		colorButtonsPanel.notifyForUpdate();
		sizeButtonsPanel.notifyForUpdate();
	}

}
