<%@ page import="application.entity.Persona" %>
<%@ page import="java.util.Random" %>
<%@ page import="application.entity.Lista" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="./css/areaPersonale.css?v=<%=new Random().nextInt()%>"/>
<html>
<%Persona p = (Persona) request.getSession().getAttribute("Persona");%>
<head>
    <title>Area personale di <%=p.getNome()%> </title>
</head>
<body>
<%@ include file="/WEB-INF/navbar/navbar.jsp" %>
<div class="container">
    <button class="item" id="datiPersona" value=" " onclick="location.href='MainServlet?action=datiPersona'">
        <span class="item-text"></span>
    </button>
    <button class="item" id="creaLista" value=" " onclick="location.href='MainServlet?action=creazioneLista'">
        <span class="item-text"></span>
    </button>
    <button class="item" id="watchlist" value=" " onclick="location.href='MainServlet?action=watchlist'">
        <span class="item-text"></span>
    </button>
</div>
<div class="item" id="listeUtente">
    <h1>Le tue liste</h1>
    <ul>
        <%
            ArrayList<Lista> userLists = (ArrayList<Lista>) request.getSession().getAttribute("userLists");
            if (userLists != null && !userLists.isEmpty()) {
                int counter = 1;
                for (Lista l : userLists) {
        %>
        <li>
            <a href="ListaServlet?action=info&id=<%= l.getId() %>">
                <%= counter++ %>) <%= l.getNome() %>
                <% if (l.isPrivata()) { %>
                <span style="color: red;"> (Privata) </span>
                <% }
                else { %>
                <span style="color: green;"> (Pubblica) </span>
                <% } %>
            </a>
        </li>
        <%
            }
        } else {
        %>
        <li>Nessuna lista presente.</li>
        <%
            }
        %>
    </ul>
</div>
</body>
</html>
