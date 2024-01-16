package storage.model;

import application.entity.Lista;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ListaDAO {

    public static ArrayList<Lista> doRetrieveAll(){
        try (Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT id, nome, descrizione, immagine, privata FROM Lista");
            ResultSet rs = ps.executeQuery();
            ArrayList<Lista> lists = new ArrayList<>();
            while (rs.next()){
                Lista l = new Lista();
                l.setId(rs.getInt(1));
                l.setNome(rs.getString(2));
                l.setDescrizione(rs.getString(3));
                l.setImmagine(rs.getString(4));
                l.setPrivata(rs.getBoolean(5));

                lists.add(l);
            }
            return lists;
        } catch (SQLException s){
            throw new RuntimeException(s);
        }
    }

    public Lista doRetrieveById(int id){ //TROVA INFO LISTA DALL'ID
        try (Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT id, nome, descrizione, immagine, privata FROM Lista WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Lista l = new Lista();
            while(rs.next()){
                l.setId(rs.getInt(1));
                l.setNome(rs.getString(2));
                l.setDescrizione(rs.getString(3));
                l.setImmagine(rs.getString(4));
                l.setPrivata(rs.getBoolean(5));
            }
            return l;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doUpdate(Lista l) { //MODIFICA LE INFORMAZIONI DELLA LISTA
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Lista SET nome = ?, descrizione = ?, immagine = ?, privata = ? WHERE id = ?");
            ps.setString(1, l.getNome());
            ps.setString(2, l.getDescrizione());
            ps.setString(3, l.getImmagine());
            ps.setBoolean(4, l.isPrivata());
            ps.setInt(5, l.getId());

            return ps.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    public int doDeleteList(int id) { //ELIMINA LISTA DAL NOME
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Lista WHERE id = ?");
            ps.setInt(1, id);

            return ps.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    public int doDeleteFilmList(int idLista, int idFilm) { //ELIMINA FILM DALLA LISTA
        try (Connection connection = ConPool.getConnection()){
            //AGGIUNGERE CONTROLLO SESSIONE EMAIL_PERSONA = ID_LISTA
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Include WHERE ID_Lista = ? AND ID_Film = ?");
            ps.setInt(1, idLista);
            ps.setInt(2, idFilm);

            return ps.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }


    public int doInsertFilmIntoList(int idLista, int idFilm) { //INSERIMENTO FILM IN LISTA
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Include (ID_Lista, ID_Film) VALUES (?, ?)");
            ps.setInt(1, idLista);
            ps.setInt(2, idFilm);

            return ps.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }


}

