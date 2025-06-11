package view;

import javax.swing.*;
import java.awt.*;

public class ImprimerView extends JFrame {

    public ImprimerView() {
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Imprimer laissez-passer");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JTextField txtId = new JTextField();
        JButton btnImprimer = new JButton("Générer PDF");
        JButton btnPrevisualiser = new JButton("Prévisualiser");

        panel.add(new JLabel("ID du laissez-passer :"));
        panel.add(txtId);
        panel.add(btnPrevisualiser);
        panel.add(btnImprimer);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        btnImprimer.addActionListener(e -> {
            String id = txtId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir un ID.");
                return;
            }
            // Simulation de génération PDF
            JOptionPane.showMessageDialog(this, "PDF généré avec succès pour l'ID : " + id);
        });

        btnPrevisualiser.addActionListener(e -> {
            String id = txtId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir un ID.");
                return;
            }
            JOptionPane.showMessageDialog(this, "Prévisualisation du laissez-passer ID : " + id);
        });

        add(panel);
    }
}