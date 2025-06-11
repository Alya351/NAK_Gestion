package view;

import controller.DemandeController;
import javax.swing.*;
import java.awt.*;

public class GestionDemandesView extends JFrame {

    public GestionDemandesView() {
        setTitle("Gestion des demandes (Organisateur)");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtId = new JTextField();
        JButton btnValider = new JButton("Valider");
        JButton btnRefuser = new JButton("Refuser");

        panel.add(new JLabel("ID de la demande :"));
        panel.add(txtId);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(btnValider);
        panel.add(btnRefuser);

        add(panel);

        btnValider.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                // ID opérateur par défaut (à adapter selon votre logique)
                int idOperateur = 1;
                if (DemandeController.validerDemande(id, idOperateur)) {
                    JOptionPane.showMessageDialog(this, "✅ Demande validée.");
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Échec de la validation.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID invalide.");
            }
        });

        btnRefuser.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                if (DemandeController.refuserDemande(id)) {
                    JOptionPane.showMessageDialog(this, "🚫 Demande refusée.");
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Échec du refus.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID invalide.");
            }
        });
    }
}
