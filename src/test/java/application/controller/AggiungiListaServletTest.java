package application.controller;

import application.entity.Lista;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import application.controller.AggiungiFilmServlet;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.model.ListaDAO;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

class AggiungiListaServletTest {

    @Test
    void testDoPost() throws Exception {
        // Preparazione dei mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        ListaDAO listaDAO = ListaDAO.getInstance(); // Otteniamo l'istanza del singleton
        ListaDAO listaDAOSpy = spy(listaDAO); // Creiamo uno spy del singleton
        AggiungiListaServlet servlet = new AggiungiListaServlet();

        // Configurazione degli input simulati
        when(request.getParameter("Email_Persona")).thenReturn("user@gmail.com");
        when(request.getParameter("Nome")).thenReturn("TestList");
        when(request.getParameter("Descrizione")).thenReturn("Descrizione di test");
        when(request.getParameter("Privata")).thenReturn("1");

        // Configurazione del comportamento simulato del DAO
        when(listaDAOSpy.doInsert(any())).thenReturn(1); // Simula che l'inserimento abbia avuto successo
        when(listaDAOSpy.doRetrieveByEmail(anyString())).thenReturn(new ArrayList<>()); // Simula un elenco vuoto

        // Configurazione del comportamento simulato del dispatcher
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        // Esecuzione del metodo da testare
        servlet.doPost(request, response);

        // Verifica che il DAO sia stato chiamato con i parametri corretti
        verify(listaDAOSpy).doInsert(any(Lista.class));
        verify(listaDAOSpy).doRetrieveByEmail("user@gmail.com");

        // Verifica che il dispatcher sia stato chiamato con il percorso corretto
        verify(requestDispatcher).forward(request, response);

        // Verifica che l'attributo "result" sia impostato correttamente
        verify(request).setAttribute("result", "Lista inserita!");

        // Verifica che l'attributo "userLists" sia impostato correttamente
        verify(request).setAttribute("userLists", new ArrayList<>());
    }

    @Test
    void testNomeListaMaxLength() throws Exception {
        // Preparazione dei mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = null; // Inizializza a null
        ListaDAO listaDAO = mock(ListaDAO.class);
        AggiungiListaServlet servlet = new AggiungiListaServlet();

        // Configurazione degli input simulati
        when(request.getParameter("Email_Persona")).thenReturn("user@gmail.com");
        when(request.getParameter("Nome")).thenReturn("NomeL");

        try {
            // Configurazione del comportamento simulato del DAO
            when(listaDAO.doInsert(any())).thenReturn(1); // Simula che l'inserimento abbia avuto successo
            when(listaDAO.doRetrieveByEmail(anyString())).thenReturn(new ArrayList<>()); // Simula un elenco vuoto

            // Configurazione del comportamento simulato del dispatcher
            requestDispatcher = mock(RequestDispatcher.class);
            when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

            // Esecuzione del metodo da testare
            servlet.doPost(request, response);

            // Verifica che il metodo doInsert del DAO non sia stato chiamato
            verify(listaDAO, never()).doInsert(any());

            // Verifica che il dispatcher sia stato chiamato con il percorso corretto
            verify(requestDispatcher).forward(request, response);

            // Verifica che l'attributo "result" sia impostato correttamente
            verify(request).setAttribute(eq("result"), anyString());
            // Verifica che l'attributo "result" contenga un messaggio di errore
            verify(request).setAttribute("result", "Errore: il nome della lista non può superare i 30 caratteri");

            // Verifica che non sia stato impostato l'attributo "userLists"
            verify(request, never()).setAttribute(eq("userLists"), any(ArrayList.class));

        } catch (Exception e) {
            fail("Eccezione non attesa: " + e.getMessage());
        }
    }

}
