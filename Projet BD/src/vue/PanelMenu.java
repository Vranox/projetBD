package vue;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JPanel;

public class PanelMenu extends JPanel {
	CardLayout cartesLayout = new CardLayout(4,4);
	JPanel panelCartes;
	JPanel panelSide;
	PanelRecherche panelRecherche;
	PanelGestionEtudiant panelGestionEtudiant;
	Color orange  = new Color(242,159,5);
	Color violet = new Color(64,2,53);
	Color blanc = new Color(255,255,255);
	Color bleu = new Color(4,57,217);
	public PanelMenu(Connection connexion) throws SQLException, IOException {
		setLayout(new BorderLayout());
		panelCartes = new JPanel();
		panelSide = new JPanel();
		panelRecherche = new PanelRecherche(connexion);
		panelGestionEtudiant = new PanelGestionEtudiant(connexion);
		panelGestionEtudiant.setPreferredSize(new Dimension(805,800));
		panelGestionEtudiant.setBackground(orange);
		panelGestionEtudiant.setOpaque(true);
		add(panelCartes,BorderLayout.CENTER);
		add(panelSide,BorderLayout.WEST);
		//Gestion panelSide
		panelSide.setBackground(violet);
		panelSide.setPreferredSize(new Dimension(195,800));
		panelSide.setOpaque(true);
		//Gestion panelCartes
		panelCartes.setPreferredSize(new Dimension(805,800));
		cartesLayout.setHgap(0);
		cartesLayout.setVgap(0);
		panelCartes.setLayout(cartesLayout);
		panelCartes.add(panelRecherche,"Recherche");
		panelCartes.add(panelGestionEtudiant,"GestionEtudiant");
	}
	public void setCartes(int i) {
		switch(i) {
		case 1 :
			cartesLayout.show(panelCartes, "Recherche");
			break;
		case 2:
			cartesLayout.show(panelCartes, "GestionEtudiant");
			break;
		}
	}
	public PanelRecherche getPanelRecherche() {
		return panelRecherche;
	}
	public PanelGestionEtudiant getPanelGestionEtudiant() {
		return panelGestionEtudiant;
	}
}
