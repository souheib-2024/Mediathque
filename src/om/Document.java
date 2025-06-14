package om;

import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable{

	private String titre;
	private Date creationDate;
	
	public Document() {
		
	}
	
	public Document(String n) {
		this.titre=n;
	}
	
	public String toString() {
		return "Le titre est ='"+this.titre+"'\nLa date ='"+this.creationDate+"'\n";
		
	}
	
	public String getTitre() {
		return this.titre;
	}
	public void setTitre(String t) {
		this.titre=t;
	}
	
	public String getCreationdate() {
		return this.creationDate.toString();
	}

}
