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

        if(!isValidDescrizione(descrizione))
            return "Formato descrizione non corretto!";

        if(!isValidGenere(genere))
            return "Formato genere non corretto!";


        Film f = new Film(titolo, descrizione, data, regista, Time.valueOf(durata), genere, copertina, trailer);
        FilmDAO.doInsert(f);
        return "Film inserito";
    }

    private static boolean isValidTitolo(String titolo) {
        if(titolo.length()!=0 && titolo.length()<=60)
            return true;
        return false;
    }

    private static boolean isValidRegista(String regista) {
        String stringRegex = "^[a-zA-Z .']*$";
        if(Pattern.matches(stringRegex, regista) && regista.length()<=60 && regista.length()!=0)
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

    public static boolean isValidDescrizione(String descrizione){
        if(descrizione!=null && descrizione.length()<=220)
            return true;
        return false;
    }

    public static boolean isValidGenere(String genere){
        String[] generi = {"Documentary", "Biography", "Drama", "Music", "Horror", "Sci-Fi", "Crime", "Mystery", "Romance", "Thriller", "Adventure", "Comedy", "Action", "Sport", "Fantasy", "History", "Family", "Animation", "War", "Western", "Musical"};
        boolean isValid = true;
        if(genere!=null) {
            String[] genereArray = genere.split(",\\s*"); // Divide la stringa genere in elementi separati da virgola e spazio

            for (String g : genereArray) { // Itera su ogni elemento della stringa genere
                boolean found = false;
                for (String validGenere : generi) { // Confronta l'elemento con gli elementi dell'array generi
                    if (g.equals(validGenere)) {
                        found = true;
                        break;
                    }
                }
                // Se l'elemento non è stato trovato nell'array generi, imposta la variabile booleana su false e interrompi il ciclo
                if (!found) {
                    isValid = false;
                    break;
                }
            }
        }else
            return false;

        if(isValid && genere.length()<=100)
            return true;
        return false;
    }
}
