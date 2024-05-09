package ui;

import javax.swing.*;
import java.awt.*;
/**
 * La zone ou seront affiches les boutons dans l'application
 * @author enzog
 */
public class SizeButtonsPanel extends JPanel {
	
	private final MazeApp mazeApp;	
	private final AddLineButton addLineButton;
	private final AddColumnButton addColumnButton;
	private final DeleteLineButton deleteLineButton;
	private final DeleteColumnButton deleteColumnButton;

	/**
	 * Boutons pour ajouter/enlever des lignes/colonnes 
	 * @param mazeApp L'application
	 */
	public SizeButtonsPanel(MazeApp mazeApp) {
		this.mazeApp = mazeApp; 
		setLayout(new GridLayout(1,4)); 
		setPreferredSize(new Dimension(300,50)); 
		
		add(addLineButton = new AddLineButton(mazeApp));
		add(deleteLineButton = new DeleteLineButton(mazeApp));
		add(addColumnButton = new AddColumnButton(mazeApp));
		add(deleteColumnButton = new DeleteColumnButton(mazeApp));
		
	}
	/**
	 * Informe les boutons du raffraichissement de l'affichage.
	 */
	public void notifyForUpdate() {
		addLineButton.notifyForUpdate();
		addColumnButton.notifyForUpdate();
		deleteLineButton.notifyForUpdate();
		deleteColumnButton.notifyForUpdate();
		
	}

}
