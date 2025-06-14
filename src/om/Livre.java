package om;

import java.io.Serializable;

public class Livre extends Document implements Serializable{

	private int page;
	private String auteur;
	private String editeur;
	
	public Livre(String titre,int page, String auteur,String editeur) {
		super(titre);
		this.page=page;
		this.auteur=auteur;
		this.editeur=editeur;
	}
	public String toString() {
		return "Le titre est :"+this.getTitre()+""
				+ "\n La page :"+this.page+
				"\n L'auteur est :"+this.auteur+
				"\n L'editeur :"+this.editeur;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getEditeur() {
		return editeur;
	}
	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

}
