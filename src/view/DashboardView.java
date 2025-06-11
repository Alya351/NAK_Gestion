package view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    private String utilisateur;

    public DashboardView(String utilisateur) {
        this.utilisateur = utilisateur;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Tableau de bord - " + utilisateur);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header
        JLabel welcomeLabel = new JLabel("Bienvenue " + utilisateur + " !", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Panel des boutons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btnDemande = new JButton("Nouvelle demande de laissez-passer");
        btnDemande.setPreferredSize(new Dimension(200, 60));
        btnDemande.addActionListener(e -> new DemandeView(utilisateur).setVisible(true));

        JButton btnRecherche = new JButton("Rechercher mes laissez-passer");
        btnRecherche.setPreferredSize(new Dimension(200, 60));
        btnRecherche.addActionListener(e -> new RechercheView().setVisible(true));

        buttonPanel.add(btnDemande);
        buttonPanel.add(btnRecherche);

        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
}
