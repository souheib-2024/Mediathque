package dao;

import java.sql.*;
import om.Periodique;

public class PeriodiqueDAO {

    public static void ajouterPeriodique(Periodique periodique) {
        String insertDocument = "INSERT INTO document (titre, creation_date) VALUES (?, ?)";
        String insertPeriodique = "INSERT INTO periodique (id, periodicite, pages) VALUES (?, ?, ?)";

        try (Connection con = ConnexionDB.getConnection()) {
            PreparedStatement stmtDoc = con.prepareStatement(insertDocument, Statement.RETURN_GENERATED_KEYS);
            stmtDoc.setString(1, periodique.getTitre());
            java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
            stmtDoc.setDate(2, date);
            stmtDoc.executeUpdate();

            ResultSet rs = stmtDoc.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);

                PreparedStatement stmtPeriodique = con.prepareStatement(insertPeriodique);
                stmtPeriodique.setInt(1, id);
                stmtPeriodique.setString(2, periodique.getFrequence());
                stmtPeriodique.setInt(3, periodique.getPage());
                stmtPeriodique.executeUpdate();

                System.out.println("✅ Périodique inséré avec ID = " + id);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur d’insertion périodique : " + e.getMessage());
        }
    }
}