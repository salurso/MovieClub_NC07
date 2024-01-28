package application.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.ListaDAO;
import storage.model.RecensioneDAO;

import java.io.IOException;

@WebServlet(name = "RecensioneServlet", value = "/RecensioneServlet")
public class RecensioneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RecensioneDAO rDAO = new RecensioneDAO();
        String result = "";
        int idFilm = 0;

        if (action != null) {
            if(action.equals("aggRecensione")){
                if(request.getParameter("idFilm") != null) {
                    idFilm = Integer.parseInt(request.getParameter("idFilm"));
                }
                request.setAttribute("ifFilm", idFilm);
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/aggiungiRecensione.jsp");
                ds.forward(request, response);
            }else{
                result = "Error!";
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"result\": \"" + result + "\"}");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
