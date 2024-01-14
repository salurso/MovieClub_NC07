package storage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ListaDAO {

    public static void prova(){
        try(Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Watchlist (ID) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 1);

            if(ps.executeUpdate() != 1)
                throw  new RuntimeException("Errore");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        prova();
    }
}

