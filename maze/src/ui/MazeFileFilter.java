package ui;

import java.io.File;

import javax.swing.filechooser.FileFilter;
/**
 * Un filtre permettant d'afficher uniquement les fichiers texte de type labyrinthe
 * @author enzog
 */
public class MazeFileFilter extends FileFilter{
	/**
	 * Permet d'afficher uniquement les fichiers terminant en .maze
	 */
	@Override
	public boolean accept(File f) {
		return f.isDirectory()||f.getName().endsWith(".maze");
	}

	@Override
	public String getDescription() {
		return "Fichiers labyrinthe";
	}
	

}
