package application.controller;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import storage.service.ListaService;
import storage.service.RecensioneService;

import java.io.IOException;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class AggiugniRecensioneServiceTest {

    Faker faker = new Faker();

    @Test
    public void valutazioneTroppoGrandeTest() {
        int valutazione = 6; // SBAGLIATO, troppo grande
        String descrizione = faker.lorem().sentence(); // CORRETTO
        Date dataInserimento = Date.valueOf("2024-01-01");
        String emailPersona = faker.internet().emailAddress(); // CORRETTO
        int idFilm = 1; //CORRETTO

        assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm),
                "Valutazione non corretta: Deve essere compresa tra 1 e 5!");
    }

    @Test
    public void valutazioneTroppoPiccolaTest() {
        int valutazione = 0; // SBAGLIATO, troppo grande
        String descrizione = faker.lorem().sentence(); // CORRETTO
        Date dataInserimento = Date.valueOf("2024-01-01");
        String emailPersona = faker.internet().emailAddress(); // CORRETTO
        int idFilm = 1; //CORRETTO

        assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm),
                "Valutazione non corretta: Deve essere compresa tra 1 e 5!");
    }

    //Descrizione formato
    @Test
    public void descrizioneTroppoLungaTest() {
        int valutazione = 3; // CORRETTO
        String descrizione = faker.lorem().characters(251); // SBAGLIATO
        Date dataInserimento = Date.valueOf("2024-01-01");
        String emailPersona = faker.internet().emailAddress(); // CORRETTO
        int idFilm = 1; //CORRETTO

        assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm),
                "Formato Descrizione non valido: Non deve superare i 250 caratteri!");
    }

    @Test
    public void formatoDescrizioneNonRispettato() {
        int valutazione = 3; // CORRETTO
        String descrizione = "Descrizione con carattere speciale: @"; // SBAGLIATO
        Date dataInserimento = Date.valueOf("2024-01-01");
        String emailPersona = faker.internet().emailAddress(); // CORRETTO
        int idFilm = 1; //CORRETTO

        assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm),
                "Formato Descrizione non valido!");
    }

    @Test
    public void testSuccesso() throws IOException {
        // CASO DI SUCCESSO.
        int valutazione = 3; // CORRETTO
        String descrizione = faker.lorem().sentence(); // CORRETTO
        Date dataInserimento = Date.valueOf("2024-01-01");
        String emailPersona = faker.internet().emailAddress(); // CORRETTO
        int idFilm = 1; //CORRETTO

        try (MockedStatic<ListaService> mocked = mockStatic(ListaService.class)) {
            mocked.when(() -> RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm))
                    .thenReturn(1);

            int result = RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm);
            assertEquals(1, result);
        }
    }

}
