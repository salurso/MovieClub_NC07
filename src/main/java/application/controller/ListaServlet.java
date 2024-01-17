package application.controller;

import application.entity.Film;
import application.entity.Lista;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.FilmDAO;
import storage.model.ListaDAO;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListaServlet", value = "/ListaServlet")
public class ListaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ListaDAO lDAO = new ListaDAO();
        if (action != null) {
            if (action.equals("lista")) {
                // Blocco per gestire l'azione "lista"
                ArrayList<Lista> lists = (ArrayList<Lista>) lDAO.doRetrieveAll();
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
            } else if (action.equals("crea")) {
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/creazioneLista.jsp");
                ds.forward(request, response);
            } else if (action.equals("gestisci")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Lista lists = lDAO.doRetrieveById(id);

                request.setAttribute("lists", lists);
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/gestioneLista.jsp");
                ds.forward(request, response);
            }

            }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
