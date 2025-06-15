package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import om.Cassette;

public class CassetteDAO {

    public static void ajouterCassette(Cassette cassette) {
        String insertDocument = "INSERT INTO document (titre, creation_date) VALUES (?, ?)";
        String insertCassette = "INSERT INTO cassette (id, auteur, duree) VALUES (?, ?, ?)";

        try (Connection con = ConnexionDB.getConnection()) {
            PreparedStatement stmtDoc = con.prepareStatement(insertDocument, Statement.RETURN_GENERATED_KEYS);
            stmtDoc.setString(1, cassette.getTitre());
            java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
            stmtDoc.setDate(2, date);
            stmtDoc.executeUpdate();

            ResultSet rs = stmtDoc.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);

                PreparedStatement stmtCassette = con.prepareStatement(insertCassette);
                stmtCassette.setInt(1, id);
                stmtCassette.setString(2, cassette.getAuteur());
                stmtCassette.setInt(3, cassette.getDuree());
                stmtCassette.executeUpdate();

                System.out.println("✅ Cassette insérée avec ID = " + id);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur d’insertion cassette : " + e.getMessage());
        }
    }
    
    public static List<Cassette> toutesLesCassettes() {
        List<Cassette> cassettes = new ArrayList<>();
        String sql = """
            SELECT d.titre, d.creation_date, c.auteur, c.duree
            FROM document d
            JOIN cassette c ON d.id = c.id
        """;

        try (Connection con = ConnexionDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cassette cassette = new Cassette(
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getInt("duree")
                );
                // Tu peux formater ou utiliser la date séparément si tu veux l'afficher
                // Date dateCreation = rs.getDate("creation_date");
                cassettes.add(cassette);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors du chargement des cassettes : " + e.getMessage());
        }

        return cassettes;
    }
    
}












