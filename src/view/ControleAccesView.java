package view;

import javax.swing.*;
import java.awt.*;

public class ControleAccesView extends JFrame {

    public ControleAccesView() {
        setTitle("Contrôle d'accès");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JTextField txtCode = new JTextField();
        JButton btnVerifier = new JButton("Vérifier accès");
        JLabel lblResultat = new JLabel("", JLabel.CENTER);

        panel.add(new JLabel("Code du laissez-passer :"));
        panel.add(txtCode);
        panel.add(new JLabel(""));
        panel.add(btnVerifier);
        panel.add(new JLabel("Résultat :"));
        panel.add(lblResultat);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        btnVerifier.addActionListener(e -> {
            String code = txtCode.getText().trim();
            if (code.isEmpty()) {
                lblResultat.setText("Veuillez saisir un code");
                lblResultat.setForeground(Color.RED);
                return;
            }
            
            // Simulation de vérification
            if (code.length() >= 5) {
                lblResultat.setText("✅ ACCÈS AUTORISÉ");
                lblResultat.setForeground(Color.GREEN);
            } else {
                lblResultat.setText("❌ ACCÈS REFUSÉ");
                lblResultat.setForeground(Color.RED);
            }
        });

        add(panel);
    }
}