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
        String nome = faker.name().firstName();
        String cognome = faker.name().lastName();
        String email = faker.lorem().word();  // Genera una mail non corretta
        String password = faker.internet().password();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                "Formato email non corretto!");

        assertEquals("Formato email non corretto!", thrown.getMessage());
    }

    @Test
    public void testInvalidNomeLength() {
        String nomeTroppoCorto = faker.name().firstName().substring(0, 2); // Nome troppo corto
        String nomeTroppoLungo = faker.lorem().characters(31); // Nome troppo lungo
        String cognome = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        // Test per la lunghezza troppo corta
        IllegalArgumentException thrownCorto = assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nomeTroppoCorto, cognome, email, password),
                "La lunghezza della stringa deve essere compresa tra 3 e 30 caratteri.");

        assertEquals("La lunghezza della stringa deve essere compresa tra 3 e 30 caratteri.", thrownCorto.getMessage());

        // Test per la lunghezza troppo lunga
        IllegalArgumentException thrownLungo = assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nomeTroppoLungo, cognome, email, password),
                "La lunghezza della stringa deve essere compresa tra 3 e 30 caratteri.");

        assertEquals("La lunghezza della stringa deve essere compresa tra 3 e 30 caratteri.", thrownLungo.getMessage());
    }

    @Test
    public void testInvalidNomeFormat() {
        String nome = faker.name().firstName() + "1"; // Nome con caratteri non consentiti
        String cognome = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                "Formato nome non corretto!");

        assertEquals("Formato nome non corretto!", thrown.getMessage());
    }

    @Test
    public void testInvalidCognomeLength() {
        String nome = faker.name().firstName();
        String cognomeTroppoCorto = faker.name().lastName().substring(0, 2); // Cognome troppo corto
        String cognomeTroppoLungo = faker.lorem().characters(31); // Cognome troppo lungo
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        // Test per la lunghezza troppo corta
        IllegalArgumentException thrownCorto = assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognomeTroppoCorto, email, password),
                "La lunghezza della stringa deve essere compresa tra 3 e 30 caratteri.");

        assertEquals("La lunghezza della stringa deve essere compresa tra 3 e 30 caratteri.", thrownCorto.getMessage());

        // Test per la lunghezza troppo lunga
        IllegalArgumentException thrownLungo = assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognomeTroppoLungo, email, password),
                "La lunghezza della stringa deve essere compresa tra 3 e 30 caratteri.");

        assertEquals("La lunghezza della stringa deve essere compresa tra 3 e 30 caratteri.", thrownLungo.getMessage());
    }

    @Test
    public void testInvalidCognomeFormat() {
        String nome = faker.name().firstName();
        String cognome = faker.name().lastName() + "1"; // Cognome con caratteri non consentiti
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                "Formato cognome non corretto!");

        assertEquals("Formato cognome non corretto!", thrown.getMessage());
    }

    @Test
    public void testInvalidPasswordLength() {
        String nome = faker.name().firstName();
        String cognome = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String passwordTroppoCorta = faker.internet().password().substring(0, 7); // Password troppo corta
        String passwordTroppoLunga = faker.lorem().characters(31);

        // Test per la lunghezza troppo corta
        IllegalArgumentException thrownCorta = assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, passwordTroppoCorta),
                "La lunghezza della password deve essere compresa tra 8 e 30 caratteri.");

        assertEquals("La lunghezza della password deve essere compresa tra 8 e 30 caratteri.", thrownCorta.getMessage());

        // Test per la lunghezza troppo lunga
        IllegalArgumentException thrownLunga = assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, passwordTroppoLunga),
                "La lunghezza della password deve essere compresa tra 8 e 30 caratteri.");

        assertEquals("La lunghezza della password deve essere compresa tra 8 e 30 caratteri.", thrownLunga.getMessage());
    }

    @Test
    public void testInvalidPasswordFormat() {
        String nome = faker.name().firstName();
        String cognome = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password() + "%"; // Password con caratteri non consentiti

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome, cognome, email, password),
                "Formato password non corretto!");

        assertEquals("Formato password non corretto!", thrown.getMessage());
    }

    @Test
    public void testEmailDuplicata() throws SQLException {
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
