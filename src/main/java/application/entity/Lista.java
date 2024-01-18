package application.entity;

public class Lista {
    private int id;

    private String nome;
    private String descrizione;
    private String immagine;
    private boolean privata;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
     public int getId() { return id;
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

    public boolean isPrivata() {
        return privata;
    }

    public void setPrivata(boolean privata) {
        this.privata = privata;
    }

    @Override
    public String toString() {
        return "Lista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", immagine='" + immagine + '\'' +
                ", privata=" + privata +
                '}';
    }
}
