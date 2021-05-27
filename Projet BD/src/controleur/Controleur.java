package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.io.IOException;
import java.sql.*;

import javax.swing.*;

import modele.ConnexionBD;
import modele.RequeteSQL;
import modele.Utilisateur;
import vue.*;

public class Controleur implements ActionListener {
	FenetreMere fenetremere;
	FenetreMere fenetremereD;
	PanelConnexion panelConnexion;
	PanelAdmin panelAdmin;
	PanelRecherche panelRecherche;
	PanelGestionEtudiant panelGestionEtudiant;
	PanelMenu panelMenu;
	JButton boutonLogin;
	String adminId = "a";
	String adminPwd = "a";
	Utilisateur user;
	Connection connection;
	String email;
	String pwd;
	public Controleur(FenetreMere parFenetremere, Connection parConnection) throws SQLException, IOException {
		fenetremere = parFenetremere;
		connection = parConnection;
		panelConnexion = fenetremere.getPanelConnexion();
		panelConnexion.enregistreEcouteur(this);
		//pour les tests
		/*fenetremere.dispose();
		fenetremereD= new FenetreMere("Session Admin");
		panelAdmin = fenetremereD.getPanelAdmin();
		fenetremereD.getPanelAdmin().enregistreEcouteur(this);*/
	}
	// Se mettre a l'ecoute du bouton "connexion"

	@Override
	public void actionPerformed(ActionEvent parEvt) {
		boutonLogin = panelConnexion.getLoginBtn();
		if(parEvt.getSource()==boutonLogin) {
			email = panelConnexion.getEmailTxt().getText();
			pwd = new String(panelConnexion.getPwdTxt().getPassword());
			user = new Utilisateur(email,pwd);
			System.out.println("email: "+email+" pwd: "+pwd);
			if(email.equals(adminId) && pwd.equals(adminPwd)) {
				user = new Utilisateur(adminId,adminPwd);
				System.out.println("success");
				fenetremere.dispose();
				try {
					fenetremereD= new FenetreMere("Session Admin",connection);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				panelAdmin = fenetremereD.getPanelAdmin();
				fenetremereD.getPanelAdmin().enregistreEcouteur(this);
			}
			if(RequeteSQL.isEtudiant(connection,user,panelConnexion)) {
				fenetremere.dispose();
				try {
					fenetremereD = new FenetreMere("Session Etudiant",connection);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				panelConnexion.getLabelFill().setText("Identifiant ou Mot de Passe INCORRECT");
				panelConnexion.getLabelFill().setOpaque(true);
			}
		}
		else if(Arrays.asList(panelAdmin.getBoutonsMenuCentre()).contains(parEvt.getSource())) // bouton du panelAdmin (ex: rechercher livre, ajouter livre.. ) cliqué 
		{
			JButton btnClicked = (JButton) parEvt.getSource();
			panelMenu = fenetremereD.getPanelMenu();
			panelAdmin.removeAll();
			panelAdmin.add(panelMenu);
			panelAdmin.revalidate();
			panelAdmin.repaint();
			if(btnClicked.getText().equals(panelAdmin.getIntitulesMenuCentre()[0])) { // Rechercher Livre
				
				panelMenu.setCartes(1);
				
			}
			if(btnClicked.getText().equals(panelAdmin.getIntitulesMenuCentre()[1])) { // Gérer Comptes Etudiant
				
				panelMenu.setCartes(2);
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
