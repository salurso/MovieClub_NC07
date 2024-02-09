package storage.model;

import application.entity.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatasetSampleDAO {
    public static String getFormattedGenres(int idPersona) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT DISTINCT f.Genere FROM Film f JOIN watchlist w ON f.ID = w.ID_Film WHERE w.ID_Persona = ?");
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();

            StringBuilder formattedGenres = new StringBuilder();
            formattedGenres.append("[");
            while (rs.next()) {
                formattedGenres.append(rs.getString("Genere"));
                if(!rs.isLast())
                    formattedGenres.append(",");
            }
            formattedGenres.append("]");

            return formattedGenres.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getAverageReleaseYear(int idPersona) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT AVG(YEAR(f.DataUscita)) AS AvgYear FROM Film f JOIN watchlist w ON f.ID = w.ID_Film WHERE w.ID_Persona = ?");
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("AvgYear");
            }

            return 0; // O un valore di default se la watchlist è vuota
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Film> filterWatchlist(ArrayList<Film> filmList, int idPersona) {
        try (Connection con = ConPool.getConnection()) {

            ArrayList<Film> watchlist = PersonaDAO.getWatchlistFilms(idPersona);
            ArrayList<Film> filteredList = new ArrayList<>(filmList); // Creazione di una nuova lista per evitare ConcurrentModificationException

            for (int i = 0; i < filteredList.size(); i++) {
                Film f = filteredList.get(i);
                if (f.getTitolo() != null) {
                    for (int j = 0; j < watchlist.size(); j++) {
                        Film fWatch = watchlist.get(j);
                        if (f.getId() == fWatch.getId()) {
                            filteredList.remove(i);
                            i--; // Decremento l'indice per gestire la rimozione dell'elemento corrente
                            break; // Esci dal ciclo for interno, poiché il film è stato trovato nella watchlist
                        }
                    }
                } else {
                    filteredList.remove(i);
                    i--; // Decremento l'indice per gestire la rimozione dell'elemento corrente
                }
            }
            return filteredList;
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }
}
