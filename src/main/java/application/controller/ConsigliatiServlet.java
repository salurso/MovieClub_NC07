package application.controller;

import application.entity.Film;
import application.entity.Lista;
import application.entity.Persona;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.model.FilmDAO;
import storage.model.ListaDAO;
import storage.model.PersonaDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ConsigliatiServlet", value = "/ConsigliatiServlet")
public class ConsigliatiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Persona p = (Persona) request.getSession().getAttribute("Persona");
        String generi = PersonaDAO.getInstance().getFormattedGenres(p.getId());
        int annoMedio = PersonaDAO.getInstance().getAverageReleaseYear(p.getId());

        // Costruisci i dati da inviare come JSON
        String jsonInput = "{\"Genere\": \"" + generi + "\", \"MediaAnno\": " + annoMedio + "}";

        // Configura l'URL del servizio di raccomandazione
        URL url = new URL("http://localhost:5000/");

        // Apri una connessione HTTP
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Invia i dati dell'utente al server
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Leggi la risposta dal server
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String responseJson = br.lines().collect(Collectors.joining(System.lineSeparator()));
//            response.getWriter().println(responseJson);

            // Ottieni la lista di id film raccomandati
            List<Integer> raccomandazioni = new ArrayList<>();
            responseJson = responseJson.replace("\"", "");

            String[] ids = responseJson.substring(1, responseJson.length()-1).split(",");
            for (String id : ids) {
                raccomandazioni.add(Integer.parseInt(id));
//                response.getWriter().println(id);
            }

            FilmDAO fDAO = FilmDAO.getInstance();

            ArrayList<Film> films = new ArrayList<Film>();
            for (int id : raccomandazioni) {
                Film film = fDAO.doRetrieveById(id);
                films.add(film);
            }

            request.setAttribute("films", PersonaDAO.filterWatchlist(films, p.getId()));
            ListaDAO lDAO = ListaDAO.getInstance();
            if(p!=null) {
                ArrayList<Lista> lists = lDAO.doRetrieveByEmail(p.getEmail());
                request.setAttribute("userLists", lists);
            }

            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/film.jsp");
            ds.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Chiudi la connessione
            conn.disconnect();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
