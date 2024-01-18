function validaRegistrazione() {
    var email = document.getElementById('email').value;
    var emailRGX = /^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,3}$/;
    if (emailRGX.test(email) == false) {
        alert("Formato email non valido!");
        return false;
    }

    var passwordRGX = /^[A-Za-z0-9$!@?]+$/; // filtra una stringa consentendo solo caratteri alfanumerici (lettere maiuscole e minuscole da A a Z e numeri da 0 a 9) e i simboli !, @ e ?
    var password = document.getElementById('password').value;
    if (passwordRGX.test(password) == false) {
        alert("Caratteri password non validi!");
        return false;
    }
    if (password.length < 8 || password.length > 30) {
        alert("Dimensione password non valida!");
        return false;
    }

    var nomeRGX = /^[a-zA-Z]*$/; // solo caratteri alfabetici (lettere maiuscole e minuscole da A a Z)
    var nome = document.getElementById('nome').value;
    if (nomeRGX.test(nome) == false) {
        alert("Nome non valido!");
        return false;
    }

    var cognomeRGX = /^[a-zA-Z]*$/;
    var cognome = document.getElementById('cognome').value;
    if (cognomeRGX.test(cognome) == false) {
        alert("Cognome non valido!");
        return false;
    }

    return true;
}