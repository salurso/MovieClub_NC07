function validateInsert() {
    var descrizione = document.getElementById('Descrizione').value;
    const formatoConsentito = /^[A-Za-z0-9.,'"\s!?()-]+$/;

    // Verifica se la stringa Descrizione rispetta il formato
    if (!(formatoConsentito.test(descrizione))) {
        alert("La stringa Descrizione non rispetta il formato richiesto.");
        return false;
    }

    return true;
}