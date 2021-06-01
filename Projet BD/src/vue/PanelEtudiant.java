package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;

import javax.swing.*;

import controleur.Controleur;
import modele.Livre;
import modele.ModeleLivreTable;
import modele.RequeteSQL;

public class PanelEtudiant extends JPanel{

	JLabel searchLabel = new JLabel("Chercher");
	String[] intitulesTab = {"ID_LIVRE","AUTEUR","LIVRE"};
	JComboBox filtresBox = new JComboBox(intitulesTab);
	JLabel imageRecherche = new JLabel(new ImageIcon("images/recherche.png"));
	JTextField searchBar = new JTextField();
	ModeleLivreTable tableauModel;
	JScrollPane scroll;
	JTable tableau;
	String idEtudiant;
	Livre[] data;
	JButton btnReserv = new JButton("Réserver");
	JLabel labelReserv = new JLabel("Réservation");
	Connection connexion;
	
	public PanelEtudiant(Connection connexion, String idEtudiant) {
		this.idEtudiant = idEtudiant;
		this.connexion = connexion;
		setLayout(new BorderLayout());
		
		// NORD
		JPanel panelNord = new JPanel();
		panelNord.add(searchLabel);
		panelNord.add(filtresBox);
		imageRecherche.setForeground(new Color(255, 255, 255));
		panelNord.add(imageRecherche);
		searchBar.setPreferredSize(new Dimension(120, 20));
		panelNord.add(searchBar);
		
		
		add(panelNord,BorderLayout.NORTH);
		
		// OUEST
		JPanel panelOuest = new JPanel();
		tableau = new JTable();
		data = RequeteSQL.getBooks(connexion,"");
		initTable();
		scroll.setPreferredSize(new Dimension(370,700));
		tableau.setRowSelectionInterval(0,0);
		
		panelOuest.add(scroll);
		
		add(panelOuest,BorderLayout.WEST);
		
		// EST 
		JPanel panelEst = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		updateReservationPossibilities();
		
		c.gridx = 0; c.gridy = 0;
		panelEst.add(labelReserv,c);
		c.gridx = 0; c.gridy = 1;
		panelEst.add(btnReserv,c);
		
		add(panelEst,BorderLayout.EAST);
	}
	
	public void initTable() {
		tableau.getTableHeader().setResizingAllowed(true);
		tableau.getTableHeader().setReorderingAllowed(false);
		tableau.setRowHeight(20);
		tableau.setSize(475,250);
		tableau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scroll = new JScrollPane(tableau,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		tableau.setModel(new ModeleLivreTable(data));
		tableau.getColumnModel().getColumn(1).setPreferredWidth(127);
		tableau.getColumnModel().getColumn(2).setPreferredWidth(150);
		
	}
	
	public void enregistreEcouteur(Controleur ctrl) {
		btnReserv.addActionListener(ctrl);
		tableau.getSelectionModel().addListSelectionListener(ctrl);
	}
	
	public void updateReservationPossibilities() {
		int id_livre = (int) tableau.getValueAt(tableau.getSelectedRow(),0);
		int whoReserved = RequeteSQL.whoReserved(connexion, String.valueOf(id_livre));
		
		if(whoReserved == -1) {
			labelReserv.setText("Livre disponible à la réservation");
			btnReserv.setEnabled(true);
		}
		else if (whoReserved == Integer.parseInt(idEtudiant)){ // si c'est l'étudiant connecté qui a émis la réservation
			labelReserv.setText("Vous avez réservé ce livre!");
			btnReserv.setEnabled(false);
		}
		else { // si le livre est emprunté par un autre étudiant
			labelReserv.setText("Livre déja réservé");
			btnReserv.setEnabled(false);
		}
	}
}
