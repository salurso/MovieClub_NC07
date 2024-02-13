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
        // Ottieni l'ID del film dalla richiesta
        int id = Integer.parseInt(request.getParameter("id"));

        // Ottieni il film dal database
        FilmDAO fDAO = FilmDAO.getInstance();
        Film f = fDAO.doRetrieveById(id);

        // Imposta il film come attributo di richiesta
        request.setAttribute("film", f);

        // Reindirizza alla pagina di modifica film per l'amministratore
        RequestDispatcher ds = request.getRequestDispatcher("./WEB-INF/guiAdmin/modificaFilm.jsp");
        ds.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implementa la logica per la modifica del film se necessario
    }
}
