<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Crea Lista </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
   <script>
    function validateInsert() {
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
   </script>
</head>
<body>

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<h1> CREA LISTA </h1>


    <form action="ModificaListaServlet?action=creazione" enctype="multipart/form-data" method="POST">
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="floatingInput" placeholder="name@example.com">
        <label for="floatingInput">Nome</label>
    </div>
    <div class="form-floating">
        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
        <label for="floatingTextarea2">Descrizione</label>
    </div>
        <input type="file" id="immagine" name="immagine" value="inserisci immagine">
    <div class="form-check">
        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
        <label class="form-check-label" for="flexRadioDefault1">
            Pubblica
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
        <label class="form-check-label" for="flexRadioDefault1">
            Privata
        </label>
    </div>

    <input type="submit" class="btn btn-outline-warning" value="CREA LISTA" onclick="return(validateInsert())">

</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>