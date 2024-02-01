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

@WebServlet(name = "RecensioneServlet", value = "/RecensioneServlet")
public class RecensioneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RecensioneDAO rDAO = RecensioneDAO.getInstance();
        String result = "";
        int idFilm = 0;

        if (action != null) {
            if(action.equals("AGGIUNGI RECENSIONE")){
                if(request.getParameter("idFilm") != null) {
                    idFilm = Integer.parseInt(request.getParameter("idFilm"));
                }
                request.setAttribute("idFilm", idFilm);
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/aggiungiRecensione.jsp");
                ds.forward(request, response);

            } else if(action.equals("MODIFICA")) {
                int idFilm1 = Integer.parseInt(request.getParameter("ID_Film"));
                String emailPersona = request.getParameter("Email_persona");
                Recensione recensione = rDAO.doRetrievebyEmailID(emailPersona, idFilm1);
                request.setAttribute("recensione", recensione);

                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/gestioneRecensione.jsp");
                ds.forward(request, response);

            } else if(action.equals("ELIMINA")) {
                int idFilmToDelete = Integer.parseInt(request.getParameter("ID_Film"));
                String emailPersonaToDelete = request.getParameter("Email_persona");

                // Rimuovi la recensione
                int removedRows = rDAO.removeRecensione(emailPersonaToDelete, idFilmToDelete);

                if (removedRows > 0) {
                    // La recensione è stata eliminata con successo
                    result = "Recensione eliminata con successo!";
                } else {
                    // Errore durante l'eliminazione della recensione
                    result = "Errore durante l'eliminazione della recensione.";
                }

                // Ora puoi effettuare eventuali operazioni aggiuntive o reindirizzare l'utente
                request.setAttribute("result", result);

                // Per esempio, reindirizza l'utente alla pagina del film dopo l'eliminazione
                RequestDispatcher ds = request.getRequestDispatcher("index.jsp");
                ds.forward(request, response);

            } else {
                result = "Error!";
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"result\": \"" + result + "\"}");
    }
}
