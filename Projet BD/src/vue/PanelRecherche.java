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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import modele.ConnexionBD;
import modele.Livre;
import modele.ModeleLivreTable;
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
	int lTab;
	int cTab;
	
	public PanelRecherche() {
		setLayout(new BorderLayout());
		/*setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		//c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,10,10,10);
		add(zoneRecherche,c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(btnRecherche,c);
		*/
		Connection connexion;
		tableau = new JTable();
		ModeleLivreTable tableauModel;
		Livre[] data;
		int nbLignes = 0;
		int counter = 0;
		JPanel panelGauche = new JPanel();
		JPanel panelDroite = new JPanel();
		
		/**
		 * CALCUL DE LA TAILLE DU TABLEAU
		 */
		try {
			connexion = ConnexionBD.ConnectFromIUT();
			Statement stmt = connexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT COUNT(*) FROM LIVRE");
			
			while(rset.next()) {
				nbLignes = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		data = new Livre[nbLignes];
		
		/**
		 * EXECUTION DE LA REQUETE
		 */
		try {
			connexion = ConnexionBD.ConnectFromIUT();
			Statement stmt = connexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM LIVRE");
			
			while(rset.next()) {
				int id_livre = rset.getInt("ID_LIVRE");
				String auteur = rset.getString("AUTEUR");
				String titre = rset.getString("TITRE");
				data[counter] = new Livre(id_livre,auteur,titre);
				counter++;
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
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
		//scroll.setPreferredSize(new Dimension(850,500));

		tableau.setModel(tableauModel);
		panelGauche.add(scroll,BorderLayout.WEST);
		add(panelGauche,BorderLayout.WEST);
		
		
	}
}
