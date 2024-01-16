package application.entity;


import java.util.Date;

public class Recensione {

    private int valutazione;
    private String descrizione;
    private Date data;
    private String Email_Persona;
    private int ID_Film;

    public Recensione(int valutazione, String descrizione, Date data, String Email_Persona, int ID_Film) {
        this.valutazione = valutazione;
        this.descrizione = descrizione;
        this.data = data;
        this.Email_Persona = Email_Persona;
        this.ID_Film = ID_Film;
    }

    public Recensione(){
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEmail_Persona() {
        return Email_Persona;
    }

    public void setEmail_Persona(String email_Persona) {
        Email_Persona = email_Persona;
    }

    public int getID_Film() {
        return ID_Film;
    }

    public void setID_Film(int ID_Film) {
        this.ID_Film = ID_Film;
    }

    @Override
    public String toString() {
        return "Recensione{" +
                "valutazione=" + valutazione +
                ", descrizione='" + descrizione + '\'' +
                ", data=" + data +
                ", emailPersona='" + Email_Persona + '\'' +
                ", ID_Film=" + ID_Film +
                '}';
    }
}
