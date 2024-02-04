
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Random" %>
<%@ page import="storage.model.PersonaDAO" %>
<%@ page import="storage.model.FilmDAO" %>
<%@ page import="application.entity.Film" %>

<html>
<head>
    <title>Recensione del Film</title>
    <link rel="stylesheet" type="text/css" href="./css/recensione.css?v=<%=new Random().nextInt()%>">
    <script src="./js/aggiungiRecensione.js"></script>
</head>
<body class="body">

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<%
    // Ottieni il valore del parametro "idFilm" dalla richiesta
    String idFilmParameter = request.getParameter("ID_Film");
    int idFilm = 0;
    if (idFilmParameter != null && !idFilmParameter.isEmpty()) {
        idFilm = Integer.parseInt(idFilmParameter);
    }
%>

<h1>Recensione del Film</h1>

<div class="formRecensione">
    <form action="AggiungiRecensioneServlet" method="POST">

        <label for="Valutazione">Valutazione:</label>
        <input type="text" name="Valutazione" id="Valutazione" maxlength="1">
<%--        required min="1" max="5"--%>
        <label for="Descrizione">Descrizione:</label>
        <textarea name="Descrizione" id="Descrizione" placeholder="Commenta qui..."></textarea>

        <!-- Campi Email_persona e ID_Film nascosti -->
        <input type="hidden" name="Email_persona" value="<%=persona.getEmail()%>">
        <input type="hidden" name="ID_Film" value="<%=idFilm%>">

        <input class="btn_add" type="submit" name="action" value="INVIA RECENSIONE" onclick="return (validateInsert())">
    </form>
</div>

</body>
</html>
