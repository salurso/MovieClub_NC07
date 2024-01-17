<%@ page import="application.entity.Lista" %>
<%@ page import="application.entity.Film" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <% Lista l = (Lista) request.getAttribute("lists"); %>
  <title>Informazioni Lista: <%= l.getNome()%></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="./css/liste.css?v=<%=new Random().nextInt()%>"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<% ArrayList<Film> films = (ArrayList<Film>) request.getAttribute("films"); %>

<% if (films.isEmpty()) { %>
<div>
  <p>La lista non contiene film.</p>
  <form action="" method="post">
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
    <% for (Film f: films){ %>
    <tr>
      <td><%=f.getTitolo()%></td>
      <td><%=f.getRegista()%></td>
      <td><%=f.getDurata()%></td>
      <td><%=f.getCopertina()%></td>
      <td><%=f.getGenere()%></td>
      <td><button type="button" class="btn btn-outline-danger">Rimuovi</button></td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
<% } %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
