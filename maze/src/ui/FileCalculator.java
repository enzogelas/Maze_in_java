package ui;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * Une classe permettant des operation intermediaires sur des fichiers
 *  * @author enzog
 */
public class FileCalculator { 
	/**
	 * Renvoie les dimensions du fichier file
	 * @param file Le fichier a analyser
	 * @return Un objet Dimension ayant pour attributs le nombre de lignes du fichier, et la longueur commune a ces lignes. 
	 * Le cas ou les lignes n'auraient pas toutes la meme longueur est traite dans d'autres methodes.
	 */
	public static Dimension getFileDimension(File file) { 
		BufferedReader br = null;
		int i=0;
		int j=0;
		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);  
			String line;
			i = 0;
			j = 0;
			while ((line = br.readLine()) != null) {
				i = line.length();
				j++;
			}			
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier n'existe pas");
		} catch (IOException e) {
			System.err.println("Impossible de lire le fichier" + file.getName().toString() );
		}
		try {
			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return new Dimension(i,j);
	}

}
