package vue;
import java.awt.Color;

import javax.swing.* ;
public class FenetreMere extends JFrame {

	PanelConnexion panelConnexion;
	public FenetreMere(String parTitre){
		super(parTitre);
		panelConnexion = new PanelConnexion();
		setContentPane(panelConnexion);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		
		setVisible(true);
		
		
		setLocation(80,50);
		
		setBackground (Color.BLUE);
	}
	public static void main(String[] args){
		// gerer la cr�ation du modele
		FenetreMere vue = new FenetreMere("bibliotheque");
		// gerer la cr�ation du controleur
	}
}
