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
import java.util.Date;

@WebServlet(name = "ModificaRecensioneServlet", value = "/ModificaRecensioneServlet")
public class ModificaRecensioneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottenimento dell'istanza del DAO per le recensioni
        RecensioneDAO rDAO = RecensioneDAO.getInstance();

        // Ottenimento dei parametri dalla richiesta
        String valutazione = request.getParameter("Valutazione");
        int stelle = Integer.parseInt(valutazione);
        String descrizione = request.getParameter("Descrizione");
        Date dataInserimento = new Date();
        int idFilm = Integer.parseInt(request.getParameter("ID_Film"));
        String emailPersona = request.getParameter("Email_persona");

        // Creazione di un oggetto Recensione con i parametri ottenuti
        Recensione recensione = new Recensione();
        recensione.setValutazione(stelle);
        recensione.setDescrizione(descrizione);
        recensione.setDataInserimento(dataInserimento);
        recensione.setIdFilm(idFilm);
        recensione.setEmailPersona(emailPersona);

        String result = "";

        try {
            // Tentativo di modifica della recensione nel database
            rDAO.doUpdate(recensione);
            result = "Recensione modificata!";
        } catch (Exception e) {
            // Gestione dell'eccezione in caso di errore nella modifica della recensione
            result = "Errore nella modifica della recensione!";
            request.setAttribute("result", result);
        }

        // Impostazione del risultato come attributo di richiesta
        request.setAttribute("result", result);

        // Reindirizzamento alla pagina principale
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chiamata al metodo doPost in caso di richiesta GET
        doPost(request, response);
    }
}
