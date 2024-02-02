package application.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.sql.SQLException;

public class AggiungiFilmServletTest {
    @Test
    public void doInsertFilmService() throws SQLException {
        String titolo = "titolo";
        String regista = "regista";
        String copertina = "copertina";
        String trailer = "trailer";
        Date date = Date.valueOf("2024-01-01");
        String durata = "durata";
        String descrizione = "descrizione";
        String genere = null;
        // Ottieni i valori delle checkbox dalla richiesta
//        String[] generiSelezionati = "generi";

        // Verifica se almeno una checkbox è stata selezionata
//        if (generiSelezionati != null && generiSelezionati.length > 0) {
//            // Concatena i valori in una stringa separata da virgole
//            genere = String.join(", ", generiSelezionati);
//        }
    }
}
