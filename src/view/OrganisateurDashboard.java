package view;

import javax.swing.*;
import java.awt.*;

public class OrganisateurDashboard extends JFrame {

    public OrganisateurDashboard() {
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Dashboard Organisateur - NAK 2025");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Header
        JLabel titleLabel = new JLabel("Gestion des Laissez-Passer - Organisateur", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        // Panel des boutons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        JButton btnGerer = new JButton("<html><center>Gérer les<br>demandes</center></html>");
        btnGerer.setPreferredSize(new Dimension(150, 80));
        btnGerer.addActionListener(e -> new GestionDemandesView().setVisible(true));

        JButton btnRecherche = new JButton("<html><center>Recherche<br>avancée</center></html>");
        btnRecherche.setPreferredSize(new Dimension(150, 80));
        btnRecherche.addActionListener(e -> new RechercheView().setVisible(true));

        JButton btnImprimer = new JButton("<html><center>Imprimer<br>laissez-passer</center></html>");
        btnImprimer.setPreferredSize(new Dimension(150, 80));
        btnImprimer.addActionListener(e -> new ImprimerView().setVisible(true));

        JButton btnControle = new JButton("<html><center>Contrôle<br>d'accès</center></html>");
        btnControle.setPreferredSize(new Dimension(150, 80));
        btnControle.addActionListener(e -> new ControleAccesView().setVisible(true));

        JButton btnArchives = new JButton("<html><center>Archives<br>et historique</center></html>");
        btnArchives.setPreferredSize(new Dimension(150, 80));

        buttonPanel.add(btnGerer);
        buttonPanel.add(btnRecherche);
        buttonPanel.add(btnImprimer);
        buttonPanel.add(btnControle);
        buttonPanel.add(btnArchives);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
}
