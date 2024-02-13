package application.controller;

import application.entity.Film;
import application.entity.Lista;
import application.entity.Persona;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.FilmDAO;
import storage.model.ListaDAO;
import storage.model.PersonaDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MainServlet", value = "/MainServlet")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni l'azione dalla richiesta
        String action = request.getParameter("action");
        // Ottieni l'oggetto Persona dalla sessione
        Persona p = (Persona) request.getSession().getAttribute("Persona");

        if (action.equals("homePage")) {
            // Reindirizza alla home page
            RequestDispatcher ds = request.getRequestDispatcher("index.jsp");
            ds.forward(request, response);
        }
        if (action.equals("login")) {
            // Reindirizza alla pagina di login
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/login.jsp");
            ds.forward(request, response);
        }
        if (action.equals("logout")) {
            // Invalida la sessione e reindirizza alla home page
            request.getSession().invalidate();
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);
        }
        if (action.equals("registrazione")) {
            // Reindirizza alla pagina di registrazione
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/registrazione.jsp");
            ds.forward(request, response);
        }
        if (action.equals("watchlist")) {
            // Imposta il tipo di richiesta e reindirizza alla pagina delle liste
            request.setAttribute("tipoRichiesta", "areaPersonale");
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/infoListe.jsp");
            ds.forward(request, response);
        }
        if (action.equals("contatti")) {
            // Reindirizza alla pagina dei contatti
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/contatti.jsp");
            ds.forward(request, response);
        }
        if (action.equals("creazioneLista")) {
            // Reindirizza alla pagina di creazione lista
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/creazioneLista.jsp");
            ds.forward(request, response);
        }
        if (action.equals("datiPersona")) {
            // Reindirizza alla pagina dei dati personali
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/datiPersona.jsp");
            ds.forward(request, response);
        }
        if (action.equals("film")) {
            // Ottieni tutti i film e le liste dell'utente corrente
            FilmDAO fDAO = FilmDAO.getInstance();
            ArrayList<Film> films = (ArrayList<Film>) fDAO.doRetrieveAll();
            request.setAttribute("films", films);

            ListaDAO lDAO = ListaDAO.getInstance();
            if (p != null) {
                ArrayList<Lista> lists = lDAO.doRetrieveByEmail(p.getEmail());
                request.setAttribute("userLists", lists);
            }

            // Reindirizza alla pagina dei film
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/film.jsp");
            ds.forward(request, response);
        }
        if (action.equals("consigliati")) {
            // Reindirizza alla pagina dei film consigliati
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/consigliati.jsp");
            ds.forward(request, response);
        }
        if (action.equals("areaPersonale")) {
            // Ottieni le liste dell'utente corrente e reindirizza alla pagina dell'area personale
            ListaDAO lDAO = ListaDAO.getInstance();
            ArrayList<Lista> userLists = lDAO.doRetrieveByEmail(p.getEmail());
            request.setAttribute("userLists", userLists);

            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/areaPersonale.jsp");
            ds.forward(request, response);
        }
        if (action.equals("watchlistEmpty")) {
            // Reindirizza alla pagina della watchlist vuota
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/consigliatiVuoti.jsp");
            ds.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Reindirizza alla stessa operazione di doGet
        doGet(request, response);
    }
}
