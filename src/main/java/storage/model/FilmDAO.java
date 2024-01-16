package storage.model;

import application.entity.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO {

    public List<Film> doRetrieveAll() {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select id, titolo, regista, durata, copertina, genere from film");
            ResultSet rs = ps.executeQuery();
            ArrayList<Film> films = new ArrayList<>();

            while (rs.next()){
                Film f = new Film();
                f.setId(rs.getInt(1));
                f.setTitolo(rs.getString(2));
                f.setRegista(rs.getString(3));
                f.setDurata(rs.getDouble(3));
                f.setCopertina(rs.getString(4));
                f.setGenere(rs.getString(5));

                films.add(f);
            }
            return films;

        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    public ArrayList<String> cercaGeneri(){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select distinct genere from film");
            ResultSet rs = ps.executeQuery();
            ArrayList<String> generi = new ArrayList<>();

            while (rs.next()){
                generi.add(rs.getString(1));
            }
            return generi;

        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }


    public Film doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select id, titolo, regista, durata, copertina, genere from film where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Film f = new Film();
            while (rs.next()) {
                f.setId(rs.getInt(1));
                f.setTitolo(rs.getString(2));
                f.setRegista(rs.getString(3));
                f.setDurata(rs.getDouble(3));
                f.setCopertina(rs.getString(4));
                f.setGenere(rs.getString(5));
            }
            return f;
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }
}
