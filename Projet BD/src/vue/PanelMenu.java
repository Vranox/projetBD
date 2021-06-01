package vue;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controleur.Controleur;

public class PanelMenu extends JPanel {
	CardLayout cartesLayout = new CardLayout(4,4);
	JPanel panelCartes;
	JPanel panelSide;
	JPanel panelSideMenu;
	JPanel panelSideBottom;
	JPanel panelLogo;
	JPanel panelHome;
	JPanel panelLivre;
	JPanel panelEtudiant;
	JPanel panelRetard;
	ImageIcon imageLogo = new ImageIcon("images/sideLogo.png");
	ImageIcon imageHome = new ImageIcon("images/sideHome.png");
	ImageIcon imageLivre = new ImageIcon("images/sideLivre.png");
	ImageIcon imageEtudiant = new ImageIcon("images/sideEtudiant.png");
	ImageIcon imageRetard = new ImageIcon("images/sideRetard.png");
	JLabel labelLogo;
	JLabel labelHome;
	JLabel labelLivre;
	JLabel labelEtudiant;
	JLabel labelRetard;
	JLabel labelLogoTxt;
	JLabel labelHomeTxt;
	JLabel labelLivreTxt;
	JLabel labelEtudiantTxt;
	JLabel labelRetardTxt;
	PanelRecherche panelRecherche;
	PanelGestionLivre panelGestionLivre;
	PanelGestionEtudiant panelGestionEtudiant;
	Color orange  = new Color(242,159,5);
	Color violet2 = new Color(64,2,53);
	Color violet = new Color(130,70,120);
	Color blanc = new Color(255,255,255);
	Color bleu = new Color(4,57,217);
	int numCarteOld = 0;
	public PanelMenu(Connection connexion) throws SQLException, IOException {
		setLayout(new BorderLayout());
		Font font1 = new Font("SansSerif", 1, 20);
		labelLogo = new JLabel(imageLogo);
		labelHome = new JLabel(imageHome);
		labelLivre = new JLabel(imageLivre);
		labelEtudiant = new JLabel(imageEtudiant);
		labelRetard = new JLabel(imageRetard);
		labelLogoTxt = new JLabel("BU VELIZY");
		labelLogoTxt.setPreferredSize(new Dimension(151,56));
		labelLogoTxt.setForeground(blanc);
		labelLogoTxt.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogoTxt.setFont(font1);
		labelHomeTxt = new JLabel("ACCUEIL");
		labelHomeTxt.setPreferredSize(new Dimension(136,56));
		labelHomeTxt.setForeground(blanc);
		labelHomeTxt.setHorizontalAlignment(SwingConstants.LEFT);
		labelLivreTxt = new JLabel("GESTION LIVRE");
		labelLivreTxt.setPreferredSize(new Dimension(136,56));
		labelLivreTxt.setForeground(blanc);
		labelLivreTxt.setAlignmentX(CENTER_ALIGNMENT);
		labelLivreTxt.setHorizontalAlignment(SwingConstants.LEFT);
		labelEtudiantTxt = new JLabel("GESTION ETUDIANT");
		labelEtudiantTxt.setPreferredSize(new Dimension(136,56));
		labelEtudiantTxt.setForeground(blanc);
		labelEtudiantTxt.setHorizontalAlignment(SwingConstants.LEFT);
		labelRetardTxt = new JLabel("RETARDS");
		labelRetardTxt.setPreferredSize(new Dimension(136,56));
		labelRetardTxt.setForeground(blanc);
		labelRetardTxt.setHorizontalAlignment(SwingConstants.LEFT);
		panelCartes = new JPanel();
		panelSide = new JPanel();
		panelLogo = new JPanel();
		panelHome = new JPanel();
		panelLivre = new JPanel();
		panelEtudiant = new JPanel();
		panelRetard = new JPanel();
		panelRecherche = new PanelRecherche(connexion);
		panelGestionLivre = new PanelGestionLivre(connexion);
		panelGestionEtudiant = new PanelGestionEtudiant(connexion);
		panelGestionEtudiant.setPreferredSize(new Dimension(805,800));
		panelGestionEtudiant.setBackground(orange);
		panelGestionEtudiant.setOpaque(true);
		add(panelCartes,BorderLayout.CENTER);
		add(panelSide,BorderLayout.WEST);
		//Gestion panelSide
		panelSide.setBackground(violet);
		panelSide.setPreferredSize(new Dimension(195,800));
		panelSide.setLayout(new BorderLayout());
		panelSideMenu = new JPanel();
		panelSideMenu.setPreferredSize(new Dimension(195,295));
		panelSideMenu.setBackground(blanc);
		GridLayout grid = new GridLayout();
		grid.setRows(5);
		panelSideMenu.setLayout(grid);
		panelSide.add(panelSideMenu,BorderLayout.NORTH);
		
		GridBagConstraints gbc;
		panelLogo.setPreferredSize(new Dimension(195,67));
		panelLogo.setBackground(orange);
		panelLogo.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0,15,0,0);
		panelLogo.add(labelLogo,gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0,0,0,0);
		panelLogo.add(labelLogoTxt,gbc);
		panelSideMenu.add(panelLogo);
		
