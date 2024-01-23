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
</head>
<body>

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<h1>Recensione del Film</h1>

<div class="formRecensione">
    <form action="AggiungiRecensioneServlet" method="POST" enctype="multipart/form-data">

        <label for="Valutazione">Valutazione:</label>
        <input type="number" name="Valutazione" id="Valutazione" required min="1" max="5">

        <label for="Descrizione">Descrizione:</label>
        <textarea name="Descrizione" id="Descrizione" placeholder="Commenta qui..."></textarea>

        <!-- Campo data automatico -->
        <!--<input type="hidden" name="DataInserimento" value="<= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) >">-->

        <!-- Campi Email_persona e ID_Film nascosti -->
        <input type="hidden" name="Email_Persona" value="%>">
        <input type="hidden" name="ID_Film" value="<=%>">

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
