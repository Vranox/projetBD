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
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import modele.*;
import vue.*;

public class Controleur implements ActionListener, MouseListener, ListSelectionListener{
	FenetreMere fenetremere;
	FenetreWarning fenetreWarning;
	FenetreAjoutEtudiant fenetreAjoutEtudiant;
	FenetreEditEtudiant fenetreEditEtudiant;
	PanelConnexion panelConnexion;
	PanelAdmin panelAdmin;
	PanelRecherche panelRecherche;
	PanelGestionEtudiant panelGestionEtudiant;
	PanelMenu panelMenu;
	JButton boutonLogin;
	JButton boutonOuiSup;
	JButton boutonNonSup;
	JLabel boutonAjout;
	JLabel boutonEdit;
	JTextField champCherche;
	JTable tableauEtudiant;
	String adminId = "a";
	String adminPwd = "a";
	Utilisateur user;
	Connection connection;
	String email;
	String pwd;
	JComboBox<String> comboChoix;
	Etudiant[] dataEtudiant;
	String id_et;
	String nomEt;
	String prenomEt;
	String emailEt;
	String mdpEt;
	Color orange;
	Color violet;
	Color blanc;
	Color bleu;
	Color rouge;
	Color violet2 = Couleur.getViolet2();
	public Controleur(FenetreMere parFenetremere, Connection parConnection) throws SQLException, IOException {
		orange = Couleur.getOrange();
		violet = Couleur.getViolet();
		blanc = Couleur.getBlanc();
		bleu = Couleur.getBleu();
		rouge = Couleur.getRouge();
		fenetremere = parFenetremere;
		connection = parConnection;
		panelMenu = new PanelMenu(connection);
		panelAdmin = new PanelAdmin();
		panelConnexion = fenetremere.getPanelConnexion();
		panelConnexion.enregistreEcouteur(this);
		panelGestionEtudiant = panelMenu.getPanelGestionEtudiant();
		panelRecherche = panelMenu.getPanelRecherche();
		panelGestionEtudiant.enregistreEcouteur(this);
		panelRecherche.enregistreEcouteur(this);
		panelAdmin.enregistreEcouteur(this);
		
		
		//pour les tests
		fenetremere.dispose();
		fenetremere= new FenetreMere("Session Admin",connection,panelAdmin,panelMenu);
		fenetremere.getPanelAdmin().enregistreEcouteur(this);
	}
	// Se mettre a l'ecoute du bouton "connexion"

	public PanelAdmin getPanelAdmin() {
		return panelAdmin;
	}

	public void setPanelAdmin(PanelAdmin panelAdmin) {
		this.panelAdmin = panelAdmin;
	}

	public PanelMenu getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(PanelMenu panelMenu) {
		this.panelMenu = panelMenu;
	}

