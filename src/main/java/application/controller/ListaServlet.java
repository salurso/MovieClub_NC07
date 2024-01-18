package application.controller;

import application.entity.Film;
import application.entity.Lista;
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

        if (action != null) {
            if (action.equals("lista")) {
                // Blocco per gestire l'azione "lista"
                ArrayList<Lista> lists = lDAO.doRetrieveAll();
                request.setAttribute("lists", lists);
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
                ds.forward(request, response);

            } else if (action.equals("info")) {
                // Blocco per gestire l'azione "info"
                int id = Integer.parseInt(request.getParameter("id"));
                Lista list = lDAO.doRetrieveById(id);

                // Ottieni la lista di film associati a questa lista
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

                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/filmLista.jsp");
                ds.forward(request, response);

            } else if (action.equals("aggiungiFilm")) {
                String idListaParam = request.getParameter("idLista");
                int idFilm = Integer.parseInt(request.getParameter("idFilm"));
                String result;

                if (idListaParam != null && !idListaParam.isEmpty()) {
                    int idLista = Integer.parseInt(idListaParam);

                    int filmPresente = lDAO.doRetrieveFilmIntoList(idLista, idFilm);

                    if (filmPresente > 0) {
                        result = "Errore: Il film è già presente nella lista.";
                    } else {
                        int rowsAffected = lDAO.doInsertFilmIntoList(idLista, idFilm);

                        if (rowsAffected > 0) {
                            result = "Film aggiunto con successo alla lista!";
                        } else {
                            result = "Errore durante l'aggiunta del film alla lista.";
                        }
                    }
                } else {
                    result = "Errore: Nessuna Lista Selezionata!";
                }

                // Restituisci la risposta come JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"result\": \"" + result + "\"}");
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
