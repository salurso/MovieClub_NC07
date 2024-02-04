<%@ page import="application.entity.Film" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="application.entity.Lista" %>
<%@ page import="storage.model.FilmDAO" %>
<%@ page import="storage.model.ListaDAO" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="./css/film.css?v=<%=new Random().nextInt()%>"/>
    <link rel="stylesheet" type="text/css" href="./css/admin/alert.css?v=<%=new Random().nextInt()%>"/>
    <title>MovieClub</title>

    <script>

        //funzione aggiunta film watchlist
        function addWatchlist(filmId) {
            var formId = "watchlistForm_" + filmId;
            var form = document.getElementById(formId);
            var xhr = new XMLHttpRequest();

            var formData = new FormData(form);

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        alert("Film aggiunto dalla watchlist con successo!");
                        window.location.reload();
                    } else {
                        alert("Film già presente nella watchlist");
                    }
                }
            };

            xhr.open("POST","WatchlistServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send(new URLSearchParams(formData));
        }

    </script>

</head>
<body class="body">
    <%@ include file="/WEB-INF/navbar/navbar.jsp" %>

    <div class="centered-alert">
        <%if(request.getAttribute("LoginSuccess")!=null){%>
        <div class="alert" id="alert">
            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
            <%=request.getAttribute("LoginSuccess")%>
        </div>
        <%}%>
    </div>

    <div class="centered-alert">
            <% if(request.getAttribute("result")!=null){ %>
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            <%=request.getAttribute("result")%>
        </div>
            <% } %>
    </div>

    <%
        ArrayList<Film> films = (ArrayList<Film>) FilmDAO.getInstance().doRetrieveAllFromYear2022();
        ArrayList<Lista> userLists = new ArrayList<>();
        if(persona!=null) {
            userLists = ListaDAO.getInstance().doRetrieveByEmail(persona.getEmail());
        }
    %>

    <div id="carouselExampleCaptions" class="carousel slide">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="./images/home1.jpg" class="d-block w-100" alt="non disponibile" style="max-height: 80vh">
            </div>
            <div class="carousel-item">
                <img src="./images/home2.jpg" class="d-block w-100" alt="non disponibile" style="max-height: 80vh">
            </div>
            <div class="carousel-item">
                <img src="./images/home3.jpg" class="d-block w-100" alt="non disponibile" style="max-height: 80vh">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <h2 class="title_release">Ultime uscite</h2>
    <div class="row row-cols-1 row-cols-md-4 g-4" id="container-film">
        <%
            for(Film f : films){
        %>
        <div class="col" data-genre="<%=f.getGenere()%>" id="genere-paragraph">
            <div class="card h-100">
                <% if (persona != null) { %>
                <form id="watchlistForm_<%= f.getId() %>" action="WatchlistServlet" method="post">
                    <input type="hidden" name="idFilm" value="<%= f.getId() %>">
                    <input type="hidden" name="richiesta" value="addWatchlist">
                    <button type="button" class="btn btn-primary position-absolute top-0 end-0"
                            onclick="addWatchlist(<%= f.getId() %>)">+
                    </button>
                </form>
                <% } else{ %>
                <a href="MainServlet?action=login" class="btn btn-primary position-absolute top-0 end-0" id="link-addWatchlist">+</a>
                <%}%>
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
                        <%if(persona != null){%>
                        <select name="idLista" class="btn btn-primary" id="listaSelect_<%= f.getId() %>" onchange="aggiungiFilm('<%= f.getId() %>')">
                            <option value="" disabled selected>Lista +</option>
                            <%
                                for(Lista l : userLists){
                            %>
                            <option value="<%= l.getId() %>"><%= l.getNome() %></option>
                            <%
                                }
                            %>
                        </select>
                        <%}%>
                    </form>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</body>
</html>