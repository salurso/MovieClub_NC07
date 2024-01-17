package application.entity;


import java.util.Date;

public class Recensione {

    private int valutazione;
    private String descrizione;
    private Date data;
    private String emailPersona;
    private int ID_Film;

    public Recensione(int valutazione, String descrizione, Date data, String emailPersona, int ID_Film) {
        this.valutazione = valutazione;
        this.descrizione = descrizione;
        this.data = data;
        this.emailPersona = emailPersona;
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

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
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
                ", emailPersona='" + emailPersona + '\'' +
                ", ID_Film=" + ID_Film +
                '}';
    }
}