		panelHome.setPreferredSize(new Dimension(195,56));
		panelHome.setBackground(violet);
		panelHome.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0,15,0,0);
		panelHome.add(labelHome,gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,0,0);
		panelHome.add(labelHomeTxt,gbc);
		panelSideMenu.add(panelHome);
		
		panelLivre.setPreferredSize(new Dimension(195,56));
		panelLivre.setBackground(violet);
		panelLivre.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0,15,0,0);
		panelLivre.add(labelLivre,gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,0,0);
		panelLivre.add(labelLivreTxt,gbc);
		panelSideMenu.add(panelLivre);
		
		panelEtudiant.setPreferredSize(new Dimension(195,56));
		panelEtudiant.setBackground(violet);
		panelEtudiant.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0,15,0,0);
		panelEtudiant.add(labelEtudiant,gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,0,0);
		panelEtudiant.add(labelEtudiantTxt,gbc);
		panelSideMenu.add(panelEtudiant);
		
		panelRetard.setPreferredSize(new Dimension(195,56));
		panelRetard.setBackground(violet);
		panelRetard.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0,15,0,0);
		panelRetard.add(labelRetard,gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,0,0);
		panelRetard.add(labelRetardTxt,gbc);
		panelSideMenu.add(panelRetard);
		
		panelSideBottom = new JPanel();
		panelSideBottom.setPreferredSize(new Dimension(195,505));
		panelSideBottom.setBackground(violet);
		panelSide.add(panelSideBottom,BorderLayout.CENTER);
		//Gestion panelCartes
		panelCartes.setPreferredSize(new Dimension(805,800));
		cartesLayout.setHgap(0);
		cartesLayout.setVgap(0);
		panelCartes.setLayout(cartesLayout);
		panelCartes.add(panelRecherche,"Recherche");
		panelCartes.add(panelGestionEtudiant,"GestionEtudiant");
		panelCartes.add(panelGestionLivre,"GestionLivre");
	}
	public void enregistreEcouteur(Controleur parControleur) {
		panelHome.addMouseListener(parControleur);
		panelLivre.addMouseListener(parControleur);
		panelEtudiant.addMouseListener(parControleur);
		panelRetard.addMouseListener(parControleur);
	}
	public void setCouleur(int numOld) {
		switch(numOld) {
		case 0:
			break;
		case 1:
			panelRetard.setBackground(violet);
			break;
		case 2:
			panelEtudiant.setBackground(violet);
			break;
		case 3:
			panelLivre.setBackground(violet);
			break;
		}
	}
	public void setCartes(int i) {
		switch(i) {
		case 1 :
			setCouleur(numCarteOld);
			cartesLayout.show(panelCartes, "Recherche");
			panelRetard.setBackground(violet2);
			numCarteOld=i;
			break;
		case 2:
			setCouleur(numCarteOld);
			cartesLayout.show(panelCartes, "GestionEtudiant");
			panelEtudiant.setBackground(violet2);
			numCarteOld=i;
			break;
		case 3:
			setCouleur(numCarteOld);
			cartesLayout.show(panelCartes, "GestionLivre");
			panelLivre.setBackground(violet2);
			numCarteOld=i;
			break;
		}
	}
	

	public JPanel getPanelHome() {
		return panelHome;
	}
	public void setPanelHome(JPanel panelHome) {
		this.panelHome = panelHome;
	}
	public JPanel getPanelLivre() {
		return panelLivre;
	}
	public void setPanelLivre(JPanel panelLivre) {
		this.panelLivre = panelLivre;
	}
	public JPanel getPanelEtudiant() {
		return panelEtudiant;
	}
	public void setPanelEtudiant(JPanel panelEtudiant) {
		this.panelEtudiant = panelEtudiant;
	}
	public JPanel getPanelRetard() {
		return panelRetard;
	}
	public void setPanelRetard(JPanel panelRetard) {
		this.panelRetard = panelRetard;
	}
	public PanelGestionLivre getPanelGestionLivre() {
		return panelGestionLivre;
	}
	public PanelRecherche getPanelRecherche() {
		return panelRecherche;
	}
	public PanelGestionEtudiant getPanelGestionEtudiant() {
		return panelGestionEtudiant;
	}
}
