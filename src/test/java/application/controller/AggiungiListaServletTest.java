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
        when(request.getParameter("Email_Persona")).thenReturn("user1@gmail.com");
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
        verify(listaDAOSpy).doRetrieveByEmail("user1@gmail.com");

        // Verifica che il dispatcher sia stato chiamato con il percorso corretto
        verify(requestDispatcher).forward(request, response);

        // Verifica che l'attributo "result" sia impostato correttamente
        verify(request).setAttribute("result", "Lista inserita!");

        // Verifica che l'attributo "userLists" sia impostato correttamente
        verify(request).setAttribute("userLists", new ArrayList<>());
    }

    @Test
    void testNomeListaMaxLengthDue() throws Exception {
        // Preparazione dei mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = null; // Inizializza a null
        ListaDAO listaDAO = mock(ListaDAO.class);
        AggiungiListaServlet servlet = new AggiungiListaServlet();

        // Configurazione degli input simulati
        when(request.getParameter("Email_Persona")).thenReturn("user1@gmail.com");
        // Simula un nome troppo lungo
        when(request.getParameter("Nome")).thenReturn("PROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVA");

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il metodo doInsert del mock ListaDAO non sia stato chiamato
        verify(listaDAO, never()).doInsert(any());
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
        when(request.getParameter("Email_Persona")).thenReturn("user1@gmail.com");
        when(request.getParameter("Nome")).thenReturn("PROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVA");

            try{
                when(listaDAO.doInsert(any())).thenReturn(1); // Simula che l'inserimento abbia avuto successo

                servlet.doPost(request, response);
            }catch (Exception e){
                fail("Eccezione non attesa: " + e.getMessage());
            }


//        try {
//            // Configurazione del comportamento simulato del DAO
//            when(listaDAO.doInsert(any())).thenReturn(1); // Simula che l'inserimento abbia avuto successo
//            when(listaDAO.doRetrieveByEmail(anyString())).thenReturn(new ArrayList<>()); // Simula un elenco vuoto
//
//            // Configurazione del comportamento simulato del dispatcher
//            requestDispatcher = mock(RequestDispatcher.class);
//            when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
//
//            // Esecuzione del metodo da testare
//            servlet.doPost(request, response);
//
////            // Verifica che il metodo doInsert del DAO non sia stato chiamato
////            verify(listaDAO, never()).doInsert(any());
//
////            // Verifica che il dispatcher sia stato chiamato con il percorso corretto
////            verify(requestDispatcher).forward(request, response);
//
////            // Verifica che l'attributo "result" sia impostato correttamente
////            verify(request).setAttribute(eq("result"), anyString());
////            // Verifica che l'attributo "result" contenga un messaggio di errore
////            verify(request).setAttribute("result", "Errore: il nome della lista non può superare i 30 caratteri");
//
////            // Verifica che non sia stato impostato l'attributo "userLists"
////            verify(request, never()).setAttribute(eq("userLists"), any(ArrayList.class));
//
//        } catch (Exception e) {
//            fail("Eccezione non attesa: " + e.getMessage());
//        }
    }

//        try{
//        // Configurazione del comportamento simulato del DAO
//        when(listaDAO.doInsert(any())).thenReturn(0); // Simula che l'inserimento abbia avuto successo
//        when(listaDAO.doRetrieveByEmail(anyString())).thenReturn(new ArrayList<>()); // Simula un elenco vuoto
//
//        // Configurazione del comportamento simulato del dispatcher
//        requestDispatcher = mock(RequestDispatcher.class);
//        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
//
//        // Esecuzione del metodo da testare
//        servlet.doPost(request, response);
//    }catch (Exception e) {
//        e.printStackTrace();
//        verify(request).setAttribute(eq("result"), "Errore durante l'inserimento della lista: " + e.getMessage());
//    }

//    @Test
//    public void testAggiungiListaServlet() throws Exception {
//        // Crea un mock per ListaDAO
//        ListaDAO listaDAOMock = mock(ListaDAO.class);
//
//        // Imposta il mock come DAO nella servlet
//        AggiungiListaServlet.setListaDAO(listaDAOMock);
//
//        // Crea un'istanza di AggiungiListaServlet
//        AggiungiListaServlet aggiungiListaServlet = new AggiungiListaServlet();
//
//        // Crea un oggetto MockMvc per eseguire i test sulla servlet
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(aggiungiListaServlet).build();
//
//        // Simula una richiesta POST alla servlet
//        mockMvc.perform(post("/AggiungiListaServlet")
//                        .param("Email_Persona", "test@example.com")
//                        .param("Nome", "TestLista")
//                        .param("Descrizione", "Descrizione di test")
//                        .param("Privata", "1"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"));  // Assicurati che il view name sia corretto
//
//        // Verifica che il metodo doInsert sia stato chiamato esattamente una volta con un oggetto Lista
//        verify(listaDAOMock, times(1)).doInsert(any(Lista.class));
//
//        // Verifica che l'attributo "result" sia presente nella richiesta e che abbia il valore corretto
//        assertEquals("Lista inserita!", mockMvc.getRequest().getAttribute("result"));
//
//        // Verifica che l'attributo "userLists" sia presente nella richiesta e che sia una lista non vuota
//        ArrayList<Lista> userLists = (ArrayList<Lista>) mockMvc.getRequest().getAttribute("userLists");
//        assertNotNull(userLists);
//        assertFalse(userLists.isEmpty());
//    }

}
