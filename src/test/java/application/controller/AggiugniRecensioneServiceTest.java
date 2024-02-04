package application.controller;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import storage.service.AutenticazioneService;
import storage.service.RecensioneService;

import java.io.IOException;
import java.sql.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class AggiugniRecensioneServiceTest {

    private static final String VALUTAZIONE_ERROR_MESSAGE = "La valutazione deve essere compresa tra 1 e 5";
    private static final String DESCRIZIONE_LENGTH_ERROR_MESSAGE = "La descrizione non deve superare i 250 caratteri";
    private static final String DESCRIZIONE_FORMAT_ERROR_MESSAGE = "Formato descrizione non valido";

    Faker faker = new Faker();

    @Test
    public void valutazioneTroppoPiccolaTest() {
        int valutazione = faker.number().numberBetween(-5, 0);
        String descrizione = faker.lorem().sentence(10);
        Date dataInserimento = new Date(faker.date().future(1, TimeUnit.DAYS).getTime());
        String emailPersona = faker.internet().emailAddress();
        int idFilm = faker.number().numberBetween(1, 100);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm),
                VALUTAZIONE_ERROR_MESSAGE);

        assertEquals(VALUTAZIONE_ERROR_MESSAGE, thrown.getMessage());
    }

    @Test
    public void valutazioneTroppoGrandeTest() {
        int valutazione = faker.number().numberBetween(6, 10);
        String descrizione = faker.lorem().characters(10);
        Date dataInserimento = new Date(faker.date().future(1, TimeUnit.DAYS).getTime());
        String emailPersona = faker.internet().emailAddress();
        int idFilm = faker.number().numberBetween(1, 100);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm),
                VALUTAZIONE_ERROR_MESSAGE);

        assertEquals(VALUTAZIONE_ERROR_MESSAGE, thrown.getMessage());
    }

    @Test
    public void descrizioneTroppoLungaTest() {
        int valutazione = faker.number().numberBetween(1, 5);
        String descrizione = faker.lorem().characters(251);
        Date dataInserimento = new Date(faker.date().future(1, TimeUnit.DAYS).getTime());
        String emailPersona = faker.internet().emailAddress();
        int idFilm = faker.number().numberBetween(1, 100);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm),
                DESCRIZIONE_LENGTH_ERROR_MESSAGE);

        assertEquals(DESCRIZIONE_LENGTH_ERROR_MESSAGE, thrown.getMessage());
    }



    @Test
    public void formatoDescrizioneNonRispettato() {
        int valutazione = faker.number().numberBetween(1, 5);
        String descrizione = faker.lorem() + "@";
        Date dataInserimento = new Date(faker.date().future(1, TimeUnit.DAYS).getTime());
        String emailPersona = faker.internet().emailAddress();
        int idFilm = faker.number().numberBetween(1, 100);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm),
                DESCRIZIONE_FORMAT_ERROR_MESSAGE);

        assertEquals(DESCRIZIONE_FORMAT_ERROR_MESSAGE, thrown.getMessage());
    }

    @Test
    public void testSuccesso() throws IOException {
        int valutazione = faker.number().numberBetween(1, 5);
        String descrizione = faker.lorem().sentence(10);
        Date dataInserimento = new Date(faker.date().future(1, TimeUnit.DAYS).getTime());
        String emailPersona = faker.internet().emailAddress();
        int idFilm = faker.number().numberBetween(1, 100);

        try (MockedStatic<RecensioneService> mocked = mockStatic(RecensioneService.class)) {
            mocked.when(() -> RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm))
                    .thenReturn(1);

            int result = RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm);
            assertEquals(1, result);
        }
    }




}
