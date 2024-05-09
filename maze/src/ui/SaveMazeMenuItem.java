package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import exception.MazeReadingException;
import modelmaze.Maze;

import java.io.File;
import java.io.IOException;
/**
 * Item de menu pour sauvegarder le labyrinthe dans un fichier texte
 * @author enzog
 */
public class SaveMazeMenuItem extends JMenuItem implements ActionListener {
	
	private final MazeApp mazeApp;
	/**
	 * Cree un item de menu dans l'application specifiee
	 * @param mazeApp L'application
	 */
	public SaveMazeMenuItem(MazeApp mazeApp) {
		super("Sauvegarder le labyrinthe");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	/**
	 * Quand on appuie sur le bouton, cela ouvre un explorateur de fichier pour choisir dans quel fichier sauvegarder le labyrinthe
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.addChoosableFileFilter(new MazeFileFilter());
		int response = jfc.showSaveDialog(this); 
		switch(response) { 
		case(JFileChooser.APPROVE_OPTION):
			File selectedFile = jfc.getSelectedFile();
			try {
				mazeApp.getMazeAppModel().saveMaze(selectedFile); 
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case(JFileChooser.CANCEL_OPTION):
			return;
		}
	}
	

}
