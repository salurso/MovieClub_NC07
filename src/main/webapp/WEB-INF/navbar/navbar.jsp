<%@ page import="java.util.Random" %>
<%@ page import="application.entity.Persona" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 16/01/2024
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css?v=<%=new Random().nextInt()%>"/>
</head>
<body>
<% Persona persona = (Persona) session.getAttribute("Persona"); %>

<nav class="navbar navbar-expand-lg bg-dark" id="navbar">
    <div class="container-fluid">
        <a class="navbar-brand" href="MainServlet?action=homePage">MovieClub</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0" id="text-ul">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="MainServlet?action=film">Film</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="MainServlet?action=consigliati">Consigliati</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ListaServlet?action=pubblica">Liste pubbliche</a>
                </li>
                <%if(persona != null) {%>
                <li class="nav-item">
                    <a class="nav-link" href="MainServlet?action=watchlist">Watchlist</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ListaServlet?action=lista">Le mie liste</a>
                </li>
                <%}%>
            </ul>
            <%if(persona == null) {%>
                <a class="nav-link" href="MainServlet?action=login"><button type="button" class="btn btn-primary">ACCEDI</button></a>
            <%} else { %>
            <a class="nav-link" href="MainServlet?action=areaPersonale"><span class="navbar-text" id="text-areaPersonale">Area Personale</span></a>
            <input type="hidden" name="Email_persona" id="Email_persona" value="<%=persona.getEmail()%>">
            <div class="nav-item dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" id="navbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Ciao, <%= persona.getNome() %>
                </button>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <form action="MainServlet?action=logout" method="post">
                        <button class="dropdown-item" type="submit">Logout</button>
                    </form>

                </div>
            </div>
           <%}%>
        </div>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
