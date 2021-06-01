package modele;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	public static int getNumberOfRows2(Connection connexion, String requete) {
		int res = 0;
		try {
			Statement stmt = connexion.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			
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
		Livre[] res = new Livre[getNumberOfRows(connexion,"LIVRE " + options)];
		int index = 0;
		try {
			Statement stmt = connexion.createStatement();
			//System.out.println("SELECT * FROM LIVRE " + options);
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
			ResultSet rset = stmt.executeQuery("SELECT * FROM ETUDIANT" + options);
			
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
	public static Etudiant[] getEmp(Connection connexion,String options) {
		Etudiant[] res = new Etudiant[getNumberOfRows2(connexion,"SELECT COUNT(*) FROM ETUDIANT,EMPRUNT,LIVRE,EXEMPLAIRE WHERE EMPRUNT.ID_ET = ETUDIANT.ID_ET AND DATE_RETOUR IS NULL AND EXEMPLAIRE.ID_LIVRE=LIVRE.ID_LIVRE AND EXEMPLAIRE.ID_EX = EMPRUNT.ID_EX AND LIVRE.ID_LIVRE="+options)];
		int index = 0;
		try {
			Statement stmt = connexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT ETUDIANT.ID_ET,NOM,PRENOM,EMAIL,EXEMPLAIRE.ID_EX FROM ETUDIANT,EMPRUNT,LIVRE,EXEMPLAIRE WHERE EMPRUNT.ID_ET = ETUDIANT.ID_ET AND DATE_RETOUR IS NULL AND EXEMPLAIRE.ID_LIVRE=LIVRE.ID_LIVRE AND EXEMPLAIRE.ID_EX = EMPRUNT.ID_EX AND LIVRE.ID_LIVRE="+options);
			
			while(rset.next()) {
				//System.out.println("test");
				String id_et = rset.getString("id_et");
 				String nom = rset.getString("nom");
 				String prenom = rset.getString("prenom");
 				String email = rset.getString("email");
 				String mdp = rset.getString("id_ex");
 				//System.out.println("id: "+id_et+" nom: "+nom+" prenom: "+prenom+" email: "+email+" mdp: "+mdp);
				
				res[index] = new Etudiant(id_et,nom,prenom,email,mdp);
				index++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static Reservation[] getRes(Connection connexion,String options) {
		Reservation[] res = new Reservation[getNumberOfRows(connexion,"RESERV,LIVRE,ETUDIANT WHERE RESERV.ID_ET = ETUDIANT.ID_ET AND LIVRE.ID_LIVRE = RESERV.ID_LIVRE AND DATE_FIN_RES IS NULL AND LIVRE.ID_LIVRE ='"+options+"'")];
		int index = 0;
		try {
			Statement stmt = connexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM 	 RESERV,LIVRE,ETUDIANT WHERE RESERV.ID_ET = ETUDIANT.ID_ET AND LIVRE.ID_LIVRE = RESERV.ID_LIVRE AND DATE_FIN_RES IS NULL AND LIVRE.ID_LIVRE ='"+options+"'");
			
			while(rset.next()) {
				String id_et = rset.getString("id_et");
 				String titre = rset.getString("titre");
 				String nom = rset.getString("nom");
 				String prenom = rset.getString("prenom");
 				String date_res = rset.getString("date_res");
				
				res[index] = new Reservation(id_et,date_res,titre,nom,prenom);
				index++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * Renvoie l'id de l'étudiant ayant réservé le livre. -1 si le livre n'est pas réservé
	 * @param connexion
	 * @param id_livre
	 * @param id_etudiant
	 * @return
	 */
	public static int whoReserved(Connection connexion, String id_livre) {
		int res = -1;
		try {
			Statement stmt = connexion.createStatement();
			String str = "SELECT * FROM RESERV WHERE ID_LIVRE = " + id_livre + " AND sysDate BETWEEN DATE_RES AND DATE_FIN_RES";
			ResultSet rset = stmt.executeQuery(str);
			System.out.println(str);
			while(rset.next()) {
				System.out.println(rset.getString("ID_LIVRE"));
				res = Integer.parseInt(rset.getString("ID_ET"));
				System.out.println("passed");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static  void deleteEtudiant(Connection connexion,String id) throws SQLException {
			Statement stmt = connexion.createStatement();
			stmt.executeUpdate("DELETE FROM ETUDIANT WHERE ID_ET = '"+id+"'" );
	}
	public static  void deleteLivre(Connection connexion,String id) throws SQLException {
		Statement stmt = connexion.createStatement();
		RequeteSQL.delAllExemplaire(connexion, id);
		stmt.executeUpdate("DELETE FROM LIVRE WHERE ID_LIVRE = '"+id+"'" );
}
	public static void addEtudiant(Connection connexion, String nom,String prenom,String email,String mdp) throws SQLException {
		Statement stmt = connexion.createStatement();
		stmt.executeUpdate("INSERT INTO ETUDIANT (nom,prenom,email,mdp) VALUES ('"+nom+"', '"+prenom+"','"+email+"','"+mdp+"')" );
	}
	public static void addLivre(Connection connexion, String titre,String auteur) throws SQLException {
		Statement stmt = connexion.createStatement();
		stmt.executeUpdate("INSERT INTO LIVRE (titre,auteur) VALUES ('"+titre+"', '"+auteur+"')" );
	}
	public static void editEtudiant(Connection connexion, String nom, String prenom, String email,String mdp, String id_et) throws SQLException {
		Statement stmt = connexion.createStatement();
		stmt.executeUpdate("UPDATE ETUDIANT SET nom = '"+nom+"', prenom = '"+prenom+"', email = '"+email+"', mdp = '"+mdp+"' WHERE id_et = "+id_et+"" );
	}
	public static void editLivre(Connection connexion, String titre, String auteur, String id_livre) throws SQLException {
		Statement stmt = connexion.createStatement();
		System.out.println("Titre: "+titre+" Auteur: "+auteur+" id_livre: "+id_livre);
		stmt.executeQuery("UPDATE LIVRE SET titre = '"+titre+"', auteur = '"+auteur+"' WHERE id_livre = "+id_livre );
	}
	public static int getExemplaire(Connection connexion, String id_livre) throws SQLException{
		int res = 0;
		try {
			Statement stmt = connexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT COUNT(*) FROM EXEMPLAIRE WHERE ID_LIVRE = "+ id_livre );
			
			while(rset.next()) {
				res = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	public static int getDisponible(Connection connexion, int nbreExemplaire, String id_livre) throws SQLException {
		int res = 0;
		int nbreEmprunt = RequeteSQL.getEmprunt(connexion, id_livre);
		res = nbreExemplaire - nbreEmprunt;
		return res;
	}
	public static int getEmprunt(Connection connexion,String id_livre) throws SQLException {
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT COUNT(*) FROM EXEMPLAIRE,EMPRUNT WHERE EMPRUNT.ID_EX=EXEMPLAIRE.ID_EX AND DATE_RETOUR IS NULL AND ID_LIVRE = '"+id_livre+"'" );
		int res = 0;
		while(rset.next()) {
				res += rset.getInt("COUNT(*)");
		}
		return res;
	}
	public static int getEmpruntEtudiant(Connection connexion,String id_et) throws SQLException {
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT * FROM EXEMPLAIRE,EMPRUNT WHERE EXEMPLAIRE.ID_EX = EMPRUNT.ID_EX AND ID_ET = '"+id_et+"'" );
		int res = 0;
		while(rset.next()) {
			String id_ex = rset.getString("id_ex");
			ResultSet rset2 = stmt.executeQuery("SELECT COUNT(*) FROM EMPRUNT WHERE ID_EX = "+ id_ex +" AND DATE_RETOUR IS NULL");
				
				while(rset2.next()) {
					res += rset2.getInt("COUNT(*)");
				}
		}
		System.out.println(res);
		return res;
	}
	public static int getReservation(Connection connexion, String id_livre) throws SQLException {
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT COUNT(*) FROM RESERV WHERE ID_LIVRE = '"+id_livre+"'" );
		int id_ex=0;
		while(rset.next()) {
			id_ex = rset.getInt("COUNT(*)");
		}
		return id_ex;
	}
	public static void delExemplaire(Connection connexion, String id_ex) {
		Statement stmt;
		try {
			stmt = connexion.createStatement();
			stmt.executeUpdate("DELETE FROM EXEMPLAIRE WHERE ID_EX = '"+id_ex+"'" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void delAllExemplaire(Connection connexion, String id_livre) throws SQLException {
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT * FROM EXEMPLAIRE WHERE ID_LIVRE = '"+id_livre+"'" );
		
		while(rset.next()) {
			String id_ex = rset.getString("id_ex");
			RequeteSQL.delExemplaire(connexion, id_ex);
		}
		
	}
	public static String getLastIdLivre(Connection connexion) throws SQLException {
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT MAX(ID_LIVRE) FROM LIVRE");
		String res = null;
		while(rset.next()) {
			res = rset.getString("MAX(ID_LIVRE)");
		}
		return res;
	}
	public static void setExemplaire(Connection connexion, String id_livre, int nombreVoulu) throws SQLException{
		int nombreActuel = RequeteSQL.getExemplaire(connexion, id_livre);
		if(nombreActuel ==nombreVoulu) {
			
		}
		else if(nombreActuel>nombreVoulu) {
			Statement stmt = connexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM EXEMPLAIRE WHERE ID_LIVRE = "+ id_livre );
			while(rset.next()&&nombreActuel!=nombreVoulu) {
				String id_ex = rset.getString("id_ex");
				RequeteSQL.delExemplaire(connexion, id_ex);
				nombreActuel-=1;
			}
		}
		else if(nombreActuel<nombreVoulu) {
			while(nombreActuel!=nombreVoulu) {
				Statement stmt = connexion.createStatement();
				stmt.executeQuery("INSERT INTO EXEMPLAIRE (ID_LIVRE) VALUES ("+id_livre+")" );
				nombreActuel+=1;
			}
		}
	}
	public static void setEmpruntEtudiant(Connection connexion, String id_et, String id_livre) throws SQLException {
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT * FROM EXEMPLAIRE WHERE ID_EX NOT IN (SELECT DISTINCT EXEMPLAIRE.ID_EX FROM EXEMPLAIRE,EMPRUNT WHERE EXEMPLAIRE.ID_EX = EMPRUNT.ID_EX AND EXEMPLAIRE.ID_LIVRE ="+id_livre+") AND ID_LIVRE="+id_livre);
		rset.next();
		String id_ex = rset.getString("id_ex");
		RequeteSQL.setEmprunt(connexion, id_et, id_ex);
	}
	public static void setEmprunt(Connection connexion, String id_et,String id_ex) throws SQLException {
		Statement stmt = connexion.createStatement();
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		String date_emp = dateFormat.format(date);
		stmt.executeQuery("INSERT INTO EMPRUNT (ID_ET,ID_EX,DATE_EMP) VALUES ("+id_et+","+id_ex+",TO_DATE('"+date_emp+"','yyyy-mm-dd hh:mi:ss'))" );
	}
	public static void finEmprunt(Connection connexion, String id_et,String id_ex) throws SQLException {
		Statement stmt = connexion.createStatement();
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		String date_emp = dateFormat.format(date);
		stmt.executeQuery("UPDATE EMPRUNT SET DATE_RETOUR = TO_DATE('"+date_emp+"','yyyy-mm-dd hh:mi:ss') WHERE ID_ET="+id_et+" AND ID_EX ="+id_ex );
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
	
}
