package storage.model;

public class Lista {

    private String Nome;
    private String Descrizione;
    private String Immagine;
    private boolean Privata;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public String getImmagine() {
        return Immagine;
    }

    public void setImmagine(String immagine) {
        Immagine = immagine;
    }

    public boolean isPrivata() {
        return Privata;
    }

    public void setPrivata(boolean privata) {
        Privata = privata;
    }
}
