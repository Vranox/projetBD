package vue;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.* ;
public class FenetreMere extends JFrame {

	PanelConnexion panelConnexion;
	PanelAdmin panelAdmin;
	PanelRecherche panelRecherche;
	PanelEtudiant panelEtudiant;
	PanelGestionEtudiant panelGestionEtudiant;
	PanelMenu panelMenu;
	PanelWarningEtudiant panelWarningEtudiant;
	String titre;
	public FenetreMere(String parTitre,Connection connexion) throws SQLException, IOException{
		super(parTitre);
		titre = parTitre;
		if(parTitre.equals("Connection BU")) {
			panelConnexion = new PanelConnexion();
			setContentPane(panelConnexion);
			setSize(800,500);
			this.pack();
			this.getContentPane().requestFocusInWindow();
		}
		else if(parTitre.equals("Session Admin")) {
			
			// ajouter ici les instantiations des panels admin
			setContentPane(panelAdmin);
			setSize(1000,800);
		}
		else if(parTitre.equals("Session Etudiant")) {
			panelEtudiant = new PanelEtudiant(connexion);
			setContentPane(panelEtudiant);
			setSize(1000,800);
		}
		else if(parTitre.equals("Test")) {
			panelMenu = new PanelMenu(connexion);
			setContentPane(panelMenu);
			setSize(1000,800);
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.setResizable(false);
		FenetreMere.centreWindow(this);
	}
	public String getTitre() {
		return titre;
	}
	public PanelEtudiant getPanelEtudiant() {
		return panelEtudiant;
	}
	public FenetreMere(String parTitre,Connection connexion,PanelAdmin parPanelAdmin,PanelMenu parPanelMenu) {
		super(parTitre);
		panelAdmin = parPanelAdmin;
		panelMenu = parPanelMenu;
		if(parTitre.equals("Session Admin")) {
					
					setContentPane(panelAdmin);
					setSize(1000,800);
				}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.setResizable(false);
		FenetreMere.centreWindow(this);
	}
	public PanelMenu getPanelMenu() {
		return panelMenu;
	}
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	public PanelConnexion getPanelConnexion() {
		return panelConnexion;
	}
	public PanelAdmin getPanelAdmin() {
		return panelAdmin;
	}
	public void setPanelAdmin(PanelAdmin panelAdmin) {
		this.panelAdmin = panelAdmin;
	}
	public void setPanelMenu(PanelMenu panelMenu) {
		this.panelMenu = panelMenu;
	}
	
}
