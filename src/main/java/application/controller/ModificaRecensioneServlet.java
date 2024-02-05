package application.controller;

import application.entity.Recensione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.model.RecensioneDAO;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ModificaRecensioneServlet", value = "/ModificaRecensioneServlet")
public class ModificaRecensioneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecensioneDAO rDAO = RecensioneDAO.getInstance();

        //int valutazione = Integer.parseInt(request.getParameter("Valutazione"));
        String valutazione = request.getParameter("Valutazione");
        int stelle = Integer.parseInt(valutazione);
        String descrizione = request.getParameter("Descrizione");
        Date dataInserimento = new Date();
        int idFilm = Integer.parseInt(request.getParameter("ID_Film"));
        String emailPersona = request.getParameter("Email_persona");

        Recensione recensione = new Recensione();
        recensione.setValutazione(stelle);
        recensione.setDescrizione(descrizione);
        recensione.setDataInserimento(dataInserimento);
        recensione.setIdFilm(idFilm);
        recensione.setEmailPersona(emailPersona);
        String result = "";

        try {
            rDAO.doUpdate(recensione);
            result = "Recensione modificata!";
        } catch (Exception e) {
            result = "Errore nella modifica della recensione!";
            request.setAttribute("result", result);
        }

        request.setAttribute("result", result);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
