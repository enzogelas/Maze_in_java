package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
/**
 * Item de menu pour fermer l'application
 * @author enzog
 */
public class QuitMenuItem extends JMenuItem implements ActionListener {
	private final MazeApp mazeApp ;
	/**
	 * Cree un item de menu dans l'application specifiee
	 * @param mazeApp L'application
	 */
	public QuitMenuItem(MazeApp mazeApp) {
		super("Quitter l'application"); // nom de l'item du menu	
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	/**
	 * Quand on clique sur l'item, cela ouvre une fenetre de confirmation et demande si on veut sauvegarder le labyrinthe
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();
		
		int response = JOptionPane.showInternalOptionDialog(this,"Voulez-vous sauvegarder votre labyrinthe ?","Quitter l'application ?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		switch(response) {
		case JOptionPane.CANCEL_OPTION:
			return ;
		case JOptionPane.OK_OPTION:
			JFileChooser jfc = new JFileChooser();
			jfc.addChoosableFileFilter(new MazeFileFilter());
			int response1 = jfc.showSaveDialog(this); 
			switch(response1) {
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
			break;
		case JOptionPane.NO_OPTION:
			break;
		}
		System.exit(0);
	}
	
}
