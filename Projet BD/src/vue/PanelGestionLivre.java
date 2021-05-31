package vue;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import modele.ConnexionBD;
import modele.Couleur;
import modele.Etudiant;
import modele.Livre;
import modele.ModeleEtudiantTable;
import modele.ModeleLivreTable;
import modele.RequeteSQL;

public class PanelGestionLivre extends JPanel {
	ImageIcon imageLogo = new ImageIcon("images/logbook.png");
	ImageIcon imageRecherche = new ImageIcon("images/recherche.png");
	ImageIcon imageSupprimer = new ImageIcon("images/supprimer.png");
	ImageIcon imageModifier = new ImageIcon("images/modifier.png");
	ImageIcon imageAjouter = new ImageIcon("images/ajouter.png");
	ImageIcon imageEmprunt = new ImageIcon("images/emprunt.png");
	ImageIcon imageRetour = new ImageIcon("images/retour.png");
	Color orange = Couleur.getOrange();
	Color violet = Couleur.getViolet();
	Color violet2 = Couleur.getViolet2();
	Color blanc = Couleur.getBlanc();
	Color bleu = Couleur.getBleu();
	Color rouge = Couleur.getRouge();
	Color vert = Couleur.getVert();
	 JTextField champCherche;
     JComboBox<String> comboChoix;
     JLabel imageCherche;
     JLabel jLabel5;
     JScrollPane jScrollPane1;
     JTextField jTextField2;
     JLabel labelAj;
     JLabel labelAjImg;
     JLabel labelChoix;
     JPanel labelFill1;
     JPanel panelBouton;
     JLabel labelMod;
     JLabel labelModImg;
     JLabel labelSup;
     JLabel labelSupImg;
     JLabel labelTitre;
     JLabel labelEmpruntImg;
     JLabel labelRetourImg;
     JLabel labelRetour;
     JLabel labelEmprunt;
     JPanel panelBouton1;
     JPanel panelBouton2;
     JPanel panelBouton3;
     JPanel panelCherche;
     JPanel panelChoix;
     JPanel panelDroit;
     JPanel panelGestionEtudiant;
     JPanel panelHead;
     JPanel panelLogo;
     JPanel panelRecherche;
     JPanel panelTable;
     JPanel panelTable2;
     JPanel panelDroit2;
     JPanel panelTitre;
     JPanel panelDesc;
     JTable tableau;
     JLabel labelLivreAuteur;
     JLabel labelLivreExemplaire;
     JLabel labelLivreDisponible;
     JLabel labelLivreReservation;
     JLabel labelLivreAuteurData;
     JLabel labelLivreEmprunt;
     JLabel labelLivreTitreData;
     JLabel labelLivreExemplaireData;
     JLabel labelLivreDisponibleData;
     JLabel labelLivreReservationData;
     JLabel labelLivreEmpruntData;
     JPanel panelPretBouton;
     JPanel panelRendreBouton;
     JButton boutonReservation;
     JPanel panelDesc1;
     JPanel panelDesc2;
     JPanel panelDesc3;
     Font labelFont = new Font("Dubai", 1, 15);
     Font labelFont2 = new Font("Dubai", 1, 18);
     Insets labelInsets = new Insets(6,0,0,0);
     public JLabel getLabelLivreEmpruntData() {
		return labelLivreEmpruntData;
	}
	public void setLabelLivreEmpruntData(JLabel labelLivreEmpruntData) {
		this.labelLivreEmpruntData = labelLivreEmpruntData;
	}
	Border selectBorder = BorderFactory.createLineBorder(orange, 4);
     
     ModeleLivreTable tableauModel;
     Livre[] data;
     Connection connexion;


