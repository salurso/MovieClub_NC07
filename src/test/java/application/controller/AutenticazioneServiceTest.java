package application.controller;

import application.entity.Persona;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import storage.service.AutenticazioneService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.Locale;

public class AutenticazioneServiceTest {
    private final Faker faker = new Faker(new Locale("it"));

    @Test
    public void testSuccessfulRegistration() throws SQLException {
        String nome = faker.name().firstName();
        String cognome = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        try (MockedStatic<AutenticazioneService> mocked = mockStatic(AutenticazioneService.class)) {
            Persona persona = new Persona(nome, cognome, email, password);

            mocked.when(() -> AutenticazioneService.doRegistrationService(nome, cognome, email, password))
                    .thenReturn(persona);

            Persona result = AutenticazioneService.doRegistrationService(nome, cognome, email, password);
            assertEquals(persona, result);
        }
    }

    @Test
    public void testEmailNonCorretta() {
        Faker faker = new Faker();
        String nome = faker.name().firstName();
        String cognome = faker.name().lastName();
        String email = faker.lorem().word();  // Genera una mail non corretta
        String password = faker.internet().password();

        assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                "Formato email non corretto!");
    }


    @Test
    public void testInvalidNomeLength() {
        String nome = faker.name().firstName().substring(0, 2); // Nome troppo corto
        String cognome = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                "La lunghezza della stringa deve essere compresa tra 3 e 30 caratteri.");
    }

    @Test
    public void testInvalidNomeFormat() {
        String nome = faker.name().firstName() + "1"; // Nome con caratteri non consentiti
        String cognome = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                "Formato nome non corretto!");
    }

    @Test
    public void testInvalidCognomeLength() {
        String nome = faker.name().firstName();
        String cognome = faker.name().lastName().substring(0, 2); // Cognome troppo corto
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                "La lunghezza della stringa deve essere compresa tra 3 e 30 caratteri.");
    }
    @Test
    public void testInvalidCognomeFormat() {
        String nome = faker.name().firstName();
        String cognome = faker.name().lastName() + "1"; // Cognome con caratteri non consentiti
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                "Formato cognome non corretto!");
    }

    @Test
    public void testInvalidPasswordLength() {
        String nome = faker.name().firstName();
        String cognome = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password().substring(0, 7); // Password troppo corta

        assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                "La lunghezza della password deve essere compresa tra 3 e 8 caratteri.");
    }

    @Test
    public void testInvalidPasswordFormat() {
        String nome = faker.name().firstName();
        String cognome = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password() + "%"; // Password con caratteri non consentiti

        assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                "Formato password non corretto!");
    }



    @Test
    public void testEmailDuplicata() throws SQLException {
        Faker faker = new Faker();
        String nome = faker.name().firstName();
        String cognome = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        try (MockedStatic<AutenticazioneService> mocked = mockStatic(AutenticazioneService.class)) {
            mocked.when(() -> AutenticazioneService.doRegistrationService(nome, cognome, email, password))
                    .thenReturn(null);

            assertNull(AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                    "doRegistrationService deve restituire null in caso di email duplicata");
        }
    }

}
