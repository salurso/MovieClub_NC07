    function validateUpdate() {
    var nome = document.getElementById('nome').value;
    if (nome.length > 30) {
    alert("Lunghezza Nome non rispettata! (deve essere <=30)");
    return false;
}

    var descrizione = document.getElementById('descrizione').value;
    if (descrizione.length > 100) {
    alert("Lunghezza descrizione non valida!");
    return false;
}

    var immagine = document.getElementById('immagine').value;
    if (immagine.length > 50){
    alert("Lunghezza descrizione non rispettata! (deve essere <=50)");
    return false
}
    return true;
}

    function confirmDelete(id){
    if(confirm("Sei sicuro di voler eliminare questa lista??\nRICORDA: Eliminando questa lista, verranno persi tutti i film all'interno")){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function (){
    if(xhr.readyState == 4 && xhr.status == 200){
    alert(xhr.responseText)
    window.location.href="ListaServlet?action=lista";
}
};
    xhr.open("POST", "ModificaListaServlet?action=ELIMINA&id=" + id, true);
    xhr.send();
}else{
    alert("eliminazione annullata");

}
}
