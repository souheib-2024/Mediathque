package iu;

import java.awt.FlowLayout;
import java.util.Enumeration;

import javax.swing.*;

import dao.DocumentService;
import om.Bibliotheque;
import om.Cassette;
import om.Document;
import om.DocumentPasTrouve;
import om.Livre;
import om.Periodique;

public class BibliothequeGUI extends JFrame {
    private Bibliotheque maBibliotheque;

    public BibliothequeGUI() {
        maBibliotheque = new Bibliotheque("Bibliothèque Municipale");

        setTitle("Bibliothèque");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Boutons
        JButton btnCreer = new JButton("Créer un document");
        JButton btnLister = new JButton("Lister les documents");
        JButton btnRechercher = new JButton("Rechercher un document");
        JButton btnQuitter = new JButton("Quitter");

        // Actions
        btnCreer.addActionListener(e -> ouvrirSousMenuCreation());
        btnLister.addActionListener(e -> afficherDocuments());
        btnRechercher.addActionListener(e -> rechercherDocument());
        btnQuitter.addActionListener(e -> System.exit(0));

        add(btnCreer);
        add(btnLister);
        add(btnRechercher);
        add(btnQuitter);

        setVisible(true);
    }

    private void ouvrirSousMenuCreation() {
        String[] options = {"Cassette", "Livre", "Périodique", "Retour"};
        int choix = JOptionPane.showOptionDialog(
                this,
                "Choisissez un type de document :",
                "Création de document",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choix) {
            case 0 -> creerCassette();
            case 1 -> creerLivre();
            case 2 -> creerPeriodique();
            default -> {
            }
        }
    }

    private void creerCassette() {
        try {
            String titre = JOptionPane.showInputDialog("Entrez le titre de la cassette :");
            String auteur = JOptionPane.showInputDialog("Entrez l'auteur :");
            int duree = Integer.parseInt(JOptionPane.showInputDialog("Entrez la durée en minutes :"));

            Cassette c = new Cassette(titre, auteur, duree);
            maBibliotheque.ajouterDocument(c);
            DocumentService.sauvegarder(c);
            JOptionPane.showMessageDialog(this, "Cassette ajoutée et enregistrée !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Erreur : " + e.getMessage());
        }
    }

    private void creerLivre() {
        try {
            String titre = JOptionPane.showInputDialog("Entrez le titre du livre :");
            String auteur = JOptionPane.showInputDialog("Entrez l'auteur :");
            String editeur = JOptionPane.showInputDialog("Entrez l'éditeur :");
            int pages = Integer.parseInt(JOptionPane.showInputDialog("Entrez le nombre de pages :"));

            Livre l = new Livre(titre, pages, auteur, editeur);
            maBibliotheque.ajouterDocument(l);
            DocumentService.sauvegarder(l);
            JOptionPane.showMessageDialog(this, "Livre ajouté et enregistré !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Erreur : " + e.getMessage());
        }
    }

    private void creerPeriodique() {
        try {
            String titre = JOptionPane.showInputDialog("Entrez le titre du périodique :");
            String type = JOptionPane.showInputDialog("Entrez la fréquence de publication :");
            int pages = Integer.parseInt(JOptionPane.showInputDialog("Entrez le nombre de pages :"));

            Periodique p = new Periodique(titre, pages, type);
            maBibliotheque.ajouterDocument(p);
            DocumentService.sauvegarder(p);
            JOptionPane.showMessageDialog(this, "Périodique ajouté et enregistré !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Erreur : " + e.getMessage());
        }
    }

    private void afficherDocuments() {
        StringBuilder listeDocs = new StringBuilder("📖 Documents dans la bibliothèque :\n");
        Enumeration<Document> tousDocs = maBibliotheque.documents();
        while (tousDocs.hasMoreElements()) {
            listeDocs.append(tousDocs.nextElement().toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, listeDocs.toString());
    }

    private void rechercherDocument() {
        String critere = JOptionPane.showInputDialog("Entrez un mot-clé :");
        try {
            Enumeration<Document> docsTrouves = maBibliotheque.trouverDocumentsIndex(critere);
            StringBuilder resultats = new StringBuilder("🔎 Résultats de recherche :\n");
            while (docsTrouves.hasMoreElements()) {
                resultats.append(docsTrouves.nextElement().toString()).append("\n");
            }
            JOptionPane.showMessageDialog(this, resultats.toString());
        } catch (DocumentPasTrouve e) {
            JOptionPane.showMessageDialog(this, "❌ Aucun document trouvé pour ce mot-clé.");
        }
    }
}