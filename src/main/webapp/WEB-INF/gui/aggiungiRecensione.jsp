<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: gaetanovitofaraco
  Date: 17/01/24
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recensione</title>
    <link rel="stylesheet" type="text/css" href="./css/recensione.css?v=<%=new Random().nextInt()%>">
</head>
<body>

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<h3>Recensione del Film<h3>

    <div id="formRecensione">
        <form action="AggiungiRecensioneServlet" method="POST">
            <input type="number" id="valutazione" name="valutazione" required>
            <textarea name="descrizione" id="descrizione" style="height:200px" maxlength="500"></textarea>
            <button class="btn_invia" type="submit">Invia</button>
        </form>
    </div>

</body>
</html>
