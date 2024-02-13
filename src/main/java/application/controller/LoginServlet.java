package application.controller;

import application.entity.Lista;
import application.entity.Persona;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.PersonaDAO;
import storage.model.ListaDAO;
import storage.service.AutenticazioneService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verifica se l'utente è già autenticato
        if (request.getSession().getAttribute("Persona") != null) {
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);
        }

        // Ottieni email e password dalla richiesta
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Effettua il login e ottieni l'oggetto Persona
            Persona p = AutenticazioneService.doLoginService(email, password);
            // Imposta l'oggetto Persona come attributo di sessione
            request.getSession().setAttribute("Persona", p);

            if (p == null) {
                // Se il login non è riuscito, mostra un messaggio di errore
                request.setAttribute("LoginFail", "Email o password errati");
                RequestDispatcher rs = request.getRequestDispatcher("./WEB-INF/gui/login.jsp");
                rs.forward(request, response);
                return;
            }

            // Se il login è riuscito, mostra un messaggio di successo e reindirizza alla home
            request.setAttribute("LoginSuccess", "Login effettuato");
            RequestDispatcher rs = request.getRequestDispatcher(p.isAdmin() ? "./WEB-INF/guiAdmin/homeAdmin.jsp" : "index.jsp");
            rs.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            // In caso di errore generico, mostra un messaggio di errore e reindirizza alla pagina di login
            request.setAttribute("LoginFail", "Errore generico, riprova");
            RequestDispatcher rs = request.getRequestDispatcher("./WEB-INF/gui/login.jsp");
            rs.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni l'oggetto Persona dalla sessione
        Persona p = (Persona) request.getSession().getAttribute("Persona");
        // Reindirizza alla home o alla homeAdmin in base al ruolo dell'utente
        RequestDispatcher rs = request.getRequestDispatcher(p.isAdmin() ? "./WEB-INF/guiAdmin/homeAdmin.jsp" : "index.jsp");
        rs.forward(request, response);
    }
}
