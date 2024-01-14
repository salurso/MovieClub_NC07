package storage.service;

import storage.model.PersonaDAO;

public class PersonaService {

    public static void retriveById(int id){
        if(id != 0) {
            PersonaDAO.retriveById(id);
        }

    }
}
