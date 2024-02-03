package application.controller;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import storage.service.RecensioneService;

import java.io.IOException;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class AggiugniRecensioneServiceTest {

    private static final String VALUTAZIONE_ERROR_MESSAGE = "Valutazione non corretta: Deve essere compresa tra 1 e 5!";
    private static final String DESCRIZIONE_LENGTH_ERROR_MESSAGE = "Formato Descrizione non valido: Non deve superare i 250 caratteri!";
    private static final String DESCRIZIONE_FORMAT_ERROR_MESSAGE = "Formato Descrizione non valido!";

    @Test
    public void valutazioneTroppoGrandeTest() {
        assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(6, "descrizione", Date.valueOf("2024-01-01"), "email", 1),
                VALUTAZIONE_ERROR_MESSAGE);
    }

    @Test
    public void valutazioneTroppoPiccolaTest() {
        assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(0, "descrizione", Date.valueOf("2024-01-01"), "email", 1),
                VALUTAZIONE_ERROR_MESSAGE);
    }

    @Test
    public void descrizioneTroppoLungaTest() {
        assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(3, "descrizione lunga oltre i 250 caratteri", Date.valueOf("2024-01-01"), "email", 1),
                DESCRIZIONE_LENGTH_ERROR_MESSAGE);
    }

    @Test
    public void formatoDescrizioneNonRispettato() {
        assertThrows(IllegalArgumentException.class,
                () -> RecensioneService.doSaveService(3, "Descrizione con carattere speciale: @", Date.valueOf("2024-01-01"), "email", 1),
                DESCRIZIONE_FORMAT_ERROR_MESSAGE);
    }

    @Test
    public void testSuccesso() throws IOException {
        int valutazione = 3;
        String descrizione = "descrizione";
        Date dataInserimento = Date.valueOf("2024-01-01");
        String emailPersona = "email";
        int idFilm = 1;

        try (MockedStatic<RecensioneService> mocked = mockStatic(RecensioneService.class)) {
            mocked.when(() -> RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm))
                    .thenReturn(1);

            int result = RecensioneService.doSaveService(valutazione, descrizione, dataInserimento, emailPersona, idFilm);
            assertEquals(1, result);
        }
    }
}
