package view;

import controller.DemandeController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RechercheView extends JFrame {

    private JTable tableResultats;
    private DefaultTableModel tableModel;

    public RechercheView() {
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Recherche de laissez-passer");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel de recherche
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Critères de recherche"));

        JComboBox<String> comboCritere = new JComboBox<>(new String[]{
            "Tous", "Nom", "Véhicule", "Zone"
        });
        JTextField txtRecherche = new JTextField(20);
        JButton btnRechercher = new JButton("Rechercher");

        searchPanel.add(new JLabel("Rechercher par :"));
        searchPanel.add(comboCritere);
        searchPanel.add(new JLabel("Valeur :"));
        searchPanel.add(txtRecherche);
        searchPanel.add(btnRechercher);

        // Table des résultats
        String[] colonnes = {"ID", "Nom", "Prénom", "Véhicule", "Zone", "Date début", "Date fin", "Statut"};
        tableModel = new DefaultTableModel(colonnes, 0);
        tableResultats = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableResultats);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Résultats"));

        btnRechercher.addActionListener(e -> {
            String critere = (String) comboCritere.getSelectedItem();
            String valeur = txtRecherche.getText().trim();

            if (valeur.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir une valeur à rechercher.");
                return;
            }

            // Effacer les résultats précédents
            tableModel.setRowCount(0);

            // Effectuer la recherche
            List<String[]> resultats = DemandeController.rechercherLaissezPasser(
                critere.equals("Tous") ? "default" : critere.toLowerCase(), valeur);

            // Afficher les résultats
            for (String[] ligne : resultats) {
                tableModel.addRow(ligne);
            }

            if (resultats.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Aucun résultat trouvé.");
            }
        });

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }
}
