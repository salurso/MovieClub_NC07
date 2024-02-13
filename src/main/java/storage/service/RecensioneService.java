package storage.service;

import application.entity.Recensione;
import storage.model.FilmDAO;
import storage.model.RecensioneDAO;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

public class RecensioneService {

    // Metodo per salvare una nuova recensione nel database
    public static int doSaveService(int valutazione, String descrizione, Date dataInserimento, String emailPersona, int idFilm) throws IOException {
        validateRecensione(valutazione, descrizione, dataInserimento, emailPersona);

        // Creazione di un oggetto Recensione con i dati forniti
        Recensione r = new Recensione();
        r.setValutazione(valutazione);
        r.setDescrizione(descrizione);
        r.setDataInserimento(dataInserimento);
        r.setEmailPersona(emailPersona);
        r.setIdFilm(idFilm);

        // Ottenimento dell'istanza di RecensioneDAO
        RecensioneDAO rDAO = RecensioneDAO.getInstance();

        // Salvataggio della recensione nel database e restituzione dell'ID generato
        return rDAO.doSave(r);
    }

    // Metodo per eseguire la validazione dei dati della recensione
    public static void validateRecensione(int valutazione, String descrizione, Date dataInserimento, String emailPersona) {
        validateValutazione(valutazione);
        validateDescrizione(descrizione);
        validateDataInserimento(dataInserimento);
        validateEmailPersona(emailPersona);
    }

    // Metodo per validare la valutazione
    public static void validateValutazione(int valutazione) {
        if (valutazione < 1 || valutazione > 5) {
            throw new IllegalArgumentException("La valutazione deve essere compresa tra 1 e 5");
        }
    }

    // Metodo per validare la descrizione della recensione
    public static void validateDescrizione(String descrizione) {
        if (descrizione.length() > 250) {
            throw new IllegalArgumentException("La descrizione non deve superare i 250 caratteri");
        }

        String stringRegex = "^[A-Za-z0-9.,'\"\\s!?()-]+$";
        if (!Pattern.matches(stringRegex, descrizione)) {
            throw new IllegalArgumentException("Formato descrizione non valido");
        }
    }

    // Metodo per validare la data di inserimento della recensione
    public static void validateDataInserimento(Date dataInserimento) {
        if (dataInserimento == null) {
            throw new IllegalArgumentException("Formato data non corretto");
        }
    }

    // Metodo per validare il formato dell'email della persona che scrive la recensione
    public static void validateEmailPersona(String emailPersona) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,3}$";
        if (!Pattern.matches(emailRegex, emailPersona)) {
            throw new IllegalArgumentException("Formato email non corretto");
        }
    }
}
