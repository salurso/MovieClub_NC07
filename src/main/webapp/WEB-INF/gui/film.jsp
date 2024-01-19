<%@ page import="application.entity.Film" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %>
<%@ page import="application.entity.Lista" %><%--
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
    <link rel="stylesheet" type="text/css" href="./css/film.css?v=<%=new Random().nextInt()%>"/>

    <script>
        //funzione per gestire il cambio nella tendina di selezione
        function aggiungiFilm(idFilm) {
            var listaSelect = document.getElementById('listaSelect_' + idFilm);
            listaSelect.form.submit();
        }

        // Funzione per filtrare i film in base ai generi selezionati
        function filterFilms() {
            var selectedGenres = [];

            // Raccogli i generi selezionati
            document.querySelectorAll('.genre-filter:checked').forEach(function(checkbox) {
                selectedGenres.push(checkbox.value);
            });

            // Mostra o nascondi i paragrafi dei film in base ai generi selezionati
            var filmParagraphs = document.querySelectorAll('#genere-paragraph');
            filmParagraphs.forEach(function(paragraph) {
                var genre = paragraph.getAttribute('data-genre');
                if (selectedGenres.length === 0 || hasAllSelectedGenres(selectedGenres, genre)) {
                    paragraph.style.display = 'block';
                } else {
                    paragraph.style.display = 'none';
                }
            });
        }

        // Funzione per verificare se tutti i generi selezionati sono presenti nella lista di generi del film
        function hasAllSelectedGenres(selectedGenres, filmGenre) {
            var filmGenresArray = filmGenre.split(',').map(function (genre) {
                return genre.trim(); // Rimuove eventuali spazi prima o dopo il genere
            });

            return selectedGenres.every(function (selectedGenre) {
                return filmGenresArray.includes(selectedGenre);
            });
        }

        // Aggiungi un listener per gli eventi di cambio nelle checkbox
        document.addEventListener('DOMContentLoaded', function() {
            document.querySelectorAll('.genre-filter').forEach(function(checkbox) {
                checkbox.addEventListener('change', filterFilms);
            });
        });
    </script>


</head>
<body class="body">
<%@ include file="/WEB-INF/navbar/navbar.jsp" %>
<% if(request.getAttribute("result") != null) { %>
<div class="alert alert-warning alert-dismissible fade show" role="alert">
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    <%= request.getAttribute("result") %>
</div>
<% } %>
<%
    ArrayList<Film> films = (ArrayList<Film>) request.getAttribute("films");
    ArrayList<Lista> lists = (ArrayList<Lista>) request.getAttribute("lists");

%>
<div>
    <div>
        <div id="div-filtri">
            <h2 id="title-filtri">Filtri: </h2>
            <div id="filtri-genre">
                <label><input type="checkbox" class="genre-filter" value="Documentary">Documentary</label>
                <label><input type="checkbox" class="genre-filter" value="Biography">Biography</label>
                <label><input type="checkbox" class="genre-filter" value="Drama">Drama</label>
                <label><input type="checkbox" class="genre-filter" value="Music">Music</label>
                <label><input type="checkbox" class="genre-filter" value="Horror">Horror</label>
                <label><input type="checkbox" class="genre-filter" value="Sci-Fi">Sci-Fi</label>
                <label><input type="checkbox" class="genre-filter" value="Crime">Crime</label>
                <label><input type="checkbox" class="genre-filter" value="Mystery">Mystery</label>
                <label><input type="checkbox" class="genre-filter" value="Romance">Romance</label>
                <label><input type="checkbox" class="genre-filter" value="Thriller">Thriller</label>
                <label><input type="checkbox" class="genre-filter" value="Adventure">Adventure</label>
                <label><input type="checkbox" class="genre-filter" value="Comedy">Comedy</label>
                <label><input type="checkbox" class="genre-filter" value="Action">Action</label>
                <label><input type="checkbox" class="genre-filter" value="Sport">Sport</label>
                <label><input type="checkbox" class="genre-filter" value="Fantasy">Fantasy</label>
                <label><input type="checkbox" class="genre-filter" value="History">History</label>
                <label><input type="checkbox" class="genre-filter" value="Family">Family</label>
                <label><input type="checkbox" class="genre-filter" value="Animation">Animation</label>
                <label><input type="checkbox" class="genre-filter" value="War">War</label>
                <label><input type="checkbox" class="genre-filter" value="Western">Western</label>
                <label><input type="checkbox" class="genre-filter" value="Musical">Musical</label>
            </div>
        </div>
    </div>


    <div class="row row-cols-1 row-cols-md-4 g-4" id="container-film">
        <%
            for(Film f : films){
        %>
        <div class="col" data-genre="<%=f.getGenere()%>" id="genere-paragraph">
            <div class="card h-100">
                <a href="#" class="btn btn-primary position-absolute top-0 end-0" id="link-addWatchlist">+W</a>
                <img src="<%=f.getCopertina()%>" class="card-img-top" alt="immagine non disponibile">
                <div class="card-body">
                    <h5 class="card-title"><%=f.getTitolo()%></h5>
                    <p class="card-text">Genere: <%=f.getGenere()%></p>
                    <p class="card-text">Regista: <%=f.getRegista()%></p>
                    <p class="card-text">Durata: <%=f.getDurata()%></p>
                    <a href="FilmServlet?id=<%=f.getId()%>" class="btn btn-primary">Dettagli</a>

                    <!-- Aggiungi un film alla lista -->
                    <form action="ListaServlet" method="POST" id="aggiungiFilmForm_<%= f.getId() %>">
                        <input type="hidden" name="action" value="aggiungiFilm">
                        <input type="hidden" name="idFilm" value="<%= f.getId() %>">

                        <!-- tendina per vedere le liste -->
                        <select name="idLista" class="btn btn-primary" id="listaSelect_<%= f.getId() %>" onchange="aggiungiFilm('<%= f.getId() %>')">
                            <option value="" disabled selected>Aggiungi a Lista</option>
                            <%
                                for(Lista l : lists){
                            %>
                            <option value="<%= l.getId() %>"><%= l.getNome() %></option>
                            <%
                                }
                            %>
                        </select>
                    </form>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
