package vue;
import java.awt.*;

import java.io.File;

import javax.swing.*;

import controleur.Controleur;
import modele.Couleur;
import modele.HintPasswordField;
import modele.HintTextField;
/**
 * Cette classe s'affiche lorsque l'utilisateur connécté est du personnel de la bibliothèque et permet à ces utilisateurs de rechercher un livre, gérer des comptes étudiants, ajouter des livres et afficher les derniers livres empruntés
 * @author superpaupaul
 *
 */
public class PanelAdmin extends JPanel{
	Color orange = Couleur.getOrange();
	Color violet = Couleur.getViolet();
	Color violet2 = Couleur.getViolet2();
	Color blanc = Couleur.getBlanc();
	Color bleu = Couleur.getBleu();
	Color rouge = Couleur.getRouge();
	Color vert = Couleur.getVert();
	JPanel panelHead;
	JPanel panelBouton;
	JPanel panelStats;
	JLabel labelStats;
	ImageIcon imageStat = new ImageIcon("images/stat.png");
	ImageIcon imageEtudiant = new ImageIcon("images/etudiant.png");
	ImageIcon imageLivre = new ImageIcon("images/livre.png");
	ImageIcon imageRetard = new ImageIcon("images/retard.png");
	JLabel labelEtudiant;
	JLabel labelLivre;
	JLabel labelRetard;
	JLabel labelEtudiantTxt;
	JLabel labelLivreTxt;
	JLabel labelRetardTxt;
	JPanel panelBouton1;
	JPanel panelBouton2;
	JPanel panelBouton3;
	public PanelAdmin() {
		setLayout(new BorderLayout());
		setBackground(orange);
		
		panelHead = new JPanel();
		panelBouton = new JPanel();
		panelHead.setPreferredSize(new Dimension(800,74));
		panelHead.setLayout(new GridBagLayout());
		panelHead.setBackground(orange);
		panelStats = new JPanel();
		panelStats.setBackground(violet);
		panelStats.setPreferredSize(new Dimension(60,60));
		panelStats.setBorder(BorderFactory.createLineBorder(orange, 3));
		panelStats.setLayout(new GridBagLayout());
		labelStats = new JLabel(imageStat);
		panelStats.add(labelStats,new GridBagConstraints());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,0,0,7);
		gbc.anchor = GridBagConstraints.EAST ;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		panelHead.add(panelStats,gbc);
		add(panelHead,BorderLayout.NORTH);
		
		panelBouton.setLayout(new GridBagLayout());
		panelBouton.setBackground(orange);
		panelBouton1 = new JPanel();
		panelBouton2 = new JPanel();
		panelBouton3 = new JPanel();
		labelEtudiant = new JLabel(imageEtudiant);
		labelLivre = new JLabel(imageLivre);
		labelRetard = new JLabel(imageRetard);
		labelEtudiantTxt = new JLabel("GESTION ETUDIANT");
		labelEtudiantTxt.setPreferredSize(new Dimension(180,27));
		labelEtudiantTxt.setForeground(blanc);
		labelEtudiantTxt.setBackground(violet);
		labelEtudiantTxt.setHorizontalAlignment(SwingConstants.CENTER);
		labelLivreTxt = new JLabel("GESTION LIVRE");
		labelLivreTxt.setPreferredSize(new Dimension(180,27));
		labelLivreTxt.setForeground(blanc);
		labelLivreTxt.setBackground(violet);
		labelLivreTxt.setHorizontalAlignment(SwingConstants.CENTER);
		labelRetardTxt = new JLabel("RETARD");
		labelRetardTxt.setPreferredSize(new Dimension(180,27));
		labelRetardTxt.setForeground(blanc);
		labelRetardTxt.setBackground(violet);
		labelRetardTxt.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelBouton1.setBackground(violet);
		panelBouton1.setLayout(new GridBagLayout());
		panelBouton1.setPreferredSize(new Dimension(180,180));
		panelBouton1.setBorder(BorderFactory.createLineBorder(orange, 5));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,25,0);
		panelBouton1.add(labelLivre,gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelBouton1.add(labelLivreTxt,gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,37,100);
		panelBouton.add(panelBouton1,gbc);
		
		panelBouton2.setBackground(violet);
		panelBouton2.setLayout(new GridBagLayout());
		panelBouton2.setPreferredSize(new Dimension(180,180));
		panelBouton2.setBorder(BorderFactory.createLineBorder(orange, 5));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,25,0);
		panelBouton2.add(labelEtudiant,gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelBouton2.add(labelEtudiantTxt,gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,37,100);
		panelBouton.add(panelBouton2,gbc);
		
		panelBouton3.setBackground(violet);
		panelBouton3.setLayout(new GridBagLayout());
		panelBouton3.setPreferredSize(new Dimension(180,180));
		panelBouton3.setBorder(BorderFactory.createLineBorder(orange, 5));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,25,0);
		panelBouton3.add(labelRetard,gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelBouton3.add(labelRetardTxt,gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,37,0);
		panelBouton.add(panelBouton3,gbc);
		
		add(panelBouton,BorderLayout.CENTER);
		
	}
	public void enregistreEcouteur(Controleur parControleur) {
		panelBouton1.addMouseListener(parControleur);
		panelBouton2.addMouseListener(parControleur);
		panelBouton3.addMouseListener(parControleur);
		panelStats.addMouseListener(parControleur);
	}
	public JPanel getPanelStats() {
		return panelStats;
	}
	public void setPanelStats(JPanel panelStats) {
		this.panelStats = panelStats;
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
