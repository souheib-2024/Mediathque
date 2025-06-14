package om;

public class BiblioException extends Exception {
    private Bibliotheque bibliotheque; // Référence vers la bibliothèque

    // Constructeur
    public BiblioException(String message, Bibliotheque bibliotheque) {
        super(message); // Appel du constructeur de `Exception`
        this.bibliotheque = bibliotheque;
    }

    // Getter pour récupérer la bibliothèque
    public Bibliotheque getBibliotheque() {
        return bibliotheque;
    }
}