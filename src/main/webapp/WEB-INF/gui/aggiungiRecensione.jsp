<%@ page import="java.util.Random" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
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

<h1>Recensione del Film</h1>

<div class="formRecensione">
    <form action="AggiungiRecensioneServlet" enctype="multipart/form-data" method="POST">

        <input type="number" name="Valutazione" id="Valutazione" required min="1" max="5">
        <textarea name="Descrizione" id="Descrizione" placeholder="Commenta qui..."></textarea>

        <!-- Campo data automatico -->
        <input type="hidden" name="DataInserimento" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>">

        <!-- Campi Email_persona e ID_Film nascosti -->
        <input type="hidden" name="Email_persona" value="<%= request.getParameter("Email_persona") %>">
        <input type="hidden" name="ID_Film" value="<%= request.getParameter("ID_Film") %>">

        <input class="btn_add" type="submit" name="action" value="INVIA RECENSIONE" onclick="return validateInsert()">
    </form>
</div>

<script>
    function validateInsert() {
        // Aggiungi eventuali controlli di validazione del modulo se necessario
        return true;
    }
</script>

</body>
</html>

