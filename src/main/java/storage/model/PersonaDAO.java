package storage.model;

import application.entity.Persona;

import java.sql.*;

public class PersonaDAO {

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


}
