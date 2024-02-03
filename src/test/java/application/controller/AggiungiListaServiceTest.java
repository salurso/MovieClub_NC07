package application.controller;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import storage.model.ListaDAO;
import storage.service.ListaService;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class AggiungiListaServiceTest {
    Faker faker = new Faker();
    boolean privata = true; // CORRETTO

    @Test
    public void nomeTroppoLungoTest() {
        String email = faker.internet().emailAddress(); //CORRETTO
        String nome = faker.lorem().characters(31); //SBAGLIATO
        String descrizione = faker.lorem().sentence(); //CORRETTO

        assertThrows(IllegalArgumentException.class,
                () -> ListaService.doInsertService(email, nome, descrizione, privata),
                "Formato Nome non corretto: Non deve superare i 30 caratteri!");
    }

    @Test
    public void emailNonValidaTest() {
        String email = "email-non-valida"; // SBAGLIATO
        String nome = faker.name().username(); //CORRETTO
        String descrizione = faker.lorem().sentence(); //CORRETTO

        assertThrows(IllegalArgumentException.class,
                () -> ListaService.doInsertService(email, nome, descrizione, privata),
                "Formato Email non corretto!");
    }

    @Test
    public void descrizioneTroppoLungaTest() {
        String email = faker.internet().emailAddress(); //CORRETTO
        String nome = faker.name().username(); //CORRETTO
        String descrizione = faker.lorem().characters(101); //SBAGLIATO

        assertThrows(IllegalArgumentException.class,
                () -> ListaService.doInsertService(email, nome, descrizione, privata),
                "Formato Descrizione non valido: Non deve superare i 100 caratteri!");
    }

    @Test
    public void listaDuplicataTest() throws IOException {
        String email = "andresalurso88@gmail.com";
        String nomeLista = "Ciao";

        // Simula che esiste già una lista con lo stesso nome ed email Utente
        try (MockedStatic<ListaDAO> mockedDAO = mockStatic(ListaDAO.class)) {
            ListaDAO listaDAOMock = mock(ListaDAO.class);
            mockedDAO.when(ListaDAO::getInstance).thenReturn(listaDAOMock);
            when(listaDAOMock.isListaDuplicata(nomeLista, email)).thenReturn(true);

            // Verifica che chiamare il metodo doInsertService lanci un'eccezione
            assertThrows(IllegalArgumentException.class,
                    () -> ListaService.doInsertService(email, nomeLista, "Descrizione", true),
                    "Una lista con lo stesso nome è già associata alla stessa email!");
        }
    }

    @Test
    public void testSuccesso() throws IOException {
        // CASO DI SUCCESSO.
        String email = faker.internet().emailAddress();
        String nome = faker.name().username();
        String descrizione = faker.lorem().sentence();

        try (MockedStatic<ListaService> mocked = mockStatic(ListaService.class)) {
            mocked.when(() -> ListaService.doInsertService(email, nome, descrizione, privata))
                    .thenReturn(1);

            int result = ListaService.doInsertService(email, nome, descrizione, privata);
            assertEquals(1, result);
        }
    }
}
