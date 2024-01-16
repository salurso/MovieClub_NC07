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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("lista")) {

                ListaDAO lDAO = new ListaDAO();
                ArrayList<Lista> lists = new ArrayList<Lista>();
                lists = (ArrayList<Lista>) lDAO.doRetrieveAll();
                request.setAttribute("lists", lists);
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
                ds.forward(request, response);
            }
        }
                int id = Integer.parseInt(request.getParameter("id"));
                ListaDAO lDAO = new ListaDAO();
                Lista lists = lDAO.doRetrieveById(id);
                request.setAttribute("lists", lists);
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/infoListe.jsp");
                ds.forward(request, response);
        }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
