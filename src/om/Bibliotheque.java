package om;

import java.util.Hashtable;
import java.util.Vector;
import java.io.Serializable;
import java.util.Enumeration;

public class Bibliotheque implements Serializable{
    private String nom;
    private Vector<Document> tdocument; // Stockage des documents
    private Hashtable<String, Vector<Document>> indexation; // Mots-clés → Documents

    // 📌 Constructeur : Initialise le nom et les collections
    public Bibliotheque(String nom) {
        this.nom = nom;
        this.tdocument = new Vector<>();
        this.indexation = new Hashtable<>();
    }

    // 📌 Ajouter un document
    public void ajouterDocument(Document doc) {
        tdocument.add(doc);
    }

    // 📌 Afficher tous les documents disponibles
    public void afficherDocuments() {
        System.out.println("📚 Documents dans la bibliothèque " + nom + " :");
        Enumeration<Document> docs = documents();
        while (docs.hasMoreElements()) {
            System.out.println(docs.nextElement());
        }
    }

    // 📌 Retourne une énumération des documents
    public Enumeration<Document> documents() {
        return tdocument.elements();
    }

    // 📌 Ajouter un mot-clé associé à un document
    public void ajouterIndex(String motCle, Document doc) {
        indexation.computeIfAbsent(motCle, k -> new Vector<>()).add(doc);
    }

    // 📌 Rechercher tous les documents pour un mot-clé (avec gestion des exceptions)
    public Enumeration<Document> trouverDocumentsIndex(String motCle) throws DocumentPasTrouve {
        Vector<Document> docs = indexation.get(motCle);
        if (docs == null || docs.isEmpty()) {
            throw new DocumentPasTrouve(motCle, this); // Lève une exception si aucun document n’est trouvé
        }
        return docs.elements();
    }

    // 📌 Rechercher tous les mots-clés liés à un document
    public Vector<String> trouverIndexDocument(Document doc) {
        Vector<String> motsCles = new Vector<>();
        for (String key : indexation.keySet()) {
            if (indexation.get(key).contains(doc)) {
                motsCles.add(key);
            }
        }
        return motsCles;
    }

    // 📌 Récupérer tous les mots-clés existants
    public Enumeration<String> indexes() {
        return indexation.keys();
    }
}