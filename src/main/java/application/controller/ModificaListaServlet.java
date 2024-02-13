package application.controller;

import application.entity.Lista;
import application.entity.Persona;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.ListaDAO;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ModificaListaServlet", value = "/ModificaListaServlet")
public class ModificaListaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni l'azione dalla richiesta
        String action = request.getParameter("action");

        // Ottieni l'utente dalla sessione
        Persona p = (Persona) request.getSession().getAttribute("Persona");

        // Ottieni l'istanza del DAO per le liste
        ListaDAO lDAO = ListaDAO.getInstance();

        if (action.equals("AGGIORNA")) {
            // Creazione di una nuova lista con i parametri della richiesta
            Lista l = new Lista();
            l.setId(Integer.parseInt(request.getParameter("id")));
            l.setNome(request.getParameter("nome"));
            l.setDescrizione(request.getParameter("descrizione"));
            l.setPrivata("1".equals(request.getParameter("Privata")));

            String result = "";
            try {
                // Tentativo di aggiornamento della lista nel database
                lDAO.doUpdate(l);
                result = "Lista aggiornata!";
            } catch (Exception e) {
                // Gestione dell'eccezione nel caso la lista già esista
                result = "Lista già esistente!";
                request.setAttribute("result", result);

                // Recupero e imposto le liste dell'utente corrente come attributo di richiesta
                ArrayList<Lista> userLists = lDAO.doRetrieveByEmail(p.getEmail());
                request.setAttribute("userLists", userLists);

                // Reindirizzamento alla pagina delle liste
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
                requestDispatcher.forward(request, response);
                return;
            }

            // Impostazione del risultato come attributo di richiesta
            request.setAttribute("result", result);

            // Recupero e imposto le liste dell'utente corrente come attributo di richiesta
            ArrayList<Lista> userLists = lDAO.doRetrieveByEmail(p.getEmail());
            request.setAttribute("userLists", userLists);

            // Reindirizzamento alla pagina delle liste
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
            ds.forward(request, response);
        }

        if (action.equals("ELIMINA")) {
            // Ottenimento dell'ID della lista dalla richiesta
            int id = Integer.parseInt(request.getParameter("id"));
            String result;

            // Eliminazione della lista dal database
            if (lDAO.doDeleteList(id) == 0) {
                result = "Problema eliminazione!";
            } else {
                result = "Lista eliminata!";
            }

            // Impostazione della risposta come JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
        } else if (action.equals("rimuoviFilm")) {
            // Ottenimento dell'ID della lista e del film dalla richiesta
            int idLista = Integer.parseInt(request.getParameter("idLista"));
            int idFilm = Integer.parseInt(request.getParameter("idFilm"));
            String result;

            // Rimozione del film dalla lista nel database
            int rowsAffected = lDAO.doDeleteFilmList(idLista, idFilm);
            if (rowsAffected == 0) {
                result = "Il film non era presente nella lista o si è verificato un problema durante la rimozione.";
            } else {
                result = "Il film è stato rimosso con successo dalla lista!";
            }

            // Restituzione della risposta come JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Reindirizzamento alla servlet delle liste con l'azione "lista"
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListaServlet?action=lista");
        requestDispatcher.forward(request, response);
    }
}
