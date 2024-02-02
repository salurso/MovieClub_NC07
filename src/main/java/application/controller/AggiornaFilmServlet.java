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

@WebServlet(name = "AggiornaFilmServlet", value = "/AggiornaFilmServlet")
public class AggiornaFilmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        FilmDAO fDAO = FilmDAO.getInstance();
        Film f = new Film();
        f.setId(Integer.parseInt(request.getParameter("id")));

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

            String result = "";
            try{
                fDAO.doUpdate(f);
                result = "Film aggiornato!";
            }catch (Exception e){
                e.printStackTrace(); // Aggiungi questa riga per vedere i dettagli dell'errore nella console
                throw new RuntimeException(e);
//                result = "Film già esistente!";
//                request.setAttribute("result", result);

//                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
//                requestDispatcher.forward(request, response);
            }

            request.setAttribute("result", result);

            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
            ds.forward(request, response);
        }
        if(action.equals("ELIMINA")){
            String result;

            if(fDAO.doDelete(f.getId())==0){
                result = "Problema eliminazione!";
            }else{
                result = "Prodotto eliminato!";
            }

            try{
                fDAO.doDelete(f.getId());
                result = "Film eliminato!";
            }catch (Exception e){
                e.printStackTrace(); // Aggiungi questa riga per vedere i dettagli dell'errore nella console
                throw new RuntimeException(e);
//                result = "Film già esistente!";
//                request.setAttribute("result", result);

//                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");
//                requestDispatcher.forward(request, response);
            }

            //Imposta la risposta
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainServletAdmin?action=homeAdmin");
        requestDispatcher.forward(request, response);
    }
}