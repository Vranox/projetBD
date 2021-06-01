package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class Controleur implements ActionListener, MouseListener, ListSelectionListener, KeyListener{
	FenetreMere fenetremere;
	FenetreWarning fenetreWarning;
	FenetreWarningLivre fenetreWarningLivre;
	FenetreAjoutEtudiant fenetreAjoutEtudiant;
	FenetreEditEtudiant fenetreEditEtudiant;
	FenetreReservationLivre fenetreReservationLivre;
	FenetreEmpruntEtudiant fenetreEmpruntEtudiant;
	FenetreRetourEtudiant fenetreRetourEtudiant;
	PanelConnexion panelConnexion;
	PanelAdmin panelAdmin;
	PanelRecherche panelRecherche;
	PanelGestionEtudiant panelGestionEtudiant;
	PanelGestionLivre panelGestionLivre;
	PanelMenu panelMenu;
	PanelEtudiant panelEtudiant;
	JButton boutonLogin;
	JButton boutonOuiSup;
	JButton boutonOuiSupLivre;
	JButton boutonNonSupLivre;
	JButton boutonNonSup;
	JButton boutonEmprunt;
	JButton boutonRetour;
	JLabel boutonAjout;
	JLabel boutonAjoutLivre;
	JLabel boutonEdit;
	JLabel boutonEditLivre;
	JTextField champCherche;
	JTextField champChercheLivre;
	JTextField champChercheEmprunt;
	JTextField champChercheRetour;
	JTable tableauEtudiant;
	String adminId = "a";
	String adminPwd = "a";
	Utilisateur user;
	Connection connection;
	String email;
	String pwd;
	JComboBox<String> comboChoix;
	JComboBox<String> comboChoixLivre;
	Etudiant[] dataEtudiant;
	Livre[] dataLivre;
	String id_et;
	String id_livre;
	String id_ex;
	String titreLivre;
	String auteurLivre;
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
	Color vert = Couleur.getVert();
	int numCarte;
	public int getNumCarte() {
		return numCarte;
	}

	public void setNumCarte(int numCarte) {
		this.numCarte = numCarte;
	}

	int nbreDispo;
	int nbreEmprunt;
	int nbreReserv;
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
		panelGestionLivre = panelMenu.getPanelGestionLivre();
		panelGestionLivre.enregistreEcouteur(this);
		panelRecherche = panelMenu.getPanelRecherche();
		panelGestionEtudiant.enregistreEcouteur(this);
		panelRecherche.enregistreEcouteur(this);
		panelAdmin.enregistreEcouteur(this);
		panelMenu.enregistreEcouteur(this);
		
		
		//pour les tests
		//fenetremere.dispose();
		//fenetremere= new FenetreMere("Session Admin",connection,panelAdmin,panelMenu);
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
		champChercheLivre = panelGestionLivre.getChampCherche();
		comboChoix = panelGestionEtudiant.getComboChoix();
		comboChoixLivre = panelGestionLivre.getComboChoix();
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
					panelEtudiant = fenetremere.getPanelEtudiant();
					panelEtudiant.setMailEtudiant(user.getUsername());
					panelEtudiant.updateReservationPossibilities();
					panelEtudiant.enregistreEcouteur(this);
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
		else if(parEvt.getSource()==boutonOuiSup) {
			fenetreWarning.dispose();
			try {	
				RequeteSQL.deleteEtudiant(connection, id_et);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dataEtudiant =RequeteSQL.getEtudiants(connection,"");
			panelGestionEtudiant.setData(dataEtudiant);
			champCherche.setText("");
			panelGestionEtudiant.getPanelBouton1().setBorder(null);
		}
		else if(parEvt.getSource()==boutonOuiSupLivre) {
			fenetreWarningLivre.dispose();
			try {	
				RequeteSQL.deleteLivre(connection, id_livre);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dataLivre =RequeteSQL.getBooks(connection,"");
			panelGestionLivre.setData(dataLivre);
			champCherche.setText("");
			panelGestionLivre.getPanelBouton1().setBorder(null);
		}
		else if(parEvt.getSource()==boutonNonSupLivre) {
			fenetreWarningLivre.dispose();
		}
		else if(parEvt.getSource()==panelGestionLivre.getBoutonReservation()) {
			fenetreReservationLivre = new FenetreReservationLivre(connection,id_livre);
			//fenetreReservationLivre.enregistreEcouteur(this);
			fenetreReservationLivre.setLocationRelativeTo(panelGestionEtudiant);
			fenetreReservationLivre.setVisible(true);
		}
		else if(parEvt.getSource()==boutonEmprunt) {
			int ligneSup =fenetreEmpruntEtudiant.getTableau().getSelectedRow();
			id_et = (String) fenetreEmpruntEtudiant.getTableau().getValueAt(ligneSup, 0);
			int nbreEmpruntEtudiant=0;
			try {
				nbreEmpruntEtudiant = RequeteSQL.getEmpruntEtudiant(connection, id_et);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (nbreEmpruntEtudiant<5) {
				try {
					RequeteSQL.setEmpruntEtudiant(connection,id_et,id_livre);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fenetreEmpruntEtudiant.dispose();
			}
			else {
				System.out.println("Cet �tudiant � trop d'emprunt");
			}
		}
		else if(parEvt.getSource()==boutonRetour) {
			int ligneSup =fenetreRetourEtudiant.getTableau().getSelectedRow();
			id_et = (String) fenetreRetourEtudiant.getTableau().getValueAt(ligneSup, 0);
			id_ex = (String) fenetreRetourEtudiant.getTableau().getValueAt(ligneSup, 4);
			try {
				RequeteSQL.finEmprunt(connection,id_et,id_ex);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fenetreRetourEtudiant.dispose();
		}
		else if(fenetremere.getTitre() == "Session Etudiant" && parEvt.getSource() == panelEtudiant.getBtnReserv()) {
			panelEtudiant.addReserv();
			panelEtudiant.updateReservationPossibilities();
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
		else if(e.getSource()==panelGestionLivre.getPanelBouton1()) {
			int ligneSup1 =panelGestionLivre.getTableau().getSelectedRow();
			if(ligneSup1>-1) {
				id_livre = Integer.toString((int)panelGestionLivre.getTableau().getValueAt(ligneSup1, 0));
				titreLivre = (String) panelGestionLivre.getTableau().getValueAt(ligneSup1, 1);
				auteurLivre = (String) panelGestionLivre.getTableau().getValueAt(ligneSup1, 2);
				fenetreWarningLivre = new FenetreWarningLivre(titreLivre);
				fenetreWarningLivre.enregistreEcouteur(this);
				boutonOuiSupLivre = fenetreWarningLivre.getBoutonOui();
				boutonNonSupLivre = fenetreWarningLivre.getBoutonNon();
				fenetreWarningLivre.setLocationRelativeTo(panelGestionLivre);
				fenetreWarningLivre.setVisible(true);
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
		else if(e.getSource()==panelGestionLivre.getPanelBouton2()) {
			int ligneSup =panelGestionLivre.getTableau().getSelectedRow();
			if(ligneSup>-1) {
				int id_livre = (int) panelGestionLivre.getTableau().getValueAt(ligneSup, 0);
				id_et = Integer.toString(id_livre);
				nomEt = (String) panelGestionLivre.getTableau().getValueAt(ligneSup, 1);
				prenomEt = (String) panelGestionLivre.getTableau().getValueAt(ligneSup, 2);
				try {
					fenetreEditEtudiant = new FenetreEditEtudiant(connection,id_et,nomEt,prenomEt);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fenetreEditEtudiant.enregistreEcouteur(this);
				panelGestionLivre.getPanelBouton2().setBorder(null);
				boutonEditLivre = fenetreEditEtudiant.getLabelBoutonLivre();
				fenetreEditEtudiant.setLocationRelativeTo(panelGestionLivre);
				fenetreEditEtudiant.setVisible(true);
			}
		}
		else if(e.getSource()==panelGestionEtudiant.getPanelBouton3()) {
			fenetreAjoutEtudiant = new FenetreAjoutEtudiant(1);
			fenetreAjoutEtudiant.enregistreEcouteur(this);
			boutonAjout = fenetreAjoutEtudiant.getLabelBouton();
			fenetreAjoutEtudiant.setLocationRelativeTo(panelGestionEtudiant);
			fenetreAjoutEtudiant.setVisible(true);
		}
		else if(e.getSource()==panelGestionLivre.getPanelBouton3()) {
			fenetreAjoutEtudiant = new FenetreAjoutEtudiant(2);
			fenetreAjoutEtudiant.enregistreEcouteur(this);
			boutonAjoutLivre = fenetreAjoutEtudiant.getLabelBoutonLivre();
			fenetreAjoutEtudiant.setLocationRelativeTo(panelGestionLivre);
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
			champCherche.setText("");
			fenetreAjoutEtudiant.dispose();
		}
		if(e.getSource()==boutonAjoutLivre) {
			String titreLivre = fenetreAjoutEtudiant.getTxtNom().getText();
			String auteurLivre = fenetreAjoutEtudiant.getTxtPrenom().getText();
			String idVouluString = fenetreAjoutEtudiant.getTxtEmail().getText();
			int idVoulu = Integer.valueOf(idVouluString);
			try {
				RequeteSQL.addLivre(connection, titreLivre, auteurLivre);
				String id_liv = RequeteSQL.getLastIdLivre(connection);
				RequeteSQL.setExemplaire(connection, id_liv, idVoulu);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dataLivre =RequeteSQL.getBooks(connection,"");
			panelGestionLivre.setData(dataLivre);
			champChercheLivre.setText("");
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
			champCherche.setText("");
			fenetreEditEtudiant.dispose();
		}
		if(e.getSource()==boutonEditLivre) {
			int ligneSupLivre = panelGestionLivre.getTableau().getSelectedRow();
			String titreLivre = (String) panelGestionLivre.getTableau().getValueAt(ligneSupLivre, 1);
			String auteurLivre = fenetreEditEtudiant.getTxtPrenom().getText();
			String idLivre = fenetreEditEtudiant.getId_et();
			String idVouluString = fenetreEditEtudiant.getTxtEmail().getText();
			int idVoulu = Integer.valueOf(idVouluString);
			try {
				RequeteSQL.editLivre(connection, titreLivre, auteurLivre,  idLivre);
				RequeteSQL.setExemplaire(connection, idLivre, idVoulu);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dataLivre =RequeteSQL.getBooks(connection,"");
			panelGestionLivre.setData(dataLivre);
			champChercheLivre.setText("");
			fenetreEditEtudiant.dispose();
		}
		if(e.getSource()==panelGestionLivre.getPanelPretBouton()) {
			if(nbreDispo>nbreReserv && nbreDispo!=0) {
				fenetreEmpruntEtudiant = new FenetreEmpruntEtudiant(connection,1,id_livre);
				fenetreEmpruntEtudiant.enregistreEcouteur(this);
				panelGestionLivre.getPanelPretBouton().setBorder(null);
				champChercheEmprunt = fenetreEmpruntEtudiant.getChampCherche();
				boutonEmprunt = fenetreEmpruntEtudiant.getBoutonEmprunt();
				fenetreEmpruntEtudiant.setLocationRelativeTo(panelGestionLivre);
				fenetreEmpruntEtudiant.setVisible(true);
			}
			else if(nbreDispo<=nbreReserv && nbreDispo!=0){
				fenetreEmpruntEtudiant = new FenetreEmpruntEtudiant(connection,2,id_livre);
				fenetreEmpruntEtudiant.enregistreEcouteur(this);
				panelGestionLivre.getPanelPretBouton().setBorder(BorderFactory.createLineBorder(blanc, 5));
				champChercheEmprunt = fenetreEmpruntEtudiant.getChampCherche();
				boutonEmprunt = fenetreEmpruntEtudiant.getBoutonEmprunt();
				fenetreEmpruntEtudiant.setLocationRelativeTo(panelGestionLivre);
				fenetreEmpruntEtudiant.setVisible(true);
			}
			
		}
		if(e.getSource()==panelGestionLivre.getPanelRendreBouton()) {
			if(nbreEmprunt>0) {
				fenetreRetourEtudiant = new FenetreRetourEtudiant(connection,id_livre);
				fenetreRetourEtudiant.enregistreEcouteur(this);
				panelGestionLivre.getPanelRendreBouton().setBorder(BorderFactory.createLineBorder(blanc, 5));
				champChercheRetour = fenetreRetourEtudiant.getChampCherche();
				boutonRetour = fenetreRetourEtudiant.getBoutonRetour();
				fenetreRetourEtudiant.setLocationRelativeTo(panelGestionLivre);
				fenetreRetourEtudiant.setVisible(true);
			}
		}
		if(e.getSource()==panelAdmin.getPanelBouton1()) {
			panelMenu = fenetremere.getPanelMenu();
			panelAdmin.removeAll();
			panelAdmin.add(panelMenu);
			panelAdmin.revalidate();
			panelAdmin.repaint();	
			panelMenu.setCartes(3);
			numCarte = 3;
		}
		if(e.getSource()==panelAdmin.getPanelBouton2()) {
			panelMenu = fenetremere.getPanelMenu();
			panelAdmin.removeAll();
			panelAdmin.add(panelMenu);
			panelAdmin.revalidate();
			panelAdmin.repaint();	
			panelMenu.setCartes(2);
			numCarte = 2;
		}
		if(e.getSource()==panelAdmin.getPanelBouton3()) {
			panelMenu = fenetremere.getPanelMenu();
			panelAdmin.removeAll();
			panelAdmin.add(panelMenu);
			panelAdmin.revalidate();
			panelAdmin.repaint();	
			panelMenu.setCartes(1);
			numCarte = 1;
		}
		if(e.getSource()==panelMenu.getPanelLivre()) {
			panelMenu.setCartes(3);
			numCarte = 3;
		}
		if(e.getSource()==panelMenu.getPanelEtudiant()) {
			panelMenu.setCartes(2);
			numCarte = 2;
		}
		if(e.getSource()==panelMenu.getPanelRetard()) {
			panelMenu.setCartes(1);
			numCarte = 1;
		}
		if(e.getSource()==panelMenu.getPanelHome()) {
			PanelAdmin panel = panelAdmin;
			panelAdmin = new PanelAdmin();
			panel.removeAll();
			panel.add(panelAdmin);
			panel.revalidate();
			panel.repaint();
			panelAdmin.enregistreEcouteur(this);
			panelMenu.getPanelHome().setBackground(violet2);
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
		int ligneSupLivre = panelGestionLivre.getTableau().getSelectedRow();
		Border selectBorder = BorderFactory.createLineBorder(blanc, 3);
		if(e.getSource()==panelGestionEtudiant.getPanelBouton3()) {
			panelGestionEtudiant.getPanelBouton3().setBorder(selectBorder);
		}
		else if(e.getSource()==panelGestionLivre.getPanelBouton3()) {
			panelGestionLivre.getPanelBouton3().setBorder(selectBorder);
		}
		if(ligneSup>-1 || ligneSupLivre>-1) {
			if(e.getSource()==panelGestionEtudiant.getPanelBouton1()) {
				panelGestionEtudiant.getPanelBouton1().setBorder(selectBorder);
			}
			else if(e.getSource()==panelGestionEtudiant.getPanelBouton2()) {
				panelGestionEtudiant.getPanelBouton2().setBorder(selectBorder);
			}
			if(e.getSource()==panelGestionLivre.getPanelBouton1()) {
				panelGestionLivre.getPanelBouton1().setBorder(selectBorder);
			}
			else if(e.getSource()==panelGestionLivre.getPanelBouton2()) {
				panelGestionLivre.getPanelBouton2().setBorder(selectBorder);
			}
		}
		if(e.getSource()==boutonAjout) {
			fenetreAjoutEtudiant.setBoutonImage(2);
		}
		if(e.getSource()==boutonAjoutLivre) {
			fenetreAjoutEtudiant.setBoutonImageLivre(2);
		}
		if(e.getSource()==boutonEdit) {
			fenetreEditEtudiant.setBoutonImage(2);
		}
		if(e.getSource()==boutonEditLivre) {
			fenetreEditEtudiant.setBoutonImageLivre(2);
		}
		if(e.getSource()==panelGestionLivre.getPanelPretBouton()) {
			if(nbreDispo>0) {
				panelGestionLivre.getPanelPretBouton().setBorder(null);
			}
		}
		if(e.getSource()==panelGestionLivre.getPanelRendreBouton()) {
			if(nbreEmprunt>0)
				panelGestionLivre.getPanelRendreBouton().setBorder(null);
		}
		if(e.getSource()==panelAdmin.getPanelBouton1()) {
			panelAdmin.getPanelBouton1().setBorder(null);
		}
		if(e.getSource()==panelAdmin.getPanelBouton2()) {
			panelAdmin.getPanelBouton2().setBorder(null);
		}
		if(e.getSource()==panelAdmin.getPanelBouton3()) {
			panelAdmin.getPanelBouton3().setBorder(null);
		}
		if(e.getSource()==panelAdmin.getPanelStats()) {
			panelAdmin.getPanelStats().setBorder(null);
		}
		if(e.getSource()==panelMenu.getPanelHome()) {
			panelMenu.getPanelHome().setBackground(violet);
		}
		if(e.getSource()==panelMenu.getPanelLivre()) {
			if(numCarte!=3)
				panelMenu.getPanelLivre().setBackground(violet);
		}
		if(e.getSource()==panelMenu.getPanelEtudiant()) {
			if(numCarte!=2)
				panelMenu.getPanelEtudiant().setBackground(violet);
		}
		if(e.getSource()==panelMenu.getPanelRetard()) {
			if(numCarte!=1)
				panelMenu.getPanelRetard().setBackground(violet);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		int ligneSup =panelGestionEtudiant.getTableau().getSelectedRow();
		int ligneSupLivre = panelGestionLivre.getTableau().getSelectedRow();
		Border selectBorder = null;
		if(e.getSource()==panelGestionEtudiant.getPanelBouton3()) {
			panelGestionEtudiant.getPanelBouton3().setBorder(selectBorder);
		}
		else if(e.getSource()==panelGestionLivre.getPanelBouton3()) {
			panelGestionLivre.getPanelBouton3().setBorder(selectBorder);
		}
		if(ligneSup>-1 || ligneSupLivre>-1) {
			if(e.getSource()==panelGestionEtudiant.getPanelBouton1()) {
				panelGestionEtudiant.getPanelBouton1().setBorder(selectBorder);
			}
			else if(e.getSource()==panelGestionEtudiant.getPanelBouton2()) {
				panelGestionEtudiant.getPanelBouton2().setBorder(selectBorder);
			}
			if(e.getSource()==panelGestionLivre.getPanelBouton1()) {
				panelGestionLivre.getPanelBouton1().setBorder(selectBorder);
			}
			else if(e.getSource()==panelGestionLivre.getPanelBouton2()) {
				panelGestionLivre.getPanelBouton2().setBorder(selectBorder);
			}
		}
		if(e.getSource()==boutonAjout) {
			fenetreAjoutEtudiant.setBoutonImage(1);
		}
		if(e.getSource()==boutonAjoutLivre) {
			fenetreAjoutEtudiant.setBoutonImageLivre(1);
		}
		if(e.getSource()==boutonEdit) {
			fenetreEditEtudiant.setBoutonImage(1);
		}
		if(e.getSource()==boutonEditLivre) {
			fenetreEditEtudiant.setBoutonImageLivre(1);
		}
		if(e.getSource()==panelGestionLivre.getPanelPretBouton()) {
			if(nbreDispo>0)
				panelGestionLivre.getPanelPretBouton().setBorder(BorderFactory.createLineBorder(blanc, 5));
		}
		if(e.getSource()==panelGestionLivre.getPanelRendreBouton()) {
			if(nbreEmprunt>0)
				panelGestionLivre.getPanelRendreBouton().setBorder(BorderFactory.createLineBorder(blanc, 5));
		}
		if(e.getSource()==panelAdmin.getPanelBouton1()) {
			panelAdmin.getPanelBouton1().setBorder(BorderFactory.createLineBorder(orange, 5));
		}
		if(e.getSource()==panelAdmin.getPanelBouton2()) {
			panelAdmin.getPanelBouton2().setBorder(BorderFactory.createLineBorder(orange, 5));
		}
		if(e.getSource()==panelAdmin.getPanelBouton3()) {
			panelAdmin.getPanelBouton3().setBorder(BorderFactory.createLineBorder(orange, 5));
		}
		if(e.getSource()==panelAdmin.getPanelStats()) {
			panelAdmin.getPanelStats().setBorder(BorderFactory.createLineBorder(orange, 5));
		}
		if(e.getSource()==panelMenu.getPanelHome()) {
			panelMenu.getPanelHome().setBackground(violet2);
		}
		if(e.getSource()==panelMenu.getPanelLivre()) {
			if(numCarte!=3)
				panelMenu.getPanelLivre().setBackground(violet2);
		}
		if(e.getSource()==panelMenu.getPanelEtudiant()) {
			if(numCarte!=2)
				panelMenu.getPanelEtudiant().setBackground(violet2);
		}
		if(e.getSource()==panelMenu.getPanelRetard()) {
			if(numCarte!=1)
				panelMenu.getPanelRetard().setBackground(violet2);
		}
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		int ligneSup =panelGestionEtudiant.getTableau().getSelectedRow();
		int ligneSupLivre = panelGestionLivre.getTableau().getSelectedRow();
		if(ligneSup>-1) {
			panelGestionEtudiant.getPanelBouton1().setBackground(violet);
			panelGestionEtudiant.getPanelBouton2().setBackground(violet);
		}
		else if(ligneSup==-1) {
			panelGestionEtudiant.getPanelBouton1().setBackground(violet2);
			panelGestionEtudiant.getPanelBouton2().setBackground(violet2);
		}
		if(ligneSupLivre>-1) {
			panelGestionLivre.getPanelBouton1().setBackground(violet);
			panelGestionLivre.getPanelBouton2().setBackground(violet);
			panelGestionLivre.getPanelDroit().setVisible(true);
			int nbreEx = 0;
			nbreDispo = 0;
			nbreReserv = 0;
			nbreEmprunt = 0;
			id_livre = Integer.toString((int)panelGestionLivre.getTableau().getValueAt(ligneSupLivre, 0));
			titreLivre = (String) panelGestionLivre.getTableau().getValueAt(ligneSupLivre, 1);
			auteurLivre = (String) panelGestionLivre.getTableau().getValueAt(ligneSupLivre, 2);
			panelGestionLivre.getLabelLivreTitreData().setText(titreLivre);
			panelGestionLivre.getLabelLivreAuteurData().setText(auteurLivre);
			try {
				nbreEx = RequeteSQL.getExemplaire(connection, id_livre);
				nbreDispo = RequeteSQL.getDisponible(connection, nbreEx, id_livre);
				nbreReserv = RequeteSQL.getReservation(connection, id_livre);
				nbreEmprunt = RequeteSQL.getEmprunt(connection, id_livre);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			panelGestionLivre.getLabelLivreExemplaireData().setText(String.valueOf(nbreEx));
			panelGestionLivre.getLabelLivreDisponibleData().setText(String.valueOf(nbreDispo));
			panelGestionLivre.getLabelLivreReservationData().setText(String.valueOf(nbreReserv));
			panelGestionLivre.getLabelLivreEmpruntData().setText(String.valueOf(nbreEmprunt));
			if(nbreDispo<1) {
				panelGestionLivre.getPanelPretBouton().setBackground(violet2);
			}
			else {
				panelGestionLivre.getPanelPretBouton().setBackground(violet);
			}
			if(nbreEmprunt>0) {
				panelGestionLivre.getPanelRendreBouton().setBackground(violet);
			}
			else {
				panelGestionLivre.getPanelRendreBouton().setBackground(violet2);
			}
		}
		else if(ligneSupLivre==-1) {
			panelGestionLivre.getPanelBouton1().setBackground(violet2);
			panelGestionLivre.getPanelBouton2().setBackground(violet2);
			panelGestionLivre.getPanelDroit().setVisible(false);
		}
		
		else if(e.getSource() == panelRecherche.getBtnRecherche()) {
			panelRecherche.search();
		}
		
		if(fenetremere.getTitre() == "Session Etudiant") {
			panelEtudiant.updateReservationPossibilities();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent parEvt) {
		if(parEvt.getSource()==champCherche||parEvt.getSource()==comboChoix||parEvt.getSource()==panelGestionEtudiant.getComboTri()) {
			if(panelGestionEtudiant.getComboTri().getSelectedItem().equals("Ordre Croissant")) {
				dataEtudiant =RequeteSQL.getEtudiants(connection," WHERE "+comboChoix.getSelectedItem()+" LIKE '"+champCherche.getText()+"%' ORDER BY "+comboChoix.getSelectedItem()+" ASC");
			}
			else {
				dataEtudiant =RequeteSQL.getEtudiants(connection," WHERE "+comboChoix.getSelectedItem()+" LIKE '"+champCherche.getText()+"%' ORDER BY "+comboChoix.getSelectedItem()+" DESC");
			}
			panelGestionEtudiant.setData(dataEtudiant);
		}
		if(parEvt.getSource()==champChercheLivre||parEvt.getSource()==comboChoixLivre) {
			dataLivre =RequeteSQL.getBooks(connection," WHERE "+comboChoixLivre.getSelectedItem()+" LIKE '%"+champChercheLivre.getText()+"%' ORDER BY "+comboChoixLivre.getSelectedItem());
			panelGestionLivre.setData(dataLivre);
		}
		if(parEvt.getSource()==champChercheEmprunt) {
			dataEtudiant =RequeteSQL.getEtudiants(connection," WHERE "+fenetreEmpruntEtudiant.getComboChoix().getSelectedItem()+" LIKE '%"+champChercheEmprunt.getText()+"%' ORDER BY "+fenetreEmpruntEtudiant.getComboChoix().getSelectedItem());
			fenetreEmpruntEtudiant.setData(dataEtudiant);
		}
		if(parEvt.getSource()==champChercheRetour) {
			dataEtudiant =RequeteSQL.getEmp(connection,id_livre+" AND "+fenetreRetourEtudiant.getComboChoix().getSelectedItem()+" LIKE '%"+champChercheRetour.getText()+"%' ORDER BY "+fenetreRetourEtudiant.getComboChoix().getSelectedItem());
			fenetreRetourEtudiant.setData(dataEtudiant);
		}
		if(fenetremere.getTitre() == "Session Etudiant" && parEvt.getSource() == panelEtudiant.getSearchBar()) {
			panelEtudiant.search();
		}
		
	}


	
	
	// changer le contentPane de la fenetre mere en fonction du type d'utilisateur.
}
