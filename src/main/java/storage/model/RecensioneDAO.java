package storage.model;

import application.entity.Recensione;
import storage.service.RecensioneService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {

    public List<Recensione> doRetrieveAll(){

        try(Connection con = ConPool.getConnection()){

            PreparedStatement ps = con.prepareStatement("Select * From Recensione");
            ResultSet rs = ps.executeQuery();
            ArrayList<Recensione> recensioni = new ArrayList<>();

            while(rs.next()){
                Recensione r = new Recensione();
                r.setValutazione(rs.getInt(1));
                r.setDescrizione(rs.getString(2));
                r.setData(rs.getDate(3));
                r.setEmailPersona(rs.getString(4));
                r.setID_Film(rs.getInt(5));

                recensioni.add(r);
            }
            return recensioni;
        }catch(SQLException s){
            throw new RuntimeException(s);
        }
    }

}
