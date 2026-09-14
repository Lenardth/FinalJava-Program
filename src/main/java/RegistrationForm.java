import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class RegistrationForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField nameField, emailField, phoneField, addressField, usernameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JCheckBox termsCheckBox, newsletterCheckBox;
    private JComboBox<String> genderComboBox, countryComboBox;
    private JRadioButton studentRadioButton, employeeRadioButton;
    private ButtonGroup occupationGroup;
    private HashMap<String, String> users;

    public RegistrationForm() {
        setTitle("User Registration");
        setSize(1100, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        users = loadUsers();

        GradientPanel backgroundPanel = new GradientPanel(new Color(15, 23, 42), new Color(59, 130, 246));
        backgroundPanel.setLayout(new BorderLayout(24, 24));
        backgroundPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel introPanel = new JPanel();
        introPanel.setOpaque(false);
        introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));

        JLabel brandLabel = new JLabel("Create your account");
        brandLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        brandLabel.setForeground(Color.WHITE);
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Join the smarter way to get around the city.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitle.setForeground(new Color(191, 219, 254));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle.setBorder(new EmptyBorder(8, 0, 14, 0));

        JLabel info = new JLabel("<html><div style='width:300px'>Register in seconds to access taxi booking, route planning, wallet management, and live service alerts.</div></html>");
        info.setForeground(new Color(226, 232, 240));
        info.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        info.setAlignmentX(Component.LEFT_ALIGNMENT);

        introPanel.add(brandLabel);
        introPanel.add(subtitle);
        introPanel.add(info);

        RoundedPanel formCard = new RoundedPanel(new GridBagLayout(), 28, new Color(15, 23, 42, 210));
        formCard.setPreferredSize(new Dimension(640, 680));
        formCard.setBorder(new EmptyBorder(24, 24, 24, 24));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel nameLabel = new JLabel("Full name");
        nameLabel.setForeground(new Color(191, 219, 254));
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formCard.add(nameLabel, gbc);

        nameField = new JTextField(18);
        styleTextField(nameField);
        gbc.gridx = 1;
        formCard.add(nameField, gbc);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(new Color(191, 219, 254));
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formCard.add(usernameLabel, gbc);

        usernameField = new JTextField(18);
        styleTextField(usernameField);
        gbc.gridx = 1;
        formCard.add(usernameField, gbc);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(new Color(191, 219, 254));
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formCard.add(emailLabel, gbc);

        emailField = new JTextField(18);
        styleTextField(emailField);
        gbc.gridx = 1;
        formCard.add(emailField, gbc);

        JLabel phoneLabel = new JLabel("Phone number");
        phoneLabel.setForeground(new Color(191, 219, 254));
        phoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formCard.add(phoneLabel, gbc);

        phoneField = new JTextField(18);
        styleTextField(phoneField);
        gbc.gridx = 1;
        formCard.add(phoneField, gbc);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setForeground(new Color(191, 219, 254));
        addressLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 4;
        formCard.add(addressLabel, gbc);

        addressField = new JTextField(18);
        styleTextField(addressField);
        gbc.gridx = 1;
        formCard.add(addressField, gbc);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setForeground(new Color(191, 219, 254));
        genderLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 5;
        formCard.add(genderLabel, gbc);

        String[] genders = {"Male", "Female", "Other"};
        genderComboBox = new JComboBox<>(genders);
        styleComboBox(genderComboBox);
        gbc.gridx = 1;
        formCard.add(genderComboBox, gbc);

        JLabel countryLabel = new JLabel("City");
        countryLabel.setForeground(new Color(191, 219, 254));
        countryLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 6;
        formCard.add(countryLabel, gbc);

        String[] countries = {"Cape Town", "Johannesburg", "Durban", "Pretoria", "Bloemfontein"};
        countryComboBox = new JComboBox<>(countries);
        styleComboBox(countryComboBox);
        gbc.gridx = 1;
        formCard.add(countryComboBox, gbc);

        JLabel occupationLabel = new JLabel("Occupation");
        occupationLabel.setForeground(new Color(191, 219, 254));
        occupationLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 7;
        formCard.add(occupationLabel, gbc);

        JPanel occupationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        occupationPanel.setOpaque(false);
        studentRadioButton = new JRadioButton("Student");
        employeeRadioButton = new JRadioButton("Employee");
        occupationGroup = new ButtonGroup();
        occupationGroup.add(studentRadioButton);
        occupationGroup.add(employeeRadioButton);
        studentRadioButton.setOpaque(false);
        employeeRadioButton.setOpaque(false);
        studentRadioButton.setForeground(Color.WHITE);
        employeeRadioButton.setForeground(Color.WHITE);
        occupationPanel.add(studentRadioButton);
        occupationPanel.add(employeeRadioButton);
        gbc.gridx = 1;
        formCard.add(occupationPanel, gbc);

        JLabel passwordLabel = new JLabel("Create password");
        passwordLabel.setForeground(new Color(191, 219, 254));
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 8;
        formCard.add(passwordLabel, gbc);

        passwordField = new JPasswordField(18);
        styleTextField(passwordField);
        gbc.gridx = 1;
        formCard.add(passwordField, gbc);

        JLabel confirmPasswordLabel = new JLabel("Confirm password");
        confirmPasswordLabel.setForeground(new Color(191, 219, 254));
        confirmPasswordLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 9;
        formCard.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(18);
        styleTextField(confirmPasswordField);
        gbc.gridx = 1;
        formCard.add(confirmPasswordField, gbc);

        termsCheckBox = new JCheckBox("I agree to the terms and conditions");
        termsCheckBox.setOpaque(false);
        termsCheckBox.setForeground(new Color(226, 232, 240));
        gbc.gridx = 1;
        gbc.gridy = 10;
        formCard.add(termsCheckBox, gbc);

        newsletterCheckBox = new JCheckBox("Subscribe to our newsletter");
        newsletterCheckBox.setOpaque(false);
        newsletterCheckBox.setForeground(new Color(226, 232, 240));
        gbc.gridx = 1;
        gbc.gridy = 11;
        formCard.add(newsletterCheckBox, gbc);

        RoundedButton registerButton = new RoundedButton("Register Now", new Color(59, 130, 246), Color.WHITE);
        registerButton.setToolTipText("Click to complete registration");
        registerButton.addActionListener(e -> registerUser());
        gbc.gridx = 1;
        gbc.gridy = 12;
        formCard.add(registerButton, gbc);

        backgroundPanel.add(introPanel, BorderLayout.WEST);
        backgroundPanel.add(formCard, BorderLayout.EAST);
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBackground(new Color(15, 23, 42, 200));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(96, 165, 250), 1),
                new EmptyBorder(10, 12, 10, 12)));
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboBox.setBackground(new Color(15, 23, 42, 200));
        comboBox.setForeground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(96, 165, 250), 1));
    }

    private void registerUser() {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        String nameRegex = "^[a-zA-Z ]+$";
        String phoneRegex = "^[0-9]{10}$";
        String passwordRegex = ".{4,}";

        String name = nameField.getText();
        if (!name.matches(nameRegex)) {
            JOptionPane.showMessageDialog(this, "Name can only contain letters and spaces.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String username = usernameField.getText();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.", "Invalid Username", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (users.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose another one.", "Username Taken", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String email = emailField.getText();
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String phone = phoneField.getText();
        if (!phone.matches(phoneRegex)) {
            JOptionPane.showMessageDialog(this, "Phone number must be exactly 10 digits and cannot contain special characters or letters.", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!password.matches(passwordRegex)) {
            JOptionPane.showMessageDialog(this, "Password must be at least 4 characters long.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!termsCheckBox.isSelected()) {
            JOptionPane.showMessageDialog(this, "You must agree to the terms and conditions to register.", "Terms Not Accepted", JOptionPane.WARNING_MESSAGE);
            return;
        }

        users.put(username, password);
        saveUsers();
        JOptionPane.showMessageDialog(this, "Registration successful!\nName: " + name + "\nUsername: " + username + "\nEmail: " + email);
        this.dispose();
        new LoginForm();
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/users.ser"))) {
            oos.writeObject(users);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving users data!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private HashMap<String, String> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/users.ser"))) {
            System.out.println("Attempting to load users from file...");
            return (HashMap<String, String>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating new users HashMap...");
            return new HashMap<>();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading users data!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm());
    }
}
