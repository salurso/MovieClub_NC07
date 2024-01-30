package application.entity;

public class Lista {
    private int id;

    private String nome;
    private String descrizione;
    private boolean privata;
    private String Email_Persona;

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

    public boolean isPrivata() {
        return privata;
    }

    public void setPrivata(boolean privata) {
        this.privata = privata;
    }

    public String getEmail_Persona() {
        return Email_Persona;
    }

    public void setEmail_Persona(String email_Persona) {
        Email_Persona = email_Persona;
    }

    @Override
    public String toString() {
        return "Lista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", privata=" + privata +
                '}';
    }
}
