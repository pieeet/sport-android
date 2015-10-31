package com.rocdev.piet.sport.backend;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

/**
 * Created by piet on 31-10-15.
 */
public class DatastoreIO {
    DatastoreService datastore;


    public DatastoreIO() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public void voegLidToe(Lid lid) {
        Entity e = new Entity("Lid", lid.getSpelerscode());
        e.setProperty("spelerscode", lid.getSpelerscode());
        e.setProperty("roepnaam", lid.getRoepnaam());
        e.setProperty("tussenvoegsels", lid.getTussenvoegsels());
        e.setProperty("achternaam", lid.getAchternaam());
        e.setProperty("adres", lid.getAdres());
        e.setProperty("postcode", lid.getPostcode());
        e.setProperty("woonplaats", lid.getWoonplaats());
        e.setProperty("telefoon", lid.getTelefoon());
        e.setProperty("email", lid.getEmail());
        e.setProperty("geslacht", lid.getGeslacht());
        e.setProperty("geboortedatum", lid.getGeboortedatum());
        datastore.put(e);
    }
}