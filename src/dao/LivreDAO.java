package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import om.Livre;
import om.Document;

public class LivreDAO {

    public static void ajouterLivre(Livre livre) {
        String insertDocument = "INSERT INTO document (titre, creation_date) VALUES (?, ?)";
        String insertLivre = "INSERT INTO livre (id, auteur, pages, editeur) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnexionDB.getConnection()) {
            // Étape 1 : insertion dans table `document`
            PreparedStatement stmtDoc = con.prepareStatement(insertDocument, Statement.RETURN_GENERATED_KEYS);
            stmtDoc.setString(1, livre.getTitre());
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            stmtDoc.setDate(2, sqlDate);
            stmtDoc.executeUpdate();

            // Récupérer l’ID généré
            ResultSet rs = stmtDoc.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);

                // Étape 2 : insertion dans table `livre`
                PreparedStatement stmtLivre = con.prepareStatement(insertLivre);
                stmtLivre.setInt(1, id);
                stmtLivre.setString(2, livre.getAuteur());
                stmtLivre.setInt(3, livre.getPage());
                stmtLivre.setString(4, livre.getEditeur());
                stmtLivre.executeUpdate();

                System.out.println("✅ Livre inséré avec ID = " + id);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur d’insertion livre : " + e.getMessage());
        }
    }
    
    public static List<Livre> tousLesLivres() {
        List<Livre> livres = new ArrayList<>();

        String query = """
            SELECT d.id, d.titre, d.creation_date, l.auteur, l.pages, l.editeur
            FROM document d
            JOIN livre l ON d.id = l.id
        """;

        try (Connection con = ConnexionDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livre livre = new Livre(
                    rs.getString("titre"),
                    rs.getInt("pages"),
                    rs.getString("auteur"),
                    rs.getString("editeur")
                );
                livres.add(livre);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors du chargement des livres : " + e.getMessage());
        }

        return livres;
    }
}