	public PanelGestionLivre(Connection parConnexion) throws SQLException, IOException {
    	 GridBagConstraints gridBagConstraints;
    	 
    	 connexion = parConnexion;
         jTextField2 = new JTextField();
         panelGestionEtudiant = new JPanel();
         panelHead = new JPanel();
         panelLogo = new JPanel();
         jLabel5 = new JLabel(imageLogo);
         labelTitre = new JLabel();
         labelFill1 = new JPanel();
         panelDroit = new JPanel();
         panelBouton1 = new JPanel();
         labelSupImg = new JLabel(imageSupprimer);
         labelSup = new JLabel();
         panelBouton2 = new JPanel();
         labelModImg = new JLabel(imageModifier);
         labelMod = new JLabel();
         panelBouton3 = new JPanel();
         labelAjImg = new JLabel(imageAjouter);
         labelAj = new JLabel();
         panelTable = new JPanel();
         panelTable2 = new JPanel();
         panelRecherche = new JPanel();
         panelChoix = new JPanel();
         labelChoix = new JLabel();
         comboChoix = new JComboBox<>();
         panelCherche = new JPanel();
         imageCherche = new JLabel(imageRecherche);
         champCherche = new JTextField();
         jScrollPane1 = new JScrollPane();
         panelBouton = new JPanel();
         panelDroit2 = new JPanel();
         panelTitre = new JPanel();
         panelDesc = new JPanel();
         labelLivreTitreData = new JLabel();
         labelLivreAuteur = new JLabel();
         labelLivreExemplaire = new JLabel();
         labelLivreDisponible = new JLabel();
         labelLivreReservation = new JLabel();
         labelLivreEmprunt = new JLabel();
         labelLivreAuteurData = new JLabel();
         labelLivreExemplaireData = new JLabel();
         labelLivreDisponibleData = new JLabel();
         labelLivreReservationData = new JLabel();
         labelLivreEmpruntData = new JLabel();
         panelPretBouton = new JPanel();
         panelRendreBouton = new JPanel();
         boutonReservation = new JButton();
         panelDesc1 = new JPanel();
         panelDesc2 = new JPanel();
         panelDesc3 = new JPanel();
         labelEmpruntImg = new JLabel(imageEmprunt);
         labelRetourImg = new JLabel(imageRetour);
         labelEmprunt = new JLabel();
         labelRetour = new JLabel();

         this.setLayout(new BorderLayout());
         this.add(panelHead, BorderLayout.NORTH);
         this.add(panelDroit, BorderLayout.EAST);
         this.add(panelTable, BorderLayout.CENTER);
         
         //panelHead
         panelHead.setBackground(blanc);
         panelHead.setLayout(new BoxLayout(panelHead, BoxLayout.LINE_AXIS));

         panelLogo.setBackground(blanc);
         panelLogo.setPreferredSize(new Dimension(150, 100));
         panelLogo.setLayout(new GridBagLayout());

         panelLogo.add(jLabel5, new GridBagConstraints());
         panelHead.add(panelLogo);

         labelTitre.setFont(new Font("SansSerif", 1, 28));
         labelTitre.setForeground(orange);
         labelTitre.setText("Gestion Livre");
         panelHead.add(labelTitre);

         labelFill1.setBackground(blanc);
         labelFill1.setPreferredSize(new Dimension(300, 100));
         panelHead.add(labelFill1);

         
         //panelDroit
         panelDroit.setBackground(orange);
         panelDroit.setPreferredSize(new Dimension(351, 700));
         panelDroit.setLayout(new GridBagLayout());
         
         //panelDroit/panelDroit2
         panelDroit2.setBackground(blanc);
         panelDroit2.setPreferredSize(new Dimension(272,386));
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         panelDroit.add(panelDroit2, gridBagConstraints);
         panelDroit2.setLayout(new BorderLayout());
         
         //panelDroit/panelDroit2/panelTitre
         panelTitre.setBackground(violet);
         panelTitre.setPreferredSize(new Dimension(272,47));
         panelDroit2.add(panelTitre,BorderLayout.NORTH);
         panelTitre.setLayout(new GridBagLayout());
         labelLivreTitreData.setForeground(blanc);
         labelLivreTitreData.setText("HARRY POTTER");
         panelTitre.add(labelLivreTitreData,new GridBagConstraints());
         
         
         //panelDroit/panelDroit2/panelDesc
         panelDesc.setPreferredSize(new Dimension(272,339));
         panelDroit2.add(panelDesc,BorderLayout.CENTER);
         panelDesc.setLayout(new GridBagLayout());
         
         labelLivreAuteur = new JLabel();
         labelLivreExemplaire = new JLabel();
         labelLivreDisponible = new JLabel();
         labelLivreReservation = new JLabel();
         labelLivreAuteurData = new JLabel();
         labelLivreExemplaireData = new JLabel();
         labelLivreDisponibleData = new JLabel();
         labelLivreReservationData = new JLabel();
         //panelDroit/panelDroit2/panelDesc/panelDesc1
         panelDesc1.setPreferredSize(new Dimension(272,153));
         panelDesc1.setLayout(new GridBagLayout());
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         panelDesc.add(panelDesc1, gridBagConstraints);
         
         labelLivreAuteur.setPreferredSize(new Dimension(92,23));
         labelLivreAuteur.setText("Auteur(s):");
         labelLivreAuteur.setForeground(violet);
         labelLivreAuteur.setFont(labelFont);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = labelInsets;
         panelDesc1.add(labelLivreAuteur, gridBagConstraints);
         
         labelLivreExemplaire.setPreferredSize(new Dimension(92,23));
         labelLivreExemplaire.setText("Exemplaire(s):");
         labelLivreExemplaire.setForeground(violet);
         labelLivreExemplaire.setFont(labelFont);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 1;
         gridBagConstraints.insets = labelInsets;
         panelDesc1.add(labelLivreExemplaire, gridBagConstraints);
         
         labelLivreDisponible.setPreferredSize(new Dimension(92,23));
         labelLivreDisponible.setText("Disponible:");
         labelLivreDisponible.setForeground(violet);
         labelLivreDisponible.setFont(labelFont);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 2;
         gridBagConstraints.insets = labelInsets;
         panelDesc1.add(labelLivreDisponible, gridBagConstraints);
         
         labelLivreReservation.setPreferredSize(new Dimension(92,23));
         labelLivreReservation.setText("Réservation:");
         labelLivreReservation.setForeground(violet);
         labelLivreReservation.setFont(labelFont);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 3;
         gridBagConstraints.insets = labelInsets;
         panelDesc1.add(labelLivreReservation, gridBagConstraints);
         
         labelLivreEmprunt.setPreferredSize(new Dimension(92,23));
         labelLivreEmprunt.setText("Emprunt:");
         labelLivreEmprunt.setForeground(violet);
         labelLivreEmprunt.setFont(labelFont);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 4;
         gridBagConstraints.insets = labelInsets;
         panelDesc1.add(labelLivreEmprunt, gridBagConstraints);
         
         labelLivreAuteurData.setPreferredSize(new Dimension(140,23));
         labelLivreAuteurData.setText("JK Rowling");
         labelLivreAuteurData.setForeground(orange);
         labelLivreAuteurData.setHorizontalAlignment(SwingConstants.CENTER);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = labelInsets;
         panelDesc1.add(labelLivreAuteurData, gridBagConstraints);
         
         labelLivreExemplaireData.setPreferredSize(new Dimension(140,23));
         labelLivreExemplaireData.setText("3");
         labelLivreExemplaireData.setForeground(orange);
         labelLivreExemplaireData.setHorizontalAlignment(SwingConstants.CENTER);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 1;
         gridBagConstraints.insets = labelInsets;
         panelDesc1.add(labelLivreExemplaireData, gridBagConstraints);
         
         labelLivreDisponibleData.setPreferredSize(new Dimension(140,23));
         labelLivreDisponibleData.setText("1");
         labelLivreDisponibleData.setForeground(orange);
         labelLivreDisponibleData.setHorizontalAlignment(SwingConstants.CENTER);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 2;
         gridBagConstraints.insets = labelInsets;
         panelDesc1.add(labelLivreDisponibleData, gridBagConstraints);
         
         labelLivreReservationData.setPreferredSize(new Dimension(140,23));
         labelLivreReservationData.setText("0");
         labelLivreReservationData.setForeground(orange);
         labelLivreReservationData.setHorizontalAlignment(SwingConstants.CENTER);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 3;
         gridBagConstraints.insets = labelInsets;
         panelDesc1.add(labelLivreReservationData, gridBagConstraints);
         
         labelLivreEmpruntData.setPreferredSize(new Dimension(140,23));
         labelLivreEmpruntData.setText("0");
         labelLivreEmpruntData.setForeground(orange);
         labelLivreEmpruntData.setHorizontalAlignment(SwingConstants.CENTER);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 4;
         gridBagConstraints.insets = labelInsets;
         panelDesc1.add(labelLivreEmpruntData, gridBagConstraints);
         
	     //panelDroit/panelDroit2/panelDesc/panelDesc2
         panelDesc2.setPreferredSize(new Dimension(272,73));
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 1;
         panelDesc.add(panelDesc2, gridBagConstraints);
         panelDesc2.setLayout(new GridBagLayout());
         
         boutonReservation.setPreferredSize(new Dimension(155,32));
         boutonReservation.setBackground(violet);
         boutonReservation.setForeground(blanc);
         boutonReservation.setText("Voir Réservation(s)");
         panelDesc2.add(boutonReservation,new GridBagConstraints());
         
	     //panelDroit/panelDroit2/panelDesc/panelDesc3
         panelDesc3.setPreferredSize(new Dimension(272,113));
         panelDesc3.setLayout(new GridBagLayout());
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 2;
         panelDesc.add(panelDesc3, gridBagConstraints);
         
         panelPretBouton.setPreferredSize(new Dimension(90,90));
         panelPretBouton.setBackground(violet);
         panelPretBouton.setLayout(new BorderLayout());
         panelPretBouton.setBorder(BorderFactory.createLineBorder(blanc, 5));
         //panelPretBouton.setBorder(selectBorder);
         labelEmprunt.setPreferredSize(new Dimension(90,26));
         labelEmprunt.setText("Prêter");
         labelEmprunt.setForeground(blanc);
         labelEmprunt.setFont(labelFont2);
         labelEmprunt.setHorizontalAlignment(SwingConstants.CENTER);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = new Insets(0,0,0,32);
         panelDesc3.add(panelPretBouton, gridBagConstraints);
         panelPretBouton.add(labelEmpruntImg,BorderLayout.CENTER);
         panelPretBouton.add(labelEmprunt,BorderLayout.SOUTH);
         
         panelRendreBouton.setPreferredSize(new Dimension(90,90));
         panelRendreBouton.setBackground(violet);
         panelRendreBouton.setBorder(BorderFactory.createLineBorder(blanc, 5));
         panelRendreBouton.setLayout(new BorderLayout());
         //panelRendreBouton.setBorder(selectBorder);
         labelRetour.setPreferredSize(new Dimension(90,26));
         labelRetour.setText("Rendre");
         labelRetour.setForeground(blanc);
         labelRetour.setFont(labelFont2);
         labelRetour.setHorizontalAlignment(SwingConstants.CENTER);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         panelDesc3.add(panelRendreBouton, gridBagConstraints);
         panelRendreBouton.add(labelRetourImg,BorderLayout.CENTER);
         panelRendreBouton.add(labelRetour,BorderLayout.SOUTH);
         
         panelDroit.setVisible(false);
         
         
         //panelTable
         panelTable.setBackground(new Color(242, 159, 5));
         panelTable.setLayout(new GridBagLayout());
         
         //panelTable/panelBouton
         
         panelBouton.setPreferredSize(new Dimension(390, 38));
         panelBouton.setLayout(new GridBagLayout());
         panelBouton.setBackground(orange);
         
         //panelTable/panelBouton/panelBouton1
         panelBouton1.setBackground(violet2);
         panelBouton1.setPreferredSize(new Dimension(112, 38));
         panelBouton1.setLayout(new GridBagLayout());
         labelSupImg.setForeground(blanc);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelBouton1.add(labelSupImg, gridBagConstraints);
         labelSup.setForeground(blanc);
         labelSup.setText("Supprimer");
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         panelBouton1.add(labelSup, gridBagConstraints);
         
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = new Insets(0,0,0,27);
         panelBouton.add(panelBouton1,gridBagConstraints);
         
         //panelTable/panelBouton/panelBouton2
         panelBouton2.setBackground(violet2);
         panelBouton2.setPreferredSize(new Dimension(112, 38));
         panelBouton2.setLayout(new GridBagLayout());
         labelModImg.setForeground(new Color(255, 255, 255));
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelBouton2.add(labelModImg, gridBagConstraints);
         labelMod.setForeground(blanc);
         labelMod.setText("Modifier");
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         panelBouton2.add(labelMod, gridBagConstraints);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = new Insets(0,0,0,27);
         panelBouton.add(panelBouton2,gridBagConstraints);
         
         //panelTable/panelBouton/panelBouton3
         panelBouton3.setBackground(violet);
         panelBouton3.setPreferredSize(new Dimension(112, 38));
         panelBouton3.setLayout(new GridBagLayout());
         labelAjImg.setForeground(blanc);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelBouton3.add(labelAjImg, gridBagConstraints);
         labelAj.setForeground(blanc);
         labelAj.setText("Ajouter");
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         panelBouton3.add(labelAj, gridBagConstraints);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 2;
         gridBagConstraints.gridy = 0;
         panelBouton.add(panelBouton3, gridBagConstraints);
         
         //panelTable/panelTable2
         
         panelTable2.setPreferredSize(new Dimension(390, 580));
         panelTable2.setLayout(new BorderLayout());

         panelRecherche.setBackground(orange);
         panelRecherche.setPreferredSize(new Dimension(560, 50));
         panelRecherche.setLayout(new GridBagLayout());

         panelChoix.setBackground(violet);
         panelChoix.setPreferredSize(new Dimension(195, 50));
         panelChoix.setLayout(new GridBagLayout());

         labelChoix.setForeground(blanc);
         labelChoix.setText("Chercher par:");
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelChoix.add(labelChoix, gridBagConstraints);

         comboChoix.setModel(new DefaultComboBoxModel<>(new String[] { "ID_LIVRE", "AUTEUR", "TITRE"}));
         panelChoix.add(comboChoix, new GridBagConstraints());

         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         panelRecherche.add(panelChoix, gridBagConstraints);

         panelCherche.setBackground(violet);
         panelCherche.setPreferredSize(new Dimension(195, 50));
         panelCherche.setLayout(new GridBagLayout());

         imageCherche.setForeground(blanc);
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelCherche.add(imageCherche, gridBagConstraints);

         champCherche.setPreferredSize(new Dimension(120, 20));
         panelCherche.add(champCherche, new GridBagConstraints());

         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         panelRecherche.add(panelCherche, gridBagConstraints);



         panelTable2.add(panelRecherche, BorderLayout.NORTH);
         //tableau
 		tableau = new JTable();
 		tableau.setSelectionBackground(violet);
 		tableau.setSelectionForeground(blanc);
 		tableau.getTableHeader().setReorderingAllowed(false);
 		data = RequeteSQL.getBooks(connexion,"");
 		tableauModel = new ModeleLivreTable(data);
		JScrollPane scroll = new JScrollPane(tableau,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(390,529));
		tableau.setModel(tableauModel);
		tableau.getColumnModel().getColumn(0).setPreferredWidth(30);
	    tableau.getColumnModel().getColumn(1).setPreferredWidth(190);
	    tableau.getColumnModel().getColumn(2).setPreferredWidth(170);
	    panelTable2.add(scroll, BorderLayout.CENTER);
	    
	    
	    gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0,0,18,0);
	    panelTable.add(panelBouton,gridBagConstraints);
	    gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
	    panelTable.add(panelTable2, gridBagConstraints);
     }
	public JPanel getPanelPretBouton() {
		return panelPretBouton;
	}
	public void setPanelPretBouton(JPanel panelPretBouton) {
		this.panelPretBouton = panelPretBouton;
	}
	public JPanel getPanelRendreBouton() {
		return panelRendreBouton;
	}
	public void setPanelRendreBouton(JPanel panelRendreBouton) {
		this.panelRendreBouton = panelRendreBouton;
	}
	public JLabel getLabelRetour() {
		return labelRetour;
	}
	public void setLabelRetour(JLabel labelRetour) {
		this.labelRetour = labelRetour;
	}
	public JLabel getLabelEmprunt() {
		return labelEmprunt;
	}
	public void setLabelEmprunt(JLabel labelEmprunt) {
		this.labelEmprunt = labelEmprunt;
	}
	public JLabel getLabelLivreAuteurData() {
		return labelLivreAuteurData;
	}
	public void setLabelLivreAuteurData(JLabel labelLivreAuteurData) {
		this.labelLivreAuteurData = labelLivreAuteurData;
	}
	public JLabel getLabelLivreTitreData() {
		return labelLivreTitreData;
	}
	public void setLabelLivreTitreData(JLabel labelLivreTitreData) {
		this.labelLivreTitreData = labelLivreTitreData;
	}
	public JLabel getLabelLivreExemplaireData() {
		return labelLivreExemplaireData;
	}
	public void setLabelLivreExemplaireData(JLabel labelLivreExemplaireData) {
		this.labelLivreExemplaireData = labelLivreExemplaireData;
	}
	public JLabel getLabelLivreDisponibleData() {
		return labelLivreDisponibleData;
	}
	public void setLabelLivreDisponibleData(JLabel labelLivreDisponibleData) {
		this.labelLivreDisponibleData = labelLivreDisponibleData;
	}
	public JLabel getLabelLivreReservationData() {
		return labelLivreReservationData;
	}
	public void setLabelLivreReservationData(JLabel labelLivreReservationData) {
		this.labelLivreReservationData = labelLivreReservationData;
	}
	public JPanel getPanelDroit() {
		return panelDroit;
	}
	public void setPanelDroit(JPanel panelDroit) {
		this.panelDroit = panelDroit;
	}
	public void setData(Livre[] dataLivre) {
		tableauModel =new ModeleLivreTable(dataLivre);
		tableau.setModel(tableauModel);
		tableau.getColumnModel().getColumn(0).setPreferredWidth(30);
	    tableau.getColumnModel().getColumn(1).setPreferredWidth(190);
	    tableau.getColumnModel().getColumn(2).setPreferredWidth(170);
	}
	public Livre[] getData() {
		return data;
	}
	
	public JTable getTableau() {
		return tableau;
	}
	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}
	public void enregistreEcouteur(Controleur parControleur) {
		comboChoix.addActionListener(parControleur);
		panelBouton1.addMouseListener(parControleur);
		panelBouton2.addMouseListener(parControleur);
		panelBouton3.addMouseListener(parControleur);
		champCherche.addKeyListener(parControleur);
		tableau.getSelectionModel().addListSelectionListener(parControleur);
		boutonReservation.addActionListener(parControleur);
		panelRendreBouton.addMouseListener(parControleur);
		panelPretBouton.addMouseListener(parControleur);
	}
	public JButton getBoutonReservation() {
		return boutonReservation;
	}
	public void setBoutonReservation(JButton boutonReservation) {
		this.boutonReservation = boutonReservation;
	}
	public JTextField getChampCherche() {
		return champCherche;
	}
	public void setChampCherche(JTextField champCherche) {
		this.champCherche = champCherche;
	}
	public JComboBox<String> getComboChoix() {
		return comboChoix;
	}
	public void setComboChoix(JComboBox<String> comboChoix) {
		this.comboChoix = comboChoix;
	}
	public JPanel getPanelBouton1() {
		return panelBouton1;
	}
	public void setPanelBouton1(JPanel panelBouton1) {
		this.panelBouton1 = panelBouton1;
	}
	public JPanel getPanelBouton2() {
		return panelBouton2;
	}
	public void setPanelBouton2(JPanel panelBouton2) {
		this.panelBouton2 = panelBouton2;
	}
	public JPanel getPanelBouton3() {
		return panelBouton3;
	}
	public void setPanelBouton3(JPanel panelBouton3) {
		this.panelBouton3 = panelBouton3;
	}
}

