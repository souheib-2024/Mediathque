package om;

import java.io.Serializable;

public class Cassette extends Document implements Serializable{

	private String auteur;
	private int duree;
	
	public Cassette(String titre, String auteur, int duree) {
		super(titre);
		this.auteur=auteur;
		this.duree=duree;
	}
	public String toString() {
		return "Le titre est :"+this.getTitre()+""+
				"\n L'auteur est :"+this.auteur+
				"\n La durer :"+this.duree;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
}
