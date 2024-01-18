function hideMessage() {
    let stato = document.getElementById("stato");

    if (stato) {
        stato.style.display = "none";
    }

}
// Imposta un ritardo prima di chiamare la funzione hideMessage
setTimeout(hideMessage, 3000);