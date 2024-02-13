package storage.service;

import application.entity.Lista;
import storage.model.ListaDAO;

import java.io.IOException;
import java.util.regex.Pattern;

public class ListaService {

    // Metodo per inserire una nuova lista nel database
    public static int doInsertService(String email, String nome, String descrizione, boolean privata) throws IOException {
        validateLista(email, nome, descrizione); // Esegue la validazione generale

        // Creazione di un oggetto Lista con i dati forniti
        Lista l = new Lista();
        l.setEmail_Persona(email);
        l.setNome(nome);
        l.setDescrizione(descrizione);
        l.setPrivata(privata);

        // Ottenimento dell'istanza di ListaDAO
        ListaDAO listaDAO = ListaDAO.getInstance();

        // Controllo se esiste già una lista con lo stesso nome per l'utente corrente
        if (listaDAO.isListaDuplicata(nome, email)) {
            throw new IllegalArgumentException("Errore: Lista già esistente per l'utente corrente!");
        }

        // Inserimento della lista nel database e restituzione dell'ID generato
        return listaDAO.doInsert(l);
    }

    // Metodo per eseguire la validazione dei dati della lista
    public static void validateLista(String email, String nome, String descrizione) {
        validateEmail(email);
        validateNomeLista(nome);
        validateDescrizione(descrizione);
    }

    // Metodo per validare il formato dell'email
    public static void validateEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,3}$";
        if (!Pattern.matches(emailRegex, email)) {
            throw new IllegalArgumentException("Formato email non corretto!");
        }
    }

    // Metodo per validare il formato del nome della lista
    public static void validateNomeLista(String input) {
        if (input.length() < 1 || input.length() > 30) {
            throw new IllegalArgumentException("Formato nome non corretto: Non deve superare i 30 caratteri!");
        }
    }

    // Metodo per verificare se esiste una lista con lo stesso nome per l'utente corrente
    public static boolean isListaDuplicata(String nome, String email) throws IOException {
        ListaDAO listaDAO = ListaDAO.getInstance();
        return listaDAO.isListaDuplicata(nome, email);
    }

    // Metodo per validare il formato della descrizione della lista
    public static void validateDescrizione(String input) {
        if (input.length() > 100) {
            throw new IllegalArgumentException("Formato descrizione non valido: Non deve superare i 100 caratteri!");
        }
    }
}
