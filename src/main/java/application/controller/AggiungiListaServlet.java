package application.controller;

import application.entity.Lista;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import storage.model.ListaDAO;
import storage.service.ListaService;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AggiungiListaServlet", value = "/AggiungiListaServlet")
public class AggiungiListaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListaDAO lDAO = ListaDAO.getInstance();

        String emailPersona = request.getParameter("Email_Persona");
        String nome = request.getParameter("Nome");
        String descrizione = request.getParameter("Descrizione");
        boolean privata = "1".equals(request.getParameter("Privata"));

        Lista l = new Lista();
        l.setNome(nome);
        l.setDescrizione(descrizione);
        l.setPrivata(privata);
        l.setEmail_Persona(emailPersona);

        String result = "";
        try {
            int rowsAffected = ListaService.doInsertService(emailPersona, nome, descrizione, privata);
            if (rowsAffected > 0) {
                result = "Lista inserita!";
            } else {
                result = "Nome Lista già presente per l'utente corrente";
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = "Errore durante l'inserimento della lista: " + e.getMessage();
        }
        request.setAttribute("result", result);

        // Recupera solo le liste create dall'utente corrente
        ArrayList<Lista> userLists = lDAO.doRetrieveByEmail(emailPersona);

        // Aggiorna la sessione con le liste dell'utente corrente
        request.setAttribute("userLists", userLists);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Reindirizza alla home page
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainServlet?action=homePage");
        requestDispatcher.forward(request, response);
    }
}
