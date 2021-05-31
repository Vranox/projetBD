package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.*;

import controleur.Controleur;
import modele.Couleur;

public class FenetreWarningLivre extends JDialog{
	JPanel panelWarningLivre;
	Color orange = Couleur.getOrange();
	Color violet = Couleur.getViolet();
	Color blanc = Couleur.getBlanc();
	Color bleu = Couleur.getBleu();
	Color rouge = Couleur.getRouge();
	JButton boutonNon;
    JButton boutonOui;
    JPanel panelWarning;
    JLabel labelEtudiant;
    JLabel labelImageWarning;
    JLabel labelPhrase;
    ImageIcon iconeWarning = new ImageIcon("images/warning.png");
	
	public FenetreWarningLivre(String livre) {
		super((Window)null);
		setModal(true);
		panelWarningLivre = new JPanel();
		setContentPane(panelWarningLivre);
		setSize(500,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		setLayout(new GridBagLayout());
		panelWarning = new JPanel();
		panelWarning.setBackground(blanc);
		panelWarning.setOpaque(true);
		add(panelWarning,makeGbc(0,0,1));
		panelWarning.setPreferredSize(new Dimension(350, 170));
		panelWarning.setLayout(new GridBagLayout());
		labelImageWarning = new JLabel(iconeWarning);
		panelWarning.add(labelImageWarning,makeGbc(1,0,1));
        labelPhrase = new JLabel("            Etes vous sûr de vouloir supprimer :");
        labelPhrase.setHorizontalTextPosition(SwingConstants.CENTER);
        labelPhrase.setForeground(rouge);
        panelWarning.add(labelPhrase,makeGbc(0,1,3));
        labelEtudiant = new JLabel(livre+" ?");
        labelEtudiant.setForeground(orange);
        GridBagConstraints gbc = makeGbc(1,2,1);
        gbc.insets = new Insets(10, 0, 20, 0);
        panelWarning.add(labelEtudiant,gbc);
        boutonOui = new JButton("OUI");
        boutonOui.setPreferredSize(new Dimension(100, 34));
        boutonOui.setBackground(violet);
        boutonOui.setForeground(blanc);
        panelWarning.add(boutonOui,makeGbc(0,3,1));
        boutonNon = new JButton("NON");
        boutonNon.setPreferredSize(new Dimension(100, 34));
        boutonNon.setBackground(violet);
        boutonNon.setForeground(blanc);
        panelWarning.add(boutonNon,makeGbc(2,3,1));
		
		
	}
	public void enregistreEcouteur(Controleur parControleur) {
		boutonOui.addActionListener(parControleur);
		boutonNon.addActionListener(parControleur);
	}
	public JButton getBoutonNon() {
		return boutonNon;
	}
	public JButton getBoutonOui() {
		return boutonOui;
	}
	private GridBagConstraints makeGbc(int x, int y, int fillx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = fillx;
        return gbc;
    }
	
}
