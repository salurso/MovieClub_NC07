package application.entity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Persona {

    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private boolean admin;
    private ArrayList<Film> watchlist;

    public Persona() {
    }

    public Persona(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        setPassword(password);
        this.admin = false;
        watchlist = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.password = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ArrayList<Film> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(ArrayList<Film> watchlist) {
        this.watchlist = watchlist;
    }
}
