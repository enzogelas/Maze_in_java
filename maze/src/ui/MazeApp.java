package ui;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

import exception.BoxException;
import exception.MazeReadingException;
import modelmaze.*;
/**
 * L'application d'edition et de resolution du labyrinthe
 * @author enzog
 */
public class MazeApp extends JFrame implements ChangeListener{
	
	private final MazeMenuBar mazeMenuBar;
	private final WindowPanel windowPanel ;
	private final MazeAppModel mazeAppModel;
	/**
	 * Cree une application
	 */
	public MazeApp() {
		super("Creation de labyrinthe");		
		setJMenuBar(mazeMenuBar = new MazeMenuBar(this)) ;
		setContentPane(windowPanel = new WindowPanel(this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		this.mazeAppModel = new MazeAppModel(this);	
		mazeAppModel.addObserver(this); // pour que l'app suive le modele
		
		/* Demander a l'utilisateur s'il veut ouvrir un labyrinthe au lancement de l'application, ou en creer un nouveau */
		firstOptions();	
		pack() ;
		setVisible(true);
	}
	/**
	 * Renvoie le modele de l'application
	 */
	public MazeAppModel getMazeAppModel() {
		return mazeAppModel;
	}
	/**
	 * Methode permettant d'afficher les options possibles au lancement de l'application, avant l'ouverture de la fenetre principale
	 */
	private void firstOptions() { 
		String[] options = {"Ouvrir un labyrinthe existant", "Creer un nouveau labyrinthe vide", "Creer un nouveau labyrinthe aleatoire"};
		int response = JOptionPane.showOptionDialog(this, "Que voulez-vous faire ?", "Ouverture de l'application", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);
		switch(response) {
		case(0): // ouvrir un labyrinthe via un fichier
			JFileChooser jfc = new JFileChooser();
			jfc.addChoosableFileFilter(new MazeFileFilter());
			int response1 = jfc.showSaveDialog(this); 
			switch(response1) { 
			case(JFileChooser.APPROVE_OPTION):
				File selectedFile = jfc.getSelectedFile();
				try {
					mazeAppModel.openMaze(selectedFile,FileCalculator.getFileDimension(selectedFile)); 
				} catch (IOException | MazeReadingException | BoxException e1) {
					e1.printStackTrace();
				}
				break;
			case(JFileChooser.CANCEL_OPTION):
				return;
			}
			break;
		case(1): // creer un labyrinthe vide de dimensions choisies
			String sLargeur1 = JOptionPane.showInputDialog(this, "Entrez la largeur du labyrinthe","Choix de la largeur",JOptionPane.QUESTION_MESSAGE);
			int mazeLarg1 = Integer.parseInt(sLargeur1);
			String sHauteur1 = JOptionPane.showInputDialog(this, "Entrez la hauteur du labyrinthe","Choix de la hauteur",JOptionPane.QUESTION_MESSAGE);
			int mazeHaut1 = Integer.parseInt(sHauteur1);
			mazeAppModel.createNewEmptyMaze(mazeLarg1,mazeHaut1);			
			break;
		case(2): // creer un labyrinthe aleatoire, de dimensions et de frequence d'apparition de murs choisies
			String sLargeur2 = JOptionPane.showInputDialog(this, "Entrez la largeur du labyrinthe","Choix de la largeur",JOptionPane.QUESTION_MESSAGE);
			int mazeLarg2 = Integer.parseInt(sLargeur2);
			String sHauteur2 = JOptionPane.showInputDialog(this, "Entrez la hauteur du labyrinthe","Choix de la hauteur",JOptionPane.QUESTION_MESSAGE);
			int mazeHaut2 = Integer.parseInt(sHauteur2);
			String swallFrequency = JOptionPane.showInputDialog(this, "Entrez la frequence d'apparition des murs", "Choix de la frequence d'apparition des murs", JOptionPane.QUESTION_MESSAGE);
			double wallFrequency = Double.parseDouble(swallFrequency);
			mazeAppModel.createNewRandomMaze(mazeLarg2,mazeHaut2,wallFrequency);			
			break;
		}
	}
	/**
	 * Informe la zone principale du raffraichissement de l'affichage
	 */
	public void stateChanged(ChangeEvent evt) {
		windowPanel.notifyForUpdate();		
	}
}
