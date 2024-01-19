<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="application.entity.Lista" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.model.ListaDAO" %>

<html>
<head>
    <title>Liste Pubbliche</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="./css/listePubbliche.css?v=<%=new Random().nextInt()%>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body class="body">
<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<h1 align="center" class="title_pubblica"> Liste Pubbliche</h1>
<div class="row row-cols-1 row-cols-md-4 g-4">
    <%
        // Ottieni tutte le liste pubbliche
        ListaDAO listaDAO = new ListaDAO();
        ArrayList<Lista> listePubbliche = listaDAO.getPublicLists();

        // Itera attraverso le liste pubbliche e visualizzale
        for (Lista lista : listePubbliche) {
    %>

    <div class="grid-container">
        <a href="ListaServlet?action=info&id=<%= lista.getId() %>" class="card-link-apri">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h4><%= lista.getNome() %></h4>
                    <p><%= lista.getDescrizione() %></p>
                </div>
            </div>
        </a>
    </div>
    <%
        }
    %>
</div>

</body>
</html>
