package modele;

import java.io.*;
import java.sql.*;

/**
 * Cette classe permet la connexion � diff�rentes BD depuis diff�rents endroits.
 * @author pvallee
 *
 */
public class ConnexionBD {

	/**
	 * Cette fonction permet de se connecter � la base de donn�es de l'IUT depuis les postes de l'IUT.
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static Connection ConnectFromIUT() throws SQLException, IOException{
		try{ 
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			}    
		catch (ClassNotFoundException e){ 
			System.out.println ("Echec dans le chargement de driver d'oracle"); 
			e.printStackTrace (); 
			}// Connection � la base de donn�es
		System.out.println ("Connexion � la base de donn�es r�ussie....");
		return DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:info","dstengel","azerty");
		}
	}
	