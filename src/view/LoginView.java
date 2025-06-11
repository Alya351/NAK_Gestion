package view;

import controller.AuthController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

public class LoginView extends JFrame {

    public static void main(String[] args) {
        // Configuration du Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }

    public LoginView() {
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("NAK Koudougou - Connexion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal avec layout personnalisé
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Panel gauche avec image de fond et texte
        JPanel leftPanel = createLeftPanel();
        
        // Panel droit avec formulaire de connexion
        JPanel rightPanel = createRightPanel();

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient bleu
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(74, 144, 226),
                    0, getHeight(), new Color(52, 119, 235)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(450, 500));

        // Logo NAK
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setOpaque(false);
        logoPanel.setBorder(new EmptyBorder(30, 30, 0, 0));
        
        JLabel logoLabel = createLogoLabel();
        logoPanel.add(logoLabel);

        // Texte principal
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setBorder(new EmptyBorder(80, 40, 100, 40));

        JLabel titleLabel = new JLabel("<html><div style='text-align: left;'>Bienvenue à la 30e édition des<br>Nuits Atypiques de KOUDOUGOU!</div></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("<html><div style='margin-top: 20px; color: rgba(255,255,255,0.9);'>Thème: Culture et valorisation des instruments de musique traditionnels</div></html>");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(20));
        textPanel.add(subtitleLabel);

        leftPanel.add(logoPanel, BorderLayout.NORTH);
        leftPanel.add(textPanel, BorderLayout.CENTER);

        return leftPanel;
    }

    private JLabel createLogoLabel() {
        JLabel logoLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Cercle blanc pour le logo
                g2d.setColor(Color.WHITE);
                g2d.fillOval(5, 5, 70, 70);
                
                // Texte NAK
                g2d.setColor(new Color(52, 119, 235));
                g2d.setFont(new Font("Arial", Font.BOLD, 16));
                FontMetrics fm = g2d.getFontMetrics();
                String text = "NAK";
                int x = (80 - fm.stringWidth(text)) / 2;
                int y = 35 + fm.getAscent() / 2;
                g2d.drawString(text, x, y);
                
                // Sous-texte
                g2d.setFont(new Font("Arial", Font.PLAIN, 8));
                fm = g2d.getFontMetrics();
                String subtext = "KOUDOUGOU";
                x = (80 - fm.stringWidth(subtext)) / 2;
                g2d.drawString(subtext, x, y + 15);
            }
        };
        logoLabel.setPreferredSize(new Dimension(80, 80));
        return logoLabel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(350, 500));
        rightPanel.setBackground(Color.WHITE);

