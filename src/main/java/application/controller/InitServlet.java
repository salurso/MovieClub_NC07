package application.controller;

import application.entity.Film;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.FilmDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "InitServlet", value = "/InitServlet")
public class InitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FilmDAO fDAO = FilmDAO.getInstance();
        ArrayList<Film> films = (ArrayList<Film>) fDAO.doRetrieveAll();
        request.setAttribute("film", films);

        RequestDispatcher ds = request.getRequestDispatcher("index.jsp");
        ds.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}