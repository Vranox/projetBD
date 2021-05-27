package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import modele.ConnexionBD;
import modele.Livre;
import modele.ModeleLivreTable;
import modele.RequeteSQL;
/**
 * Ce panel permet aux administrateurs de rechercher des livres, de voir leur disponibilités et de prêter / récupérer un livre
 * @author superpaupaul
 *
 */
public class PanelRecherche extends JPanel{

	JTextArea zoneRecherche = new JTextArea(200,500);
	JButton btnRecherche = new JButton("Go");
	JTable tableau;
	String[] intitulesTab = {"ID_LIVRE","AUTEUR","LIVRE"};
	JTextArea searchBar = new JTextArea("Rechercher");
	Livre currentBook;
	String[] exemplairesLivre;
	JComboBox comboBox;
	JComboBox filtresBox = new JComboBox(new String[]{"ID_LIVRE","Titre","Auteur"});
	JLabel titreLivre ;
	JLabel isEmprunte;
	JLabel isReserve;
	JButton pretBtn;
	ModeleLivreTable tableauModel;
	Connection connexion;
	JScrollPane scroll;
	
	public PanelRecherche(Connection connexion) throws SQLException, IOException {
		setLayout(new BorderLayout());
		
		this.connexion = connexion;
		tableau = new JTable();
		
		Livre[] data;
		int counter = 0;
		JPanel panelGauche = new JPanel(new GridBagLayout());
		JPanel panelDroite = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		
		
		/**
		 * Panel Gauche
		 */
		
		
		data = RequeteSQL.getBooks(connexion,"");
		
		initTable(data);
		
		
		
		
		
		
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0; c.gridy = 0;
		panelGauche.add(filtresBox,c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1; c.gridy = 0;
		//searchBar.setPreferredSize(new Dimension(200,30));
		panelGauche.add(searchBar,c);
		
		c.gridx = 2; c.gridy = 0;
		panelGauche.add(btnRecherche,c);
		
		c.gridx = 0; c.gridy = 1;
		c.gridwidth = 3;
		scroll.setPreferredSize(new Dimension(370,700));
		panelGauche.add(scroll,c);
		
		
		add(panelGauche,BorderLayout.WEST);
		
		
		
		/**
		 * 	PANEL DROIT
		 */
		
		currentBook = data[0];
		
		titreLivre = new JLabel(currentBook.getTitre() + " de " + currentBook.getAuteur() + System.lineSeparator());
		
		
		
		exemplairesLivre = RequeteSQL.getId_Exemplaires(connexion,currentBook);
		
		isEmprunte = new JLabel("Exemplaire non emprunté");
		isReserve = new JLabel("Livre non réservé");
		pretBtn = new JButton("Prêter");
		comboBox = new JComboBox(exemplairesLivre);
		
		c = new GridBagConstraints();
		
		c.gridx = 0; c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		panelDroite.add(titreLivre,c);
		
		
		c.gridx = 0; c.gridy = 1;
		panelDroite.add(new JLabel("Exemplaire(s) : "),c);
		
		c.gridx = 1; c.gridy = 1;
		panelDroite.add(comboBox,c);
		
		c.gridx = 0; c.gridy = 2;
		panelDroite.add(isReserve,c);
		
		c.gridx = 0; c.gridy = 3;
		panelDroite.add(isEmprunte,c);
		
		c.gridx = 0; c.gridy = 4;
		panelDroite.add(pretBtn,c);
		
		add(panelDroite,BorderLayout.EAST);
		
	}
	
	public void enregistreEcouteur(Controleur ctrl) {
		btnRecherche.addActionListener(ctrl);
	}
	
	public JButton getBtnRecherche() {
		return btnRecherche;
	}

	/**
	 * Permet de rechercher des livres dans la base de données en fonction du filtre et de l'entrée
	 */
	public void search() {
		String options = "WHERE " + ((String) filtresBox.getSelectedItem()).toUpperCase() + " LIKE '%" + searchBar.getText() + "'%";
		Livre[] res = RequeteSQL.getBooks(connexion,options);
		initTable(res);
	}
	
	

	/**
	 * Permet d'initialiser la table avec une liste de livres en entrée
	 * @param data
	 */
	public void initTable(Livre[] data) {
		tableauModel = new ModeleLivreTable(data);
		tableau.getTableHeader().setResizingAllowed(true);
		tableau.getTableHeader().setReorderingAllowed(false);
		tableau.setRowHeight(20);
		tableau.setSize(475,250);
		tableau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scroll = new JScrollPane(tableau,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//tableau.setDefaultRenderer(Integer.class,new CelluleRenderer());

		tableau.setModel(tableauModel);
		tableau.getColumnModel().getColumn(1).setPreferredWidth(127);
		tableau.getColumnModel().getColumn(2).setPreferredWidth(150);
	}
	
	public void checkEmprunt(int id_ex) {
		
	}

	public JTextArea getSearchBar() {
		return searchBar;
	}


	public void setSearchBar(JTextArea searchBar) {
		this.searchBar = searchBar;
	}
}