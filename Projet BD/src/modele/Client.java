package modele;

import java.sql.*;
import java.io.IOException;

public class Client {
	static public void main(String args[]) throws SQLException, IOException{
		//chargement du driver
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

	    // Connexion
	    Connection AcceBD =
	      DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:info",
					   "dstengel", "azerty");
	    
	    
	    // Traitement

	    Statement stmt = AcceBD.createStatement ();
		ResultSet rset = stmt.executeQuery("Select * from ETUDIANT");
		while (rset.next())
		{ 
			System.out.println ("le mail est "+rset.getString("email")+"le mdp est: " + rset.getString("mdp")); 
		}
		AcceBD.close();
		stmt.close();
		rset.close();
	}
	 
}
