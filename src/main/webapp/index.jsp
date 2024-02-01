<%@ page import="application.entity.Film" %>
<%@ page import="java.util.ArrayList" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Hello World </title>
</head>
<body>
    <%@ include file="/WEB-INF/navbar/navbar.jsp" %>

    <div class="centered-alert">
            <% if(request.getAttribute("result")!=null){ %>
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            <%=request.getAttribute("result")%>
        </div>
            <% } %>
    </div>

<%--    <%ArrayList<Film> films = (ArrayList<Film>) request.getAttribute("films");%>--%>

<%--    <div id="carouselExampleCaptions" class="carousel slide">--%>
<%--        <div class="carousel-indicators">--%>
<%--            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>--%>
<%--            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>--%>
<%--            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>--%>
<%--        </div>--%>
<%--        <div class="carousel-inner">--%>
<%--            <div class="carousel-item active">--%>
<%--                <img src="../images/home1.jpg" class="d-block w-100" alt="...">--%>
<%--                <div class="carousel-caption d-none d-md-block">--%>
<%--                    <h5>First slide label</h5>--%>
<%--                    <p>Some representative placeholder content for the first slide.</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="carousel-item">--%>
<%--                <img src="" class="d-block w-100" alt="...">--%>
<%--                <div class="carousel-caption d-none d-md-block">--%>
<%--                    <h5>Second slide label</h5>--%>
<%--                    <p>Some representative placeholder content for the second slide.</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="carousel-item">--%>
<%--                <img src="" class="d-block w-100" alt="...">--%>
<%--                <div class="carousel-caption d-none d-md-block">--%>
<%--                    <h5>Third slide label</h5>--%>
<%--                    <p>Some representative placeholder content for the third slide.</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">--%>
<%--            <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
<%--            <span class="visually-hidden">Previous</span>--%>
<%--        </button>--%>
<%--        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">--%>
<%--            <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
<%--            <span class="visually-hidden">Next</span>--%>
<%--        </button>--%>
<%--    </div>--%>



    <h1> Ciao, Mondo! </h1>

    <a href="MainServlet?action=film">VISUALIZZA FILM</a><br>
    <a href="ListaServlet?action=lista">VISUALIZZA LISTE</a><br>
    <a href="MainServlet?action=registrazione">VISUALIZZA REGISTRAZIONE</a><br>
    <a href="ListaServlet?action=creazione">CREA LISTE</a><br>
    <a href="RecensioneServlet?action=aggRecensione">INSERISCI UNA RECENSIONE</a><br>
    <a href="ListaServlet?action=pubblica">TUTTE LE LISTE PUBBLICHE</a><br>
</body>
</html>