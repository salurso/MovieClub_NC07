package storage.model;

import application.entity.Film;
import application.entity.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonaDAO {

    private static PersonaDAO instance;

    private PersonaDAO() {

    }

    public static synchronized PersonaDAO getInstance() {
        if (instance == null) {
            instance = new PersonaDAO();
        }
        return instance;
    }

    public static void doRegistration(Persona p) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Persona (email, password, nome, cognome, Admin) VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getEmail());
            ps.setString(2, p.getPassword());
            ps.setString(3, p.getNome());
            ps.setString(4, p.getCognome());
            ps.setBoolean(5, p.isAdmin());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Errore nel definire l'utente");
        }
    }

    public static boolean checkEmailDuplicate(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM Persona WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;// Restituisce true se l'email è un duplicato, altrimenti false
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public static Persona doLogin(String email, String password) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            String query = "SELECT * FROM Persona WHERE email = ? AND password = SHA1(?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, email);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return parsePersona(rs);
                    } else {
                        return null;
                    }
                }
            }
        }
    }



    public static Persona parsePersona(ResultSet rs) throws SQLException {
        Persona p = new Persona();
        p.setId(rs.getInt("ID"));
        p.setEmail(rs.getString("email"));
        p.setPassword(rs.getString("password"));
        p.setNome(rs.getString("nome"));
        p.setCognome(rs.getString("cognome"));
        p.setAdmin(rs.getBoolean("Admin"));
        return p;
    }

    public Persona doRetrieveByEmail(String email) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Persona WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Persona persona = new Persona();
                persona.setEmail(rs.getString("email"));
                persona.setNome(rs.getString("nome"));

                return persona;
            } else {
                return null; // Utente non trovato
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

                watchlistFilms.add(film);
            }
            return watchlistFilms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToWatchlist(int idPersona, int idFilm) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO watchlist (ID_Persona, ID_Film) VALUES (?, ?)");
            ps.setInt(1, idPersona);
            ps.setInt(2, idFilm);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeFromWatchlist(int idPersona, int idFilm) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM watchlist WHERE ID_Persona = ? AND ID_Film = ?");
            ps.setInt(1, idPersona);
            ps.setInt(2, idFilm);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Persona> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select Email, Nome, Cognome, Admin from persona");
            ResultSet rs = ps.executeQuery();
            ArrayList<Persona> persone = new ArrayList<>();

            while (rs.next()){
                Persona p = new Persona();
                p.setEmail(rs.getString(1));
                p.setNome(rs.getString(2));
                p.setCognome(rs.getString(3));
                p.setAdmin(rs.getBoolean(4));

                persone.add(p);
            }
            return persone;

        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

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
                    formattedGenres.append(", ");
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

    public ArrayList<Film> filterWatchlist(ArrayList<Film> filmList, int idPersona) {
        try (Connection con = ConPool.getConnection()) {

            ArrayList<Film> watchlist = getWatchlistFilms(idPersona);

            filmList.removeAll(watchlist);

            return filmList;
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }


}
