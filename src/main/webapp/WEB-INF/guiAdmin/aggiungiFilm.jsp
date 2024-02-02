<%--
  Created by IntelliJ IDEA.
  User: Costantino
  Date: 18/01/2024
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="./css/admin/aggiungiFilm.css?v=<%=new Random().nextInt()%>"/>
    <link rel="stylesheet" type="text/css" href="./css/admin/alert.css?v=<%=new Random().nextInt()%>"/>
    <script src="./js/film.js"></script>
    <title>Inserisci prodotto</title>
</head>
<body>
    <%@ include file="/WEB-INF/navbar/navbarAdmin.jsp" %>

    <%if(request.getAttribute("result")!=null){%>
    <div class="alert" id="alert">
        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
        <%=request.getAttribute("result")%>
    </div>
    <%}%>

    <div class="container-info">
        <div class="add_film">
            <form action="AggiungiFilmServlet" enctype="multipart/form-data" method="POST">
                <h3>Aggiungi Film</h3>

                <label for="titolo"> Titolo: </label>
                <input type="text" name="titolo" id="titolo" maxlength="60" required>

                <label for="regista"> Regista: </label>
                <input type="text" name="regista" id="regista" maxlength="60" required>

                <label for="image">Link copertina: </label>
                <input type="text" id="image" name="copertina" maxlength="150">

                <label for="trailer">Link trailer: </label>
                <input type="text" id="trailer" name="trailer" maxlength="100">

                <label>Generi: </label>
                <div id="filtri-genre">
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Documentary">Documentary</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Biography">Biography</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Drama">Drama</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Music">Music</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Horror">Horror</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Sci-Fi">Sci-Fi</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Crime">Crime</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Mystery">Mystery</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Romance">Romance</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Thriller">Thriller</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Adventure">Adventure</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Comedy">Comedy</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Action">Action</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Sport">Sport</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Fantasy">Fantasy</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="History">History</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Family">Family</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Animation">Animation</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="War">War</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Western">Western</label>
                    <label><input name="generi" type="checkbox" class="genre-filter" value="Musical">Musical</label>
                </div>
                <br>
                <label for="data"> Data uscita: </label>
                <input type="date" name="data" id="data" required>

                <label for="durata"> Durata: </label>
                <input type="text" name="durata" id="durata" placeholder="00:00:00">

                <label for="descrizione">Descrizione: </label>
                <textarea name="descrizione" id="descrizione" style="height:200px" maxlength="220" required></textarea>

                <input class="btn_add" type="submit" name="action" value="AGGIUNGI FILM" onclick="return(validateInsert())">
<%--                onclick="return(validateInsert())"--%>
            </form>
        </div>
    </div>
</body>
</html>

