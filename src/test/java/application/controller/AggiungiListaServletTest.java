//package application.controller;
//
//import com.github.javafaker.Faker;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//import storage.service.ListaService;
//
//import java.io.IOException;
//import java.util.Locale;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mockStatic;
//
//public class AggiungiListaServletTest {
//    Faker faker = new Faker();
//    boolean privata = true; // CORRETTO
//
//    @Test
//    public void nomeTroppoLungoTest() {
//        String email = faker.internet().emailAddress(); //CORRETTO
//        String nome = faker.lorem().characters(31); //SBAGLIATO
//        String descrizione = faker.lorem().sentence(); //CORRETTO
//
//        assertThrows(IllegalArgumentException.class,
//                () -> ListaService.doInsertService(email, nome, descrizione, privata),
//                "Formato Nome non corretto: Non deve superare i 30 caratteri!");
//    }
//
//    @Test
//    void testNomeListaMaxLength() throws Exception {
//        // Preparazione dei mock
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        RequestDispatcher requestDispatcher = null; // Inizializza a null
//        ListaDAO listaDAO = mock(ListaDAO.class);
//        AggiungiListaServlet servlet = new AggiungiListaServlet();
//
//        // Configurazione degli input simulati
//        when(request.getParameter("Email_Persona")).thenReturn("user1@gmail.com");
//        when(request.getParameter("Nome")).thenReturn("PROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVAPROVA");
//
//            try{
//                when(listaDAO.doInsert(any())).thenReturn(0); // Simula che l'inserimento abbia avuto successo
//                servlet.doPost(request, response);
//            }catch (Exception e){
//                fail("Eccezione non attesa: " + e.getMessage());
//            }
//
//
////        try {
////            // Configurazione del comportamento simulato del DAO
////            when(listaDAO.doInsert(any())).thenReturn(1); // Simula che l'inserimento abbia avuto successo
////            when(listaDAO.doRetrieveByEmail(anyString())).thenReturn(new ArrayList<>()); // Simula un elenco vuoto
////
////            // Configurazione del comportamento simulato del dispatcher
////            requestDispatcher = mock(RequestDispatcher.class);
////            when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
////
////            // Esecuzione del metodo da testare
////            servlet.doPost(request, response);
////
//////            // Verifica che il metodo doInsert del DAO non sia stato chiamato
//////            verify(listaDAO, never()).doInsert(any());
////
//////            // Verifica che il dispatcher sia stato chiamato con il percorso corretto
//////            verify(requestDispatcher).forward(request, response);
////
//////            // Verifica che l'attributo "result" sia impostato correttamente
//////            verify(request).setAttribute(eq("result"), anyString());
//////            // Verifica che l'attributo "result" contenga un messaggio di errore
//////            verify(request).setAttribute("result", "Errore: il nome della lista non può superare i 30 caratteri");
////
//////            // Verifica che non sia stato impostato l'attributo "userLists"
//////            verify(request, never()).setAttribute(eq("userLists"), any(ArrayList.class));
////
////        } catch (Exception e) {
////            fail("Eccezione non attesa: " + e.getMessage());
////        }
//
//    public void emailNonValidaTest() {
//        String email = "email-non-valida"; // SBAGLIATO
//        String nome = faker.name().username(); //CORRETTO
//        String descrizione = faker.lorem().sentence(); //CORRETTO
//
//        assertThrows(IllegalArgumentException.class,
//                () -> ListaService.doInsertService(email, nome, descrizione, privata),
//                "Formato Email non corretto!");
//    }
//
//    @Test
//    public void descrizioneTroppoLungaTest() {
//        String email = faker.internet().emailAddress(); //CORRETTO
//        String nome = faker.name().username(); //CORRETTO
//        String descrizione = faker.lorem().characters(101); //SBAGLIATO
//
//        assertThrows(IllegalArgumentException.class,
//                () -> ListaService.doInsertService(email, nome, descrizione, privata),
//                "Formato Descrizione non valido: Non deve superare i 100 caratteri!");
//    }
//
//    @Test
//    public void testSuccesso() throws IOException {
//        // CASO DI SUCCESSO.
//        String email = faker.internet().emailAddress();
//        String nome = faker.name().username();
//        String descrizione = faker.lorem().sentence();
//
//        try (MockedStatic<ListaService> mocked = mockStatic(ListaService.class)) {
//            mocked.when(() -> ListaService.doInsertService(email, nome, descrizione, privata))
//                    .thenReturn(1);
//
//            int result = ListaService.doInsertService(email, nome, descrizione, privata);
//            assertEquals(1, result);
//        }
//    }
//}
