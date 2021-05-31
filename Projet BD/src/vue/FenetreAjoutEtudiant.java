package vue;

import java.awt.*;
import java.sql.Connection;

import javax.swing.*;

import controleur.Controleur;
import modele.Couleur;
import modele.HintTextField;
import modele.JNumberTextField;
import modele.RequeteSQL;

public class FenetreAjoutEtudiant extends JDialog{
	Color orange = Couleur.getOrange();
	Color violet = Couleur.getViolet();
	Color violet2 = Couleur.getViolet2();
	Color blanc = Couleur.getBlanc();
	Color bleu = Couleur.getBleu();
	Color rouge = Couleur.getRouge();
	JPanel panelAjoutEtudiant;
	JPanel panelHead;
	JPanel panelForm2;
	JPanel panelForm;
	ImageIcon iconeLogo = new ImageIcon("images/log.png");
	ImageIcon iconeLogoLivre = new ImageIcon("images/logbook.png");
	ImageIcon iconeBouton = new ImageIcon("images/boutonAdd1.png");
	ImageIcon iconeBouton2 = new ImageIcon("images/boutonAdd2.png");
	JLabel labelLogoImage;
	JLabel labelBouton;
	JLabel labelBoutonLivre;
	JLabel labelTitre;
	JLabel labelExemplaire;
	JTextField txtNom;
	JTextField txtPrenom;
	JTextField txtEmail;
	JTextField txtMdp;
	JSeparator nomSep;
	JSeparator prenomSep;
	JSeparator emailSep;
	JSeparator mdpSep;
	int nbreExemplaire;
	Font fontTitre= new Font("Courier New", 1, 18);
	int Option;
	
