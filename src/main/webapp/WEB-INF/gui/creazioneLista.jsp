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
<h1>Crea Lista</h1>

<form action="ListaServlet?action=crea" enctype="multipart/form-data" method="POST">
    <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" id="nome" name="nome" required>
    </div>
    <div class="mb-3">
        <label for="descrizione" class="form-label">Descrizione</label>
        <textarea class="form-control" id="descrizione" name="descrizione" rows="3" required></textarea>
    </div>
    <div class="mb-3">
        <label for="immagine" class="form-label">Immagine</label>
        <input type="file" class="form-control" id="immagine" name="immagine" required>
    </div>
    <div class="mb-3">
        <label class="form-check-label">Visibilità:</label>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="visibilita" id="pubblica" value="Pubblica" checked>
            <label class="form-check-label" for="pubblica">Pubblica</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="visibilita" id="privata" value="Privata">
            <label class="form-check-label" for="privata">Privata</label>
        </div>
    </div>

    <button type="submit" class="btn btn-outline-warning">Crea Lista</button>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>