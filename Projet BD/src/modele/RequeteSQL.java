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
			panel.getLabelFill().setText("Echec de connexion � la BD");
			panel.getLabelFill().setOpaque(true);
			return false;
		}
	}
	public static Etudiant[] getEtudiants(Connection connexion,String options) {
		Etudiant[] res = new Etudiant[getNumberOfRows(connexion,"ETUDIANT"+options)];
		int index = 0;
		try {
			Statement stmt = connexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM 	 ETUDIANT" + options);
			
			while(rset.next()) {
				//System.out.println("test");
				String id_et = rset.getString("id_et");
 				String nom = rset.getString("nom");
 				String prenom = rset.getString("prenom");
 				String email = rset.getString("email");
 				String mdp = rset.getString("mdp");
 				//System.out.println("id: "+id_et+" nom: "+nom+" prenom: "+prenom+" email: "+email+" mdp: "+mdp);
				
				res[index] = new Etudiant(id_et,nom,prenom,email,mdp);
				index++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * fonction qui renvoie -1 si l'exemplaire n'est pas emprunté et l'id de l'etudiant s'il l'est pas
	 * @param connexion
	 * @param id_ex
	 * @return
	 */
	public static int whoEmprunted(Connection connexion, String id_ex) {
		int res = -1;
		try {
 			Statement stmt = connexion.createStatement();
 			ResultSet rset=stmt.executeQuery("SELECT * FROM EMPRUNT WHERE ID_EX = " + id_ex + " AND (sysDate BETWEEN DATE_EMP AND DATE_RETOUR)");
 			
 			while(rset.next()) {
 				res = rset.getInt("ID_ET");
 			}
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
		return res;
	}
	
	/**
	 * Fonction qui renvoie -1 si l'exemplaire est n'est pas reservé et l'id de l'etudiant s'il l'est
	 * @param connexion
	 * @param id_livre
	 * @return
	 */
	public static int whoReserved(Connection connexion, String id_livre) {
		int res = -1;
		try {
 			Statement stmt = connexion.createStatement();
 			ResultSet rset=stmt.executeQuery("SELECT * FROM RESERV WHERE ID_LIVRE = " + id_livre + " AND (sysDate BETWEEN DATE_RES AND DATE_FIN_RES)");
 			
 			while(rset.next()) {
 				res = rset.getInt("ID_ET");
 			}
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
		return res;
	}
}
