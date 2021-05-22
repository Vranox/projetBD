package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.*;

import javax.swing.*;

import modele.ConnexionBD;
import modele.Utilisateur;
import vue.*;

public class Controleur implements ActionListener {
	FenetreMere fenetremere;
	FenetreMere fenetremereD;
	PanelConnexion panelConnexion;
	JButton boutonLogin;
	String adminId = "a";
	String adminPwd = "a";
	Utilisateur user;
	Connection connection;
	String email;
	String pwd;
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
			email = panelConnexion.getEmailTxt().getText();
			pwd = new String(panelConnexion.getPwdTxt().getPassword());
			System.out.println("email: "+email+" pwd: "+pwd);
			if(email.equals(adminId) && pwd.equals(adminPwd)) {
				user = new Utilisateur(adminId,adminPwd);
				System.out.println("success");
				fenetremere.dispose();
				fenetremereD= new FenetreMere("Session Admin");
			}
			try {
				new ConnexionBD();
				connection = ConnexionBD.ConnectFromIUT();
				Statement stmt = connection.createStatement ();
				String sql = "SELECT * FROM ETUDIANT WHERE email = '"+email+"' AND mdp='"+pwd+"'";
				ResultSet rset = stmt.executeQuery(sql);
				if(rset.next()) {
					user = new Utilisateur(email,pwd);
					System.out.println("success");
					fenetremere.dispose();
					fenetremereD = new FenetreMere("Session Etudiant");
				}
				else {
					panelConnexion.getLabelFill().setText("Identifiant ou Mot de Passe INCORRECT");
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	// changer le contentPane de la fenetre mere en fonction du type d'utilisateur.
}
