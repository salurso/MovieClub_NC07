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
    <title>Inserisci prodotto</title>
<%--    <script>--%>
<%--        function validateInsert() {--%>
<%--            var nameRGX=/^[a-zA-Z' ']*$/;--%>
<%--            var name=document.getElementById('name').value;--%>
<%--            if((nameRGX.test(name))==false){--%>
<%--                alert("Nome non valido!");--%>
<%--                return false;--%>
<%--            }--%>

<%--            var price=document.getElementById('price').value;--%>
<%--            var priceRGX=/^[0-9'.']*$/;--%>
<%--            if(priceRGX.test(price)==false){--%>
<%--                alert("Errore nella definizione del prezzo");--%>
<%--                return false;--%>
<%--            }--%>

<%--            var filePart=document.getElementById('image').value;;--%>
<%--            if(filePart.length>32){--%>
<%--                alert("Nome immagine massimo 20 caratteri");--%>
<%--                return false;--%>
<%--            }--%>

<%--            return true;--%>
<%--        }--%>
<%--    </script>--%>
</head>
<body>
<%--<%@ include file="/WEB-INF/navbar/navbarAdmin.jsp" %>--%>

<div class="container-info">
    <div class="add_product">
        <form action="AggiungiFilmServlet" enctype="multipart/form-data" method="POST">
            <h3>Aggiungi Film</h3>

            <label for="titolo"> Titolo: </label>
            <input type="text" name="titolo" id="titolo" maxlength="50"  required>

            <label for="regista"> Regista: </label>
            <input type="text" name="regista" id="regista" required>

            <label for="image">Link copertina: </label>
            <input type="text" id="image" name="copertina">

            <label for="trailer">Link trailer: </label>
            <input type="text" id="trailer" name="trailer">

            <label for="generi">Genere: </label>
            <select id="generi" name="generi" required>
                <option value="Inserisci genere" disabled selected>Inserisci genere</option>
                <option value="Documentary">Documentary</option>
                <option value="Biography">Biography</option>
                <option value="Drama">Drama</option>
                <option value="Music">Music</option>
                <option value="Horror">Horror</option>
                <option value="Sci-Fi">Sci-Fi</option>
                <option value="Crime">Crime</option>
                <option value="Mystery">Mystery</option>
                <option value="Romance">Romance</option>
                <option value="Thriller">Thriller</option>
                <option value="Adventure">Adventure</option>
                <option value="Comedy">Comedy</option>
                <option value="Action">Action</option>
                <option value="Sport">Sport</option>
                <option value="Fantasy">Fantasy</option>
                <option value="History">History</option>
                <option value="Family">Family</option>
                <option value="Animation">Animation</option>
                <option value="War">War</option>
                <option value="Western">Western</option>
                <option value="Musical">Musical</option>
            </select>

            <label for="data"> Data uscita: </label>
            <input type="date" name="data" id="data" required>

            <label for="durata"> Durata: </label>
            <input type="time" name="durata" id="durata" required>

            <label for="descrizione">Descrizione: </label>
            <textarea name="descrizione" id="descrizione" style="height:200px" maxlength="500"></textarea>

            <input class="btn_add" type="submit" name="action" value="AGGIUNGI FILM">
<%--            onclick="return(validateInsert())"--%>
        </form>
    </div>
</div>


</body>
</html>

