package application.controller;

import application.entity.Recensione;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import storage.model.RecensioneDAO;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "AggiungiRecensioneServlet", value = "/AggiungiRecensioneServlet")
public class AggiungiRecensioneServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int valutazione = Integer.parseInt(request.getParameter("valutazione"));
       String descrizione = request.getParameter("descrizione");

       Recensione r = new Recensione();
       r.setValutazione(valutazione);
       r.setDescrizione(descrizione);

       String result = "";
       RecensioneDAO rDAO = new RecensioneDAO();
       try{
            rDAO.doSave(r);
            result = "Recensione aggiunta con successo!";

       }catch(Exception e){
           result = "Recensione già esistente!";
           rDAO.doUpdate(r);
       }

       request.setAttribute("result", result);



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
