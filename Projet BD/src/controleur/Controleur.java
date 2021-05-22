package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

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
			}
			
			else {
				System.out.println("denied");
			}
		}
		
	}
	
	// changer le contentPane de la fenetre mere en fonction du type d'utilisateur.
}