	public FenetreAjoutEtudiant(int option) {
		super((Window)null);
		setModal(true);
		panelAjoutEtudiant = new JPanel();
		setContentPane(panelAjoutEtudiant);
		setSize(350,330);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		Option = option;
		
		if(option == 1) {
			panelAjoutEtudiant.setLayout(new BorderLayout());
			panelAjoutEtudiant.setBackground(blanc);
			panelAjoutEtudiant.setOpaque(true);
			panelHead = new JPanel();
			panelHead.setPreferredSize(new Dimension(350,64));
			panelForm = new JPanel();
			panelForm.setPreferredSize(new Dimension(350,266));
			panelAjoutEtudiant.add(panelHead,BorderLayout.NORTH);
			panelAjoutEtudiant.add(panelForm,BorderLayout.CENTER);
			
			panelHead.setLayout(new GridBagLayout());
			labelLogoImage = new JLabel(iconeLogo);
			labelLogoImage.requestFocusInWindow();
			panelHead.add(labelLogoImage,makeGbc(0,0,1));
			labelTitre = new JLabel("AJOUTER UN ETUDIANT");
			labelTitre.setForeground(orange);
			labelTitre.setFont(fontTitre);
			panelHead.add(labelTitre,makeGbc(1,0,1));
			
			panelForm.setLayout(new GridBagLayout());
			panelForm2 = new JPanel();
			panelForm2.setPreferredSize(new Dimension(260,210));
			panelForm.add(panelForm2,makeGbc(0,0,1));
			panelForm2.setLayout(new GridBagLayout());
			txtNom = new HintTextField("Nom",orange);
			txtNom.setPreferredSize(new Dimension(140,25));
			txtNom.setBorder(null);
			txtPrenom = new HintTextField("Prenom",orange);
			txtPrenom.setPreferredSize(new Dimension(140,25));
			txtPrenom.setBorder(null);
			txtEmail = new HintTextField("Email",orange);
			txtEmail.setPreferredSize(new Dimension(140,25));
			txtEmail.setBorder(null);
			txtMdp = new HintTextField("Mdp",orange);
			txtMdp.setPreferredSize(new Dimension(140,25));
			txtMdp.setBorder(null);
			labelBouton = new JLabel(iconeBouton);
			nomSep = new JSeparator();
			nomSep.setOrientation(SwingConstants.HORIZONTAL);
			nomSep.setForeground(orange);
			prenomSep = new JSeparator();
			prenomSep.setOrientation(SwingConstants.HORIZONTAL);
			prenomSep.setForeground(orange);
			emailSep = new JSeparator();
			emailSep.setOrientation(SwingConstants.HORIZONTAL);
			emailSep.setForeground(orange);
			mdpSep = new JSeparator();
			mdpSep.setOrientation(SwingConstants.HORIZONTAL);
			mdpSep.setForeground(orange);
			panelForm2.add(txtNom,makeGbc(0,0,1));
			GridBagConstraints gbc = makeGbc(0,2,1);
			gbc.insets = new Insets(5,0,0,0);
			panelForm2.add(txtPrenom,gbc);
			gbc = makeGbc(0,4,1);
			gbc.insets = new Insets(5,0,0,0);
			panelForm2.add(txtEmail,gbc);
			gbc = makeGbc(0,6,1);
			gbc.insets = new Insets(5,0,0,0);
			panelForm2.add(txtMdp,gbc);
			gbc = makeGbc(1,8,1);
			gbc.insets = new Insets(10,0,0,0);
			panelForm2.add(labelBouton, gbc);
			panelForm2.add(nomSep,makeGbc(0,1,2));
			panelForm2.add(prenomSep,makeGbc(0,3,2));
			panelForm2.add(emailSep,makeGbc(0,5,2));
			panelForm2.add(mdpSep,makeGbc(0,7,2));
		}
		else if(option == 2) {
			panelAjoutEtudiant.setLayout(new BorderLayout());
			panelAjoutEtudiant.setBackground(blanc);
			panelAjoutEtudiant.setOpaque(true);
			panelHead = new JPanel();
			panelHead.setPreferredSize(new Dimension(350,64));
			panelForm = new JPanel();
			panelForm.setPreferredSize(new Dimension(350,266));
			panelAjoutEtudiant.add(panelHead,BorderLayout.NORTH);
			panelAjoutEtudiant.add(panelForm,BorderLayout.CENTER);
			
			panelHead.setLayout(new GridBagLayout());
			labelLogoImage = new JLabel(iconeLogoLivre);
			labelLogoImage.requestFocusInWindow();
			panelHead.add(labelLogoImage,makeGbc(0,0,1));
			labelTitre = new JLabel("AJOUTER UN LIVRE");
			labelTitre.setForeground(orange);
			labelTitre.setFont(fontTitre);
			panelHead.add(labelTitre,makeGbc(1,0,1));
			
			panelForm.setLayout(new GridBagLayout());
			panelForm2 = new JPanel();
			panelForm2.setPreferredSize(new Dimension(260,210));
			panelForm.add(panelForm2,makeGbc(0,0,1));
			panelForm2.setLayout(new GridBagLayout());
			txtNom = new HintTextField("Titre",orange);
			txtNom.setPreferredSize(new Dimension(140,25));
			txtNom.setBorder(null);
			txtPrenom = new HintTextField("Auteur(s)",orange);
			txtPrenom.setPreferredSize(new Dimension(140,25));
			txtPrenom.setBorder(null);
			txtEmail = new JNumberTextField("0",orange);
			txtEmail.setPreferredSize(new Dimension(70,25));
			txtEmail.setBorder(null);
			labelExemplaire = new JLabel("Exemplaire:");
			labelExemplaire.setForeground(orange);
			labelExemplaire.setHorizontalAlignment(SwingConstants.CENTER);
			emailSep = new JSeparator();
			emailSep.setOrientation(SwingConstants.HORIZONTAL);
			emailSep.setForeground(orange);
			panelForm2.add(labelExemplaire,makeGbc(0,4,1));
			labelBoutonLivre = new JLabel(iconeBouton);
			nomSep = new JSeparator();
			nomSep.setOrientation(SwingConstants.HORIZONTAL);
			nomSep.setForeground(orange);
			prenomSep = new JSeparator();
			prenomSep.setOrientation(SwingConstants.HORIZONTAL);
			prenomSep.setForeground(orange);
			panelForm2.add(txtNom,makeGbc(0,0,1));
			GridBagConstraints gbc = makeGbc(0,2,1);
			gbc.insets = new Insets(5,0,0,0);
			panelForm2.add(txtPrenom,gbc);
			gbc = makeGbc(1,4,1);
			gbc.insets = new Insets(0,0,0,0);
			panelForm2.add(txtEmail,gbc);
			gbc = makeGbc(1,8,1);
			gbc.insets = new Insets(10,0,0,0);
			panelForm2.add(labelBoutonLivre, gbc);
			panelForm2.add(nomSep,makeGbc(0,1,2));
			panelForm2.add(prenomSep,makeGbc(0,3,2));
			panelForm2.add(emailSep,makeGbc(1,5,1));
		}
		
		
		this.pack();
		this.getContentPane().requestFocusInWindow();
	}
	public JLabel getLabelBoutonLivre() {
		return labelBoutonLivre;
	}
	public void enregistreEcouteur(Controleur parControleur) {
		if(Option ==1) {
			labelBouton.addMouseListener(parControleur);
		}
		else if(Option ==2) {
			labelBoutonLivre.addMouseListener(parControleur);
		}
	}
	public void setBoutonImage(int i) {
		if(i==1) {
			labelBouton.setIcon(iconeBouton);
		}
		else {
			labelBouton.setIcon(iconeBouton2);
		}
	}
	public void setBoutonImageLivre(int i) {
		if(i==1) {
			labelBoutonLivre.setIcon(iconeBouton);
		}
		else {
			labelBoutonLivre.setIcon(iconeBouton2);
		}
	}
	private GridBagConstraints makeGbc(int x, int y, int fillx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = fillx;
        return gbc;
    }
	public JLabel getLabelBouton() {
		return labelBouton;
	}
	public JTextField getTxtNom() {
		return txtNom;
	}
	public JTextField getTxtPrenom() {
		return txtPrenom;
	}
	public JTextField getTxtEmail() {
		return txtEmail;
	}
	public JTextField getTxtMdp() {
		return txtMdp;
	}
}
