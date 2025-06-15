package iu;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import dao.DocumentService;
import om.*;
import java.util.List;

public class BibliothequeGUI extends JFrame {
    private Bibliotheque maBibliotheque;

    public BibliothequeGUI() {
        maBibliotheque = new Bibliotheque("Bibliothèque Municipale");

        setTitle("Méthiathèque");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Titre
        JLabel lblTitre = new JLabel("📚 Bibliothèque Municipale", SwingConstants.CENTER);
        lblTitre.setFont(new Font("Serif", Font.BOLD, 24));
        lblTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(lblTitre);

        // Espacement
        add(Box.createVerticalStrut(30));

        // Boutons
        JButton btnCreer = new JButton("Créer un document");
        JButton btnLister = new JButton("Lister les documents");
        JButton btnRechercher = new JButton("Rechercher un document");
        JButton btnQuitter = new JButton("Quitter");

        // Centrage
        btnCreer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRechercher.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuitter.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Écouteurs
        btnCreer.addActionListener(e -> ouvrirSousMenuCreation());
        btnLister.addActionListener(e -> afficherDocuments());
        btnRechercher.addActionListener(e -> rechercherDocument());
        btnQuitter.addActionListener(e -> System.exit(0));

        // Ajout des boutons avec espace
        add(btnCreer);
        add(Box.createVerticalStrut(10));
        add(btnLister);
        add(Box.createVerticalStrut(10));
        add(btnRechercher);
        add(Box.createVerticalStrut(10));
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
            default -> {}
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
        try {
            List<Document> documents = DocumentService.chargerTous();

         
            String[] colonnes = {"Type", "Titre", "Auteur / Éditeur", "Pages / Durée", "Fréquence"};

           
            Object[][] data = new Object[documents.size()][colonnes.length];

            for (int i = 0; i < documents.size(); i++) {
                Document doc = documents.get(i);
                String type = doc.getClass().getSimpleName();
                String titre = doc.getTitre();
                String auteurEditeur = "-";
                String pagesDuree = "-";
                String frequence = "-";

                if (doc instanceof Livre l) {
                    auteurEditeur = l.getAuteur() + " / " + l.getEditeur();
                    pagesDuree = l.getPage() + " pages";
                } else if (doc instanceof Cassette c) {
                    auteurEditeur = c.getAuteur();
                    pagesDuree = c.getDuree() + " min";
                } else if (doc instanceof Periodique p) {
                    pagesDuree = p.getPage() + " pages";
                    frequence = p.getFrequence();
                }

                data[i][0] = type;
                data[i][1] = titre;
                data[i][2] = auteurEditeur;
                data[i][3] = pagesDuree;
                data[i][4] = frequence;
            }

            
            JTable table = new JTable(data, colonnes);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);

          
            JOptionPane.showMessageDialog(this, scrollPane, "📋 Documents en base", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Erreur d'affichage : " + e.getMessage());
        }
    }

    private void rechercherDocument() {
        String critere = JOptionPane.showInputDialog("Entrez un mot-clé :");
        try {
            Enumeration<Document> docsTrouves = maBibliotheque.trouverDocumentsIndex(critere);
            StringBuilder resultats = new StringBuilder("🔎 Résultats de recherche :\n");
            while (docsTrouves.hasMoreElements()) {
                resultats.append(docsTrouves.nextElement()).append("\n");
            }
            JOptionPane.showMessageDialog(this, resultats.toString());
        } catch (DocumentPasTrouve e) {
            JOptionPane.showMessageDialog(this, "❌ Aucun document trouvé pour ce mot-clé.");
        }
    }

   
}