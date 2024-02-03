package application.controller;

import application.entity.Recensione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.model.RecensioneDAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "/AggiungiRecensioneServlet", value="/AggiungiRecensioneServlet")
public class AggiungiRecensioneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String result = "";

        // Ottieni i parametri dalla richiesta
        String valutazione = request.getParameter("Valutazione");
        int stelle = Integer.parseInt(valutazione);
        String descrizione = request.getParameter("Descrizione");
        String emailPersona = request.getParameter("Email_persona");
        int idFilm = Integer.parseInt(request.getParameter("ID_Film"));

        // Crea un oggetto Recensione con i parametri ricevuti
        Recensione recensione = new Recensione();
        recensione.setValutazione(stelle);
        recensione.setDescrizione(descrizione);
        recensione.setDataInserimento(new Date());  // Utilizziamo la data corrente
        //controllo con if
        recensione.setEmailPersona(emailPersona);
        recensione.setIdFilm(idFilm);

        // Usa il DAO per salvare la recensione nel database
        RecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        try {
            int rowsAffected = recensioneDAO.doSave(recensione);
            if (rowsAffected > 0) {
                result = "Recensione inserita con successo";
            }
        } catch (IOException e) {
            // Gestione dell'eccezione, ad esempio reindirizzamento a una pagina di errore
            result = "Recensione non inserita ";
        }

        request.setAttribute("result", result);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}


