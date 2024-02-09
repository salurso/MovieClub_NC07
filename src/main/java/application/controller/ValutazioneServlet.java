package application.controller;

import application.entity.Persona;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.DatasetSampleDAO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ValutazioneServlet", value = "/ValutazioneServlet")
public class ValutazioneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String valutazione = request.getParameter("value");

        Persona p = (Persona) request.getSession().getAttribute("Persona");
        String generi = DatasetSampleDAO.getFormattedGenres(p.getId());
        int annoMedio = DatasetSampleDAO.getAverageReleaseYear(p.getId());
        generi = generi.replaceAll("\\s", "");

        // Costruisci i dati da inviare come JSON
        String jsonInput = "{\"Genere\": \"" + generi + "\", \"MediaAnno\": " + annoMedio + "\", \"Consigliati\": " + valutazione + "\", \"Valutazione\": " + valutazione +"}";

        // Configura l'URL del servizio di raccomandazione
        URL url = new URL("http://localhost:5000/feedback");

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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}