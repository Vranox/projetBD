package modele;

import java.io.*;
import java.sql.*;

/**
 * Cette classe permet la connexion à différentes BD depuis différents endroits.
 * @author pvallee
 *
 */
public class ConnexionBD {

	/**
	 * Cette fonction permet de se connecter à la base de données de l'IUT depuis les postes de l'IUT.
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static Connection ConnectFromIUT() throws SQLException, IOException{
		Connection connexion = null;
		try{ 
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			connexion = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:info","dstengel","azerty");
			}    
		catch (ClassNotFoundException e){ 
			System.out.println ("Echec dans le chargement de driver d'oracle"); 
			e.printStackTrace (); 
			}// Connection à la base de données
		System.out.println ("Connexion à la base de données réussie....");
		return connexion;
		}
	}
	