package application.controller;

import application.entity.Recensione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.model.RecensioneDAO;
import storage.service.RecensioneService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "/AggiungiRecensioneServlet", value="/AggiungiRecensioneServlet")
public class AggiungiRecensioneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";

        // Ottieni i parametri dalla richiesta
        String valutazione = request.getParameter("Valutazione");
        int stelle = Integer.parseInt(valutazione);
        String descrizione = request.getParameter("Descrizione");
        String emailPersona = request.getParameter("Email_persona");
        int idFilm = Integer.parseInt(request.getParameter("ID_Film"));

        int rowsAffected = RecensioneService.doSaveService(stelle, descrizione, new Date(), emailPersona, idFilm);
            if (rowsAffected > 0) {
                result = "Recensione inserita con successo";
            } else {
                result = "Recensione non inserita ";
            }
        request.setAttribute("result", result);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}


