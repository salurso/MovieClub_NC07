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
    <title>Recensione del Film</title>
    <link rel="stylesheet" type="text/css" href="./css/recensione.css?v=<%=new Random().nextInt()%>">
</head>
<body>

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<h1>Recensione del Film<h1>

    <div class="formRecensione">
        <form action="AggiungiRecensioneServlet" method="POST">
            <input type="number" id="valutazione" name="valutazione" required>
            <textarea name="descrizione" id="descrizione" placeholder="Commenta qui..."></textarea>

            <input class="btn_add" type="submit" name="action" value="INVIA RECENSIONE" onclick="return(validateInsert())">
        </form>
    </div>

</body>
</html>
