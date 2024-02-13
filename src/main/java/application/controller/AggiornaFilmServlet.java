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
        // Ottieni l'azione (AGGIORNA o ELIMINA) dalla richiesta
        String action = request.getParameter("action");

        // Ottieni l'istanza del Data Access Object per i film
        FilmDAO fDAO = FilmDAO.getInstance();

        // Crea un nuovo oggetto Film
        Film f = new Film();

        // Imposta l'ID del film dalla richiesta
        f.setId(Integer.parseInt(request.getParameter("id")));

        // Ottieni la lista di tutti i film dal database e inseriscila nella richiesta
        ArrayList<Film> films = (ArrayList<Film>) fDAO.doRetrieveAll();
        request.setAttribute("films", films);

        // Se l'azione è AGGIORNA
        if(action.equals("AGGIORNA")){
            // Imposta i nuovi valori del film dalla richiesta
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
                // Esegui l'aggiornamento del film nel database
                fDAO.doUpdate(f);
                request.setAttribute("result", "Film aggiornato!");

                // Reindirizza alla pagina home dell'amministratore
                RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
                ds.forward(request, response);
            } catch (Exception e){
                // In caso di errore durante l'aggiornamento, mostra un messaggio di errore
                request.setAttribute("result", "Errore aggiornamento!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
                requestDispatcher.forward(request, response);
            }
        }

        // Se l'azione è ELIMINA
        if(action.equals("ELIMINA")){
            try{
                // Esegui l'eliminazione del film dal database
                fDAO.doDelete(f.getId());
                request.setAttribute("result", "Film eliminato!");

                // Reindirizza alla pagina home dell'amministratore
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
                requestDispatcher.forward(request, response);
            } catch (Exception e){
                // In caso di errore durante l'eliminazione, mostra un messaggio di errore
                request.setAttribute("result", "Errore eliminazione!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Reindirizza alla servlet principale dell'amministratore con l'azione "gestisciFilm.jsp"
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainServletAdmin?action=gestisciFilm.jsp");
        requestDispatcher.forward(request, response);
    }
}
