package storage.service;

import application.entity.Film;
import application.entity.Persona;
import storage.model.PersonaDAO;

import java.sql.SQLException;
import java.util.Set;
import java.util.regex.Pattern;

public class AutenticazioneService {

    public static Persona doRegistrationService(String nome, String cognome, String email, String password) throws SQLException {
        if(PersonaDAO.checkEmailDuplicate(email)){
            return null;
        }

        if(!isValidRegistration(email, password, nome, cognome)) {
            return null;
        }

        Persona p = new Persona(nome, cognome, email, password);
        PersonaDAO.doRegistration(p);
        return p;

    }

    public static Persona doLoginService(String email, String password) throws SQLException {
        if(!isValidLogin(email, password)) {
            return null;
        }
        Persona p = PersonaDAO.doLogin(email, password);
        assert p != null;
        p.setWatchlist(PersonaDAO.getWatchlistFilms(p.getId()));
        return p;
    }

    public static void watchlistService(String tipoRichiesta, int id_persona, int id_film) {
        if(tipoRichiesta != null) {
            PersonaDAO.addToWatchlist(id_persona, id_film);
        } else {
            PersonaDAO.removeFromWatchlist(id_persona, id_film);
        }
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,3}$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^[A-Za-z0-9$!@?]+$";
        return Pattern.matches(passwordRegex, password) && password.length() >= 8 && password.length() <= 30;
    }

    public static boolean isValidString(String input) {
        String stringRegex = "^[a-zA-Z]*$";
        return Pattern.matches(stringRegex, input);
    }

    public static boolean isValidRegistration(String email, String password, String nome, String cognome) {
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
        return isValidEmail(email) && isValidPassword(password);
    }





}
