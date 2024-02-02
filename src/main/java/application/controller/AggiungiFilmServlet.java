package application.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.service.FilmService;

import java.io.IOException;
import java.sql.Date;

@MultipartConfig
@WebServlet(name = "AggiungiFilmServlet", value = "/AggiungiFilmServlet")
public class AggiungiFilmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String titolo = request.getParameter("titolo");
        String regista = request.getParameter("regista");
        String copertina = request.getParameter("copertina");
        String trailer = request.getParameter("trailer");
        Date date = Date.valueOf(request.getParameter("data"));
        String durata = request.getParameter("durata");
        String descrizione = request.getParameter("descrizione");
        String genere = null;
        // Ottieni i valori delle checkbox dalla richiesta
        String[] generiSelezionati = request.getParameterValues("generi");

        // Verifica se almeno una checkbox è stata selezionata
        if (generiSelezionati != null && generiSelezionati.length > 0) {
            // Concatena i valori in una stringa separata da virgole
            genere = String.join(", ", generiSelezionati);
        }
        try{
            String result = FilmService.doInsertFilmService(titolo, regista, copertina, trailer, date, durata, descrizione, genere);

            if(result.equals("Film inserito")){
                request.setAttribute("result", "Film inserito!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
                requestDispatcher.forward(request, response);
            }
            request.setAttribute("result", result);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/aggiungiFilm.jsp");
            requestDispatcher.forward(request, response);

        }catch (Exception e){
            request.setAttribute("result", "Errore generio, riprovare");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/aggiungiFilm.jsp");
            requestDispatcher.forward(request, response);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainServletAdmin?action=homeAdmin");
        requestDispatcher.forward(request, response);
    }
}
