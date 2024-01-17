package application.controller;

import application.entity.Lista;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.ListaDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet(name = "ModificaListaServlet", value = "/ModificaListaServlet")
public class ModificaListaServlet extends HttpServlet {
    private static final String CARTELLA_UPLOAD = "upload";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ListaDAO lDAO = new ListaDAO();

        if (action.equals("AGGIORNA")) {
            Lista l = new Lista();
            l.setId(Integer.parseInt(request.getParameter("id")));
            l.setNome(request.getParameter("nome"));
            l.setDescrizione(request.getParameter("descrizione"));
            l.setImmagine(request.getParameter("immagine"));

            String result = "";
            try {
                lDAO.doUpdate(l);
                result = "Lista aggiornato!";
            } catch (Exception e) {
                result = "Lista già esistente!";
                request.setAttribute("result", result);

                ArrayList<Lista> lists = (ArrayList<Lista>) lDAO.doRetrieveAll();
                request.setAttribute("lists", lists);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
                requestDispatcher.forward(request, response);
                return;
            }

            request.setAttribute("result", result);

            ArrayList<Lista> lists = (ArrayList<Lista>) lDAO.doRetrieveAll();
            request.setAttribute("lists", lists);
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
            ds.forward(request, response);
        }

        if (action.equals("ELIMINA")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String result;

            if (lDAO.doDeleteList(id) == 0) {
                result = "Problema eliminazione!";
            } else {
                result = "Lista eliminata!";
            }

            //Imposta la risposta
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);




//        } else if (action.equals("creazione")) {
//            String nome = request.getParameter("nome");
//            String descrizione = request.getParameter("descrizione");
//
//            Part filePart = request.getPart("immagine");
//
//            // Verifica se il filePart è diverso da null e se ha un nome
//            if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
//                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//
//                Lista l = new Lista();
//                l.setNome(nome);
//                l.setDescrizione(descrizione);
//                l.setImmagine(fileName);
//
//                String result = "";
//                ListaDAO pDAO = new ListaDAO();
//                try {
//                    lDAO.doInsert(l);
//                    result = "Lista Creata!";
//                } catch (Exception e) {
//                    result = "Lista già creata!";
//                    request.setAttribute("result", result);
//
//                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
//                    requestDispatcher.forward(request, response);
//                }
//
//                String destinazione = CARTELLA_UPLOAD + File.separator + fileName;
//                Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
//
//                // se un file con quel nome esiste già, gli cambia nome
//                for (int i = 2; Files.exists(pathDestinazione); i++) {
//                    destinazione = CARTELLA_UPLOAD + File.separator + i + "_" + fileName;
//                    pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
//                }
//
//                InputStream fileInputStream = filePart.getInputStream();
//                // crea CARTELLA_UPLOAD, se non esiste
//                Files.createDirectories(pathDestinazione.getParent());
//                // scrive il file
//                Files.copy(fileInputStream, pathDestinazione);
//
//                request.setAttribute("result", result);
//
//                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
//                requestDispatcher.forward(request, response);
//            } else {
//                // Nessun file fornito per l'upload
//                String errorMessage = "Nessun file fornito per l'upload.";
//                request.setAttribute("result", errorMessage);
//
//                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/gui/liste.jsp");
//                requestDispatcher.forward(request, response);
//            }
        } else if (action.equals("rimuoviFilm")) {
            int idLista = Integer.parseInt(request.getParameter("idLista"));
            int idFilm = Integer.parseInt(request.getParameter("idFilm"));
            String result;

            int rowsAffected = lDAO.doDeleteFilmList(idLista, idFilm);
            if (rowsAffected == 0) {
                result = "Il film non era presente nella lista o si è verificato un problema durante la rimozione.";
            } else {
                result = "Il film è stato rimosso con successo dalla lista!";
            }

            // Restituisci la risposta come JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }
        }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListaServlet?action=lista");
        requestDispatcher.forward(request, response);
    }
}