        // Panel du formulaire avec coins arrondis
        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 25, 25));
            }
        };
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(248, 249, 250));
        formPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // Titre du formulaire
        JLabel formTitle = new JLabel("Connexion");
        formTitle.setFont(new Font("Arial", Font.BOLD, 24));
        formTitle.setForeground(new Color(33, 37, 41));
        formTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Champs de saisie
        JTextField usernameField = createModernTextField("Nom d'utilisateur");
        JPasswordField passwordField = createModernPasswordField("Mot de passe");
        
        // ComboBox pour le type d'utilisateur
        JComboBox<String> userTypeCombo = createModernComboBox();
        
        // Bouton de connexion
        JButton loginButton = createModernButton("Se connecter");
        
        // Label pour les messages
        JLabel messageLabel = new JLabel("");
        messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Lien "Mot de passe oublié"
        JLabel forgotPasswordLabel = new JLabel("<html><a href='#'>Inscription seul</a></html>");
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPasswordLabel.setForeground(new Color(52, 119, 235));
        forgotPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Action du bouton de connexion
        loginButton.addActionListener(e -> {
            String nom = usernameField.getText().trim();
            String mdp = new String(passwordField.getPassword());
            String type = (String) userTypeCombo.getSelectedItem();

            if (nom.isEmpty() || mdp.isEmpty()) {
                showMessage(messageLabel, "❌ Veuillez remplir tous les champs.", Color.RED);
                return;
            }

            boolean success = false;
            if ("Participant".equals(type)) {
                success = AuthController.authenticate(nom, mdp);
            } else {
                success = AuthController.authenticateOperateur(nom, mdp);
            }

            if (success) {
                showMessage(messageLabel, "✅ Connexion réussie !", new Color(40, 167, 69));
                
                SwingUtilities.invokeLater(() -> {
                    if ("Participant".equals(type)) {
                        new DashboardView(nom).setVisible(true);
                    } else {
                        new OrganisateurDashboard().setVisible(true);
                    }
                    dispose();
                });
            } else {
                showMessage(messageLabel, "❌ Identifiants incorrects.", Color.RED);
            }
        });

        // Assemblage du formulaire
        formPanel.add(formTitle);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(createFieldLabel("Nom d'utilisateur"));
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(usernameField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(createFieldLabel("Mot de passe"));
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(createFieldLabel("Type d'utilisateur"));
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(userTypeCombo);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(forgotPasswordLabel);
        formPanel.add(Box.createVerticalStrut(25));
        formPanel.add(loginButton);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(messageLabel);

        // Container principal du panel droit
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(Color.WHITE);
        container.add(formPanel);

        rightPanel.add(container, BorderLayout.CENTER);

        return rightPanel;
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(73, 80, 87));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createModernTextField(String placeholder) {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fond avec bordure arrondie
                g2d.setColor(getBackground());
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 8, 8));
                
                // Bordure
                g2d.setColor(hasFocus() ? new Color(52, 119, 235) : new Color(206, 212, 218));
                g2d.setStroke(new BasicStroke(hasFocus() ? 2 : 1));
                g2d.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 8, 8));
                
                super.paintComponent(g);
            }
        };
        
        field.setPreferredSize(new Dimension(250, 45));
        field.setMaximumSize(new Dimension(250, 45));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(new EmptyBorder(12, 15, 12, 15));
        field.setBackground(Color.WHITE);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setOpaque(false);
        
        return field;
    }

    private JPasswordField createModernPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fond avec bordure arrondie
                g2d.setColor(getBackground());
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 8, 8));
                
                // Bordure
                g2d.setColor(hasFocus() ? new Color(52, 119, 235) : new Color(206, 212, 218));
                g2d.setStroke(new BasicStroke(hasFocus() ? 2 : 1));
                g2d.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 8, 8));
                
                super.paintComponent(g);
            }
        };
        
        field.setPreferredSize(new Dimension(250, 45));
        field.setMaximumSize(new Dimension(250, 45));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(new EmptyBorder(12, 15, 12, 15));
        field.setBackground(Color.WHITE);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setOpaque(false);
        
        return field;
    }

    private JComboBox<String> createModernComboBox() {
        String[] items = {"Participant", "Organisateur"};
        JComboBox<String> combo = new JComboBox<String>(items) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fond avec bordure arrondie
                g2d.setColor(getBackground());
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 8, 8));
                
                // Bordure
                g2d.setColor(hasFocus() ? new Color(52, 119, 235) : new Color(206, 212, 218));
                g2d.setStroke(new BasicStroke(hasFocus() ? 2 : 1));
                g2d.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 8, 8));
                
                super.paintComponent(g);
            }
        };
        
        combo.setPreferredSize(new Dimension(250, 45));
        combo.setMaximumSize(new Dimension(250, 45));
        combo.setFont(new Font("Arial", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setAlignmentX(Component.LEFT_ALIGNMENT);
        combo.setOpaque(false);
        
        return combo;
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fond avec gradient
                Color startColor = getModel().isPressed() ? 
                    new Color(42, 100, 200) : new Color(52, 119, 235);
                Color endColor = getModel().isPressed() ? 
                    new Color(32, 90, 190) : new Color(42, 109, 225);
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, startColor,
                    0, getHeight(), endColor
                );
                g2d.setPaint(gradient);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 8, 8));
                
                // Texte
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - 2;
                g2d.drawString(getText(), x, y);
            }
        };
        
        button.setPreferredSize(new Dimension(250, 45));
        button.setMaximumSize(new Dimension(250, 45));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }

    private void showMessage(JLabel label, String message, Color color) {
        label.setText(message);
        label.setForeground(color);
    }
}