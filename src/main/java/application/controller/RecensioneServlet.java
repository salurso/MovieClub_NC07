package application.controller;

import application.entity.Film;
import application.entity.Recensione;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.model.FilmDAO;
import storage.model.RecensioneDAO;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "RecensioneServlet", value = "/RecensioneServlet")
public class RecensioneServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
