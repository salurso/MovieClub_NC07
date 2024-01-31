function removeFilm(filmId) {
    var formId = "removeForm_" + filmId;
    var form = document.getElementById(formId);

    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Aggiungi un avviso qui quando la rimozione ha successo
                alert("Film rimosso con successo!");
                // Puoi gestire ulteriori azioni o semplicemente ricaricare la pagina
                window.location.reload();
            } else {
                alert("Errore durante la rimozione del film");
            }
        }
    };

    xhr.open("POST", form.action, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(new URLSearchParams(formData));
}

function removeWatchlist(filmId) {
    var formId = "watchlistForm_" + filmId;
    var form = document.getElementById(formId);

    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                alert("Film rimosso dalla watchlist con successo!");
                window.location.reload();
            } else {
                alert("Errore durante la rimozione del film dalla watchlist");
            }
        }
    };

    xhr.open("POST", form.action, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(new URLSearchParams(formData));
}
