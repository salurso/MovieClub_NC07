function validateInsert() {
    var descrizione = document.getElementById('Descrizione').value;
    const formatoConsentito = /^[A-Za-z0-9.,'"\s!?()-]+$/;

    // Verifica se la stringa Descrizione rispetta il formato
    if (!(formatoConsentito.test(descrizione))) {
        alert("La stringa Descrizione non rispetta il formato richiesto.");
        return false;
    }

    var valore = document.getElementById('Valutazione').value;
    // const valoreNumerico = parseInt(valore);

    // Verifica se il valore non è un numero compreso tra 1 e 5
    if (valore < 1 || valore > 5 || isNaN(valore)) {
        // Mostra l'alert
        alert("Inserisci solo numeri da 1 a 5!");
        // Resetta il valore dell'input
        input.value = '';
        return false;
    }

    return true;
}