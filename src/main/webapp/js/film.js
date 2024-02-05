function validateInsert() {

    // validazione regista
    var regista = document.getElementById('regista').value.trim();
    var registaRegex = /^[A-Za-z .'\s]+$/;
    if (!registaRegex.test(regista)) {
        alert('Il campo "Regista" non può contenere numeri o caratteri speciali.');
        return false;
    }

    // valizazione copertina
    var copertina = document.getElementById('image').value.trim();
    if (copertina.length > 150) {
        alert('Il campo "Link copertina" non può superare i 150 caratteri.');
        return false;
    }

    // valizazione trailer
    var trailer = document.getElementById('trailer').value.trim();
    if (trailer.length > 100) {
        alert('Il campo "Link trailer" non può superare i 100 caratteri.');
        return false;
    }

    // validazione data
    var data = document.getElementById('data');
    if (data.value === '') {
        alert('Il campo Data è obbligatorio. Compilalo prima di inviare il modulo.');
        data.focus();
        return false;
    }

    // validazione durata
    var durata = document.getElementById('durata').value.trim();
    var durataRegex = /^([0-9]{2}):([0-9]{2}):([0-9]{2})$/;
    if (durata !== '' && !durataRegex.test(durata)) {
        alert('Il formato della durata deve essere 00:00:00');
        durata.focus();
        return false;
    }
    var durataEmpity = document.getElementById('durata');
    if (durataEmpity.value === '') {
        // Se il campo è vuoto, imposta il valore predefinito a "00:00:00"
        durataEmpity.value = '00:00:00';
    }

    // validazione descrizione
    var descrizione = document.getElementById('descrizione');
    if (descrizione.value.length > 220) {
        alert('La descrizione non può superare i 220 caratteri.');
        descrizione.focus();
        return false;
    }

    // validazione generi
    var checkboxes = document.getElementsByName('generi');
    var atLeastOneChecked = false;
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            atLeastOneChecked = true;
            break;
        }
    }

    if (!atLeastOneChecked) {
        alert('Seleziona almeno un genere.');
        return false;
    }

    var titolo = document.getElementById('titolo').value;
    if(titolo.length>60){
        alert('Lunghezza titolo non rispetatta.');
        return false;
    }

    // Verifica se titolo e regista sono compilati
    var titolo = document.getElementById('titolo').value.trim();
    var descrizione = document.getElementById('titolo').value.trim();
    if (titolo === '' || regista === '' || descrizione === '') {
        alert('Compila tutti i campi obbligatori.');
        return false;
    }

    return true;
}

function confirmDelete(id){
    if(confirm("Sei sicuro di voler eliminare il film?")){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function (){
            if(xhr.readyState == 4 && xhr.status == 200){
                alert(xhr.responseText);
                window.location.href="MainServletAdmin?action=homeAdmin";
            }
        };
        xhr.open("POST", "AggiornaFilmServlet?action=ELIMINA&id=" + id, true);
        xhr.send();
    }else{
        alert("eliminazione annullata");

    }
}

