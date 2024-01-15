<%@ page import="application.entity.Film" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Costantino
  Date: 15/01/2024
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Film</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

    <%
        ArrayList<Film> films = (ArrayList<Film>) request.getAttribute("films");
    %>
    <div>

        <div class="row row-cols-1 row-cols-md-4 g-4">

            <%
                for(Film f : films){
            %>

            <div class="col">
                <div class="card h-100">
                    <a href="#" class="btn btn-primary">+</a>
                    <img src="..." class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title"><%f.getTitolo();%></h5>
                        <p class="card-text">Genere: <%f.getGenere();%></p>
                        <p class="card-text">Regista: <%f.getRegista();%></p>
                        <p class="card-text">Durata: <%f.getDurata();%></p>
                        <a href="#" class="btn btn-primary">Dettagli</a>
                        <a href="#" class="btn btn-primary">Lista +</a>
                    </div>
                </div>
            </div>

            <%
                }
            %>

            <div class="col">
                <div class="card h-100">
                    <a href="#" class="btn btn-primary">+</a>
                    <img src="..." class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">The Avengers</h5>
                        <p class="card-text">Genere: Fantasy</p>
                        <p class="card-text">Regista: Gino</p>
                        <p class="card-text">Durata: 2:30</p>
                        <a href="#" class="btn btn-primary">Dettagli</a>
                        <a href="#" class="btn btn-primary">Lista +</a>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card h-100">
                    <a href="#" class="btn btn-primary">+</a>
                    <img src="..." class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">The Avengers</h5>
                        <p class="card-text">Genere: Fantasy</p>
                        <p class="card-text">Regista: Gino</p>
                        <p class="card-text">Durata: 2:30</p>
                        <a href="#" class="btn btn-primary">Dettagli</a>
                        <a href="#" class="btn btn-primary">Lista +</a>
                    </div>
                </div>
            </div>

        </div>

    </div>
</body>
</html>
