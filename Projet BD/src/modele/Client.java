package modele;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {
	static public void main(String args[]) throws SQLException, IOException{
		//chargement du driver
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

	    // Connexion
	    Connection AcceBD =
	      DriverManager.getConnection ("jdbc:oracle:thin:@madere:1521:info",
					   "dstengel", "azerty");
	    
	    
	    // Traitement

	    Statement stmt = AcceBD.createStatement ();
		ResultSet rset = stmt.executeQuery("Select * from ETUDIANT");
		while (rset.next())
		{ 
			System.out.println ("le mail est"+rset.getString("email")+"le mdp est :" + rset.getString("mdp")); 
		}
		AcceBD.close();
		stmt.close();
		rset.close();
	}
	 
}
