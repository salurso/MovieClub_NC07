package application.controller;

import application.entity.Film;
import application.entity.Recensione;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.FilmDAO;
import storage.model.RecensioneDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FilmServlet", value = "/FilmServlet")
public class FilmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Ottieni l'id del film dalla richiesta
        int id = Integer.parseInt(request.getParameter("id"));

        FilmDAO fDAO = FilmDAO.getInstance();
        Film f = fDAO.doRetrieveById(id);

        RecensioneDAO rDAO = RecensioneDAO.getInstance();
        ArrayList<Recensione> recensioni = rDAO.doRetrieveByIDFilm(id);
        request.setAttribute("film", f);
        request.setAttribute("recensioni", recensioni);

        RequestDispatcher ds = request.getRequestDispatcher("./WEB-INF/gui/infoFilm.jsp");
        ds.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher ds = request.getRequestDispatcher("./WEB-INF/gui/infoFilm.jsp");
        ds.forward(request, response);
    }
}