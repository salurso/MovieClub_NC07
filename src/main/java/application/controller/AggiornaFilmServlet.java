package application.controller;

import application.entity.Film;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.model.FilmDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

@WebServlet(name = "AggiornaFilmServlet", value = "/AggiornaFilmServlet")
public class AggiornaFilmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        FilmDAO fDAO = FilmDAO.getInstance();
        Film f = new Film();
        f.setId(Integer.parseInt(request.getParameter("id")));
        ArrayList<Film> films = new ArrayList<Film>();
        films = (ArrayList<Film>) fDAO.doRetrieveAll();
        request.setAttribute("films", films);

        if(action.equals("AGGIORNA")){
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

            try{
                fDAO.doUpdate(f);
                request.setAttribute("result", "Film aggiornato!");
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
                ds.forward(request, response);
            }catch (Exception e){
//                e.printStackTrace(); // Visualizza i dettagli dell'errore nella console
//                throw new RuntimeException(e);
                request.setAttribute("result", "Errore aggiornamento!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
                requestDispatcher.forward(request, response);
            }
        }
        if(action.equals("ELIMINA")){

            try{
                fDAO.doDelete(f.getId());
                request.setAttribute("result", "Film eliminato!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
                requestDispatcher.forward(request, response);
            }catch (Exception e){
//                e.printStackTrace(); // Visualizza i dettagli dell'errore nella console
//                throw new RuntimeException(e);
                request.setAttribute("result", "Errore eliminazione!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainServletAdmin?action=gestisciFilm.jsp");
        requestDispatcher.forward(request, response);
    }
}