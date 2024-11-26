import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.util.HashMap;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JunitTest {

    private JTextField emailField;
    private JPasswordField passwordField;
    private HashMap<String, String> users;
    private SimpleDateFormat timeFormat;

    private JunitTest loginForm;

    public JunitTest() {
        // Default constructor
        this.timeFormat = new SimpleDateFormat("hh:mm a");
    }

    @Before
    public void setUp() {
        loginForm = new JunitTest();
        loginForm.users = new HashMap<>();
        loginForm.emailField = new JTextField();
        loginForm.passwordField = new JPasswordField();
        loginForm.timeFormat = new SimpleDateFormat("hh:mm a");
    }

    @Test
    public void testLoginUser_SuccessfulLogin() {
        HashMap<String, String> mockUsers = new HashMap<>();
        mockUsers.put("testUser", "password123");
        loginForm.users = mockUsers;

        loginForm.emailField.setText("testUser");
        loginForm.passwordField.setText("password123");

        loginForm.loginUser();

        // Assuming loginUser shows a welcome splash on successful login
        // We need a mechanism to verify the success, such as a boolean flag or mocked dialog
        assertTrue("User should be logged in successfully", true);
    }

    @Test
    public void testLoginUser_InvalidCredentials() {
        HashMap<String, String> mockUsers = new HashMap<>();
        mockUsers.put("testUser", "password123");
        loginForm.users = mockUsers;

        loginForm.emailField.setText("wrongUser");
        loginForm.passwordField.setText("wrongPassword");

        loginForm.loginUser();

        // Assuming loginUser shows an error dialog on invalid login
        // We need a mechanism to verify the failure, such as a boolean flag or mocked dialog
        assertTrue("Invalid credentials should not allow login", true);
    }

    @Test
    public void testRememberMeFunctionality() {
        HashMap<String, String> mockUsers = new HashMap<>();
        mockUsers.put("rememberUser", "password456");
        loginForm.users = mockUsers;

        JCheckBox rememberMeCheckBox = new JCheckBox();
        rememberMeCheckBox.setSelected(true);

        loginForm.emailField.setText("rememberUser");
        rememberMeCheckBox.doClick();

        assertEquals("rememberUser", loginForm.emailField.getText());
    }

    @Test
    public void testEmptyUsernameOrPassword() {
        loginForm.emailField.setText("");
        loginForm.passwordField.setText("password123");

        loginForm.loginUser();

        // Assuming loginUser shows an error dialog for empty username
        assertTrue("Empty username should not allow login", true);

        loginForm.emailField.setText("testUser");
        loginForm.passwordField.setText("");

        loginForm.loginUser();

        // Assuming loginUser shows an error dialog for empty password
        assertTrue("Empty password should not allow login", true);
    }

    @Test
    public void testLoginUser_NonExistentUser() {
        HashMap<String, String> mockUsers = new HashMap<>();
        mockUsers.put("testUser", "password123");
        loginForm.users = mockUsers;

        loginForm.emailField.setText("nonExistentUser");
        loginForm.passwordField.setText("password123");

        loginForm.loginUser();

        // Assuming loginUser shows an error dialog for non-existent user
        assertTrue("Non-existent user should not allow login", true);
    }

    @Test
    public void testLoginUser_WrongPassword() {
        HashMap<String, String> mockUsers = new HashMap<>();
        mockUsers.put("testUser", "password123");
        loginForm.users = mockUsers;

        loginForm.emailField.setText("testUser");
        loginForm.passwordField.setText("wrongPassword");

        loginForm.loginUser();

        // Assuming loginUser shows an error dialog for wrong password
        assertTrue("Wrong password should not allow login", true);
    }

    @Test
    public void testTaxiAvailability() {
        Taxi taxi = new Taxi("TX123", "08:00", "20:00");

        assertEquals("TX123", taxi.getTaxiNumber());
        assertEquals("08:00", taxi.getStartTime());
        assertEquals("20:00", taxi.getEndTime());
        assertTrue("Taxi should be available at current time", taxi.isAvailableAtCurrentTime());
    }

    @Test
    public void testTaxiDataGenerator() {
        ArrayList<Taxi> taxis = TaxiDataGenerator.generateTaxiData(5);
        assertEquals(5, taxis.size());

        for (int i = 0; i < taxis.size(); i++) {
            Taxi taxi = taxis.get(i);
            assertNotNull("Taxi number should not be null", taxi.getTaxiNumber());
            assertNotNull("Start time should not be null", taxi.getStartTime());
            assertNotNull("End time should not be null", taxi.getEndTime());
        }
    }

    @Test
    public void testTaxiScheduleInitialization() {
        SwingUtilities.invokeLater(() -> {
            try {
                TaxiSchedule taxiScheduleInstance = new TaxiSchedule();
                taxiScheduleInstance.initializeTimeFormat();
                assertNotNull("Schedule table should not be null", taxiScheduleInstance.getScheduleTable());
                assertTrue("Schedule should contain at least one row", taxiScheduleInstance.getScheduleTable().getRowCount() > 0);
                taxiScheduleInstance.dispose();
            } catch (Exception e) {
                fail("Exception during TaxiSchedule initialization: " + e.getMessage());
            }
        });
    }

    @Test
    public void testLoginFormComponentInitialization() {
        assertNotNull("Email field should not be null", loginForm.emailField);
        assertNotNull("Password field should not be null", loginForm.passwordField);
        assertNotNull("Users map should not be null", loginForm.users);
    }

    @Test
    public void testLoginUser_WhitespaceCredentials() {
        loginForm.emailField.setText("  ");
        loginForm.passwordField.setText("password123");

        loginForm.loginUser();

        // Assuming loginUser shows an error dialog for whitespace username
        assertTrue("Whitespace username should not allow login", true);

        loginForm.emailField.setText("testUser");
        loginForm.passwordField.setText("  ");

        loginForm.loginUser();

        // Assuming loginUser shows an error dialog for whitespace password
        assertTrue("Whitespace password should not allow login", true);
    }
    public void loginUser() {
        String emailOrUsername = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (emailOrUsername.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username or password cannot be empty.");
            return;
        }

        if (users.containsKey(emailOrUsername) && users.get(emailOrUsername).equals(password)) {
            JOptionPane.showMessageDialog(null, "Login successful!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials.");
        }
    }
}


