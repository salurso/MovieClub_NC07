package storage.service;

import application.entity.Lista;
import storage.model.ListaDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ListaService {

    public static int doInsertService(String email, String nome, String descrizione, boolean privata) throws IOException {

        if(!validLista(email, nome, descrizione))
            return 0;

        Lista l = new Lista();
        l.setEmail_Persona(email);
        l.setNome(nome);
        l.setDescrizione(descrizione);
        l.setPrivata(privata);

        ListaDAO listaDAO = ListaDAO.getInstance();
        return listaDAO.doInsert(l);
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,3}$";
        return Pattern.matches(emailRegex, email);
    }

    private static boolean isValidNomeLista(String input) {
        if(input.length() < 1 || input.length() > 30) {
            throw new RuntimeException("Dimensione nome non corretta");
        }

        String stringRegex = "^[a-zA-Z]*$";
        return Pattern.matches(stringRegex, input);
    }

    private static boolean isValidDescrizione(String input) {
        if(input.length() > 100) {
            throw new RuntimeException("Dimensione descrizione non corretta");
        }

        String stringRegex = "^[a-zA-Z]*$";
        return Pattern.matches(stringRegex, input);
    }

    private static boolean validLista(String email, String nome, String descrizione) {
        if (!isValidEmail(email))
            throw new IllegalArgumentException("Formato email non corretto!");

        if (!isValidNomeLista(nome))
            throw new IllegalArgumentException("Formato nome non corretto!");

        if (!isValidDescrizione(descrizione))
            throw new IllegalArgumentException("Formato descrizione non valido!");

        return true;
    }

}
