package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.sql.Connection;

import javax.swing.*;

import controleur.Controleur;
import modele.Etudiant;
import modele.ModeleEtudiantTable;
import modele.RequeteSQL;

public class FenetreRetourEtudiant extends JDialog{
	JPanel panelTable;
	JPanel panelRecherche;
	JPanel panelChoix;
	JLabel labelChoix;
	JComboBox<String> comboChoix;
	JPanel panelCherche;
	JPanel panelBouton;
	JButton boutonRetour;
	JTable tableau;
	JLabel imageCherche;
	JTextField champCherche;
	Etudiant[] data;
	ModeleEtudiantTable tableauModel;
	ImageIcon imageRecherche = new ImageIcon("images/recherche.png");
	String[] intitules = {"ID_ET","NOM","PRENOM","EMAIL","ID_EX"};
	public FenetreRetourEtudiant(Connection connexion,  String id_livre) {
		super((Window)null);
		setModal(true);
		panelTable = new JPanel();
		setContentPane(panelTable);
		setSize(560,620);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(true);
		
		comboChoix = new JComboBox<>();
		 panelRecherche = new JPanel();
		 panelChoix = new JPanel();
		 labelChoix = new JLabel();
		 panelCherche = new JPanel(); 
		 panelBouton = new JPanel();
		 boutonRetour = new JButton();
		 tableau = new JTable();
		 imageCherche = new JLabel(imageRecherche);
		 champCherche = new JTextField();
		
         panelTable.setPreferredSize(new Dimension(560, 620));
         panelTable.setLayout(new BorderLayout());

         panelRecherche.setBackground(new Color(64, 2, 53));
         panelRecherche.setPreferredSize(new Dimension(540, 50));
         panelRecherche.setLayout(new GridBagLayout());

         panelChoix.setBackground(new Color(64, 2, 53));
         panelChoix.setPreferredSize(new Dimension(160, 50));
         panelChoix.setLayout(new GridBagLayout());

         labelChoix.setForeground(new Color(255, 255, 255));
         labelChoix.setText("Chercher par:");
         GridBagConstraints gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         panelChoix.add(labelChoix, gridBagConstraints);

         comboChoix.setModel(new DefaultComboBoxModel<>(new String[] { "ETUDIANT.ID_ET", "NOM", "PRENOM", "EMAIL" ,"MDP"}));
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
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 0;
         panelCherche.add(imageCherche, gridBagConstraints);

         champCherche.setPreferredSize(new Dimension(120, 20));
         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.insets = new Insets(0, 0, 0, 10);
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         panelCherche.add(champCherche, gridBagConstraints);

         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 0;
         panelRecherche.add(panelCherche, gridBagConstraints);

         panelBouton.setBackground(new Color(64, 2, 53));
         panelBouton.setPreferredSize(new Dimension(170, 50));
         panelBouton.setLayout(new GridBagLayout());

         boutonRetour.setForeground(new Color(64, 2, 53));
         boutonRetour.setText("RETOUR LIVRE");
         boutonRetour.setPreferredSize(new Dimension(130, 20));
         boutonRetour.setBackground(new Color(255,255,255));
         gridBagConstraints = new GridBagConstraints();
         panelBouton.add(boutonRetour, gridBagConstraints);


         gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 2;
         gridBagConstraints.gridy = 0;
         panelRecherche.add(panelBouton, gridBagConstraints);
         panelTable.add(panelRecherche, BorderLayout.NORTH);
         //tableau
 		tableau = new JTable();
 		tableau.setSelectionBackground(new Color(64, 2, 53));
 		tableau.setSelectionForeground(new Color(255,255,255));
 		tableau.getTableHeader().setReorderingAllowed(false);
 		data = RequeteSQL.getEmp(connexion,id_livre);
 		tableauModel = new ModeleEtudiantTable(data);
 		tableauModel.setColumnIdentifiers(intitules);
		JScrollPane scroll = new JScrollPane(tableau,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//tableau.setDefaultRenderer(Integer.class,new CelluleRenderer());

		tableau.setModel(tableauModel);
 		
 		
 		/*DefaultTableModel tableauModel = new DefaultTableModel();
 		tableauModel.setColumnIdentifiers(intitulesTab);
 		RequeteSQL.ordreTable(connexion, tableauModel, "normal");
 		
 		tableau.setModel(tableauModel);
 		jScrollPane1.setViewportView(tableau);*/
	    panelTable.add(scroll, BorderLayout.CENTER);
	}
	public void enregistreEcouteur(Controleur parControleur) {
		champCherche.addKeyListener(parControleur);
		boutonRetour.addActionListener(parControleur);
		tableau.getSelectionModel().addListSelectionListener(parControleur);
	}
	public JComboBox<String> getComboChoix() {
		return comboChoix;
	}
	public void setComboChoix(JComboBox<String> comboChoix) {
		this.comboChoix = comboChoix;
	}
	public JButton getBoutonRetour() {
		return boutonRetour;
	}
	public void setBoutonRetour(JButton boutonEmprunt) {
		this.boutonRetour = boutonEmprunt;
	}
	public JTable getTableau() {
		return tableau;
	}
	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}
	public JTextField getChampCherche() {
		return champCherche;
	}
	public void setChampCherche(JTextField champCherche) {
		this.champCherche = champCherche;
	}
	public ModeleEtudiantTable getTableauModel() {
		return tableauModel;
	}
	public void setTableauModel(ModeleEtudiantTable tableauModel) {
		this.tableauModel = tableauModel;
	}
	public void setData(Etudiant[] dataEtudiant) {
		tableauModel =new ModeleEtudiantTable(dataEtudiant);
		tableauModel.setColumnIdentifiers(intitules);
		tableau.setModel(tableauModel);
	}
}

