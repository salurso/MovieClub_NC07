package application.controller;

import application.entity.Persona;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        // Ottieni i dati dell'utente
        Persona p = (Persona) request.getSession().getAttribute("Persona");
        String generi = PersonaDAO.getInstance().getFormattedGenres(p.getId());
        int annoMedio = PersonaDAO.getInstance().getAverageReleaseYear(p.getId());

        // Costruisci i dati da inviare come JSON
        String jsonInput = "{\"Generi\": \"" + generi + "\", \"AnnoMedio\": " + annoMedio + "}";

        // Configura l'URL del servizio di raccomandazione
        URL url = new URL("http://localhost:5000/raccomandazioni");

        // Apri una connessione HTTP
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
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

//            // Converti la risposta JSON in un oggetto Java (gestisci come preferisci)
//            JSONObject jsonResponse = new JSONObject(responseJson);
//
//            // Ottieni la lista di id film raccomandati
//            JSONArray raccomandazioniArray = jsonResponse.getJSONArray("raccomandazioni");
            List<Integer> raccomandazioni = new ArrayList<>();
            String[] ids = responseJson.replaceAll("\\[|\\]|\\s", "").split(",");
            for (String id : ids) {
                raccomandazioni.add(Integer.parseInt(id));
            }

            // Passa i dati alla JSP
            request.setAttribute("raccomandazioni", raccomandazioni);
            RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
            dispatcher.forward(request, response);

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
