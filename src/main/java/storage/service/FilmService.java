package storage.service;

import application.entity.Film;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.regex.Pattern;

import storage.model.FilmDAO;

public class FilmService {

    public static String doInsertFilmService(String titolo, String regista, String copertina, String trailer, Date data, String durata, String descrizione, String genere) throws IOException {

        if(!isValidTitolo(titolo))
            return "Formato titolo non corretto!";

        if(!isValidRegista(regista))
            return "Formato regista non corretto!";

        if(!isValidCopertina(copertina))
            return "Formato copertina non corretto!";

        if(!isValidTrailer(trailer))
            return "Formato trailer non corretto!";

        if(!isValidData(data))
            return "Formato data non corretto!";

        if(!isValidDurata(durata))
            return "Formato durata non corretto!";

        if(!isValidDesccrizione(descrizione))
            return "Formato descrizione non corretto!";

        if(!isValidGenere(genere))
            return "Formato genere non corretto!";


        Film f = new Film(titolo, descrizione, data, regista, Time.valueOf(durata), genere, copertina, trailer);
        FilmDAO.doInsert(f);
        return "Film inserito";
    }

    private static boolean isValidTitolo(String titolo) {
        if(!titolo.isEmpty() && titolo.length()<=60)
            return true;
        return false;
    }

    private static boolean isValidRegista(String regista) {
        String stringRegex = "^[a-zA-Z]*$";
        if(Pattern.matches(stringRegex, regista) && regista.length()<=60 && !regista.isEmpty())
            return true;
        return false;
    }

    private static boolean isValidCopertina(String copertina) {
        if(copertina.length()<=150)
            return true;
        return false;
    }

    private static boolean isValidTrailer(String trailer) {
        if(trailer.length()<=100)
            return true;
        return false;
    }

    public static boolean isValidData(Date data){
        if(data!=null)
            return true;
        return false;
    }

    public static boolean isValidDurata(String durata){
        String stringRegex = "^([0-9]{2}):([0-9]{2}):([0-9]{2})$";
        if(Pattern.matches(stringRegex, durata) && durata!=null)
            return true;
        return false;
    }

    public static boolean isValidDesccrizione(String descrizione){
        if(descrizione!=null && descrizione.length()<=220)
            return true;
        return false;
    }

    public static boolean isValidGenere(String genere){
        if(!genere.isEmpty() && genere.length()<=100)
            return true;
        return false;
    }
}
