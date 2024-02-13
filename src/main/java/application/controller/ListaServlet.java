package application.controller;

import application.entity.Film;
import application.entity.Lista;
import application.entity.Persona;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.FilmDAO;
import storage.model.ListaDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ListaServlet", value = "/ListaServlet")
public class ListaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni l'azione dalla richiesta
        String action = request.getParameter("action");
        // Ottieni un'istanza del Data Access Object per le Liste
        ListaDAO lDAO = ListaDAO.getInstance();
        // Ottieni l'oggetto Persona dalla sessione
        Persona p = (Persona) request.getSession().getAttribute("Persona");

        if (action != null) {
            if (action.equals("lista")) {
                // Recupera le liste dell'utente corrente
                ArrayList<Lista> userLists = lDAO.doRetrieveByEmail(p.getEmail());
                request.setAttribute("userLists", userLists);
                // Reindirizza alla pagina delle liste personali
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
                ds.forward(request, response);
            } else if (action.equals("pubblica")) {
                // Reindirizza alla pagina delle liste pubbliche
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/listePubbliche.jsp");
                ds.forward(request, response);
            } else if (action.equals("info")) {
                // Ottieni l'ID della lista dalla richiesta
                int id = Integer.parseInt(request.getParameter("id"));
                // Recupera la lista e i film associati
                Lista list = lDAO.doRetrieveById(id);
                ArrayList<Film> films = lDAO.getFilmsInList(id);
                // Inserisci nella richiesta i dati per la pagina di informazioni sulla lista
                request.setAttribute("lists", list);
                request.setAttribute("films", films);
                // Reindirizza alla pagina di informazioni sulla lista
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/infoListe.jsp");
                ds.forward(request, response);
            } else if (action.equals("gestisci")) {
                // Ottieni l'ID della lista dalla richiesta
                int id = Integer.parseInt(request.getParameter("id"));
                // Recupera la lista
                Lista lists = lDAO.doRetrieveById(id);
                // Inserisci nella richiesta i dati per la pagina di gestione della lista
                request.setAttribute("lists", lists);
                // Reindirizza alla pagina di gestione della lista
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/gestioneLista.jsp");
                ds.forward(request, response);
            } else if (action.equals("creazione")) {
                // Reindirizza alla pagina di creazione della lista
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/creazioneLista.jsp");
                ds.forward(request, response);
            } else if (action.equals("aggiungiFilm")) {
                // Ottieni un'istanza del Data Access Object per i Film
                FilmDAO fDAO = FilmDAO.getInstance();
                // Recupera tutti i film
                ArrayList<Film> films = (ArrayList<Film>) fDAO.doRetrieveAll();
                request.setAttribute("films", films);
                // Ottieni l'ID della lista e l'ID del film dalla richiesta
                String idListaParam = request.getParameter("idLista");
                int idFilm = Integer.parseInt(request.getParameter("idFilm"));
                String result;
                if (idListaParam != null && !idListaParam.isEmpty()) {
                    int idLista = Integer.parseInt(idListaParam);
                    int filmPresente = lDAO.doRetrieveFilmIntoList(idLista, idFilm);
                    if (filmPresente > 0) {
                        result = "Errore: Il film è già presente nella lista.";
                    } else {
                        try {
                            // Aggiungi il film alla lista
                            lDAO.doInsertFilmIntoList(idLista, idFilm);
                            result = "Film aggiunto con successo alla lista!";
                        } catch (Exception e) {
                            result = "Errore durante l'aggiunta del film alla lista.";
                        }
                    }
                } else {
                    result = "Errore: Nessuna Lista Selezionata!";
                }
                request.setAttribute("result", result);
                // Aggiorna la lista delle liste dell'utente corrente
                ArrayList<Lista> lists = lDAO.doRetrieveByEmail(p.getEmail());
                request.setAttribute("userLists", lists);
                // Reindirizza alla pagina dei film
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/film.jsp");
                ds.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Reindirizza alla stessa operazione di POST
        doPost(request, response);
    }
}
