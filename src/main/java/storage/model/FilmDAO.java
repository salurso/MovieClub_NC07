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

}
