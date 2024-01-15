package application.entity;

public class Lista {
    private String nome;
    private String descrizione;
    private String immagine;
    private boolean privata;

    public Lista(String nome, String descrizione, String immagine, boolean privata) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.immagine = immagine;
        this.privata = privata;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public boolean isPrivata() {
        return privata;
    }

    public void setPrivata(boolean privata) {
        this.privata = privata;
    }
}
