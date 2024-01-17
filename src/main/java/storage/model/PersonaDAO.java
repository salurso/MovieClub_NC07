package storage.model;

import application.entity.Persona;

import java.sql.*;

public class PersonaDAO {

    public static void doRegistration(Persona p) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO utente (email, password, nome, cognome) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getEmail());
            ps.setString(2, p.getPassword());
            ps.setString(3, p.getNome());
            ps.setString(4, p.getCognome());

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

}
