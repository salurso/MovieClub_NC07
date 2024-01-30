package application.controller;

import application.entity.Lista;
import application.entity.Persona;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.ListaDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet(name = "ModificaListaServlet", value = "/ModificaListaServlet")
public class ModificaListaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        boolean Privata = "1".equals(request.getParameter("Privata"));
        Persona p = (Persona) request.getSession().getAttribute("Persona");
        ListaDAO lDAO = new ListaDAO();

        if (action.equals("AGGIORNA")) {
            Lista l = new Lista();
            l.setId(Integer.parseInt(request.getParameter("id")));
            l.setNome(request.getParameter("nome"));
            l.setDescrizione(request.getParameter("descrizione"));
            l.setPrivata(Privata);
            String result = "";
            try {
                lDAO.doUpdate(l);
                result = "Lista aggiornata!";
            } catch (Exception e) {
                result = "Lista già esistente!";
                request.setAttribute("result", result);

                ArrayList<Lista> userLists = lDAO.doRetrieveByEmail(p.getEmail());
                request.setAttribute("userLists", userLists);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
                requestDispatcher.forward(request, response);
                return;
            }

            request.setAttribute("result", result);


            ArrayList<Lista> userLists = lDAO.doRetrieveByEmail(p.getEmail());
            request.setAttribute("userLists", userLists);
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
            ds.forward(request, response);
        }

        if (action.equals("ELIMINA")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String result;

            if (lDAO.doDeleteList(id) == 0) {
                result = "Problema eliminazione!";
            } else {
                result = "Lista eliminata!";
            }

            //Imposta la risposta
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);




        } else if (action.equals("rimuoviFilm")) {
            int idLista = Integer.parseInt(request.getParameter("idLista"));
            int idFilm = Integer.parseInt(request.getParameter("idFilm"));
            String result;

            int rowsAffected = lDAO.doDeleteFilmList(idLista, idFilm);
            if (rowsAffected == 0) {
                result = "Il film non era presente nella lista o si è verificato un problema durante la rimozione.";
            } else {
                result = "Il film è stato rimosso con successo dalla lista!";
            }

            // Restituisci la risposta come JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }
        }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListaServlet?action=lista");
        requestDispatcher.forward(request, response);
    }
}
