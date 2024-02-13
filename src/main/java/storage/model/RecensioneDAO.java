package storage.model;

import application.entity.Recensione;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {

    private static RecensioneDAO instance;

    private RecensioneDAO() {
        // Costruttore privato per impedire l'istanziazione esterna
    }

    public static synchronized RecensioneDAO getInstance() {
        if (instance == null) {
            instance = new RecensioneDAO();
        }
        return instance;
    }

    //VisualizzaRecensione
    public List<Recensione> doRetrieveAll(){ //RESTITUISCE TUTTE LE RECENSIONI

        try(Connection con = ConPool.getConnection()){

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione");
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();

            while(rs.next()){
            }
            return recensioni;
        }catch(SQLException s){
            throw new RuntimeException(s);
        }
    }


    public int doSave(Recensione recensione) throws IOException { //INSERISCE UNA NUOVA RECENSIONE ALL'INTERNO DEL DB
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Recensione(Valutazione, Descrizione, DataInserimento, Email_Persona, ID_Film) VALUES (?, ?, ?, ?, ?)");

            ps.setInt(1, recensione.getValutazione());
            ps.setString(2, recensione.getDescrizione());
            ps.setDate(3, new java.sql.Date(recensione.getDataInserimento().getTime()));
            ps.setString(4, recensione.getEmailPersona());
            ps.setInt(5, recensione.getIdFilm());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public int doUpdate(Recensione r){  //MODIFICA LA RECENSIONE
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE Recensione SET Valutazione = ?, Descrizione = ?, DataInserimento = ? WHERE Email_Persona = ? AND ID_Film = ?");
            ps.setInt(1, r.getValutazione());
            ps.setString(2, r.getDescrizione());
            ps.setDate(3, new java.sql.Date(r.getDataInserimento().getTime()));
            ps.setString(4, r.getEmailPersona());
            ps.setInt(5, r.getIdFilm());

            return ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }



    public int removeRecensione(String emailPersona, int idFilm){ //ELIMINA UNA RECENSIONE
        try(Connection con = ConPool.getConnection()){

            //Cancella la recensione dal database
            PreparedStatement pt = con.prepareStatement("DELETE FROM Recensione WHERE Email_Persona = ? AND ID_Film = ?");
            pt.setString(1, emailPersona);
            pt.setInt(2, idFilm);
            return pt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("Errore durante la rimozione della Recensione", e);
        }
    }


    public static Recensione doRetrievebyEmailID(String Email_Persona, int ID_Film) {  //Cerca la Recensione effettuata dall'Utente (Email_Persona) su quel determinato film(ID_Film)
        Recensione r = null;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione WHERE Email_Persona = ? AND ID_Film = ?");
            ps.setString(1, Email_Persona);
            ps.setInt(2, ID_Film);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                r = new Recensione(); // Inizializza l'oggetto Recensione
                r.setValutazione(rs.getInt("Valutazione"));
                r.setDescrizione(rs.getString("Descrizione"));
                r.setDataInserimento(rs.getDate("DataInserimento"));
                r.setEmailPersona(rs.getString("Email_Persona"));
                r.setIdFilm(rs.getInt("ID_Film"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return r;
    }


    //check duplicato
    public static Recensione checkDuplicate(String Email_Persona, int ID_Film){
        Recensione r = null;
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione WHERE Email_Persona = ? AND ID_Film = ?");
            ps.setString(1, Email_Persona);
            ps.setInt(2, ID_Film);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                r.setValutazione(rs.getInt("Valutazione"));
                r.setDescrizione(rs.getString("Descrizione"));
                r.setDataInserimento(rs.getDate("DataInserimento"));
                r.setEmailPersona(rs.getString("Email_Persona"));
                r.setIdFilm(rs.getInt("ID_Film"));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return r;
    }

    public ArrayList<Recensione> doRetrieveByIDFilm(int ID_Film){ //RESTITUISCE TUTTE LE RECENSIONE DI UN DETERMINATO FILM
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione WHERE ID_Film = ?");
            ps.setInt(1, ID_Film);
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();

            while(rs.next()){
                Recensione r = new Recensione();
                r.setValutazione(rs.getInt("Valutazione"));
                r.setDescrizione(rs.getString("Descrizione"));
                r.setDataInserimento(rs.getDate("DataInserimento"));
                r.setEmailPersona(rs.getString("Email_Persona"));
                r.setIdFilm(rs.getInt("ID_Film"));
                recensioni.add(r);
            }
            return recensioni;
        }catch(SQLException s){
            throw new RuntimeException(s);
        }
    }

    private static java.util.Date sqlToJavaDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    private static java.sql.Date javaToSqlDate(java.util.Date javaDate) {
        return new java.sql.Date(javaDate.getTime());
    }

}
