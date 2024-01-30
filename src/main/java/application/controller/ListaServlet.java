package application.controller;

import application.entity.Film;
import application.entity.Lista;
import application.entity.Persona;
import com.mysql.cj.xdevapi.Session;
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
        String action = request.getParameter("action");
        ListaDAO lDAO = new ListaDAO();
        Persona p = (Persona) request.getSession().getAttribute("Persona");

        if (action != null) {
            if (action.equals("lista")) {

                ArrayList<Lista> userLists = lDAO.doRetrieveByEmail(p.getEmail());
                request.setAttribute("userLists", userLists);

                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
                ds.forward(request, response);
            } else if (action.equals("pubblica")) {
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/listePubbliche.jsp");
                ds.forward(request, response);

            } else if (action.equals("info")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Lista list = lDAO.doRetrieveById(id);

                ArrayList<Film> films = lDAO.getFilmsInList(id);

                request.setAttribute("lists", list);
                request.setAttribute("films", films);
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/infoListe.jsp");
                ds.forward(request, response);

            } else if (action.equals("gestisci")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Lista lists = lDAO.doRetrieveById(id);

                request.setAttribute("lists", lists);
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/gestioneLista.jsp");
                ds.forward(request, response);

            } else if (action.equals("creazione")) {
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/creazioneLista.jsp");
                ds.forward(request, response);

            } else if (action.equals("aggiuntaFilm")) {
                FilmDAO fDAO = new FilmDAO();
                ArrayList<Film> films = new ArrayList<Film>();
                films = (ArrayList<Film>) fDAO.doRetrieveAll();
                request.setAttribute("films", films);

                // Ottieni la lista delle liste
                ArrayList<Lista> lists = lDAO.doRetrieveAll();
                request.setAttribute("lists", lists);

                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/film.jsp");
                ds.forward(request, response);

            } else if (action.equals("aggiungiFilm")) {
                FilmDAO fDAO = new FilmDAO();
                ArrayList<Film> films = new ArrayList<Film>();
                films = (ArrayList<Film>) fDAO.doRetrieveAll();
                request.setAttribute("films", films);

                // Ottieni la lista delle liste
                ArrayList<Lista> lists = lDAO.doRetrieveAll();
                request.setAttribute("lists", lists);

                // Aggiungi il film alla lista
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

                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/film.jsp");
                ds.forward(request, response);
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
