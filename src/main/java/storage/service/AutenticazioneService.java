package storage.service;

import application.entity.Film;
import application.entity.Persona;
import storage.model.PersonaDAO;

import java.sql.SQLException;
import java.util.Set;
import java.util.regex.Pattern;

public class AutenticazioneService {

    public static Persona doRegistrationService(String nome, String cognome, String email, String password) throws SQLException {
        // Verifica se l'email è duplicata
        if(PersonaDAO.checkEmailDuplicate(email)){
            return null;
        }

        // Verifica la validità dei dati di registrazione
        if(!isValidRegistration(email, password, nome, cognome)) {
            return null;
        }

        // Creazione di un oggetto Persona e registrazione nel database
        Persona p = new Persona(nome, cognome, email, password);
        return PersonaDAO.doRegistration(p);
    }

    public static Persona doLoginService(String email, String password) throws SQLException {
        // Verifica la validità dei dati di login
        if(!isValidLogin(email, password)) {
            return null;
        }

        // Esecuzione del login e restituzione dell'oggetto Persona
        Persona p = PersonaDAO.doLogin(email, password);
        assert p != null;
        return p;
    }

    public static void watchlistService(String tipoRichiesta, int id_persona, int id_film) {
        // Aggiunta o rimozione del film dalla watchlist in base alla richiesta
        if(tipoRichiesta != null) {
            PersonaDAO.addToWatchlist(id_persona, id_film);
        } else {
            PersonaDAO.removeFromWatchlist(id_persona, id_film);
        }
    }

    public static boolean isValidEmail(String email) {
        // Verifica del formato dell'email utilizzando una regex
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,3}$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isValidPassword(String password) {
        // Verifica del formato della password utilizzando una regex
        if (password.length() < 8 || password.length() > 30) {
            throw new IllegalArgumentException("La lunghezza della password deve essere compresa tra 8 e 30 caratteri.");
        }

        String passwordRegex = "^[A-Za-z0-9$!@?]+$";
        return Pattern.matches(passwordRegex, password);
    }

    public static boolean isValidString(String input) {
        // Verifica del formato della stringa utilizzando una regex
        if (input.length() < 3 || input.length() > 30) {
            throw new IllegalArgumentException("La lunghezza della stringa deve essere compresa tra 3 e 30 caratteri.");
        }

        String stringRegex = "^[a-zA-Z]*$";
        return Pattern.matches(stringRegex, input);
    }

    public static boolean isValidRegistration(String email, String password, String nome, String cognome) {
        // Verifica la validità di email, password, nome e cognome
        if (!isValidEmail(email))
            throw new IllegalArgumentException("Formato email non corretto!");

        if (!isValidPassword(password))
            throw new IllegalArgumentException("Formato password non corretto!");

        if (!isValidString(nome))
            throw new IllegalArgumentException("Formato nome non corretto!");

        if (!isValidString(cognome))
            throw new IllegalArgumentException("Formato cognome non corretto!");

        return true;
    }

    public static boolean isValidLogin(String email, String password) {
        // Verifica la validità di email e password per il login
        return isValidEmail(email) && isValidPassword(password);
    }
}
