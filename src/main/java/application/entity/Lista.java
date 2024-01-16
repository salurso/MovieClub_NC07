package application.entity;

public class Lista {
    private int id;

    private String nome;
    private String descrizione;
    private String immagine;
    private boolean visibilita;


    public boolean isVisibilita() {
        return visibilita;
    }

    public void setVisibilita(boolean visibilita) {
        this.visibilita = visibilita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
     public int getId() {
            return id;
        }

    public void setId(int id) {
        this.id = id;
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

}
