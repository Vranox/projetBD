package modele;
/**
 * Cette classe permet de créer des objets d'utilisateurs et ainsi les garder en memoire pour pouvoir y accéder
 * @author pvallee
 *
 */
public class Utilisateur {
	private String username;
	private String password;
	
	public Utilisateur(String username, String password){
			//TODO
		}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
