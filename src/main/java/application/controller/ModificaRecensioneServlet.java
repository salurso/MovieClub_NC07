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

@WebServlet(name = "ModificaRecensioneServlet", value = "/ModificaRecensioneServlet")
public class ModificaRecensioneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RecensioneDAO rDAO = RecensioneDAO.getInstance();
        Recensione r = new Recensione();

        if(action.equals("aggiornaRecensione")){
            r.setValutazione(Integer.parseInt(request.getParameter("Valutazione")));
            r.setDescrizione(request.getParameter("Descrizione"));
            r.setEmailPersona(request.getParameter("Email_persona"));
            r.setIdFilm(Integer.parseInt(request.getParameter("ID_Film")));
            String result = "";
            try{
                rDAO.doUpdate(r);
                result = "Recensione aggiornata con successo!";
            }catch(Exception e){
                result = "Errore nell'aggiornamento della recensione!";
            }

            request.setAttribute("result", result);

            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/infoFilm.jsp");
            ds.forward(request, response);
        }

        if(action.equals("eliminaRecensione")){
            r.setValutazione(Integer.parseInt(request.getParameter("Valutazione")));
            r.setDescrizione(request.getParameter("Descrizione"));
            r.setEmailPersona(request.getParameter("Email_persona"));
            r.setIdFilm(Integer.parseInt(request.getParameter("ID_Film")));
            String result = "";
            try{
                rDAO.doUpdate(r);
                result = "Recensione eliminata con successo!";
            }catch(Exception e){
                result = "Errore nella rimozione della recensione!";
            }

            request.setAttribute("result", result);

            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/infoFilm.jsp");
            ds.forward(request, response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
