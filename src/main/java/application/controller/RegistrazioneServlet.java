package application.controller;

import application.entity.Persona;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.service.AutenticazioneService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrazioneServlet", value = "/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottenimento dei parametri dalla richiesta
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String password = request.getParameter("password");

        try {
            // Chiamata al servizio di registrazione
            Persona p = AutenticazioneService.doRegistrationService(nome, cognome, email, password);

            if (p == null) {
                // Gestione del caso in cui l'email è già esistente
                request.setAttribute("LoginFail", "Email già esistente");
                RequestDispatcher rs = request.getRequestDispatcher("./WEB-INF/gui/registrazione.jsp");
                rs.forward(request, response);
                return;
            }

            // Impostazione degli attributi di richiesta e reindirizzamento
            request.setAttribute("result", "Registrazione effettuata");
            request.getSession().setAttribute("Persona", p);
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);

        } catch (SQLException e) {
            // Gestione dell'eccezione in caso di errore generico durante la registrazione
            e.printStackTrace();
            request.setAttribute("LoginFail", "Errore generico, riprova");
            RequestDispatcher rs = request.getRequestDispatcher("./WEB-INF/gui/registrazione.jsp");
            rs.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupero dell'oggetto Persona dalla sessione
        Persona p = (Persona) request.getSession().getAttribute("Persona");

        // Reindirizzamento alla pagina principale in base al ruolo (admin o utente)
        RequestDispatcher rs = request.getRequestDispatcher(p.isAdmin() ? "./WEB-INF/guiAdmin/homeAdmin.jsp" : "index.jsp");
        rs.forward(request, response);
    }
}
