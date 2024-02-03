package storage.service;

import application.entity.Recensione;
import storage.model.RecensioneDAO;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

public class RecensioneService {

    public static int doSaveService(int valutazione, String descrizione, Date dataInserimento, String emailPersona, int idFilm) throws IOException {
        validateRecensione(valutazione, descrizione, dataInserimento, emailPersona, idFilm); //validazione generale

        Recensione r = new Recensione();
        r.setValutazione(valutazione);
        r.setDescrizione(descrizione);
        r.setDataInserimento(dataInserimento);
        r.setEmailPersona(emailPersona);
        r.setIdFilm(idFilm);

        RecensioneDAO rDAO = RecensioneDAO.getInstance();

       rDAO.doSave(r);
       return  rDAO.doSave(r);
    }

    public static void validateRecensione(int valutazione, String descrizione, Date dataInserimento, String emailPersona, int idFilm){
        validateValutazione(valutazione);
        validateDescrizione(descrizione);
        validateDataInserimento(dataInserimento);
        validateEmailPersona(emailPersona);
        //validateIdFilm(idFilm);
    }

    public static void validateValutazione(int valutazione){
        int n1 = 1;
        int n2 = 2;
        int n3 = 3;
        int n4 = 4;
        int n5 = 5;
        if(n1 != valutazione && n2 != valutazione && n3 != valutazione && n4 != valutazione && n5 != valutazione){
            throw new RuntimeException("Valutazione non corretta");
        }
    }

    public static void validateDescrizione(String descrizione){
        if (descrizione.length() > 250) {
            throw new IllegalArgumentException("Formato descrizione non valido: Non deve superare i 250 caratteri!");
        }

        String stringRegex = "^[A-Za-z0-9.,’”\\s!?()-]";
        if (!Pattern.matches(stringRegex, descrizione)) {
            throw new IllegalArgumentException("Formato descrizione non rispettata.");
        }
    }

    public static boolean validateDataInserimento(Date dataInserimento){
        if(dataInserimento!=null)
            return true;
        return false;
    }

    public static void validateEmailPersona(String emailPersona){
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,3}$";
        if (!Pattern.matches(emailRegex, emailPersona)) {
            throw new IllegalArgumentException("Formato email non corretto!");
        }
    }

}
