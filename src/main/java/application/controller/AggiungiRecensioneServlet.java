package application.controller;

import application.entity.Recensione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.model.RecensioneDAO;
import storage.service.RecensioneService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "/AggiungiRecensioneServlet", value="/AggiungiRecensioneServlet")
public class AggiungiRecensioneServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Inizializza una stringa per il risultato dell'inserimento
        String result = "";

        try {
            // Ottieni i parametri dalla richiesta
            String valutazione = request.getParameter("Valutazione");
            int stelle = Integer.parseInt(valutazione);
            String descrizione = request.getParameter("Descrizione");
            String emailPersona = request.getParameter("Email_persona");
            int idFilm = Integer.parseInt(request.getParameter("ID_Film"));

            // Chiamata al servizio per il salvataggio della recensione nel database
            int rowsAffected = RecensioneService.doSaveService(stelle, descrizione, new Date(), emailPersona, idFilm);

            // Verifica il numero di righe modificate nel database
            if (rowsAffected > 0) {
                result = "Recensione inserita con successo";
            } else {
                result = "Recensione non inserita ";
            }
        } catch (NumberFormatException e) {
            // Gestione dell'eccezione nel caso di errore nella conversione di numeri
            result = "Errore nella valutazione o nell'ID del film";
        }

        // Imposta il risultato come attributo della richiesta
        request.setAttribute("result", result);

        // Reindirizza alla pagina principale
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Reindirizza alla stessa operazione di POST
        doPost(request, response);
    }
}
