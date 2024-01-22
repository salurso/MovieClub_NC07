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

@WebServlet(name = "AggiungiRecensioneServlet", value = "/AggiungiRecensioneServlet")
public class AggiungiRecensioneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Valutazione = request.getParameter("Valutazione");
        String Descrizione = request.getParameter("Descrizione");
        String Email_Persona = request.getParameter("Email_persona");
        String ID_Film = request.getParameter("ID_Film");

        String result = "";

        try {
            // Aggiungi controlli per gli altri campi se necessario

            Recensione r = new Recensione();
            r.setValutazione(Integer.parseInt(Valutazione));
            r.setDescrizione(Descrizione);

            r.setEmailPersona(Email_Persona);
            r.setIdFilm(Integer.parseInt(ID_Film));

            RecensioneDAO rDA0 = new RecensioneDAO();
            rDA0.doSave(r);
            result = "Recensione inserita!";
        } catch (Exception e) {
            result = "Recensione non inserita!";
            e.printStackTrace(); // o utilizza un logger
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
