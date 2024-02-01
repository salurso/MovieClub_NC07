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

    @Test
    public void doRegistrationServiceTest() throws SQLException {
        Faker faker = new Faker(new Locale("it"));
        String nome1 = "Stefano";
        String cognome1 = "Guida";
        String email1 = "stefan";
        String password1 = "Password@1";

        assertThrows(IllegalArgumentException.class,
                () -> AutenticazioneService.doRegistrationService(nome1, cognome1, email1, password1), "Formato email non corretto!");

        // caso di successo
        String nome = "Stefano";
        String cognome = "Guida";
        String email = "stefanoguida.gs@gmail.com";
        String password = "Password@1";

        try (MockedStatic<AutenticazioneService> mocked = mockStatic(AutenticazioneService.class)) {
            mocked.when(() -> AutenticazioneService.doRegistrationService(nome, cognome, email, password))
                    .thenReturn(new Persona(nome, cognome, email, password));

            Persona oracle = new Persona(nome, cognome, email, password);
            assertEquals(oracle, AutenticazioneService.doRegistrationService(nome, cognome, email, password));
        }

    }
}
