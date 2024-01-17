<%@ page import="java.util.Random" %>
<%@ page import="java.util.List" %>
<%@ page import="application.entity.Lista" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 16/01/2024
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Le Mie Liste</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="./css/liste.css?v=<%=new Random().nextInt()%>"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<h1 align="center">Le mie liste</h1>

<div class="centered-alert">
  <% if(request.getAttribute("result")!=null){ %>
  <div class="alert alert-warning alert-dismissible fade show" role="alert">
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    <%=request.getAttribute("result")%>
  </div>
  <% } %>

  <% ArrayList<Lista> lists = (ArrayList<Lista>) request.getAttribute("lists");
    if(lists==null || lists.isEmpty()) { %>
  <form action="ListaServlet?action=crea" method="POST">
  <div class="alert alert-info" role="alert">
    Nessuna lista presente al momento.
    <input type="submit" value="CREA LISTA">
  </div>
  </form>
  <% } else { %>
</div>

<div class="row row-cols-1 row-cols-md-4 g-4">
  <%
    for(Lista l : lists){
  %>
  <div class="grid-container">
    <div class="card" style="width: 18rem;">
      <div class="card-body">
        <h5 class="card-title"><%=l.getNome()%> #<%=l.getId()%></h5>
        <p class="card-text"><%=l.getDescrizione()%></p>
        <a href="ListaServlet?action=info&id=<%=l.getId()%>" class="card-link">Apri Lista</a>
        <a href="ListaServlet?action=gestisci&id=<%=l.getId()%>" class="card-link">Gestisci Lista</a>
      </div>
    </div>
  </div>

  <%
      }
    }
  %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
