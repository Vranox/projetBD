package modele;

public class Reservation {

	private String titre;
	private String id_et;
	private String nom;
	private String prenom;
	private String date_res;
	
	public Reservation(String id_et, String date_res, String titre, String nom, String prenom) {
		this.id_et = id_et;
		this.date_res = date_res;
		this.titre = titre;
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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

	public String getDate_res() {
		return date_res;
	}

	public void setDate_res(String date_res) {
		this.date_res = date_res;
	}


}

