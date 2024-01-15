package storage.model;

import application.entity.Lista;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ListaDAO {

    public ArrayList<Lista> doRetrieveAll(){
        try (Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Lista");
            ResultSet rs = ps.executeQuery();
            ArrayList<Lista> lista = new ArrayList<>();
            while (rs.next()){
                String nome = rs.getString(1);
                String descrizione = rs.getString(2);
                String immagine = rs.getString(3);
                boolean privata = rs.getBoolean(4);
                Lista l = new Lista(nome, descrizione, immagine, privata);

                lista.add(l);
            }
            return lista;
        } catch (SQLException s){
            throw new RuntimeException(s);
        }
    }

//    public Lista doRetrieveByName(String Nome){ //TROVA INFO LISTA DAL NOME
//        try (Connection connection = ConPool.getConnection()){
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Lista WHERE Nome = ?");
//            ps.setString(1, Nome);
//            ResultSet rs = ps.executeQuery();
//            Lista l = new Lista();
//            while(rs.next()){
//                l.setNome(rs.getString(1));
//                l.setDescrizione(rs.getString(2));
//                l.setImmagine(rs.getString(3));
//                l.setPrivata(rs.getBoolean(4));
//            }
//            return l;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public int doUpdate(Lista lista) { //MODIFICA LE INFORMAZIONI DELLA LISTA
//        try (Connection con = ConPool.getConnection()) {
//            PreparedStatement ps = con.prepareStatement("UPDATE Lista SET Nome = ?, Descrizione = ?, Immagine = ?, Privata = ? WHERE id = ?");
//            ps.setString(1, lista.getNome());
//            ps.setString(2, lista.getDescrizione());
//            ps.setString(3, lista.getImmagine());
//            ps.setBoolean(4, lista.isPrivata());
//
//            return ps.executeUpdate();
//        } catch (SQLException s) {
//            throw new RuntimeException(s);
//        }
//    }
//
//    public int doDelete(String Nome) { //ELIMINA LISTA DAL NOME
//        try (Connection connection = ConPool.getConnection()) {
//            PreparedStatement ps = connection.prepareStatement("DELETE FROM Lista WHERE Nome = ?");
//            ps.setString(1, Nome);
//
//            return ps.executeUpdate();
//        } catch (SQLException s) {
//            throw new RuntimeException(s);
//        }
//    }

}

