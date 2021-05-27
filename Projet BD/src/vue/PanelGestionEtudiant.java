package vue;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import modele.ConnexionBD;
import modele.RequeteSQL;

public class PanelGestionEtudiant extends JPanel {
	ImageIcon imageLogo = new ImageIcon("images/log.png");
	ImageIcon imageRecherche = new ImageIcon("images/recherche.png");
	ImageIcon imageSupprimer = new ImageIcon("images/supprimer.png");
	ImageIcon imageModifier = new ImageIcon("images/modifier.png");
	ImageIcon imageAjouter = new ImageIcon("images/ajouter.png");
	 JTextField champCherche;
     JComboBox<String> comboChoix;
     JComboBox<String> comboTri;
     JLabel imageCherche;
     JLabel jLabel5;
     JScrollPane jScrollPane1;
     JTextField jTextField2;
     JLabel labelAj;
     JLabel labelAjImg;
     JLabel labelChoix;
     JPanel labelFill1;
     JLabel labelMod;
     JLabel labelModImg;
     JLabel labelSup;
     JLabel labelSupImg;
     JLabel labelTitre;
     JLabel labelTri;
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
     JPanel panelTri;
     JTable tableau;
     String[] intitulesTab = {"ID_ET","NOM","PRENOM","EMAIL","MDP"};
     Connection connexion;
    


