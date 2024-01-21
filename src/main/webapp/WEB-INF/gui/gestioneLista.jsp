<%@ page import="application.entity.Lista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <% Lista l = (Lista) request.getAttribute("lists"); %>
  <title>Gestisci Lista: <%=l.getNome()%></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="./css/gestioneLista.css?v=<%=new Random().nextInt()%>"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="./js/gestioneLista.js"></script>


</head>
<body class="body">

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<div class="update_list">
  <form action="ModificaListaServlet"  method="POST">

    <h3 class="modify_list_title">Modifica Lista</h3>

    <label for="id">Numero Lista: </label>
    <input id="id" name="id" type="text" value="<%=l.getId()%>" readonly>

    <label for="nome"> Nome: </label>
    <input id="nome" name="nome" type="text" value="<%=l.getNome()%>" maxlength="50">

    <label for="descrizione">descrizione: </label>
    <input id="descrizione" name="descrizione" type="text" value="<%=l.getDescrizione()%>">

    <label for="immagine">Immagine: (il nome del file deve essere di max 20 caratteri)</label>
    <input type="file" id="immagine" name="immagine" value="<%=l.getImmagine()%>"><br>

    <div class="btnadd">

      <input class="btn btn-warning" type="submit" name="action" value="AGGIORNA" onclick="return(validateUpdate())">
    </div>
  </form>
  <input type="submit" class="btn btn-danger" value="ELIMINA" onclick="return(confirmDelete(<%=l.getId()%>))">

</div>

</body>
</html>
