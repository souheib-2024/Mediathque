package om;

public class DocumentPasTrouve extends BiblioException {
    private String indexRecherché; // L’index qui n’a pas été trouvé

    // Constructeur
    public DocumentPasTrouve(String indexRecherché, Bibliotheque bibliotheque) {
        super("Document introuvable pour l'index : " + indexRecherché, bibliotheque);
        this.indexRecherché = indexRecherché;
    }

    // Getter pour récupérer l’index
    public String getIndexRecherché() {
        return indexRecherché;
    }
}