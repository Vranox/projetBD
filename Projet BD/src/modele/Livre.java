package modele;

public class Livre {

	private int id_livre;
	private String auteur;
	private String titre;
	
	public Livre(int id_livre, String auteur, String titre) {
		this.id_livre = id_livre;
		this.auteur = auteur;
		this.titre = titre;
	}

	public int getId_livre() {
		return id_livre;
	}

	public void setId_livre(int id_livre) {
		this.id_livre = id_livre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
}
