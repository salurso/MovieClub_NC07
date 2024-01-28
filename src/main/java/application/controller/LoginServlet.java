package application.controller;

import application.entity.Lista;
import application.entity.Persona;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.PersonaDAO;
import storage.model.ListaDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("Persona") != null) {
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Persona p = PersonaDAO.doLogin(email, password);
            request.getSession().setAttribute("Persona", p);

            if(p == null) {
                request.setAttribute("LoginFail", "Errore parametri");
                RequestDispatcher rs = request.getRequestDispatcher("./WEB-INF/gui/login.jsp");
                rs.forward(request, response);
                return;
            }

            // Impostare le liste dell'utente nella sessione
            ListaDAO listaDAO = new ListaDAO();
            ArrayList<Lista> userLists = listaDAO.doRetrieveByEmail(email);
            request.getSession().setAttribute("userLists", userLists);

            request.setAttribute("LoginSuccess", "Login effettuato");
            RequestDispatcher rs = request.getRequestDispatcher(p.isAdmin() ? "./WEB-INF/guiAdmin/homeAdmin.jsp" : "index.jsp");
            rs.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("LoginFail", "Errore generico, riprova");
            RequestDispatcher rs = request.getRequestDispatcher("./WEB-INF/gui/login.jsp");
            rs.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
