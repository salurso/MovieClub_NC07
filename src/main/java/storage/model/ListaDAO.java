package storage.model;

import application.entity.Film;
import application.entity.Lista;
import application.entity.Persona;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ListaDAO {

    public static ArrayList<Lista> doRetrieveAll(){
        try (Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT ID, Nome, Descrizione, Immagine, Privata FROM Lista");
            ResultSet rs = ps.executeQuery();
            ArrayList<Lista> lists = new ArrayList<>();
            while (rs.next()){
                Lista l = new Lista();
                l.setId(rs.getInt(1));
                l.setNome(rs.getString(2));
                l.setDescrizione(rs.getString(3));
                l.setImmagine(rs.getString(4));
                l.setPrivata(rs.getBoolean(5));

                lists.add(l);
            }
            return lists;
        } catch (SQLException s){
            throw new RuntimeException(s);
        }
    }

    public Lista doRetrieveById(int id){ //TROVA INFO LISTA DALL'ID
        try (Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT ID, Nome, Descrizione, Immagine, Privata FROM Lista WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Lista l = new Lista();
            while(rs.next()){
                l.setId(rs.getInt(1));
                l.setNome(rs.getString(2));
                l.setDescrizione(rs.getString(3));
                l.setImmagine(rs.getString(4));
                l.setPrivata(rs.getBoolean(5));
            }
            return l;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Lista> doRetrieveByEmail(String email) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT ID, Nome, Descrizione, Immagine, Privata FROM Lista WHERE Email_Persona = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            ArrayList<Lista> lists = new ArrayList<>();
            while (rs.next()) {
                Lista l = new Lista();
                l.setId(rs.getInt(1));
                l.setNome(rs.getString(2));
                l.setDescrizione(rs.getString(3));
                l.setImmagine(rs.getString(4));
                l.setPrivata(rs.getBoolean(5));
                lists.add(l);
            }
            return lists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public ArrayList<Film> getFilmsInList(int idLista) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT f.* FROM Film f JOIN Include i ON f.id = i.ID_Film WHERE i.ID_Lista = ?");
            ps.setInt(1, idLista);
            ResultSet rs = ps.executeQuery();

            ArrayList<Film> films = new ArrayList<>();
            while (rs.next()) {
                Film f = new Film();
                f.setId(rs.getInt("id"));
                f.setTitolo(rs.getString("titolo"));
                f.setRegista(rs.getString("regista"));
                f.setDurata(rs.getTime("durata"));
                f.setCopertina(rs.getString("copertina"));
                f.setGenere(rs.getString("genere"));
                films.add(f);
            }
            return films;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int doUpdate(Lista l) { //MODIFICA LE INFORMAZIONI DELLA LISTA
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Lista SET Nome = ?, Descrizione = ?, Immagine = ?, Privata = ? WHERE ID = ?");
            ps.setString(1, l.getNome());
            ps.setString(2, l.getDescrizione());
            ps.setString(3, l.getImmagine());
            ps.setBoolean(4, l.isPrivata());
            ps.setInt(5, l.getId());

            return ps.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    public int doInsert(Lista l) throws IOException {
        try (Connection con = ConPool.getConnection()) {
            // Verifica se esiste già una lista con lo stesso nome associata alla stessa email
            PreparedStatement psCheck = con.prepareStatement("SELECT COUNT(*) FROM Lista WHERE Nome = ? AND Email_Persona = ?");
            psCheck.setString(1, l.getNome());
            psCheck.setString(2, l.getEmail_Persona());
            ResultSet rsCheck = psCheck.executeQuery();
            rsCheck.next();
            int count = rsCheck.getInt(1);

            if (count > 0) {
                // Una lista con lo stesso nome è già associata alla stessa email, quindi non possiamo inserirla
                return 0;
            }

            // Procedi con l'inserimento della nuova lista
            PreparedStatement psInsert = con.prepareStatement("INSERT INTO Lista (Nome, Privata, Descrizione, Immagine, Email_Persona) VALUES (?,?,?,?,?)");
            psInsert.setString(1, l.getNome());
            psInsert.setBoolean(2, l.isPrivata());
            psInsert.setString(3, l.getDescrizione());
            psInsert.setString(4, l.getImmagine());
            psInsert.setString(5, l.getEmail_Persona());

            int rowsAffected = psInsert.executeUpdate();
            return rowsAffected; // Ritorniamo il numero di righe modificate (1 se l'inserimento è avvenuto correttamente)
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    public int doDeleteList(int id) {
        try (Connection connection = ConPool.getConnection()) {
            connection.setAutoCommit(false);

            try {
                PreparedStatement psInclude = connection.prepareStatement("DELETE FROM include WHERE ID_Lista = ?");
                psInclude.setInt(1, id);
                psInclude.executeUpdate();

                PreparedStatement psLista = connection.prepareStatement("DELETE FROM Lista WHERE ID = ?");
                psLista.setInt(1, id);
                int rowsAffected = psLista.executeUpdate();

                connection.commit();

                return rowsAffected;
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }


    public int doDeleteFilmList(int idLista, int idFilm) { //ELIMINA FILM DALLA LISTA
        try (Connection connection = ConPool.getConnection()){
            //AGGIUNGERE CONTROLLO SESSIONE EMAIL_PERSONA = ID_LISTA
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Include WHERE ID_Lista = ? AND ID_Film = ?");
            ps.setInt(1, idLista);
            ps.setInt(2, idFilm);

            return ps.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }


    public int doInsertFilmIntoList(int idLista, int idFilm) { //INSERIMENTO FILM IN LISTA
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Include (ID_Lista, ID_Film) VALUES (?, ?)");
            ps.setInt(1, idLista);
            ps.setInt(2, idFilm);

            return ps.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }
 public int doRetrieveFilmIntoList(int idLista, int idFilm) { //FILM GIA' PRESENTE  IN LISTA
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Include WHERE id_lista = ? AND id_film = ?");
            ps.setInt(1, idLista);
            ps.setInt(2, idFilm);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return 1; //film già presente
            } else {
                return 0; //film non presente
            }
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

    public ArrayList<Lista> getPublicLists() {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT l.ID, l.Nome, l.Descrizione, l.Immagine, l.Privata, l.Email_Persona FROM Lista l JOIN Persona p ON l.Email_Persona = p.Email WHERE l.Privata = false");
            ResultSet rs = ps.executeQuery();

            ArrayList<Lista> publicLists = new ArrayList<>();
            while (rs.next()) {
                Lista l = new Lista();
                l.setId(rs.getInt("ID"));
                l.setNome(rs.getString("Nome"));
                l.setDescrizione(rs.getString("Descrizione"));
                l.setImmagine(rs.getString("Immagine"));
                l.setPrivata(rs.getBoolean("Privata"));

                // Aggiungi l'email della persona alla lista
                String emailPersona = rs.getString("Email_Persona");
                l.setEmail_Persona(emailPersona);

                publicLists.add(l);
            }
            return publicLists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}


