package storage.model;

import application.entity.Film;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO {

    private static FilmDAO instance;

    private FilmDAO() {
        // Costruttore privato per impedire l'istanziazione esterna
    }

    public static synchronized FilmDAO getInstance() {
        if (instance == null) {
            instance = new FilmDAO();
        }
        return instance;
    }

    public List<Film> doRetrieveAll() {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select ID, Titolo, Regista, Durata, Copertina, Genere from Film");
            ResultSet rs = ps.executeQuery();
            ArrayList<Film> films = new ArrayList<>();

            while (rs.next()){
                Film f = new Film();
                f.setId(rs.getInt(1));
                f.setTitolo(rs.getString(2));
                f.setRegista(rs.getString(3));
                f.setDurata(rs.getTime(4));
                f.setCopertina(rs.getString(5));
                f.setGenere(rs.getString(6));

                films.add(f);
            }
            return films;

        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    public Film doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select ID, Titolo, Regista, Durata, Copertina, Trailer, Genere, Descrizione, DataUscita from film where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Film f = new Film();
            while (rs.next()) {
                f.setId(rs.getInt(1));
                f.setTitolo(rs.getString(2));
                f.setRegista(rs.getString(3));
                f.setDurata(rs.getTime(4));
                f.setCopertina(rs.getString(5));
                f.setTrailer(rs.getString(6));
                f.setGenere(rs.getString(7));
                f.setDescrizione(rs.getString(8));
                f.setDataUscita(rs.getDate(9));
            }
            return f;
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    public static int doInsert(Film f) throws IOException {

        int result;
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("INSERT INTO film (Titolo, Regista, Genere, Copertina, Trailer, DataUscita, Durata, Descrizione) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, f.getTitolo());
            ps.setString(2, f.getRegista());
            ps.setString(3, f.getGenere());
            ps.setString(4, f.getCopertina());
            ps.setString(5, f.getTrailer());
            ps.setDate(6, (Date) f.getDataUscita());
            ps.setTime(7, f.getDurata());
            ps.setString(8, f.getDescrizione());

            return result = ps.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    public int doUpdate(Film f) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE film SET Titolo = ?, Regista = ?, Genere = ?, Copertina = ?, Trailer = ?, DataUscita = ?, Durata = ?, Descrizione = ? WHERE ID = ?");
            ps.setString(1, f.getTitolo());
            ps.setString(2, f.getRegista());
            ps.setString(3, f.getGenere());
            ps.setString(4, f.getCopertina());
            ps.setString(5, f.getTrailer());
            ps.setDate(6, (Date) f.getDataUscita());
            ps.setTime(7, f.getDurata());
            ps.setString(8, f.getDescrizione());
            ps.setInt(9, f.getId());


            return ps.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    public int doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM film WHERE ID = ?");
            ps.setInt(1, id);

            return ps.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }
}
