package storage.model;

import application.entity.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WatchlistDAO {

    public static ArrayList<Film> getWatchlistFilms(int idPersona) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT f.* FROM Film f JOIN watchlist w ON f.ID = w.ID_Film WHERE w.ID_Persona = ?");
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();

            ArrayList<Film> watchlistFilms = new ArrayList<>();
            while (rs.next()) {
                Film film = new Film();
                film.setId(rs.getInt("ID"));
                film.setTitolo(rs.getString("Titolo"));
                film.setRegista(rs.getString("Regista"));
                film.setDurata(rs.getTime("Durata"));
                film.setCopertina(rs.getString("Copertina"));
                film.setGenere(rs.getString("Genere"));
                // Aggiungi altri campi di Film secondo necessità

                watchlistFilms.add(film);
            }
            return watchlistFilms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
