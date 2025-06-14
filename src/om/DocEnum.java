package om;

import java.util.Enumeration;

public class DocEnum implements Enumeration{
	 private int position = 0;
	 private Document[] docs;

	    // Constructeur qui prend un tableau de documents
	    public DocEnum(Document[] docs) {
	        this.docs = docs;
	    }
	    
	    @Override
	    public boolean hasMoreElements() {
	        return position < docs.length && docs[position] != null;
	    }

	    @Override
	    public Document nextElement() {
	        if (hasMoreElements()) {
	            return docs[position++];
	        }
	        throw new java.util.NoSuchElementException("Plus de documents disponibles !");
	    }


}
