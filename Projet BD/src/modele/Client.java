package modele;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {
	static public void main(String args[]) throws SQLException, IOException{
		Connection AcceBD = ConnexionBD.ConnectFromIUT("pvallee","azerty");
		Statement stmt = AcceBD.createStatement();
		ResultSet rset = stmt.executeQuery("Select * from EMP");
		while (rset.next())
		{ 
			System.out.println ("le nom de la table est :" + rset.getString("EMPNO")); 
		}
		AcceBD.close();
		stmt.close();
		rset.close();
	}
	 
}
