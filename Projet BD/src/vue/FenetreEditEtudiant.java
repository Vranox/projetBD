package vue;
import java.awt.*;

import javax.swing.*;

import controleur.Controleur;
import modele.Couleur;
import modele.HintTextField;

public class FenetreEditEtudiant extends JDialog{
	Color orange = Couleur.getOrange();
	Color violet = Couleur.getViolet();
	Color violet2 = Couleur.getViolet2();
	Color blanc = Couleur.getBlanc();
	Color bleu = Couleur.getBleu();
	Color rouge = Couleur.getRouge();
	JPanel panelEditEtudiant;
	JPanel panelHead;
	JPanel panelForm2;
	JPanel panelForm;
	ImageIcon iconeLogo = new ImageIcon("images/log.png");
	ImageIcon iconeBouton = new ImageIcon("images/boutonEdit1.png");
	ImageIcon iconeBouton2 = new ImageIcon("images/boutonEdit2.png");
	JLabel labelLogoImage;
	JLabel labelBouton;
	JLabel labelTitre;
	JTextField txtNom;
	JTextField txtPrenom;
	JTextField txtEmail;
	JTextField txtMdp;
	JSeparator nomSep;
	JSeparator prenomSep;
	JSeparator emailSep;
	JSeparator mdpSep;
	String id_et;
	Font fontTitre= new Font("Courier New", 1, 18);
	
	public FenetreEditEtudiant(String parId,String parNom,String parPrenom,String parEmail, String parMdp) {
		super((Window)null);
		setModal(true);
		panelEditEtudiant = new JPanel();
		setContentPane(panelEditEtudiant);
		setSize(350,330);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		id_et = parId;
		panelEditEtudiant.setLayout(new BorderLayout());
		panelEditEtudiant.setBackground(blanc);
		panelEditEtudiant.setOpaque(true);
		panelHead = new JPanel();
		panelHead.setPreferredSize(new Dimension(350,64));
		panelForm = new JPanel();
		panelForm.setPreferredSize(new Dimension(350,266));
		panelEditEtudiant.add(panelHead,BorderLayout.NORTH);
		panelEditEtudiant.add(panelForm,BorderLayout.CENTER);
		
		panelHead.setLayout(new GridBagLayout());
		labelLogoImage = new JLabel(iconeLogo);
		labelLogoImage.requestFocusInWindow();
		panelHead.add(labelLogoImage,makeGbc(0,0,1));
		labelTitre = new JLabel("MODIFIER UN ETUDIANT");
		labelTitre.setForeground(orange);
		labelTitre.setFont(fontTitre);
		panelHead.add(labelTitre,makeGbc(1,0,1));
		
		panelForm.setLayout(new GridBagLayout());
		panelForm2 = new JPanel();
		panelForm2.setPreferredSize(new Dimension(260,210));
		panelForm.add(panelForm2,makeGbc(0,0,1));
		panelForm2.setLayout(new GridBagLayout());
		txtNom = new JTextField(parNom);
		txtNom.setPreferredSize(new Dimension(140,25));
		txtNom.setBorder(null);
		txtNom.setForeground(orange);
		txtPrenom = new JTextField(parPrenom);
		txtPrenom.setPreferredSize(new Dimension(140,25));
		txtPrenom.setBorder(null);
		txtPrenom.setForeground(orange);
		txtEmail = new JTextField(parEmail);
		txtEmail.setPreferredSize(new Dimension(140,25));
		txtEmail.setBorder(null);
		txtEmail.setForeground(orange);
		txtMdp = new JTextField(parMdp);
		txtMdp.setPreferredSize(new Dimension(140,25));
		txtMdp.setBorder(null);
		txtMdp.setForeground(orange);
		labelBouton = new JLabel(iconeBouton);
		nomSep = new JSeparator();
		nomSep.setOrientation(SwingConstants.HORIZONTAL);
		nomSep.setForeground(orange);
		prenomSep = new JSeparator();
		prenomSep.setOrientation(SwingConstants.HORIZONTAL);
		prenomSep.setForeground(orange);
		emailSep = new JSeparator();
		emailSep.setOrientation(SwingConstants.HORIZONTAL);
		emailSep.setForeground(orange);
		mdpSep = new JSeparator();
		mdpSep.setOrientation(SwingConstants.HORIZONTAL);
		mdpSep.setForeground(orange);
		panelForm2.add(txtNom,makeGbc(0,0,1));
		GridBagConstraints gbc = makeGbc(0,2,1);
		gbc.insets = new Insets(5,0,0,0);
		panelForm2.add(txtPrenom,gbc);
		gbc = makeGbc(0,4,1);
		gbc.insets = new Insets(5,0,0,0);
		panelForm2.add(txtEmail,gbc);
		gbc = makeGbc(0,6,1);
		gbc.insets = new Insets(5,0,0,0);
		panelForm2.add(txtMdp,gbc);
		gbc = makeGbc(1,8,1);
		gbc.insets = new Insets(10,0,0,0);
		panelForm2.add(labelBouton, gbc);
		panelForm2.add(nomSep,makeGbc(0,1,2));
		panelForm2.add(prenomSep,makeGbc(0,3,2));
		panelForm2.add(emailSep,makeGbc(0,5,2));
		panelForm2.add(mdpSep,makeGbc(0,7,2));
		this.pack();
		this.getContentPane().requestFocusInWindow();
	}
	public String getId_et() {
		return id_et;
	}
	public void enregistreEcouteur(Controleur parControleur) {
		labelBouton.addMouseListener(parControleur);
	}
	public void setBoutonImage(int i) {
		if(i==1) {
			labelBouton.setIcon(iconeBouton);
		}
		else {
			labelBouton.setIcon(iconeBouton2);
		}
	}
	private GridBagConstraints makeGbc(int x, int y, int fillx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = fillx;
        return gbc;
    }
	public JLabel getLabelBouton() {
		return labelBouton;
	}
	public JTextField getTxtNom() {
		return txtNom;
	}
	public JTextField getTxtPrenom() {
		return txtPrenom;
	}
	public JTextField getTxtEmail() {
		return txtEmail;
	}
	public JTextField getTxtMdp() {
		return txtMdp;
	}
	public void setTxtNom(JTextField txtNom) {
		this.txtNom = txtNom;
	}
	public void setTxtPrenom(JTextField txtPrenom) {
		this.txtPrenom = txtPrenom;
	}
	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}
	public void setTxtMdp(JTextField txtMdp) {
		this.txtMdp = txtMdp;
	}
}

