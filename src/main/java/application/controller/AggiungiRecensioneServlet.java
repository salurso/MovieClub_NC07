package application.controller;

import application.entity.Recensione;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import storage.model.RecensioneDAO;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import jakarta.servlet.annotation.WebServlet;

import javax.xml.crypto.Data;

@WebServlet(name = "AggiungiRecensioneServlet", value = "/AggiungiRecensioneServlet")
public class AggiungiRecensioneServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       int valutazione = Integer.parseInt(request.getParameter("Valutazione"));
       String descrizione = request.getParameter("Descrizione");
//       Date data = request.getParameter("Data");
       Date data = Calendar.getInstance().getTime();
       String emailPersona = request.getParameter("Email_persona");
       int idFilm = Integer.parseInt(request.getParameter("ID_Film"));

       String result = "";

       Recensione duplicate = RecensioneDAO.checkDuplicate(emailPersona, idFilm);

       if(duplicate != null){
           result = "La recensione è già presente!";
       }else{
           Recensione r = new Recensione();
           r.setValutazione(valutazione);
           r.setDescrizione(descrizione);
           r.setData(data);
           r.setEmailPersona(emailPersona);
           r.setIdFilm(idFilm);

           RecensioneDAO.doSave(r);

       }






    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
