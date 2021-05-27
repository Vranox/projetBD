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

	JTextArea zoneRecherche = new JTextArea();
	JButton btnRecherche = new JButton("Go");
	JTable tableau;
	String[] intitulesTab = {"ID_LIVRE","AUTEUR","LIVRE"};
	JTextArea searchBar = new JTextArea("Rechercher");
	Livre currentBook;
	String[] exemplairesLivre;
	JComboBox comboBox;
	JLabel titreLivre ;
	Connection connexion;
	
	public PanelRecherche(Connection parConnexion) throws SQLException, IOException {
		setLayout(new BorderLayout());
		
		
		connexion = parConnexion;
		tableau = new JTable();
		ModeleLivreTable tableauModel;
		Livre[] data;
		int counter = 0;
		JPanel panelGauche = new JPanel(new BorderLayout());
		JPanel panelDroite = new JPanel();
		
		
		
		
		/**
		 * Panel Gauche
		 */
		
		
		data = RequeteSQL.getBooks(connexion,"");
		
		
		
		// 	PENSER A FAIRE PASSER LA CONNEXION EN PARAMETRE POUR NE PAS AVOIR A FAIRE PLUSIEURS CONNECTIONS
		
		tableauModel = new ModeleLivreTable(data);
		tableau.getTableHeader().setResizingAllowed(true);
		tableau.getTableHeader().setReorderingAllowed(false);
		tableau.setRowHeight(20);
		tableau.setSize(475,250);
		tableau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scroll = new JScrollPane(tableau,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//tableau.setDefaultRenderer(Integer.class,new CelluleRenderer());

		tableau.setModel(tableauModel);
		
		panelGauche.add(searchBar,BorderLayout.NORTH);
		panelGauche.add(btnRecherche,BorderLayout.NORTH);
		panelGauche.add(scroll,BorderLayout.SOUTH);
		
		add(panelGauche,BorderLayout.WEST);
		
		
		
		
		
		/**
		 * 	PANEL DROIT
		 */
		
		currentBook = data[0];
		
		titreLivre = new JLabel(currentBook.getTitre() + " de " + currentBook.getAuteur() + System.lineSeparator() + "id = " + currentBook.getId_livre());
		
		
		
		exemplairesLivre = RequeteSQL.getId_Exemplaires(connexion,currentBook);
		
		
		
		comboBox = new JComboBox(exemplairesLivre);
		
		panelDroite.add(titreLivre);
		panelDroite.add(comboBox);
		
		add(panelDroite,BorderLayout.EAST);
	}
}