	@Override
	public void actionPerformed(ActionEvent parEvt) {
		boutonLogin = panelConnexion.getLoginBtn();
		champCherche = panelGestionEtudiant.getChampCherche();
		comboChoix = panelGestionEtudiant.getComboChoix();
		tableauEtudiant = panelGestionEtudiant.getTableau();
		
		if(parEvt.getSource()==boutonLogin) {
			email = panelConnexion.getEmailTxt().getText();
			pwd = new String(panelConnexion.getPwdTxt().getPassword());
			user = new Utilisateur(email,pwd);
			System.out.println("email: "+email+" pwd: "+pwd);
			if(email.equals(adminId) && pwd.equals(adminPwd)) {
				user = new Utilisateur(adminId,adminPwd);
				System.out.println("success");
				fenetremere.dispose();
				fenetremere= new FenetreMere("Session Admin",connection,panelAdmin,panelMenu);
			}
			if(RequeteSQL.isEtudiant(connection,user,panelConnexion)) {
				fenetremere.dispose();
				try {
					fenetremere = new FenetreMere("Session Etudiant",connection);
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
			panelMenu = fenetremere.getPanelMenu();
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
		else if(parEvt.getSource()==champCherche||parEvt.getSource()==comboChoix||parEvt.getSource()==panelGestionEtudiant.getComboTri()) {
			if(panelGestionEtudiant.getComboTri().getSelectedItem().equals("Ordre Croissant")) {
				dataEtudiant =RequeteSQL.getEtudiants(connection," WHERE "+comboChoix.getSelectedItem()+" LIKE '"+champCherche.getText()+"%' ORDER BY "+comboChoix.getSelectedItem()+" ASC");
			}
			else {
				dataEtudiant =RequeteSQL.getEtudiants(connection," WHERE "+comboChoix.getSelectedItem()+" LIKE '"+champCherche.getText()+"%' ORDER BY "+comboChoix.getSelectedItem()+" DESC");
			}
			panelGestionEtudiant.setData(dataEtudiant);
		}
		else if(parEvt.getSource()==boutonOuiSup) {
			fenetreWarning.dispose();
			try {	
				RequeteSQL.deleteEtudiant(connection, id_et);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dataEtudiant =RequeteSQL.getEtudiants(connection,"");
			panelGestionEtudiant.setData(dataEtudiant);
			panelGestionEtudiant.getPanelBouton1().setBorder(null);
		}
		else if(parEvt.getSource()==boutonNonSup) {
			fenetreWarning.dispose();
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==panelGestionEtudiant.getPanelBouton1()) {
			int ligneSup =panelGestionEtudiant.getTableau().getSelectedRow();
			if(ligneSup>-1) {
				id_et = (String) panelGestionEtudiant.getTableau().getValueAt(ligneSup, 0);
				nomEt = (String) panelGestionEtudiant.getTableau().getValueAt(ligneSup, 1);
				prenomEt = (String) panelGestionEtudiant.getTableau().getValueAt(ligneSup, 2);
				fenetreWarning = new FenetreWarning(nomEt.toUpperCase()+" "+prenomEt.toLowerCase());
				fenetreWarning.getPanelWarningEtudiant().enregistreEcouteur(this);
				boutonOuiSup = fenetreWarning.getPanelWarningEtudiant().getBoutonOui();
				boutonNonSup = fenetreWarning.getPanelWarningEtudiant().getBoutonNon();
				fenetreWarning.setLocationRelativeTo(panelGestionEtudiant);
				fenetreWarning.setVisible(true);
			}
		}
		else if(e.getSource()==panelGestionEtudiant.getPanelBouton2()) {
			int ligneSup =panelGestionEtudiant.getTableau().getSelectedRow();
			if(ligneSup>-1) {
				id_et = (String) panelGestionEtudiant.getTableau().getValueAt(ligneSup, 0);
				nomEt = (String) panelGestionEtudiant.getTableau().getValueAt(ligneSup, 1);
				prenomEt = (String) panelGestionEtudiant.getTableau().getValueAt(ligneSup, 2);
				emailEt = (String) panelGestionEtudiant.getTableau().getValueAt(ligneSup, 3);
				mdpEt = (String) panelGestionEtudiant.getTableau().getValueAt(ligneSup, 4);
				fenetreEditEtudiant = new FenetreEditEtudiant(id_et,nomEt,prenomEt,emailEt,mdpEt);
				fenetreEditEtudiant.enregistreEcouteur(this);
				panelGestionEtudiant.getPanelBouton2().setBorder(null);
				boutonEdit = fenetreEditEtudiant.getLabelBouton();
				fenetreEditEtudiant.setLocationRelativeTo(panelGestionEtudiant);
				fenetreEditEtudiant.setVisible(true);
			}
		}
		else if(e.getSource()==panelGestionEtudiant.getPanelBouton3()) {
			fenetreAjoutEtudiant = new FenetreAjoutEtudiant();
			fenetreAjoutEtudiant.enregistreEcouteur(this);
			boutonAjout = fenetreAjoutEtudiant.getLabelBouton();
			fenetreAjoutEtudiant.setLocationRelativeTo(panelGestionEtudiant);
			fenetreAjoutEtudiant.setVisible(true);
		}
		if(e.getSource()==boutonAjout) {
			String nomEt = fenetreAjoutEtudiant.getTxtNom().getText();
			String prenomEt = fenetreAjoutEtudiant.getTxtPrenom().getText();
			String emailEt = fenetreAjoutEtudiant.getTxtEmail().getText();
			String mdpEt = fenetreAjoutEtudiant.getTxtMdp().getText();
			try {
				RequeteSQL.addEtudiant(connection, nomEt, prenomEt, emailEt, mdpEt);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dataEtudiant =RequeteSQL.getEtudiants(connection,"");
			panelGestionEtudiant.setData(dataEtudiant);
			fenetreAjoutEtudiant.dispose();
		}
		if(e.getSource()==boutonEdit) {
			String nomEt = fenetreEditEtudiant.getTxtNom().getText();
			String prenomEt = fenetreEditEtudiant.getTxtPrenom().getText();
			String emailEt = fenetreEditEtudiant.getTxtEmail().getText();
			String mdpEt = fenetreEditEtudiant.getTxtMdp().getText();
			String idEt = fenetreEditEtudiant.getId_et();
			try {
				RequeteSQL.editEtudiant(connection, nomEt, prenomEt, emailEt, mdpEt, idEt);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dataEtudiant =RequeteSQL.getEtudiants(connection,"");
			panelGestionEtudiant.setData(dataEtudiant);
			fenetreEditEtudiant.dispose();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		int ligneSup =panelGestionEtudiant.getTableau().getSelectedRow();
		Border selectBorder = BorderFactory.createLineBorder(blanc, 3);
		if(e.getSource()==panelGestionEtudiant.getPanelBouton3()) {
			panelGestionEtudiant.getPanelBouton3().setBorder(selectBorder);
		}
		if(ligneSup>-1) {
			if(e.getSource()==panelGestionEtudiant.getPanelBouton1()) {
				panelGestionEtudiant.getPanelBouton1().setBorder(selectBorder);
			}
			else if(e.getSource()==panelGestionEtudiant.getPanelBouton2()) {
				panelGestionEtudiant.getPanelBouton2().setBorder(selectBorder);
			}
		}
		if(e.getSource()==boutonAjout) {
			fenetreAjoutEtudiant.setBoutonImage(2);
		}
		if(e.getSource()==boutonEdit) {
			fenetreEditEtudiant.setBoutonImage(2);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		int ligneSup =panelGestionEtudiant.getTableau().getSelectedRow();
		Border selectBorder = null;
		if(e.getSource()==panelGestionEtudiant.getPanelBouton3()) {
			panelGestionEtudiant.getPanelBouton3().setBorder(selectBorder);
		}
		if(ligneSup>-1) {
			if(e.getSource()==panelGestionEtudiant.getPanelBouton1()) {
				panelGestionEtudiant.getPanelBouton1().setBorder(selectBorder);
			}
			else if(e.getSource()==panelGestionEtudiant.getPanelBouton2()) {
				panelGestionEtudiant.getPanelBouton2().setBorder(selectBorder);
			}
		}
		if(e.getSource()==boutonAjout) {
			fenetreAjoutEtudiant.setBoutonImage(1);
		}
		if(e.getSource()==boutonEdit) {
			fenetreEditEtudiant.setBoutonImage(1);
		}
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		int ligneSup =panelGestionEtudiant.getTableau().getSelectedRow();
		if(ligneSup>-1) {
			panelGestionEtudiant.getPanelBouton1().setBackground(violet);
			panelGestionEtudiant.getPanelBouton2().setBackground(violet);
		}
		else if(ligneSup==-1) {
			panelGestionEtudiant.getPanelBouton1().setBackground(violet2);
			panelGestionEtudiant.getPanelBouton2().setBackground(violet2);
		}
		
		else if(e.getSource() == panelRecherche.getBtnRecherche()) {
			panelRecherche.search();
		}
		
	}


	
	
	// changer le contentPane de la fenetre mere en fonction du type d'utilisateur.
}
