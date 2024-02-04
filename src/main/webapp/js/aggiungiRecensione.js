function validateInsert() {
    var descrizione = document.getElementById('Descrizione').value;
    const formatoConsentito = /^[A-Za-z0-9.,'"\s!?()-]+$/;

    // Verifica se la stringa Descrizione rispetta il formato
    if (!(formatoConsentito.test(descrizione))) {
        alert("La stringa Descrizione non rispetta il formato richiesto.");
        return false;
    }

    var valore = document.getElementById('Valutazione').value;

    // Verifica se il valore non è un numero compreso tra 1 e 5
    if (valore < 1 || valore > 5 || isNaN(valore)) {
        // Mostra l'alert
        alert("Inserisci solo numeri da 1 a 5!");
        // Resetta il valore dell'input
        document.getElementById('Valutazione').value = '';
        return false;
    }

    // Se la validazione è riuscita, ritorna true per permettere l'invio del modulo
    return true;
}
