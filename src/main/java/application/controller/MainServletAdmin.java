package application.controller;

import application.entity.Film;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.FilmDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MainServletAdmin", value = "/MainServletAdmin")
public class MainServletAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("homeAdmin")){
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
            ds.forward(request, response);
        }
        if(action.equals("aggiungi_film")){
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/administrator/addCategory.jsp");
            ds.forward(request, response);
        }
        if(action.equals("gestisci_film")){
            FilmDAO fDAO = new FilmDAO();
            ArrayList<Film> films = new ArrayList<Film>();
            films = (ArrayList<Film>) fDAO.doRetrieveAll();
            request.setAttribute("films", films);
            ArrayList<String> generi = new ArrayList<>();
            generi = fDAO.cercaGeneri();
            request.setAttribute("generi", generi);
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/guiAdmin/gestisciFilm.jsp");
            ds.forward(request, response);
        }
        if(action.equals("utenti")){
//            UtenteDAO uDAO = new UtenteDAO();
//            ArrayList<Utente> users = new ArrayList<>();
//            users = uDAO.doRetrieveAll();
//            request.setAttribute("users", users);
//            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/administrator/manageUsers.jsp");
//            ds.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
