package modele;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import vue.FenetreMere;
import vue.PanelConnexion;

/**
 * Cette classe permet d'utiliser des fonctions java classiques pour executer des requetes SQL
 * @author superpaupaul
 *
 */
public class RequeteSQL {
	
	/**
	 * Permet d'obtenir le nombre de tuples dans une table donn√©e
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
	
	public static boolean isEtudiant(Connection connexion, Utilisateur user,PanelConnexion panel) {
		try {
			Statement stmt = connexion.createStatement ();
			String sql = "SELECT * FROM ETUDIANT WHERE email = '"+user.username+"' AND mdp='"+user.password+"'";
			ResultSet rset = stmt.executeQuery(sql);
			if(rset.next()) {
				System.out.println("success");
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			panel.getLabelFill().setText("Echec de connexion ‡ la BD");
			panel.getLabelFill().setOpaque(true);
			return false;
		}
	}
	public static void ordreTable(Connection connexion,DefaultTableModel tableau,String instru) {
		try {
 			Statement stmt = connexion.createStatement();
 			ResultSet rset=stmt.executeQuery("SELECT * FROM ETUDIANT");
 			if(instru.equals("normal")){
 				rset = stmt.executeQuery("SELECT * FROM ETUDIANT");
 			}
 			
 			while(rset.next()) {
 				int id_et = rset.getInt("id_et");
 				String nom = rset.getString("nom");
 				String prenom = rset.getString("prenom");
 				String email = rset.getString("email");
 				String mdp = rset.getString("mdp");
 				tableau.addRow(new Object[] {id_et,nom,prenom,email,mdp});
 			}
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
	}
}
