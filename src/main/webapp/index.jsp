<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Hello World </title>
</head>
<body>

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>
    <div class="centered-alert">
            <% if(request.getAttribute("result")!=null){ %>
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            <%=request.getAttribute("result")%>
        </div>
            <% } %>
    <h1> Ciao, Mondo! </h1>

    <a href="MainServlet?action=film">VISUALIZZA FILM</a><br>
    <a href="ListaServlet?action=lista">VISUALIZZA LISTE</a><br>
    <a href="MainServlet?action=registrazione">VISUALIZZA REGISTRAZIONE</a><br>

    <a href="ListaServlet?action=creazione">CREA LISTE</a>
    <a href="RecensioneServlet?action=aggRecensione">INSERISCI UNA RECENSIONE</a>

</body>
</html>