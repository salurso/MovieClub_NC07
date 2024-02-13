package application.controller;

import application.entity.Film;
import application.entity.Persona;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.FilmDAO;
import storage.model.PersonaDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MainServletAdmin", value = "/MainServletAdmin")
public class MainServletAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni l'azione dalla richiesta
        String action = request.getParameter("action");

        if (action.equals("homeAdmin")) {
            // Reindirizza alla home dell'amministratore
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
            ds.forward(request, response);
        }
        if (action.equals("aggiungi_film")) {
            // Reindirizza alla pagina di aggiunta film per l'amministratore
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/guiAdmin/aggiungiFilm.jsp");
            ds.forward(request, response);
        }
        if (action.equals("gestisci_film")) {
            // Ottieni tutti i film e reindirizza alla pagina di gestione film
            FilmDAO fDAO = FilmDAO.getInstance();
            ArrayList<Film> films = (ArrayList<Film>) fDAO.doRetrieveAll();
            request.setAttribute("films", films);
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/guiAdmin/gestisciFilm.jsp");
            ds.forward(request, response);
        }
        if (action.equals("utenti")) {
            // Ottieni tutti gli utenti e reindirizza alla pagina di visualizzazione utenti
            PersonaDAO pDAO = PersonaDAO.getInstance();
            ArrayList<Persona> persone = new ArrayList<>();
            persone = pDAO.doRetrieveAll();
            request.setAttribute("persone", persone);
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/guiAdmin/visualizzaUtenti.jsp");
            ds.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Reindirizza alla stessa operazione di doPost
        doPost(request, response);
    }
}
