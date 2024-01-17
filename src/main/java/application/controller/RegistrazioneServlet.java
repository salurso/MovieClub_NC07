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

        String email = request.getParameter("email");
        String nome = request.getParameter("name");
        String cognome = request.getParameter("surname");
        String password = request.getParameter("password");

        try {
            Persona p = AutenticazioneService.doRegistrationService(nome, cognome, email, password);
            if(p == null) {
                request.setAttribute("LoginFail", "Errore registrazione");
                RequestDispatcher rs = request.getRequestDispatcher("./WEB-INF/gui/registrazione.jsp");
                rs.forward(request, response);
                return;
            }
            request.setAttribute("LoginSuccess", "Registrazione effetuata");
            request.getSession().setAttribute("Persona", p);
            RequestDispatcher rs = request.getRequestDispatcher("./WEB-INF/index.jsp");
            rs.forward(request, response);


        } catch (SQLException e) {
            request.setAttribute("LoginFail", "Errore generico");
            RequestDispatcher rs = request.getRequestDispatcher("./WEB-INF/gui/registrazione.jsp");
            rs.forward(request, response);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