	public PanelGestionEtudiant(Connection parConnexion) throws SQLException, IOException {
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
         panelTri = new JPanel();
         labelTri = new JLabel();
         comboTri = new JComboBox<>();
         jScrollPane1 = new JScrollPane();

         this.setLayout(new BorderLayout());
         this.add(panelHead, BorderLayout.NORTH);
         this.add(panelDroit, BorderLayout.EAST);
         this.add(panelTable, BorderLayout.CENTER);
         
         //panelHead
         panelHead.setBackground(new Color(255, 255, 255));
         panelHead.setLayout(new BoxLayout(panelHead, BoxLayout.LINE_AXIS));

         panelLogo.setBackground(new Color(255, 255, 255));
         panelLogo.setPreferredSize(new Dimension(150, 100));
         panelLogo.setLayout(new GridBagLayout());

         panelLogo.add(jLabel5, new GridBagConstraints());
         panelHead.add(panelLogo);

         labelTitre.setFont(new Font("SansSerif", 1, 28));
         labelTitre.setForeground(new Color(242, 159, 5));
         labelTitre.setText("Gestion Etudiant");
         panelHead.add(labelTitre);

         labelFill1.setBackground(new Color(255, 255, 255));
         labelFill1.setPreferredSize(new Dimension(300, 100));
         panelHead.add(labelFill1);

         
         //panelDroit
         panelDroit.setBackground(new Color(242, 159, 5));
         panelDroit.setPreferredSize(new Dimension(150, 700));
         panelDroit.setLayout(new GridBagLayout());

         panelBouton1.setBackground(new Color(64, 2, 53));
         panelBouton1.setPreferredSize(new Dimension(135, 38));
         panelBouton1.setLayout(new GridBagLayout());

         labelSupImg.setForeground(new Color(255, 255, 255));
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelBouton1.add(labelSupImg, gridBagConstraints);

         labelSup.setForeground(new Color(255, 255, 255));
         labelSup.setText("Supprimer");
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         panelBouton1.add(labelSup, gridBagConstraints);

         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = new Insets(0, 0, 180, 0);
         panelDroit.add(panelBouton1, gridBagConstraints);

         panelBouton2.setBackground(new Color(64, 2, 53));
         panelBouton2.setPreferredSize(new Dimension(135, 38));
         panelBouton2.setLayout(new GridBagLayout());

         labelModImg.setForeground(new Color(255, 255, 255));
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelBouton2.add(labelModImg, gridBagConstraints);

         labelMod.setForeground(new Color(255, 255, 255));
         labelMod.setText("Modifier");
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         panelBouton2.add(labelMod, gridBagConstraints);

         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 1;
         gridBagConstraints.insets = new Insets(0, 0, 180, 0);
         panelDroit.add(panelBouton2, gridBagConstraints);

         panelBouton3.setBackground(new Color(64, 2, 53));
         panelBouton3.setPreferredSize(new Dimension(135, 38));
         panelBouton3.setLayout(new GridBagLayout());

         labelAjImg.setForeground(new Color(255, 255, 255));
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelBouton3.add(labelAjImg, gridBagConstraints);

         labelAj.setForeground(new Color(255, 255, 255));
         labelAj.setText("Ajouter");
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         panelBouton3.add(labelAj, gridBagConstraints);

         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 2;
         panelDroit.add(panelBouton3, gridBagConstraints);

         //panelTable
         panelTable.setBackground(new Color(242, 159, 5));
         panelTable.setLayout(new GridBagLayout());

         panelTable2.setPreferredSize(new Dimension(560, 620));
         panelTable2.setLayout(new BorderLayout());

         panelRecherche.setBackground(new Color(242, 159, 5));
         panelRecherche.setPreferredSize(new Dimension(560, 50));
         panelRecherche.setLayout(new GridBagLayout());

         panelChoix.setBackground(new Color(64, 2, 53));
         panelChoix.setPreferredSize(new Dimension(160, 50));
         panelChoix.setLayout(new GridBagLayout());

         labelChoix.setForeground(new Color(255, 255, 255));
         labelChoix.setText("Chercher par:");
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelChoix.add(labelChoix, gridBagConstraints);

         comboChoix.setModel(new DefaultComboBoxModel<>(new String[] { "ID", "Nom", "Prenom", "Email" ,"mdp"}));
         panelChoix.add(comboChoix, new GridBagConstraints());

         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         panelRecherche.add(panelChoix, gridBagConstraints);

         panelCherche.setBackground(new Color(64, 2, 53));
         panelCherche.setPreferredSize(new Dimension(210, 50));
         panelCherche.setLayout(new GridBagLayout());

         imageCherche.setForeground(new Color(255, 255, 255));
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelCherche.add(imageCherche, gridBagConstraints);

         champCherche.setPreferredSize(new Dimension(120, 20));
         panelCherche.add(champCherche, new GridBagConstraints());

         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         panelRecherche.add(panelCherche, gridBagConstraints);

         panelTri.setBackground(new Color(64, 2, 53));
         panelTri.setPreferredSize(new Dimension(190, 50));
         panelTri.setLayout(new GridBagLayout());

         labelTri.setForeground(new Color(255, 255, 255));
         labelTri.setText("Tri:");
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelTri.add(labelTri, gridBagConstraints);

         comboTri.setModel(new DefaultComboBoxModel<>(new String[] { "Ordre Croissant","Ordre Décroissant"}));
         panelTri.add(comboTri, new GridBagConstraints());

         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 2;
         gridBagConstraints.gridy = 0;
         panelRecherche.add(panelTri, gridBagConstraints);
         panelTable2.add(panelRecherche, BorderLayout.NORTH);
 		tableau = new JTable();
 		DefaultTableModel tableauModel = new DefaultTableModel();
 		tableauModel.setColumnIdentifiers(intitulesTab);
 		RequeteSQL.ordreTable(connexion, tableauModel, "normal");
 		
 		tableau.setModel(tableauModel);
 		jScrollPane1.setViewportView(tableau);
	    panelTable2.add(jScrollPane1, BorderLayout.CENTER);
	
	    panelTable.add(panelTable2, new GridBagConstraints());
     }
	public void enregistreEcouteur(Controleur parControleur) {
		champCherche.addActionListener(parControleur);
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
	public JComboBox<String> getComboTri() {
		return comboTri;
	}
	public void setComboTri(JComboBox<String> comboTri) {
		this.comboTri = comboTri;
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
