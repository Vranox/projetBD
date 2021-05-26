package vue;
import java.awt.Color;
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
	public FenetreMere(String parTitre) throws SQLException, IOException{
		super(parTitre);
		if(parTitre.equals("Connection BU")) {
			panelConnexion = new PanelConnexion();
			setContentPane(panelConnexion);
			setSize(800,500);
		}
		else if(parTitre.equals("Session Admin")) {
			panelAdmin = new PanelAdmin();
			
			// ajouter ici les instantiations des panels admin
			panelGestionEtudiant = new PanelGestionEtudiant();
			panelMenu = new PanelMenu();
			setContentPane(panelAdmin);
			setSize(1000,800);
		}
		else if(parTitre.equals("Session Etudiant")) {
			panelEtudiant = new PanelEtudiant();
			setContentPane(panelEtudiant);
			setSize(1000,800);
		}
		else if(parTitre.equals("Test")) {
			panelMenu = new PanelMenu();
			setContentPane(panelMenu);
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
	public PanelGestionEtudiant getPanelGestionEtudiant() {
		return panelGestionEtudiant;
	}
	public void setPanelGestionEtudiant(PanelGestionEtudiant panelGestionEtudiant) {
		this.panelGestionEtudiant = panelGestionEtudiant;
	}
	public PanelRecherche getPanelRecherche() {
		return panelRecherche;
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
	
}
