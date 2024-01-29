package application.controller;

import application.entity.Film;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.FilmDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FilmServlet", value = "/FilmServlet")
public class FilmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        FilmDAO pDAO = new FilmDAO();
        Film f = pDAO.doRetrieveById(id);
        request.setAttribute("film", f);

        RequestDispatcher ds = request.getRequestDispatcher("./WEB-INF/gui/infoFilm.jsp");
        ds.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}