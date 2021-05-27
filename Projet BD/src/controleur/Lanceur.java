package controleur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import modele.ConnexionBD;
import vue.*;

public class Lanceur {
	public static void main(String[] args) throws SQLException, IOException{
		Connection connexion = ConnexionBD.ConnectFromIUT();
		FenetreMere vue = new FenetreMere("Connection BU",connexion);
		//FenetreMere vue = new FenetreMere("Test");
		Controleur controleur = new Controleur(vue,connexion);
	}
	
}
