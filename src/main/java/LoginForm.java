import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class LoginForm extends JFrame {
    public JTextField emailField;
    public JPasswordField passwordField;
    public HashMap<String, String> users;
    public BufferedImage backgroundImage;

    public LoginForm() {
        setTitle("Login");
        setSize(1000, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            backgroundImage = ImageIO.read(LoginForm.class.getResource("/images/pexels-marieke-schonfeld-1309710-2514035.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        users = loadUsers();

        GradientPanel backgroundPanel = new GradientPanel(new Color(15, 23, 42), new Color(37, 99, 235));
        backgroundPanel.setLayout(new BorderLayout(28, 28));
        backgroundPanel.setBorder(new EmptyBorder(36, 36, 36, 36));

        JPanel brandingPanel = new JPanel();
        brandingPanel.setLayout(new BoxLayout(brandingPanel, BoxLayout.Y_AXIS));
        brandingPanel.setOpaque(false);

        JLabel brandLabel = new JLabel("MoveFlow");
        brandLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        brandLabel.setForeground(Color.WHITE);
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Smart transport for modern city living");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitleLabel.setForeground(new Color(191, 219, 254));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel featureLabel = new JLabel("<html><body><div style='width:300px'>Book trips, track rides, and manage your wallet in one seamless experience.</div></body></html>");
        featureLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        featureLabel.setForeground(new Color(226, 232, 240));
        featureLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        featureLabel.setBorder(new EmptyBorder(18, 0, 0, 0));

        brandingPanel.add(brandLabel);
        brandingPanel.add(Box.createRigidArea(new Dimension(0, 16)));
        brandingPanel.add(subtitleLabel);
        brandingPanel.add(featureLabel);

        RoundedPanel formCard = new RoundedPanel(new GridBagLayout(), 28, new Color(15, 23, 42, 200));
        formCard.setPreferredSize(new Dimension(420, 520));
        formCard.setBorder(new EmptyBorder(24, 24, 24, 24));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel("Welcome back");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formCard.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel emailLabel = new JLabel("Email or username");
        emailLabel.setForeground(new Color(191, 219, 254));
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formCard.add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(18);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        emailField.setBackground(new Color(15, 23, 42, 180));
        emailField.setForeground(Color.WHITE);
        emailField.setCaretColor(Color.WHITE);
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(96, 165, 250), 1),
                new EmptyBorder(10, 12, 10, 12)));
        formCard.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(new Color(191, 219, 254));
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formCard.add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(18);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        passwordField.setBackground(new Color(15, 23, 42, 180));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(96, 165, 250), 1),
                new EmptyBorder(10, 12, 10, 12)));
        formCard.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        JCheckBox rememberMe = new JCheckBox("Remember me");
        rememberMe.addActionListener(e -> {
            if (rememberMe.isSelected() && !users.isEmpty()) {
                emailField.setText(users.keySet().iterator().next());
            }
        });
        rememberMe.setForeground(new Color(226, 232, 240));
        rememberMe.setOpaque(false);
        rememberMe.setFocusPainted(false);
        formCard.add(rememberMe, gbc);

        gbc.gridy = 4;
        RoundedButton loginButton = new RoundedButton("Login Now", new Color(59, 130, 246), Color.WHITE);
        loginButton.addActionListener(e -> loginUser());
        formCard.add(loginButton, gbc);

        gbc.gridy = 5;
        RoundedButton signUpButton = new RoundedButton("Create account", new Color(16, 185, 129), Color.WHITE);
        signUpButton.addActionListener(e -> openRegistrationForm());
        formCard.add(signUpButton, gbc);

        gbc.gridy = 6;
        RoundedButton aboutButton = new RoundedButton("About", new Color(251, 191, 36), new Color(15, 23, 42));
        aboutButton.addActionListener(e -> showAboutInfo());
        formCard.add(aboutButton, gbc);

        backgroundPanel.add(brandingPanel, BorderLayout.WEST);
        backgroundPanel.add(formCard, BorderLayout.EAST);
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    void loginUser() {
        String emailOrUsername = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (users.containsKey(emailOrUsername) && users.get(emailOrUsername).equals(password)) {
            showWelcomeSplash();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.");
        }
    }

    private void showWelcomeSplash() {
        JFrame splashFrame = new JFrame();
        splashFrame.setSize(420, 180);
        splashFrame.setLocationRelativeTo(null);
        splashFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GradientPanel splashPanel = new GradientPanel(new Color(15, 23, 42), new Color(14, 116, 144));
        splashPanel.setLayout(new BorderLayout());
        splashPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Welcome to MoveFlow!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcomeLabel.setForeground(Color.WHITE);
        splashPanel.add(welcomeLabel, BorderLayout.CENTER);
        splashFrame.setContentPane(splashPanel);
        splashFrame.setVisible(true);

        Timer timer = new Timer(2500, e -> {
            splashFrame.dispose();
            new MainMenu(0.0);
            dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void openRegistrationForm() {
        new RegistrationForm();
        this.dispose();
    }

    private void showAboutInfo() {
        JOptionPane.showMessageDialog(this, "Innovative Transport System\nVersion 1.0\nDeveloped by Lenard Hlabangwana", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private HashMap<String, String> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/users.ser"))) {
            System.out.println("Loading users from users.ser");
            HashMap<String, String> loadedUsers = (HashMap<String, String>) ois.readObject();
            System.out.println("Users loaded successfully.");
            return loadedUsers;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "User data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error reading user data.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}
