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
        String titolo = request.getParameter("titolo");
        String regista = request.getParameter("regista");
        String copertina = request.getParameter("copertina");
        String trailer = request.getParameter("trailer");
        String genere = request.getParameter("generi");
        Date data = Date.valueOf(request.getParameter("data"));
        String durata = request.getParameter("durata");
        String descrizione = request.getParameter("descrizione");

        Film f = new Film();
        f.setTitolo(titolo);
        f.setRegista(regista);
        f.setCopertina(copertina);
        f.setTrailer(trailer);
        f.setGenere(genere);
        f.setDataUscita(data);
        f.setDurata(Time.valueOf(durata));
        f.setDescrizione(descrizione);

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
