package application.controller;

import application.entity.Persona;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.service.AutenticazioneService;

import java.io.IOException;

@WebServlet(name = "WatchlistServlet", value = "/WatchlistServlet")
public class WatchlistServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        String tipoRichiesta = request.getParameter("richiesta");
        Persona p = (Persona) request.getSession().getAttribute("Persona");

        AutenticazioneService.watchlistService(tipoRichiesta, p.getId(), idFilm);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
