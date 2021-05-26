package vue;

import java.awt.*;

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
	public PanelMenu() {
		setLayout(new BorderLayout());
		panelCartes = new JPanel();
		panelSide = new JPanel();
		panelRecherche = new PanelRecherche();
		panelGestionEtudiant = new PanelGestionEtudiant();
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
	public PanelGestionEtudiant getPanelGestionEtudiant() {
		return panelGestionEtudiant;
	}
}
