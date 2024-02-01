package application.controller;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.entity.Lista;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import storage.model.ListaDAO;

import static org.junit.jupiter.api.Assertions.*;
public class AggiugniListaServletTestAssert {

    @Test
    void testDoPost_SuccessfulInsert() throws Exception {
        // Preparazione dei mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        ListaDAO listaDAO = mock(ListaDAO.class);
        Connection conn = mock(Connection.class);

        // Configurazione degli input simulati
        when(request.getParameter("Email_Persona")).thenReturn("user1@gmail.com");
        when(request.getParameter("Nome")).thenReturn("NomeLista");

        // Simula il PreparedStatement
        PreparedStatement psInsertMock = mock(PreparedStatement.class);
        // Configura il comportamento di executeUpdate() per restituire 1 (successo)
        when(psInsertMock.executeUpdate()).thenReturn(1);
        // Sostituisci il PreparedStatement reale con il mock nel DAO
        when(conn.prepareStatement(any(String.class))).thenReturn(psInsertMock);

        // Simula il comportamento di getRequestDispatcher
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        // Costruisci la servlet passando il mock del ListaDAO
//        AggiungiListaServlet servlet = new AggiungiListaServlet(listaDAO);

        // Esegui il metodo doPost della servlet
//        servlet.doPost(request, response);

        // Verifica che il metodo doInsert del mock ListaDAO sia stato chiamato
        verify(listaDAO).doInsert(any());

        // Verifica che la richiesta sia stata inoltrata alla giusta pagina JSP
        verify(request).getRequestDispatcher("index.jsp");
        verify(requestDispatcher).forward(request, response);
    }



//    @Test
//    void testDoPost_FailedInsert() throws Exception {
//        // Preparazione dei mock
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        ListaDAO listaDAO = mock(ListaDAO.class);
//        AggiungiListaServlet servlet = new AggiungiListaServlet();
//
//        // Configurazione degli input simulati
//        when(request.getParameter("Email_Persona")).thenReturn("user1@gmail.com");
//        when(request.getParameter("Nome")).thenReturn("NomeLista");
//
//        // Simula il PreparedStatement
//        PreparedStatement psInsertMock = mock(PreparedStatement.class);
//        // Configura il comportamento di executeUpdate() per restituire 0 (fallimento)
//        when(psInsertMock.executeUpdate()).thenReturn(0);
//        // Sostituisci il PreparedStatement reale con il mock nel DAO
//        when(con.prepareStatement(any(String.class))).thenReturn(psInsertMock);
//
//        // Inserisci il mock del ListaDAO nella tua servlet
//        servlet.setListaDAO(listaDAO);
//
//        // Esegui il metodo doPost della servlet
//        servlet.doPost(request, response);
//
//        // Verifica che il metodo doInsert del mock ListaDAO sia stato chiamato
//        verify(listaDAO).doInsert(any());
//
//        // Verifica che la servlet abbia impostato correttamente l'attributo "result"
//        verify(request).setAttribute("result", "Errore durante l'inserimento della lista");
//
//        // Verifica che la richiesta sia stata inoltrata alla giusta pagina JSP
//        verify(request).getRequestDispatcher("index.jsp");
//        verify(requestDispatcher).forward(request, response);
//    }

}
