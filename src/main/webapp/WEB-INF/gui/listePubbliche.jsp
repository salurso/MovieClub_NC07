<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="application.entity.Lista" %>
<%@ page import="application.entity.Persona" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.model.ListaDAO" %>
<%@ page import="storage.model.PersonaDAO" %>

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
        ListaDAO listaDAO = new ListaDAO();
        PersonaDAO personaDAO = new PersonaDAO();
        ArrayList<Lista> listePubbliche = listaDAO.getPublicLists();

        for (Lista lista : listePubbliche) {
            Persona creatoreLista = personaDAO.doRetrieveByEmail(lista.getEmail_Persona());
    %>

    <div class="grid-container">
        <a href="ListaServlet?action=info&id=<%= lista.getId() %>" class="card-link-apri">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <p>Lista di: <%= (creatoreLista != null) ? creatoreLista.getNome() : "N/A" %></p>
                    <h4><%= lista.getNome() %> </h4>
                    <p><%= lista.getDescrizione() %></p>
                </div>
            </div>
        </a>
    </div>

    <%
        }
    %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
