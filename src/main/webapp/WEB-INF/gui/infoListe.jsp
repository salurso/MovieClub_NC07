<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="application.entity.Lista" %>
<%@ page import="application.entity.Film" %>
<%@ page import="application.entity.Persona" %>
<%@ page import="storage.model.PersonaDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%
    String tipoRichiesta = (String) request.getAttribute("tipoRichiesta");
    Lista l = new Lista();
    PersonaDAO personaDAO = PersonaDAO.getInstance();

    if (tipoRichiesta == null) {
      l = (Lista) request.getAttribute("lists");
    }
  %>

  <% if (tipoRichiesta == null) { %>
  <title>Informazioni Lista: <%= l.getNome() %></title>
  <% } else { %>
  <title> Watchlist</title>
  <% } %>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="./css/infoListe.css?v=<%=new Random().nextInt()%>"/>
  <script src="./js/infoListe.js"></script>
</head>
<body class="body">
<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<%ArrayList<Film> films = null;
  ArrayList<Lista> lists = null;
  Persona creatoreLista = null;
  if(tipoRichiesta != null) {
    films = persona.getWatchlist();
} else {
    films = (ArrayList<Film>) request.getAttribute("films");
    lists = (ArrayList<Lista>) session.getAttribute("userLists");
    creatoreLista = personaDAO.doRetrieveByEmail(l.getEmail_Persona());
}
%>

<%
  if (persona == null && films.isEmpty() && (creatoreLista == null || !creatoreLista.getEmail().equals(persona != null ? persona.getEmail() : null))) { %>
<div class="containervuoto">
  <p>La lista non contiene film.</p>
</div>
<%
} else if (persona != null && films.isEmpty() && (creatoreLista == null || !creatoreLista.getEmail().equals(persona.getEmail()))){ %>
<div class="containervuoto">
  <p>La lista non contiene film.</p>
</div>
<%
  } else if (persona != null && films.isEmpty() && creatoreLista.getEmail().equals(persona.getEmail())){ %>
<div class="containervuoto">
  <p>La lista non contiene film.</p>
    <form action="MainServlet?action=film" method="POST" class="form_vuoto">
      <input type="hidden" name="idLista" value="<%= l.getId() %>">
      <button class="add_film" type="submit">Aggiungi Film</button>
    </form>
<%
} else { %>
  <% if(tipoRichiesta == null) { %>
  <h1 align="center">Lista: <%= l.getNome() %> </h1>
  <div class="container">
    <p class="pcreator" style="color: #ffa31a"><%= (creatoreLista != null) ? creatoreLista.getEmail() : "N/A" %></p>
  </div>
  <% } else { %>
  <h1 align="center"> Watchlist </h1>
  <div class="container">
    <p class="pcreator" style="color: #ffa31a"><%=persona.getNome().toUpperCase() %></p>
  </div>
  <% } %>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">Titolo</th>
      <th scope="col">Regista</th>
      <th scope="col">Durata</th>
<%--      <th scope="col">Copertina</th>--%>
      <th scope="col">Genere</th>
      <th scope="col">Azioni</th>
    </tr>
    </thead>
    <tbody>
    <% for (Film film: films){ %>
    <tr>
      <td><%=film.getTitolo()%></td>
      <td><%=film.getRegista()%></td>
      <td><%=film.getDurata()%></td>
<%--      <td><%=film.getCopertina()%></td>--%>
      <td><%=film.getGenere()%></td>
      <td>

        <%if(tipoRichiesta == null) {%>
          <form id="removeForm_<%= film.getId() %>" class="remove-form"
                action="ModificaListaServlet?action=rimuoviFilm" method="POST">
            <input type="hidden" name="idLista" value="<%= l.getId() %>">
            <input type="hidden" name="idFilm" value="<%= film.getId() %>">
            <button type="button" class="btn_remove"
                    onclick="removeFilm(<%= film.getId() %>)"
                    <%= (persona != null && creatoreLista != null && persona.getEmail().equals(creatoreLista.getEmail())) ? "" : "disabled" %>>RIMUOVI
            </button>
          </form
        <%} else {%>
            <form id="watchlistForm_<%= film.getId() %>" class="remove-form"
                  action="WatchlistServlet" method="POST">
              <input type="hidden" name="idFilm" value="<%= film.getId() %>">
              <button type="button" class="btn_remove"
                      onclick="removeWatchlist(<%= film.getId() %>)">RIMUOVI
              </button>
            </form>
        <%}%>

      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
<%
  } %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
