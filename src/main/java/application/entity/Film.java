package application.entity;

import java.util.Date;

public class Film {
    private int id;
    private String titolo;
    private String descrizione;
    private Date dataUscita;
    private String regista;
    private double durata;
    private String genere;
    private String copertina;
    private String trailer;

    public Film(int id, String titolo, String descrizione, Date dataUscita, String regista, double durata, String genere, String copertina, String trailer) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataUscita = dataUscita;
        this.regista = regista;
        this.durata = durata;
        this.genere = genere;
        this.copertina = copertina;
        this.trailer = trailer;
    }

    public Film() {
    }

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Date getDataUscita() {
        return dataUscita;
    }

    public String getRegista() {
        return regista;
    }

    public double getDurata() {
        return durata;
    }

    public String getGenere() {
        return genere;
    }

    public String getCopertina() {
        return copertina;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setDataUscita(Date dataUscita) {
        this.dataUscita = dataUscita;
    }

    public void setRegista(String regista) {
        this.regista = regista;
    }

    public void setDurata(double durata) {
        this.durata = durata;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public void setCopertina(String copertina) {
        this.copertina = copertina;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
