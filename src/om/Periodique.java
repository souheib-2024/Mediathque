package om;

import java.io.Serializable;

public class Periodique extends Document implements Serializable{

	private String frequence;
	private int page;
	
	public Periodique(String titre,int page, String frequence) {
		super(titre);
		this.page=page;
		this.frequence=frequence;
		
	}
	public String toString() {
		return "Le titre est ="+this.getTitre()+
				"\nLa page ="+this.page+
				"\nLa frequence est = "+this.frequence;
		
	}
	public String getFrequence() {
		return frequence;
	}
	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	

}
