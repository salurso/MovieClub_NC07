<%@ page import="java.util.Random" %><%--
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="./css/navbar.css?v=<%=new Random().nextInt()%>"/>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-dark" id="navbar">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">MovieClub</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0" id="text-ul">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="MainServletAdmin?action=film">Film</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="MainServletAdmin?action=consigliati">Consigliati</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="MainServletAdmin?action=contatti">Contatti</a>
                </li>
            </ul>
            <a class="nav-link" href="MainServletAdmin?action=areaPersonale"><span class="navbar-text" id="text-areaPersonale">Area Personale</span></a>
            <button type="button" class="btn btn-primary">ACCEDI</button>
        </div>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
