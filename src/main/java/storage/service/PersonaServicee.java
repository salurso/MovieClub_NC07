package storage.service;

import storage.model.PersonaDAO;

public class PersonaServicee {

    public static void retriveById(int id){
        if(id != 0) {
            PersonaDAO.retriveById(id);
        }

    }
}
