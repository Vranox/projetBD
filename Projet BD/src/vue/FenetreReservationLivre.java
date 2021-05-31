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

import modele.Couleur;
import modele.ModeleLivreTable;
import modele.ModeleResTable;
import modele.RequeteSQL;
import modele.Reservation;

public class FenetreReservationLivre extends JDialog{
	Connection connexion;
	JPanel panelMain;
	JPanel panelTableaux;
	JPanel panelBouton;
	JPanel panelTableauRes;
	JPanel panelTableauEmp;
	JPanel panelHeadRes;
	JPanel panelHeadEmp;
	JPanel panelRechercheRes;
	JPanel panelChoixRes;
	JPanel panelChercheRes;
	JLabel imageChercheRes;
	JLabel labelChoixRes;
	JTextField champChercheRes;
	JTable tableauRes;
	Reservation[] dataRes;
	ModeleResTable tableauModelRes;
	JButton boutonFermer;
	JComboBox<String> comboChoixRes;
	Color orange = Couleur.getOrange();
	Color violet = Couleur.getViolet();
	Color violet2 = Couleur.getViolet2();
	Color blanc = Couleur.getBlanc();
	Color bleu = Couleur.getBleu();
	Color rouge = Couleur.getRouge();
	Color vert = Couleur.getVert();
	
	public FenetreReservationLivre(Connection parConnexion,String id_livre) {
		super((Window)null);
		setModal(true);
		panelMain = new JPanel();
		setContentPane(panelMain);
		setSize(860,535);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		connexion = parConnexion;
		
		panelMain.setLayout(new BorderLayout());
		panelMain.setBackground(orange);
		panelMain.setOpaque(true);
		
		//panelBouton
		panelBouton = new JPanel();
		panelBouton.setLayout(new GridBagLayout());
		panelBouton.setPreferredSize(new Dimension(860,46));
		panelBouton.setBackground(orange);
		boutonFermer = new JButton("FERMER");
		boutonFermer.setBackground(violet);
		boutonFermer.setForeground(blanc);
		panelBouton.add(boutonFermer, new GridBagConstraints());
		panelMain.add(panelBouton, BorderLayout.SOUTH);
		
		//panelTableaux
		panelTableaux = new JPanel();
		panelTableaux.setLayout(new GridBagLayout());
		panelTableaux.setPreferredSize(new Dimension(860,489));
		panelTableaux.setBackground(orange);
		panelMain.add(panelTableaux, BorderLayout.CENTER);
		
		//panelTableaux/panelTableauRes
		panelTableauRes = new JPanel();
		panelTableauRes.setPreferredSize(new Dimension(414,450));
		panelTableauRes.setBackground(rouge);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,16);
		panelTableaux.add(panelTableauRes,c);
		
		panelTableauRes.setLayout(new BorderLayout());
		
		panelRechercheRes = new JPanel();
        panelRechercheRes.setBackground(violet);
        panelRechercheRes.setPreferredSize(new Dimension(414, 50));
        panelRechercheRes.setLayout(new GridBagLayout());
        
        
        labelChoixRes = new JLabel();
        labelChoixRes.setForeground(blanc);
        labelChoixRes.setText("RESERVATION");
        

        c = new GridBagConstraints();
        panelRechercheRes.add(labelChoixRes, c);



        panelTableauRes.add(panelRechercheRes, BorderLayout.NORTH);
        //tableau
		tableauRes = new JTable();
		tableauRes.setSelectionBackground(violet);
		tableauRes.setSelectionForeground(blanc);
		tableauRes.getTableHeader().setReorderingAllowed(false);
		dataRes = RequeteSQL.getRes(connexion,id_livre);
		tableauModelRes = new ModeleResTable(dataRes);
		JScrollPane scroll = new JScrollPane(tableauRes,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(414,400));
		tableauRes.setModel(tableauModelRes);
		tableauRes.getColumnModel().getColumn(0).setPreferredWidth(30);
	    tableauRes.getColumnModel().getColumn(1).setPreferredWidth(96);
	    tableauRes.getColumnModel().getColumn(2).setPreferredWidth(96);
	    tableauRes.getColumnModel().getColumn(3).setPreferredWidth(96);
	    tableauRes.getColumnModel().getColumn(4).setPreferredWidth(96);
	    panelTableauRes.add(scroll, BorderLayout.CENTER);
		
		//panelTableaux/panelTableauEmp
		panelTableauEmp = new JPanel();
		panelTableauEmp.setPreferredSize(new Dimension(414,450));
		panelTableauEmp.setBackground(bleu);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		panelTableaux.add(panelTableauEmp,c);
	}
}
