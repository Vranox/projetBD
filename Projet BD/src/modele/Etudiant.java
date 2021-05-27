package modele;

public class Etudiant {

	private String id_et;
	private String nom;
	private String prenom;
	private String email;
	private String pwd;
	
	public Etudiant(String id_et, String nom, String prenom, String email, String pwd) {
		this.id_et = id_et;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.pwd = pwd;
	}

	public String getId_et() {
		return id_et;
	}

	public void setId_et(String id_et) {
		this.id_et = id_et;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

}
