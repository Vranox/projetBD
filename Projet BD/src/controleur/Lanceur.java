package controleur;

import java.io.IOException;
import java.sql.SQLException;

import vue.*;

public class Lanceur {
	public static void main(String[] args) throws SQLException, IOException{
		FenetreMere vue = new FenetreMere("Connection BU");
		//FenetreMere vue = new FenetreMere("Test");
		Controleur controleur = new Controleur(vue);
	}
	
}
