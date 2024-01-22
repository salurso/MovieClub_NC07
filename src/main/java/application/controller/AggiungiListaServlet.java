package application.controller;

import application.entity.Lista;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import storage.model.ListaDAO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AggiungiListaServlet", value = "/AggiungiListaServlet")
public class AggiungiListaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListaDAO lDAO = new ListaDAO();
        ArrayList<Lista> lists = lDAO.doRetrieveAll();
        request.setAttribute("lists", lists);

        String Nome = request.getParameter("Nome");
        String Descrizione = request.getParameter("Descrizione");
        boolean Privata = "1".equals(request.getParameter("Privata"));

        Lista l = new Lista();
        l.setNome(Nome);
        l.setDescrizione(Descrizione);
        l.setPrivata(Privata);

        String result = "";
        try{
            lDAO.doInsert(l);
            result = "Lista inserita!";
        }catch (Exception e){
            result = "Lista già esistente!";
        }
        request.setAttribute("result", result);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MainServlet?action=homePage");
        requestDispatcher.forward(request, response);
    }
}
