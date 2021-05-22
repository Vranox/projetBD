package vue;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.*;

import controleur.Controleur;
import modele.HintPasswordField;
import modele.HintTextField;

public class PanelConnexion extends JPanel{
	//Panel
	JPanel panelDeco;
	JPanel panelDeco2;
	JPanel panelLogin;
	JPanel panelLogin2;
	//Element Panel Deco
	ImageIcon imageIut = new ImageIcon("images/IUT.jpg");
	ImageIcon iconeLogo = new ImageIcon("images/logo.png");
	JLabel LabelIUT;
	JLabel LabelLogo;
	JLabel nomTxt;
	JLabel LabelFill3;
	JLabel LabelFill4;
	//Element Panel Login2
	ImageIcon iconeUser = new ImageIcon("images/user.png");
	ImageIcon iconeCadenas = new ImageIcon("images/cadenas.png");
	JLabel LabelUser;
	JLabel LabelCadenas;
	JTextField emailTxt;
	JPasswordField pwdTxt;
	JSeparator emailSep;
	JSeparator pwdSep;
	JButton loginBtn;
	JLabel LabelFill;
	JLabel LabelFill2;
	JTextField errorTxt;
	//Couleur
	Color orange  = new Color(242,159,5);
	Color violet = new Color(64,2,53);
	Color blanc = new Color(255,255,255);
	Color bleu = new Color(4,57,217);
	//Font
	Font serif = new Font("Courier", Font.PLAIN, 14);
	
	/**
	 * Constructeur du panel gérant l'IHM login
	 */
	public PanelConnexion(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Panel Deco
		
		panelDeco = new JPanel();
		panelDeco.setBackground(violet);
		panelDeco.setPreferredSize(new Dimension(294,400));
		GridBagConstraints gbc = makeGbc(0,0,2,1);
		add(panelDeco,gbc);
		panelDeco.setLayout(new GridLayout(2,1));
		LabelIUT = new JLabel(imageIut);
		panelDeco.add(LabelIUT);
		
		//Panel Deco2
		panelDeco2 = new JPanel();
		panelDeco2.setLayout(new GridBagLayout());
		panelDeco2.setBackground(violet);
		panelDeco.add(panelDeco2);
		LabelLogo = new JLabel(iconeLogo);
		nomTxt = new JLabel("         Bibliothèque Universitaire UVSQ");
		nomTxt.setForeground(orange);
		LabelFill3 = new JLabel();
		LabelFill4 = new JLabel();
		panelDeco2.add(LabelLogo,makeGbc(1,0,1,1));
		panelDeco2.add(nomTxt,makeGbc(1,1,1,1));
		panelDeco2.add(LabelFill3, makeGbc(0,2,1,1));
		panelDeco2.add(LabelFill4,makeGbc(2,3,1,1));
		
		
		//Panel Login
		panelLogin = new JPanel();
		panelLogin.setBackground(orange);
		panelLogin.setPreferredSize(new Dimension(406, 400));
		gbc = makeGbc(2,0,3,1);
		add(panelLogin,gbc);;
		panelLogin.setLayout(new GridBagLayout());
		//Panel Login2
		panelLogin2 = new JPanel();
		panelLogin2.setBackground(orange);
		panelLogin2.setPreferredSize(new Dimension(315,207));
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,10,10,10);
		panelLogin.add(panelLogin2, c);
		panelLogin2.setLayout(new GridBagLayout());
		
		
		LabelUser = new JLabel(iconeUser);
		LabelCadenas = new JLabel(iconeCadenas);
		emailTxt = new HintTextField("Email");
		emailTxt.setBackground(orange);
		emailTxt.setForeground(blanc);
		emailTxt.setBorder(null);
		emailSep = new JSeparator();
		emailSep.setOrientation(SwingConstants.HORIZONTAL);
		emailSep.setForeground(blanc);
		pwdTxt = new HintPasswordField("Password");
		pwdTxt.setBackground(orange);
		pwdTxt.setForeground(blanc);
		pwdTxt.setBorder(null);
		pwdSep = new JSeparator();
		pwdSep.setOrientation(SwingConstants.HORIZONTAL);
		pwdSep.setForeground(blanc);
		loginBtn = new JButton("SE CONNECTER");
		loginBtn.setBackground(orange);
		loginBtn.setForeground(blanc);
		loginBtn.setBorder(BorderFactory.createLineBorder(blanc, 3));
		loginBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				loginBtn.setBackground(violet);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loginBtn.setBackground(orange);
			}
			
		});
		LabelFill = new JLabel("texte d'erreur" );
		LabelFill.setForeground(bleu);
		LabelFill.setFont(serif);
		LabelFill.setPreferredSize(new Dimension(40,40));
		LabelFill2 = new JLabel();
		LabelFill2.setPreferredSize(new Dimension(30,30));
		panelLogin2.add(LabelUser, makeGbc(0,0,1,1));
		panelLogin2.add(emailTxt,makeGbc(1,0,3));
		panelLogin2.add(emailSep,makeGbc(1,1,3));
		panelLogin2.add(LabelCadenas,makeGbc(0,2,1,1));
		panelLogin2.add(pwdTxt,makeGbc(1,2,3));
		panelLogin2.add(pwdSep,makeGbc(1,3,3));
		gbc = makeGbc(2,4,1,1);
		gbc.gridwidth = 2;
		panelLogin2.add(LabelFill,gbc);
		panelLogin2.add(LabelFill2,makeGbc(3,4,1,1));
		panelLogin2.add(loginBtn,makeGbc(2,5,1,1));
		
		
		
	}
	
	public void enregistreEcouteur(Controleur parControleur) {
		loginBtn.addActionListener(parControleur);
	}
	
	private GridBagConstraints makeGbc(int x, int y, int fillx, int filly) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = fillx;
        gbc.weighty = filly;
        return gbc;
    }
	private GridBagConstraints makeGbc(int x, int y, int fillx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = fillx;
        return gbc;
    }
	public JButton getLoginBtn() {
		return loginBtn;
	}
	public void setLoginBtn(JButton loginBtn) {
		this.loginBtn = loginBtn;
	}
	public JLabel getLabelFill() {
		return LabelFill;
	}
	public void setLabelFill(JLabel labelFill) {
		LabelFill = labelFill;
	}

	public JTextField getEmailTxt() {
		return emailTxt;
	}

	public void setEmailTxt(JTextField emailTxt) {
		this.emailTxt = emailTxt;
	}

	public JPasswordField getPwdTxt() {
		return pwdTxt;
	}

	public void setPwdTxt(JPasswordField pwdTxt) {
		this.pwdTxt = pwdTxt;
	}

	public JTextField getErrorTxt() {
		return errorTxt;
	}

	public void setErrorTxt(JTextField errorTxt) {
		this.errorTxt = errorTxt;
	}
	
}
