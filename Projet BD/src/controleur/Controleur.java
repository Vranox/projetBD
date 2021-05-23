package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.*;

import modele.Utilisateur;
import vue.*;

public class Controleur implements ActionListener {
	FenetreMere fenetremere;
	FenetreMere fenetremereD;
	PanelConnexion panelConnexion;
	PanelAdmin panelAdmin;
	JButton boutonLogin;
	String adminId = "a";
	String adminPwd = "a";
	Utilisateur user;
	public Controleur(FenetreMere parFenetremere) {
		fenetremere = parFenetremere;
		panelConnexion = fenetremere.getPanelConnexion();
		panelConnexion.enregistreEcouteur(this);
	}
	// Se mettre a l'ecoute du bouton "connexion"

	@Override
	public void actionPerformed(ActionEvent parEvt) {
		boutonLogin = panelConnexion.getLoginBtn();
		if(parEvt.getSource()==boutonLogin) {
			System.out.println("click");
			System.out.println("email: "+panelConnexion.getEmailTxt().getText()+" pwd: "+new String(panelConnexion.getPwdTxt().getPassword()));
			if(panelConnexion.getEmailTxt().getText().equals(adminId) && new String(panelConnexion.getPwdTxt().getPassword()).equals(adminPwd)) {
				user = new Utilisateur(adminId,adminPwd);
				System.out.println("success");
				fenetremere.dispose();
				fenetremereD= new FenetreMere("Session Admin");
				panelAdmin = fenetremereD.getPanelAdmin();
				fenetremereD.getPanelAdmin().enregistreEcouteur(this);
			}
			
			else {
				System.out.println("denied");
			}
		}
		else if(Arrays.asList(panelAdmin.getBoutonsMenuCentre()).contains(parEvt.getSource())) // bouton du panelAdmin (ex: rechercher livre, ajouter livre.. ) cliqué 
		{
			JButton btnClicked = (JButton) parEvt.getSource();
			if(btnClicked.getText().equals(panelAdmin.getIntitulesMenuCentre()[0])) { // Rechercher Livre
				// TODO
			}
			if(btnClicked.getText().equals(panelAdmin.getIntitulesMenuCentre()[1])) { // Gérer Comptes Etudiant
				// TODO	
			}
			if(btnClicked.getText().equals(panelAdmin.getIntitulesMenuCentre()[2])) { // Ajouter des livres au catalogue
				// TODO			
			}
			if(btnClicked.getText().equals(panelAdmin.getIntitulesMenuCentre()[3])) { // Afficher les livres en retard
				// TODO	
			}

		}
		
	}
	
	// changer le contentPane de la fenetre mere en fonction du type d'utilisateur.
}
