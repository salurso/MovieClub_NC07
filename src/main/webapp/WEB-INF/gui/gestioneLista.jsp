<%@ page import="application.entity.Lista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <% Lista l = (Lista) request.getAttribute("lists"); %>
  <title>Gestisci Lista: <%=l.getNome()%></title>
<script>
  function validateUpdate() {
    var nome = document.getElementById('nome').value;
    if (nome.length > 30) {
      alert("Lunghezza non valida!");
      return false;
    }

    var descrizione = document.getElementById('descrizione').value;
    if (descrizione.length > 100) {
      alert("Lunghezza descrizione non valida!");
      return false;
    }

    var immagine = document.getElementById('immagine').value;
    if (immagine.length > 50){
      alert("Lunghezza descrizione non valida!");
    return false
  }
    return true;
  }

  function confirmDelete(id){
    if(confirm("Sei sicuro di voler eliminare questa lista??\nRICORDA: Eliminando questa lista, verranno persi tutti i film all'interno")){
      var xhr = new XMLHttpRequest();
      xhr.onreadystatechange = function (){
        if(xhr.readyState == 4 && xhr.status == 200){
          alert(xhr.responseText)
          window.location.href="ListaServlet?action=lista";
        }
      };
      xhr.open("POST", "ModificaListaServlet?action=ELIMINA&id=" + id, true);
      xhr.send();
    }else{
      alert("eliminazione annullata");

    }
  }

  </script>
</head>
<body>

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<div class="update_list">
  <form action="ModificaListaServlet"  method="POST">

    <h3 class="add_prod_title">Modifica Lista</h3>

    <label for="id">id: </label>
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
