package controleur;

import vue.*;

public class Lanceur {
	public static void main(String[] args){
		FenetreMere vue = new FenetreMere("Connection BU");
		//FenetreMere vue = new FenetreMere("Test");
		Controleur controleur = new Controleur(vue);
	}
	
}
