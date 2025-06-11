package controller;

import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DemandeController {

    public static boolean enregistrerDemande(String zone, String vehicule, String dateDebut, String dateFin, String nomPersonne) {
        String sql = "INSERT INTO LaissezPasser (id_participant, nature_engin, id_zone, " +
                "date_creation, date_debut, date_fin, statut) " +
                "SELECT p.id_participant, ?, z.id_zone, SYSDATE, TO_DATE(?, 'DD/MM/YYYY'), " +
                "TO_DATE(?, 'DD/MM/YYYY'), 'ACTIF' " +
                "FROM Participant p " +
                "JOIN PersonnePhysique pp ON p.id_personne = pp.id_personne " +
                "JOIN Zone_laissezPasser z ON z.nom_zone = ? " +
                "WHERE pp.nom = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicule);
            stmt.setString(2, dateDebut);
            stmt.setString(3, dateFin);
            stmt.setString(4, zone);
            stmt.setString(5, nomPersonne);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modifierDemande(int idLaissezPasser, String zone, String vehicule, String dateDebut, String dateFin) {
        String sql = "UPDATE LaissezPasser SET nature_engin = ?, " +
                "id_zone = (SELECT id_zone FROM Zone_laissezPasser WHERE nom_zone = ?), " +
                "date_debut = TO_DATE(?, 'DD/MM/YYYY'), " +
                "date_fin = TO_DATE(?, 'DD/MM/YYYY') " +
                "WHERE id_laissez_passer = ? AND statut = 'ACTIF'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicule);
            stmt.setString(2, zone);
            stmt.setString(3, dateDebut);
            stmt.setString(4, dateFin);
            stmt.setInt(5, idLaissezPasser);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validerDemande(int idLaissezPasser, int idOperateur) {
        String sql = "UPDATE LaissezPasser SET id_operateur = ?, statut = 'VALIDE' WHERE id_laissez_passer = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idOperateur);
            stmt.setInt(2, idLaissezPasser);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode manquante ajoutée
    public static boolean refuserDemande(int idLaissezPasser) {
        String sql = "UPDATE LaissezPasser SET statut = 'REFUSE' WHERE id_laissez_passer = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLaissezPasser);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean annulerLaissezPasser(int idLaissezPasser) {
        String sql = "UPDATE LaissezPasser SET statut = 'EXPIRE' WHERE id_laissez_passer = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLaissezPasser);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String[]> rechercherLaissezPasser(String critere, String valeur) {
        List<String[]> resultats = new ArrayList<>();
        String sql = "SELECT lp.id_laissez_passer, pp.nom, pp.prenom, lp.nature_engin, " +
                "z.nom_zone, lp.date_debut, lp.date_fin, lp.statut " +
                "FROM LaissezPasser lp " +
                "JOIN Participant p ON lp.id_participant = p.id_participant " +
                "JOIN PersonnePhysique pp ON p.id_personne = pp.id_personne " +
                "JOIN Zone_laissezPasser z ON lp.id_zone = z.id_zone ";

        switch (critere.toLowerCase()) {
            case "nom":
                sql += "WHERE pp.nom LIKE ?";
                break;
            case "vehicule":
                sql += "WHERE lp.nature_engin LIKE ?";
                break;
            case "zone":
                sql += "WHERE z.nom_zone LIKE ?";
                break;
            default:
                sql += "WHERE pp.nom LIKE ? OR lp.nature_engin LIKE ? OR z.nom_zone LIKE ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (critere.equals("default")) {
                stmt.setString(1, "%" + valeur + "%");
                stmt.setString(2, "%" + valeur + "%");
                stmt.setString(3, "%" + valeur + "%");
            } else {
                stmt.setString(1, "%" + valeur + "%");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] ligne = {
                    String.valueOf(rs.getInt("id_laissez_passer")),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("nature_engin"),
                    rs.getString("nom_zone"),
                    rs.getDate("date_debut").toString(),
                    rs.getDate("date_fin").toString(),
                    rs.getString("statut")
                };
                resultats.add(ligne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultats;
    }

    public static String[] getStatistiques() {
        String[] stats = new String[4];
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Total des laissez-passer
            PreparedStatement stmt1 = conn.prepareStatement(
                "SELECT COUNT(*) FROM LaissezPasser");
            ResultSet rs1 = stmt1.executeQuery();
            if (rs1.next()) stats[0] = String.valueOf(rs1.getInt(1));

            // Laissez-passer actifs
            PreparedStatement stmt2 = conn.prepareStatement(
                "SELECT COUNT(*) FROM LaissezPasser WHERE statut = 'ACTIF'");
            ResultSet rs2 = stmt2.executeQuery();
            if (rs2.next()) stats[1] = String.valueOf(rs2.getInt(1));

            // Laissez-passer expirés
            PreparedStatement stmt3 = conn.prepareStatement(
                "SELECT COUNT(*) FROM LaissezPasser WHERE statut = 'EXPIRE'");
            ResultSet rs3 = stmt3.executeQuery();
            if (rs3.next()) stats[2] = String.valueOf(rs3.getInt(1));

            // Zone la plus demandée
            PreparedStatement stmt4 = conn.prepareStatement(
                "SELECT z.nom_zone FROM Zone_laissezPasser z " +
                "JOIN LaissezPasser lp ON z.id_zone = lp.id_zone " +
                "GROUP BY z.nom_zone ORDER BY COUNT(*) DESC");
            ResultSet rs4 = stmt4.executeQuery();
            if (rs4.next()) stats[3] = rs4.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }
}
