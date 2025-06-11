package view;

import controller.DemandeController;
import javax.swing.*;
import java.awt.*;

public class DemandeView extends JFrame {
    
    private String utilisateur;

    public DemandeView(String utilisateur) {
        this.utilisateur = utilisateur;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Nouvelle demande de laissez-passer");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtZone = new JTextField();
        JTextField txtVehicule = new JTextField();
        JTextField txtDateDebut = new JTextField();
        JTextField txtDateFin = new JTextField();
        JButton btnSoumettre = new JButton("Soumettre la demande");

        panel.add(new JLabel("Zone d'accès :"));
        panel.add(txtZone);
        panel.add(new JLabel("Nature du véhicule :"));
        panel.add(txtVehicule);
        panel.add(new JLabel("Date de début (DD/MM/YYYY) :"));
        panel.add(txtDateDebut);
        panel.add(new JLabel("Date de fin (DD/MM/YYYY) :"));
        panel.add(txtDateFin);
        panel.add(new JLabel(""));
        panel.add(btnSoumettre);
        panel.add(new JLabel("Utilisateur : " + utilisateur));
        panel.add(new JLabel(""));

        add(panel);

        btnSoumettre.addActionListener(e -> {
            String zone = txtZone.getText().trim();
            String vehicule = txtVehicule.getText().trim();
            String dateDebut = txtDateDebut.getText().trim();
            String dateFin = txtDateFin.getText().trim();

            if (zone.isEmpty() || vehicule.isEmpty() || dateDebut.isEmpty() || dateFin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }

            boolean success = DemandeController.enregistrerDemande(zone, vehicule, dateDebut, dateFin, utilisateur);

            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Demande soumise avec succès !");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Erreur lors de la soumission.");
            }
        });
    }
}
