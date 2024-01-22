package application.entity;


import java.util.Date;

public class Recensione {

    private int valutazione;
    private String descrizione;
    private Date dataInserimento;
    private String emailPersona;
    private int idFilm;

    public Recensione(int valutazione, String descrizione, Date dataInserimento, String emailPersona, int idFilm) {
        this.valutazione = valutazione;
        this.descrizione = descrizione;
        this.dataInserimento = dataInserimento;
        this.emailPersona = emailPersona;
        this.idFilm = idFilm;
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

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    @Override
    public String toString() {
        return "Recensione{" +
                "valutazione=" + valutazione +
                ", descrizione='" + descrizione + '\'' +
                ", data=" + dataInserimento +
                ", emailPersona='" + emailPersona + '\'' +
                ", ID Film=" + idFilm +
                '}';
    }
}
