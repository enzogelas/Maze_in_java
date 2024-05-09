package ui;

import javax.swing.*;
import java.awt.*;
/**
 * Boutons pour positionner les cases du labyrinthe et afficher le chemin
 * @author enzog
 */
public class ColorButtonsPanel extends JPanel {
	
	private final MazeApp mazeApp;	
	private final SetDepartureButton setDepartureButton;
	private final SetArrivalButton setArrivalButton;
	private final SetEmptyButton setEmptyButton;
	private final SetWallButton setWallButton;
	private final TracePathButton tracePathButton;
	
	/**
	 * Cree une zone dans l'application specifiee et y ajoute les boutons voulus.
	 * @param mazeApp L'application
	 */
	public ColorButtonsPanel(MazeApp mazeApp) {
		this.mazeApp = mazeApp; 
		setLayout(new GridLayout(1,5)); 
		setPreferredSize(new Dimension(300,50)); 
		
		add(setDepartureButton = new SetDepartureButton(mazeApp)) ;
		add(setArrivalButton = new SetArrivalButton(mazeApp)) ;
		add(setEmptyButton = new SetEmptyButton(mazeApp)) ;
		add(setWallButton = new SetWallButton(mazeApp)) ;
		add(tracePathButton = new TracePathButton(mazeApp));
		
	}
	/**
	 * Informe les boutons du raffraichissement de l'affichage.
	 */
	public void notifyForUpdate() {
		setArrivalButton.notifyForUpdate();
		setDepartureButton.notifyForUpdate();
		setEmptyButton.notifyForUpdate();
		setWallButton.notifyForUpdate();
		tracePathButton.notifyForUpdate();
	}

}

