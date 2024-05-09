package ui;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import exception.BoxException;
import exception.MazeReadingException;
import modelmaze.Maze;

import java.io.File;
import java.io.IOException;
/**
 * Item de menu pour ouvrir un labyrinthe a partir d'un fichier
 * @author enzog
 */
public class OpenMazeMenuItem extends JMenuItem implements ActionListener {
	
	private final MazeApp mazeApp;
	/**
	 * Cree un item de menu dans l'application specifiee
	 * @param mazeApp L'application
	 */
	public OpenMazeMenuItem(MazeApp mazeApp) {
		super("Ouvrir un labyrinthe");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	/**
	 * Quand on clique sur l'item, cela ouvre un explorateur de fichier pour choisir un labyrinthe a ouvrir
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser jfc = new JFileChooser();
		jfc.addChoosableFileFilter(new MazeFileFilter());
		int response = jfc.showOpenDialog(this);
		switch(response) {
		case(JFileChooser.APPROVE_OPTION):
			File selectedFile = jfc.getSelectedFile();
			try {
				mazeApp.getMazeAppModel().openMaze(selectedFile,FileCalculator.getFileDimension(selectedFile));
			} catch (IOException | MazeReadingException | BoxException e1) {
				e1.printStackTrace();
			}
			break;
		case(JFileChooser.CANCEL_OPTION):
			return;
		}
	}

}
