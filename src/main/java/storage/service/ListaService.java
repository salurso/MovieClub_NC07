package storage.service;

import application.entity.Lista;
import storage.model.ListaDAO;

import java.io.IOException;
import java.util.regex.Pattern;

public class ListaService {

    public static int doInsertService(String email, String nome, String descrizione, boolean privata) throws IOException {
        validateLista(email, nome, descrizione); // Validazione generale

        Lista l = new Lista();
        l.setEmail_Persona(email);
        l.setNome(nome);
        l.setDescrizione(descrizione);
        l.setPrivata(privata);

        ListaDAO listaDAO = ListaDAO.getInstance();

        if (listaDAO.isListaDuplicata(nome, email)) {
            throw new IllegalArgumentException("Una lista con lo stesso nome è già associata alla stessa email!");
        }

        return listaDAO.doInsert(l);
    }

    public static void validateLista(String email, String nome, String descrizione) {
        validateEmail(email);
        validateNomeLista(nome);
        validateDescrizione(descrizione);
    }

    public static void validateEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,3}$";
        if (!Pattern.matches(emailRegex, email)) {
            throw new IllegalArgumentException("Formato email non corretto!");
        }
    }

    public static void validateNomeLista(String input) {
        if (input.length() < 1 || input.length() > 30) {
            throw new IllegalArgumentException("Formato nome non corretto: Non deve superare i 30 caratteri!");
        }

        String stringRegex = "^[a-zA-Z]*$";
        if (!Pattern.matches(stringRegex, input)) {
            throw new IllegalArgumentException("Il nome deve contenere solo lettere.");
        }
    }

    public static boolean isListaDuplicata(String nome, String email) throws IOException {
        ListaDAO listaDAO = ListaDAO.getInstance();
        return listaDAO.isListaDuplicata(nome, email);
    }

    public static void validateDescrizione(String input) {
        if (input.length() > 100) {
            throw new IllegalArgumentException("Formato descrizione non valido: Non deve superare i 100 caratteri!");
        }

        String stringRegex = "^[a-zA-Z]*$";
        if (!Pattern.matches(stringRegex, input)) {
            throw new IllegalArgumentException("La descrizione deve contenere solo lettere.");
        }
    }
}
