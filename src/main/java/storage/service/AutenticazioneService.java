package storage.service;

import application.entity.Persona;
import storage.model.PersonaDAO;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class AutenticazioneService {

    public static Persona doRegistrationService(String nome, String cognome, String email, String passward) throws SQLException {

        if(PersonaDAO.checkEmailDuplicate(email)){
            return null;
        }

        if(!isValidRegistration(email, passward, nome, cognome)) {
            return null;
        }

        Persona p = new Persona(nome, cognome, email, passward);
        PersonaDAO.doRegistration(p);
        return p;

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
        return isValidEmail(email) && isValidPassword(password) && isValidString(nome) && isValidString(cognome);
    }

}
