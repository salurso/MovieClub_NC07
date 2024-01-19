package application.controller;

import application.entity.Film;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.model.FilmDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;


@MultipartConfig
@WebServlet(name = "AggiungiFilmServlet", value = "/AggiungiFilmServlet")
public class AggiungiFilmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Film f = new Film();
        f.setTitolo(request.getParameter("titolo"));
        f.setRegista(request.getParameter("regista"));
        f.setCopertina(request.getParameter("copertina"));
        f.setTrailer(request.getParameter("trailer"));
        f.setDataUscita(Date.valueOf(request.getParameter("data")));
        f.setDurata(Time.valueOf(request.getParameter("durata")));
        f.setDescrizione(request.getParameter("descrizione"));

        // Ottieni i valori delle checkbox dalla richiesta
        String[] generiSelezionati = request.getParameterValues("generi");

        // Verifica se almeno una checkbox è stata selezionata
        if (generiSelezionati != null && generiSelezionati.length > 0) {
            // Concatena i valori in una stringa separata da virgole
            String generiConcatenati = String.join(", ", generiSelezionati);

            f.setGenere(generiConcatenati);
        }

//        ValidateFilmService vFilm = new ValidateFilmService();
//        vFilm.validateFilm(f);

        String result = null;
        FilmDAO fDAO = new FilmDAO();
        try{
            fDAO.doInsert(f);
            result = "Film inserito!";
        }catch (Exception e){
            result = "Film non inserito!";
            request.setAttribute("result", result);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
            requestDispatcher.forward(request, response);
        }

        request.setAttribute("result", result);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
        requestDispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainServletAdmin?action=homeAdmin");
        requestDispatcher.forward(request, response);
    }
}
