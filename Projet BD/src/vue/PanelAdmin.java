package vue;
import java.awt.*;

import java.io.File;

import javax.swing.*;

import controleur.Controleur;
import modele.HintPasswordField;
import modele.HintTextField;
/**
 * Cette classe s'affiche lorsque l'utilisateur connécté est du personnel de la bibliothèque et permet à ces utilisateurs de rechercher un livre, gérer des comptes étudiants, ajouter des livres et afficher les derniers livres empruntés
 * @author superpaupaul
 *
 */
public class PanelAdmin extends JPanel{

	String[] intitulesMenuCentre = new String[] {"Rechercher Livre","Gestion Comptes Etudiant","Gestion Livres","Afficher les livres en retard"};
	JButton[] boutonsMenuCentre = new JButton[intitulesMenuCentre.length];
	/**
	 * Constructeur du PanelAdmin, utilise un BorderLayout qui place au centre les actions possibles.
	 * Pour l'instant qu'une chose au centre mais possibilité d'ajouter autre chose comme l'heure au nord..
	 */
	public PanelAdmin() {
		setLayout(new BorderLayout());
		
		// Centre
		JPanel panelCentre = new JPanel();
		panelCentre.setLayout(new GridLayout(intitulesMenuCentre.length,1));
		for(int i = 0; i < intitulesMenuCentre.length; i++) { // Création des boutons
			boutonsMenuCentre[i] = new JButton(intitulesMenuCentre[i]);
			panelCentre.add(boutonsMenuCentre[i]);
		}
		add(panelCentre,BorderLayout.CENTER);
		
		
	}
	
	/**
	 * Permet de se mettre à l'écoute des boutons du menu directement dans le controleur
	 * @param ctrl
	 */
	public void enregistreEcouteur(Controleur ctrl) {
		for(JButton btn : boutonsMenuCentre) {
			btn.addActionListener(ctrl);
		}
	}


	public String[] getIntitulesMenuCentre() {
		return intitulesMenuCentre;
	}


	public JButton[] getBoutonsMenuCentre() {
		return boutonsMenuCentre;
	}
}
