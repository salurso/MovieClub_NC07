 function validateInsert() {
    var nome = document.getElementById('Nome').value.trim();

    if (nome === "" || nome.length > 30) {
    var errorMessage = nome === "" ? "Il nome non puo' essere vuoto." : "La lunghezza del nome non puo' superare i 30 caratteri.";
    alert(errorMessage);
    return false;
}

    var descrizione = document.getElementById('Descrizione').value;
    if (descrizione.length > 100) {
    alert("Lunghezza descrizione non valida! (Max 100 caratteri)");
    return false;
}

    var immagine = document.getElementById('Immagine').value;
    if (immagine.length > 50) {
    alert("Lunghezza immagine non valida! (Max 50 caratteri)");
    return false;
}

    return true;
}


