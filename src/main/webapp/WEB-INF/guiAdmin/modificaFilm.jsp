<%--
  Created by IntelliJ IDEA.
  User: Costantino
  Date: 19/01/2024
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Random" %>
<%@ page import="application.entity.Film" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="./css/admin/modificaFilm.css?v=<%=new Random().nextInt()%>"/>
    <script src="./js/film.js"></script>
    <%
        Film f = (Film) request.getAttribute("film");
    %>
    <title><%=f.getTitolo()%></title>
</head>
<body>
<%@ include file="/WEB-INF/navbar/navbarAdmin.jsp" %>

<div class="update_film">
    <form action="AggiornaFilmServlet"  method="POST">

        <h3 class="add_film_title">Modifica film</h3>

        <label for="id">id: </label>
        <input id="id" name="id" type="text" value="<%=f.getId()%>" readonly>

        <label for="titolo"> Titolo: </label>
        <input type="text" name="titolo" id="titolo" maxlength="60" value="<%=f.getTitolo()%>"required>

        <label for="regista"> Regista: </label>
        <input type="text" name="regista" id="regista" maxlength="60" value="<%=f.getRegista()%>" required>

        <label for="image">Link copertina: </label>
        <input type="text" id="image" name="copertina" maxlength="150" value="<%=f.getCopertina()%>">

        <label for="trailer">Link trailer: </label>
        <input type="text" id="trailer" name="trailer" maxlength="100" value="<%=f.getTrailer()%>">

        <label>Generi: </label>
        <div id="filtri-genre">
            <label><input name="generi" type="checkbox" class="genre-filter" value="Documentary" <%= (f.getGenere().contains("Documentary") ? "checked" : "") %>>Documentary</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Biography" <%= (f.getGenere().contains("Biography") ? "checked" : "") %>>Biography</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Drama" <%= (f.getGenere().contains("Drama") ? "checked" : "") %>>Drama</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Music" <%= (f.getGenere().contains("Music") ? "checked" : "") %>>Music</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Horror" <%= (f.getGenere().contains("Horror") ? "checked" : "") %>>Horror</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Sci-Fi" <%= (f.getGenere().contains("Sci-Fi") ? "checked" : "") %>>Sci-Fi</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Crime" <%= (f.getGenere().contains("Crime") ? "checked" : "") %>>Crime</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Mystery" <%= (f.getGenere().contains("Mystery") ? "checked" : "") %>>Mystery</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Romance" <%= (f.getGenere().contains("Romance") ? "checked" : "") %>>Romance</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Thriller" <%= (f.getGenere().contains("Thriller") ? "checked" : "") %>>Thriller</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Adventure" <%= (f.getGenere().contains("Adventure") ? "checked" : "") %>>Adventure</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Comedy" <%= (f.getGenere().contains("Comedy") ? "checked" : "") %>>Comedy</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Action" <%= (f.getGenere().contains("Action") ? "checked" : "") %>>Action</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Sport" <%= (f.getGenere().contains("Sport") ? "checked" : "") %>>Sport</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Fantasy" <%= (f.getGenere().contains("Fantasy") ? "checked" : "") %>>Fantasy</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="History" <%= (f.getGenere().contains("History") ? "checked" : "") %>>History</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Family" <%= (f.getGenere().contains("Family") ? "checked" : "") %>>Family</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Animation" <%= (f.getGenere().contains("Animation") ? "checked" : "") %>>Animation</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="War" <%= (f.getGenere().contains("War") ? "checked" : "") %>>War</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Western" <%= (f.getGenere().contains("Western") ? "checked" : "") %>>Western</label>
            <label><input name="generi" type="checkbox" class="genre-filter" value="Musical" <%= (f.getGenere().contains("Musical") ? "checked" : "") %>>Musical</label>
        </div>
        <br>
        <label for="data"> Data uscita: </label>
        <input type="date" name="data" id="data" value="<%=f.getDataUscita()%>" required>

        <label for="durata"> Durata: </label>
        <input type="text" name="durata" id="durata" placeholder="00:00:00" value="<%=f.getDurata()%>">

        <label for="descrizione">Descrizione: </label>
        <textarea name="descrizione" id="descrizione" style="height:200px" maxlength="220" required><%=f.getDescrizione()%></textarea>

        <div class="btnadd">
            <input class="btn_update" type="submit" name="action" value="AGGIORNA" onclick="return(validateInsert())">
            <input class="btn_delete" type="submit" name="action" value="ELIMINA" onclick="return(confirmDelete(<%=f.getId()%>))">
        </div>
    </form>

</div>
</body>
</html>
