<%@ page import="application.entity.Persona" %>
<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: Vittorio
  Date: 18/01/2024
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="./css/areaPersonale.css?v=<%=new Random().nextInt()%>"/>
<html>
<%Persona p = (Persona) request.getSession().getAttribute("Persona");%>
<head>
    <title>Area personale di <%=p.getNome()%> </title>
</head>
<body>

    <div class="container">
        <button class="item" id="datiPersona" value=" " onclick="location.href='MainServlet?action=datiPersona'">
            <span class="item-text"></span>
        </button>
        <button class="item" id="creaLista" value=" " onclick="location.href='MainServlet?action=creazioneLista'">
            <span class="item-text"></span>
        </button>
        <button class="item" id="watchlist" value=" " onclick="location.href='MainServlet?action='">
            <span class="item-text"></span>
        </button>
    </div>

</body>
</html>
