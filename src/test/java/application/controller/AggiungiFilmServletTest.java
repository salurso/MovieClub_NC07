package application.controller;

import application.controller.AggiungiFilmServlet;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AggiungiFilmServletTest {
    @Test
    public void testDoPost() throws Exception {
        // Mock delle dipendenze
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);

        // Stubbing dei parametri della richiesta
        when(request.getParameter("titolo")).thenReturn("Titolo del film");
        when(request.getParameter("regista")).thenReturn("Nome del regista");
        // Continua con lo stubbing degli altri parametri

        // Simula una selezione di genere
        String[] generiSelezionati = {"Action", "Musical"};
        when(request.getParameterValues("generi")).thenReturn(generiSelezionati);

        // Flusso di output per catturare ciò che viene scritto dalla servlet
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        // Chiamata al metodo doPost della servlet
        new AggiungiFilmServlet().doPost(request, response);

        // Assicura che la servlet abbia inviato l'output corretto
        verify(response).setContentType("text/html;charset=UTF-8");
        verify(response).getWriter();
        verify(request).getRequestDispatcher("/WEB-INF/guiAdmin/homeAdmin.jsp");

        // Assicura che la servlet abbia inoltrato la richiesta alla destinazione corretta
        verify(requestDispatcher).forward(request, response);

        // Potresti voler aggiungere ulteriori verifiche a seconda delle logiche specifiche della tua applicazione
    }
}
