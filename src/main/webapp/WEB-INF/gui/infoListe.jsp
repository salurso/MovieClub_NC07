<%@ page import="application.entity.Lista" %>
<%@ page import="application.entity.Film" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%
    Lista l = (Lista) request.getAttribute("lists");
  %>
  <title>Informazioni Lista: <%= l.getNome()%></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="./css/liste.css?v=<%=new Random().nextInt()%>"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script>
    function removeFilm(filmId) {
      var formId = "removeForm_" + filmId;
      var form = document.getElementById(formId);

      var formData = new FormData(form);
      var xhr = new XMLHttpRequest();

      xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
          if (xhr.status === 200) {
            // Aggiungi un avviso qui quando la rimozione ha successo
            alert("Film rimosso con successo!");
            // Puoi gestire ulteriori azioni o semplicemente ricaricare la pagina
            window.location.reload();
          } else {
            alert("Errore durante la rimozione del film");
          }
        }
      };

      xhr.open("POST", form.action, true);
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      xhr.send(new URLSearchParams(formData));
    }
  </script>
</head>
<body>
<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<%
  ArrayList<Film> films = (ArrayList<Film>) request.getAttribute("films");
%>

<% if (films.isEmpty()) { %>
<div>
  <p>La lista non contiene film.</p>
  <form action="" method="POST">
    <input type="hidden" name="idLista" value="<%= l.getId() %>">
    <button type="submit">Aggiungi Film</button>
  </form>
</div>
<% } else { %>
<h1 align="center"> Lista: <%= l.getNome()%> </h1>
<div>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">Titolo</th>
      <th scope="col">Regista</th>
      <th scope="col">Durata</th>
      <th scope="col">Copertina</th>
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
      <td><%=film.getCopertina()%></td>
      <td><%=film.getGenere()%></td>
      <td>
        <form id="removeForm_<%= film.getId() %>" class="remove-form" action="ModificaListaServlet?action=rimuoviFilm" method="POST">
          <input type="hidden" name="idLista" value="<%= l.getId() %>">
          <input type="hidden" name="idFilm" value="<%= film.getId() %>">
          <button type="button" class="btn btn-outline-danger" onclick="removeFilm(<%= film.getId() %>)">RIMUOVI</button>
        </form>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
<% } %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
