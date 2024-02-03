function validaRegistrazione() {
    if (!validaLogin()) {
        return false;
    }

    var nome = document.getElementById('nome').value;
    var nomeRGX = /^[a-zA-Z]*$/;
    if (nome.length < 3 || nome.length > 30) {
        alert("Il nome deve avere una lunghezza compresa tra 3 e 30 caratteri.");
        return false;
    }
    if (!nomeRGX.test(nome)) {
        alert("Il nome deve contenere solo lettere.");
        return false;
    }

    var cognome = document.getElementById('cognome').value;
    var cognomeRGX = /^[a-zA-Z]*$/;
    if (cognome.length < 3 || cognome.length > 30) {
        alert("Il cognome deve avere una lunghezza compresa tra 3 e 30 caratteri.");
        return false;
    }
    if (!cognomeRGX.test(cognome)) {
        alert("Il cognome deve contenere solo lettere.");
        return false;
    }

    return true;
}


function validaLogin() {
    var email = document.getElementById('email').value;
    var emailRGX = /^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,3}$/;
    if (emailRGX.test(email) == false) {
        alert("Formato email non valido!");
        return false;
    }

    var passwordRGX = /^[A-Za-z0-9$!@?]+$/;
    var password = document.getElementById('password').value;
    if (passwordRGX.test(password) == false) {
        alert("Caratteri password non validi!");
        return false;
    }
    if (password.length < 8 || password.length > 30) {
        alert("Dimensione password non valida!");
        return false;
    }

    return true;
}




