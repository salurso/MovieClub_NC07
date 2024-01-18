package application.controller;

import application.entity.Film;
import application.entity.Lista;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.FilmDAO;
import storage.model.ListaDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MainServlet", value = "/MainServlet")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
//        HttpSession session = request.getSession();
//        UtenteDAO uDAO = new UtenteDAO();
//        Utente utente = UtenteDAO.doRetrieveByEmailPassword(request.getParameter("email"), request.getParameter("password"));
        if(action.equals("homePage")){
            RequestDispatcher ds = request.getRequestDispatcher("index.jsp");
            ds.forward(request, response);
        }
        if(action.equals("login")){
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/login.jsp");
            ds.forward(request, response);
        }
        if(action.equals("logout")){
            request.getSession().invalidate();
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);
        }
        if(action.equals("registrazione")){
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/registrazione.jsp");
            ds.forward(request, response);
        }
        if(action.equals("contatti")){
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/contatti.jsp");
            ds.forward(request, response);
        }
        if(action.equals("creazioneLista")){
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/creazioneLista.jsp");
            ds.forward(request, response);
        }

        if(action.equals("datiPersona")){
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/datiPersona.jsp");
            ds.forward(request, response);
        }
        if(action.equals("film")){
            FilmDAO fDAO = new FilmDAO();
            ArrayList<Film> films = new ArrayList<Film>();
            films = (ArrayList<Film>) fDAO.doRetrieveAll();
            request.setAttribute("films", films);
//            ArrayList<String> generi = new ArrayList<>();
//            generi = fDAO.cercaGeneri();
//            request.setAttribute("generi", generi);
            // Ottieni la lista delle liste
            ListaDAO lDAO = new ListaDAO();
            ArrayList<Lista> lists = lDAO.doRetrieveAll();
            request.setAttribute("lists", lists);
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/film.jsp");
            ds.forward(request, response);
        }
        if(action.equals("consigliati")){
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/consigliati.jsp");
            ds.forward(request, response);
        }
        if(action.equals("areaPersonale")){
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/areaPersonale.jsp");
            ds.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}