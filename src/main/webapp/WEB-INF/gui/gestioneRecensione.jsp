<%@ page import="application.entity.Recensione" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% Recensione r = (Recensione) request.getAttribute("recensione"); %>
    <link rel="stylesheet" type="text/css" href="./css/recensione.css?v=<%=new Random().nextInt()%>">
    <script src="./js/aggiungiRecensione.js"></script>
</head>
<body class="body">

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<h1>Modifica la tua Recensione</h1>

<div class="formRecensione">
    <form action="ModificaRecensioneServlet" method="POST">

        <label for="Valutazione">Valutazione:</label>
        <input type="number" name="Valutazione" id="Valutazione" value="<%=(r != null) ? r.getValutazione() : ""%>" required min="1" max="5">

        <label for="Descrizione">Descrizione:</label>
        <textarea name="Descrizione" id="Descrizione"><%=(r != null) ? r.getDescrizione() : ""%></textarea>

        <!-- Campi Email_persona e ID_Film nascosti -->
        <input type="hidden" name="Email_persona" value="<%= (r != null) ? r.getEmailPersona() : "" %>">
        <input type="hidden" name="ID_Film" value="<%= (r != null) ? r.getIdFilm() : "" %>">

        <input class="btn_add" type="submit" name="action" value="INVIA RECENSIONE" onclick="return validateInsert()">
    </form>
</div>

</body>
</html>
