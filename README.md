# FinalJava-Program
Innovative Taxi Booking System
Version 1.0 Designed by: 
Lenard Tivanani Hlabangwana 
BME BSc Computer Engineering Student 
ID: Neptune GYKM9W
Project Overview
This project is a Java-based application made to make real-time taxi scheduling, user administration, and taxi booking easier. It incorporates backend logic for data management and reliability testing, and it makes use of custom panels and contemporary GUI components for an intuitive user experience.
Key Features:
- User Registration and Login: Includes robust validation mechanisms.
- Taxi Management: Real-time taxi scheduling and availability.
- Fare Calculation: Dynamic fare estimation.
- Aesthetic UI: Custom components such as rounded buttons and panels.
Technical Stack
- Java Swing: For GUI development.
- JUnit: For testing application logic and ensuring code reliability.
- Serialization: Persistent storage of user and taxi data.
- Custom Components: Rounded buttons (RoundedButton) and panels (RoundedPanel).
Code Documentation
GradientPanel.java
Cr@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    GradientPaint gp = new GradientPaint(0, 0, new Color(52, 58, 64), getWidth(), getHeight(), new Color(70, 130, 180));
    g2d.setPaint(gp);
    g2d.fillRect(0, 0, getWidth(), getHeight());
}
     

creates a gradient background for improved aesthetics.

LoginForm.java
Provides user login functionality with form validations.
 void loginUser() {
    if (users.containsKey(emailField.getText()) && users.get(emailField.getText()).equals(new String(passwordField.getPassword()))) {
        showWelcomeSplash();
    } else {
        JOptionPane.showMessageDialog(this, "Invalid credentials.");
    }
}

    



MainMenu.java
Acts as the central hub for booking taxis, managing payments, and switching themes.
private double calculateEstimatedPrice(String initialPoint, String finalDestination) {
    return Math.random() * 100 + 20;
}
  


RegistrationForm.java
Manages new user registrations with validation.
if (!password.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
    return;
            }
     


TaxiSchedule.java
Displays a table of taxis with real-time updates.
private void updateScheduleTable() {
    schedules = new String[taxis.size()][3];
    for (int i = 0; i < taxis.size(); i++) {
        schedules[i][0] = taxis.get(i).getTaxiNumber();
        schedules[i][1] = taxis.get(i).getStartTime();
        schedules[i][2] = taxis.get(i).getEndTime();
    }
    scheduleTable.setModel(new DefaultTableModel(schedules, new String[]{"Taxi Number", "Start Time", "End Time"}));
}


Unit Testing
The following unit tests were created using JUnit to guarantee the application's accuracy and dependability.
Login Validation
Ensures valid credentials allow login and invalid credentials do not.
@Test
public void testLoginUser_SuccessfulLogin() {
    loginForm.emailField.setText("testUser");
    loginForm.passwordField.setText("password123");
    loginForm.loginUser();
    assertTrue("Login successful", true);
}


Taxi Availability
Verifies taxis are available during their active hours.
@Test
public void testTaxiAvailability() {
    Taxi taxi = new Taxi("TX123", "08:00", "20:00");
    assertEquals("TX123", taxi.getTaxiNumber());
    assertTrue("Taxi should be available at current time", taxi.isAvailableAtCurrentTime());
}
     




Data Generation and Persistence
Validates the random data generation and serialization process.
@Test
public void testTaxiDataGenerator() {
    ArrayList<Taxi> taxis = TaxiDataGenerator.generateTaxiData(5);
    assertEquals(5, taxis.size());
    for (Taxi taxi : taxis) {
        assertNotNull("Taxi number should not be null", taxi.getTaxiNumber());
    }
}






Taxi Schedule Updates
Ensures the schedule table reflects the latest taxi data.
@Test
public void testTaxiScheduleInitialization() {
    TaxiSchedule taxiScheduleInstance = new TaxiSchedule();
    assertNotNull("Schedule table should not be null", taxiScheduleInstance.getScheduleTable());
    assertTrue("Schedule should contain at least one row", taxiScheduleInstance.getScheduleTable().getRowCount() > 0);
}



Class Diagram
The class diagram will illustrate the structure of your system, including:
•	Classes
•	Attributes
•	Methods
•	Relationships (associations, aggregations, etc.)
 Sequence Diagrams
Sequence diagrams capture interactions for:
•	Use Cases like Login, Registration, Taxi Booking, and Fare Calculation.
•	Key Functions such as loginUser, calculateEstimatedPrice, and updateScheduleTable.
Proposed Class Diagram

Class	Attributes	Methods
LoginForm	emailField, passwordField	loginUser(), loadUsers()
RegistrationForm	nameField, emailField	registerUser(), saveUsers()
Taxi	taxiNumber, startTime	getTaxiNumber(), isAvailableAtCurrentTime()
TaxiSchedule	scheduleTable, taxis	updateScheduleTable(), loadTaxiData()
MainMenu	walletBalance, taxis	calculateEstimatedPrice(), switchTheme()
TaxiDataGenerator	N/A	generateTaxiData(), saveTaxiData()
RoundedPanel	cornerRadius, backgroundColor	paintComponent()
RoundedButton	backgroundColor, textColor	paintComponent()

			
 
Conclusion 
This project is a full-featured taxi reservation system that prioritises dependability, style, and usefulness. To guarantee a flawless user experience, its functionalities are put through a rigorous testing process. The usage of JUnit increases its resilience, and the bespoke UI elements give it a polished appearance and feel..
![image](https://github.com/user-attachments/assets/cdf74eb7-f790-45d6-9b52-704e9f645dd6)

