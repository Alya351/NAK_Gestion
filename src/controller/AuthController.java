package controller;

import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthController {

    public static boolean authenticate(String nom, String motDePasse) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM PersonnePhysique p " +
                        "JOIN Participant pt ON p.id_personne = pt.id_personne " +
                        "WHERE p.nom = ? AND pt.motDePasse = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, motDePasse);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // connexion réussie si une ligne est trouvée
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean authenticateOperateur(String nom, String motDePasse) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT id_operateur FROM Operateur WHERE nom = ? AND mot_de_passe = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, motDePasse);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
