package modele;

import java.awt.Color;
public class Couleur {
	static Color orange  = new Color(242,159,5);
	static Color violet = new Color(64,2,53);
	static Color violet2 = new Color(130,70,120);
	static Color blanc = new Color(255,255,255);
	static Color bleu = new Color(4,57,217);
	static Color rouge = new Color(255,0,51);
	static Color vert = new Color(0,255,102);
	public static Color getVert() {
		return vert;
	}
	public static Color getOrange() {
		return orange;
	}
	public static Color getViolet() {
		return violet;
	}
	public static Color getBlanc() {
		return blanc;
	}
	public static Color getBleu() {
		return bleu;
	}
	public static Color getRouge() {
		return rouge;
	}
	/*
	Color orange = Couleur.getOrange();
	Color violet = Couleur.getViolet();
	Color violet2 = Couleur.getViolet2();
	Color blanc = Couleur.getBlanc();
	Color bleu = Couleur.getBleu();
	Color rouge = Couleur.getRouge();
	Color vert = Couleur.getVert();
	*/
	public static Color getViolet2() {
		return violet2;
	}
	
}
