<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Crea Lista</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="./css/creazioneLista.css?v=<%=new Random().nextInt()%>"/>
    <script>
        function validateInsert() {
            var nome = document.getElementById('Nome').value.trim();
            var nomeError = document.getElementById('nome-error');

            if (nome === "" || nome.length > 30) {
                nomeError.innerHTML = "Il nome deve essere non vuoto e lungo al massimo 30 caratteri.";
                return false;
            } else {
                nomeError.innerHTML = "";  // Cancella il messaggio di errore se il campo è valido
            }

            var descrizione = document.getElementById('Descrizione').value;
            if (descrizione.length > 100) {
                alert("Lunghezza descrizione non valida!");
                return false;
            }

            var immagine = document.getElementById('Immagine').value;
            if (immagine.length > 50) {
                alert("Lunghezza immagine non valida!");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<div class="container-info">
    <div class="add_product">
        <form action="AggiungiListaServlet" method="POST">
            <h3>Aggiungi Lista</h3>

            <label for="Nome"> Nome: </label>
            <input type="text" name="Nome" id="Nome" required>

            <label for="Descrizione">Descrizione: </label>
            <textarea name="Descrizione" id="Descrizione" style="height:200px" maxlength="500"></textarea>

            <label for="Immagine">Immagine: </label>
            <input type="file" id="Immagine" name="Immagine" value="inserisci immagine">

            <label class="form-check-label">Visibilita': </label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="Privata" id="Pubblica" value="0" checked>
                <label class="form-check-label" for="Pubblica">Pubblica</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="Privata" id="Privata" value="1">
                <label class="form-check-label" for="Privata">Privata</label>
            </div>

            <input class="btn_add" type="submit" name="action" value="CREA LISTA" onclick="return(validateInsert())">

        </form>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>

