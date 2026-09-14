# Taxi Booking System

A desktop taxi booking demonstration built with Java Swing. It includes registration and login, taxi schedules, fare estimates, wallet controls, and a dashboard.

## Requirements

- Java 8 or newer
- Maven 3.6 or newer

## Run

From the project root:

```sh
mvn test
mvn exec:java
```

The application starts at the login screen. Run `mvn exec:java -Dexec.mainClass=RegistrationForm` to open registration directly. Taxi data can be regenerated with `mvn exec:java -Dexec.mainClass=TaxiDataGenerator`.

## Project layout

| Path | Purpose |
| --- | --- |
| `src/main/java/` | Swing UI and application classes |
| `src/main/resources/images/` | Image assets on the runtime classpath |
| `src/test/java/` | JUnit tests |
| `data/` | Serialized user and taxi data |

Run the application from the project root so the relative `data/` paths resolve correctly. The existing serialized files were kept in `data/` to preserve the sample records.

The current application is a demonstration: fare and availability estimates are randomized, and user passwords are stored in the serialized file as plain text. Avoid real credentials.
