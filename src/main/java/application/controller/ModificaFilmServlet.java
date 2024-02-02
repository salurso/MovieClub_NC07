package application.controller;

import application.entity.Film;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.FilmDAO;

import java.io.IOException;

@WebServlet(name = "ModificaFilmServlet", value = "/ModificaFilmServlet")
public class ModificaFilmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        FilmDAO fDAO = FilmDAO.getInstance();
        Film f = fDAO.doRetrieveById(id);
        request.setAttribute("film", f);

        RequestDispatcher ds = request.getRequestDispatcher("./WEB-INF/guiAdmin/modificaFilm.jsp");
        ds.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}