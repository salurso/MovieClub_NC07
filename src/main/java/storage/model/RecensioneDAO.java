package storage.model;

import application.entity.Recensione;
import storage.service.RecensioneService;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {

    //VisualizzaRecensione
    public List<Recensione> doRetrieveAll(){

        try(Connection con = ConPool.getConnection()){

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione");
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();

            while(rs.next()){
                Recensione r = parseRecensione(rs);
                recensioni.add(r);
            }
            return recensioni;
        }catch(SQLException s){
            throw new RuntimeException(s);
        }
    }

    //AggiungiRecensione
    public int doSave(Recensione r) throws IOException {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO Recensione(Valutazione, Descrizione, Data, Email_Persona, ID_Film) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, r.getValutazione());
            ps.setString(2, r.getDescrizione());
            ps.setDate(3, javaToSqlDate(r.getData()));
            ps.setString(4, r.getEmailPersona());
            ps.setInt(5, r.getIdFilm());

            return ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    //ModificaRecensione
    public int doUpdate(Recensione r){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE Recensione SET Valutazione = ?, Descrizione = ?, Data = ? WHERE Email_Persona = ? AND ID_Film = ?");
            ps.setInt(1, r.getValutazione());
            ps.setString(2, r.getDescrizione());
            ps.setDate(3, javaToSqlDate(r.getData()));
            ps.setString(4, r.getEmailPersona());
            ps.setInt(5, r.getIdFilm());

            return ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


    //EliminaRecensione
    public static void removeRecensione(Recensione r){
        try(Connection con = ConPool.getConnection()){

            //Cancella la recensione dal database
            PreparedStatement pt = con.prepareStatement("DELETE FROM Recensione WHERE Email_Persona = ? AND ID_Film = ?");
            pt.setString(1, r.getEmailPersona());
            pt.setInt(2, r.getIdFilm());
            pt.executeUpdate();

            /*cancelliamo il collegamento Recensione - Utente
            PreparedStatement ps = con.prepareStatement("DELETE FROM Recensione WHERE Email_Persona = ?");
            ps.setString(1, r.getEmailPersona());
            ps.executeUpdate();

            //cancelliamo il collegamento Recensione - Film
            PreparedStatement pr = con.prepareStatement("DELETE FROM Recensione WHERE ID_Film = ?");
            pr.setString(1, r.getEmailPersona());
            pr.executeUpdate();*/

        }catch(SQLException e){
            throw new RuntimeException("Errore durante la rimozione della Recensione", e);
        }
    }

    //Cerca la Recensione effettuata dall'Utente (Email_Persona) su quel determinato film(ID_Film)
    //Fa anche da check per controllare che quella recensione è già presente
    //Check+Search
    public static Recensione doRetrievebyEmailID(String Email_Persona, int ID_Film){
        Recensione r = null;
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Recensione WHERE Email_Persona = ? AND ID_Film = ?");
            ps.setString(1, Email_Persona);
            ps.setInt(2, ID_Film);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                r = parseRecensione(rs);
            }
            return r;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static Recensione parseRecensione(ResultSet rs) throws SQLException {
        Recensione r = new Recensione();
        r.setValutazione(rs.getInt("Valutazione"));
        r.setDescrizione(rs.getString("Descrizione"));
        r.setData(sqlToJavaDate(rs.getDate("Data")));
        r.setEmailPersona(rs.getString("Email_Persona"));
        r.setIdFilm(rs.getInt("ID_Film"));

        return r;
    }


    private static java.util.Date sqlToJavaDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    private static java.sql.Date javaToSqlDate(java.util.Date javaDate) {
        return new java.sql.Date(javaDate.getTime());
    }

}
