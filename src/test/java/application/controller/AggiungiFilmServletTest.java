package application.controller;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import storage.service.FilmService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.Date;


public class AggiungiFilmServletTest {
    Faker faker = new Faker();

    @Test
    public void doInsertFilmService() throws IOException {
        //CASO DI SUCESSO
        String titolo = faker.lorem().characters(60);
        String regista = faker.name().fullName();
        String copertina = "";
        String trailer = "";
        Date date = Date.valueOf("2024-01-01");
        String durata = "00:05:05";
        String descrizione = faker.lorem().sentence();
        String genere = "Musical";

        try (MockedStatic<FilmService> mocked = mockStatic(FilmService.class)) {
            mocked.when(() -> FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere)).thenReturn("Film inserito");

            assertEquals("Film inserito", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));
        }
    }

    @Test
    public void insertFilm_titoloErrato() throws IOException {
        //TITOLO NON VALIDO
        String titolo = "";
        String regista = faker.name().fullName();
        String copertina = "";
        String trailer = "";
        Date date = Date.valueOf("2024-01-01");
        String durata = "00:05:05";
        String descrizione = faker.lorem().sentence();
        String genere = "Musical";

        assertEquals("Formato titolo non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));

        titolo = faker.lorem().characters(101);
        assertEquals("Formato titolo non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));
    }


    @Test
    public void insertFilm_registaErrato() throws IOException {
        //REGISTA NON VALIDO
        String titolo = faker.lorem().characters(60);
        String regista = "1234";
        String copertina = "";
        String trailer = "";
        Date date = Date.valueOf("2024-01-01");
        String durata = "00:05:05";
        String descrizione = faker.lorem().sentence();
        String genere = "Musical";

        assertEquals("Formato regista non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));
    }

    @Test
    public void insertFilm_copertinaErrato() throws IOException {
        //COPERTINA NON VALIDO
        String titolo = faker.lorem().characters(60);
        String regista = faker.name().fullName();
        String copertina = faker.lorem().characters(151);;
        String trailer = "";
        Date date = Date.valueOf("2024-01-01");
        String durata = "00:05:05";
        String descrizione = faker.lorem().sentence();
        String genere = "Musical";

        assertEquals("Formato copertina non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));
    }

    @Test
    public void insertFilm_trailerErrato() throws IOException {
        //TRAILER NON VALIDO
        String titolo = faker.lorem().characters(60);
        String regista = faker.name().fullName();
        String copertina = "";
        String trailer = faker.lorem().characters(101);
        Date date = Date.valueOf("2024-01-01");
        String durata = "00:05:05";
        String descrizione = faker.lorem().sentence();
        String genere = "Musical";

        assertEquals("Formato trailer non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));
    }

    @Test
    public void insertFilm_dataErrato() throws IOException {
        //DATA NON VALIDO
        String titolo = faker.lorem().characters(60);
        String regista = faker.name().fullName();
        String copertina = "";
        String trailer = "";
        Date date = null;
        String durata = "00:05:05";
        String descrizione = faker.lorem().sentence();
        String genere = "Musical";

        assertEquals("Formato data non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));
    }

    @Test
    public void insertFilm_durataErrato() throws IOException {
        //DURATA NON VALIDO
        String titolo = faker.lorem().characters(60);
        String regista = faker.name().lastName();
        String copertina = "";
        String trailer = "";
        Date date = Date.valueOf("2024-01-01");
        String durata = "00-05-05";
        String descrizione = faker.lorem().sentence();
        String genere = "Musical";
        System.out.println(regista);
        assertEquals("Formato durata non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));
    }

    @Test
    public void insertFilm_descrizioneErrato() throws IOException {
        //DESCRIZIONE NON VALIDO
        String titolo = faker.lorem().characters(60);
        String regista = faker.name().fullName();
        String copertina = "";
        String trailer = "";
        Date date = Date.valueOf("2024-01-01");
        String durata = "00:05:05";
        String descrizione = faker.lorem().sentence(221);
        String genere = "Musical";

        assertEquals("Formato descrizione non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));

        descrizione = null;
        assertEquals("Formato descrizione non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));
    }

    @Test
    public void insertFilm_genereErrato() throws IOException {
        //GENERE NON VALIDO
        String titolo = faker.lorem().characters(60);
        String regista = faker.name().fullName();
        String copertina = "";
        String trailer = "";
        Date date = Date.valueOf("2024-01-01");
        String durata = "00:05:05";
        String descrizione = faker.lorem().sentence();
        String genere = null;

        assertEquals("Formato genere non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));

        genere = faker.lorem().sentence(101);
        assertEquals("Formato genere non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));

        genere = faker.lorem().sentence();
        assertEquals("Formato genere non corretto!", FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere));
    }

//    Caso di test per la descrizione vuota.

}
