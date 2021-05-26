package modele;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Cette classe permet d'utiliser des fonctions java classiques pour executer des requetes SQL
 * @author superpaupaul
 *
 */
public class RequeteSQL {

	/**
	 * Permet d'obtenir le nombre de tuples dans une table donnée
	 * @param connexion
	 * @return
	 */
	public static int getNumberOfRows(Connection connexion, String table) {
		int res = 0;
		try {
			Statement stmt = connexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT COUNT(*) FROM " + table);
			
			while(rset.next()) {
				res = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Permet de renvoyer la liste des livres de la table livre avec une option where ajoutable
	 * @param connexion
	 * @return
	 */
	public static Livre[] getBooks(Connection connexion,String options) {
		Livre[] res = new Livre[getNumberOfRows(connexion,"LIVRE")];
		int index = 0;
		try {
			Statement stmt = connexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM LIVRE " + options);
			
			while(rset.next()) {
				int id_livre = rset.getInt("ID_LIVRE");
				String auteur = rset.getString("AUTEUR");
				String titre = rset.getString("TITRE");
				
				res[index] = new Livre(id_livre,auteur,titre);
				index++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Permet d'obtenir une liste de String correspondant aux exemplaires d'un livre
	 * @param connexion
	 * @param book
	 * @return
	 */
	public static String[] getId_Exemplaires(Connection connexion, Livre book) {
		String[] res = new String[getNumberOfRows(connexion,"EXEMPLAIRE")];
		int index = 0;
		try {
			Statement stmt = connexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT ID_EX FROM EXEMPLAIRE WHERE ID_LIVRE = " + book.getId_livre());
			
			while(rset.next()) {
				res[index] = String.valueOf(rset.getInt("ID_EX"));
				index++;
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		return res;
	}
}
