<%--
  Created by IntelliJ IDEA.
  User: Costantino
  Date: 19/01/2024
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="application.entity.Film" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="./css/admin/modificaFilm.css?v=<%=new Random().nextInt()%>"/>
    <%
        Film f = (Film) request.getAttribute("film");
    %>
    <title><%=f.getTitolo()%></title>
    <script>
<%--        function validateUpdate() {--%>
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

<%--            return true;--%>
<%--        }--%>

        function confirmDelete(id){
            if(confirm("Sei sicuro di voler eliminare il film??")){
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function (){
                    if(xhr.readyState == 4 && xhr.status == 200){
                        alert(xhr.responseText)
                        window.location.href="MainServletAdmin?action=homeAdmin";
                    }
                };
                xhr.open("POST", "AggiornaFilmServlet?action=ELIMINA&id=" + id, true);
                xhr.send();
            }else{
                alert("eliminazione annullata");

            }
        }
    </script>
</head>
<body>
<%@ include file="/WEB-INF/navbar/navbarAdmin.jsp" %>

<div class="update_film">
    <form action="AggiornaFilmServlet"  method="POST">

        <h3 class="add_film_title">Modifica film</h3>

        <label for="id">id: </label>
        <input id="id" name="id" type="text" value="<%=f.getId()%>" readonly>

        <label for="titolo"> Titolo: </label>
        <input type="text" name="titolo" id="titolo" maxlength="50" value="<%=f.getTitolo()%>"required>

        <label for="regista"> Regista: </label>
        <input type="text" name="regista" id="regista" value="<%=f.getRegista()%>" required>

        <label for="image">Link copertina: </label>
        <input type="text" id="image" name="copertina" value="<%=f.getCopertina()%>">

        <label for="trailer">Link trailer: </label>
        <input type="text" id="trailer" name="trailer" value="<%=f.getTrailer()%>">


<%--        <select id="generi" name="generi">--%>
<%--            <option value="<%=f.getGenere()%>" selected><%=f.getGenere()%></option>--%>
<%--            <option value="Documentary">Documentary</option>--%>
<%--            <option value="Biography">Biography</option>--%>
<%--            <option value="Drama">Drama</option>--%>
<%--            <option value="Music">Music</option>--%>
<%--            <option value="Horror">Horror</option>--%>
<%--            <option value="Sci-Fi">Sci-Fi</option>--%>
<%--            <option value="Crime">Crime</option>--%>
<%--            <option value="Mystery">Mystery</option>--%>
<%--            <option value="Romance">Romance</option>--%>
<%--            <option value="Thriller">Thriller</option>--%>
<%--            <option value="Adventure">Adventure</option>--%>
<%--            <option value="Comedy">Comedy</option>--%>
<%--            <option value="Action">Action</option>--%>
<%--            <option value="Sport">Sport</option>--%>
<%--            <option value="Fantasy">Fantasy</option>--%>
<%--            <option value="History">History</option>--%>
<%--            <option value="Family">Family</option>--%>
<%--            <option value="Animation">Animation</option>--%>
<%--            <option value="War">War</option>--%>
<%--            <option value="Western">Western</option>--%>
<%--            <option value="Musical">Musical</option>--%>
<%--        </select>--%>
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
        <input type="text" name="durata" id="durata" placeholder="00:00:00" value="<%=f.getDurata()%>" required>

        <label for="descrizione">Descrizione: </label>
        <textarea name="descrizione" id="descrizione" style="height:200px" maxlength="500"><%=f.getDescrizione()%></textarea>

        <div class="btnadd">
            <input class="btn_update" type="submit" name="action" value="AGGIORNA" >
<%--            onclick="return(validateUpdate())"--%>
        </div>
    </form>
    <input class="btn_delete" type="submit" name="action" value="ELIMINA" onclick="return(confirmDelete(<%=f.getId()%>))">

</div>
</body>
</html>
