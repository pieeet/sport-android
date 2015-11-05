package com.rocdev.piet.sport.backend;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    public ArrayList<Lid> getLeden() {
        ArrayList<Lid> leden = new ArrayList<>();
        Query q = new Query("Lid");
        PreparedQuery pq = datastore.prepare(q);
        for (Entity e: pq.asIterable()) {
            Lid lid = new Lid();
            lid.setSpelerscode((String) e.getProperty("spelerscode"));
            lid.setRoepnaam((String) e.getProperty("roepnaam"));
            lid.setTussenvoegsels((String) e.getProperty("tussenvoegsels"));
            lid.setAchternaam((String) e.getProperty("achternaam"));
            lid.setAdres((String) e.getProperty("adres"));
            lid.setPostcode((String) e.getProperty("postcode"));
            lid.setWoonplaats((String) e.getProperty("woonplaats"));
            lid.setTelefoon((String) e.getProperty("telefoon"));
            lid.setEmail((String) e.getProperty("email"));
            lid.setGeslacht((String) e.getProperty("geslacht"));
            lid.setGeboortedatum((String) e.getProperty("geboortedatim"));
            leden.add(lid);
        }
        return leden;
    }

    public Lid getLid(String spelerscode) {
        Lid lid = new Lid();
        Key k = KeyFactory.createKey("Lid", spelerscode);
        Entity res = null;

        try {
            res = datastore.get(k);
            lid.setSpelerscode((String) res.getProperty("spelerscode"));
            lid.setRoepnaam((String) res.getProperty("roepnaam"));
            lid.setTussenvoegsels((String) res.getProperty("tussenvoegsels"));
            lid.setAchternaam((String) res.getProperty("achternaam"));
            lid.setAdres((String) res.getProperty("adres"));
            lid.setPostcode((String) res.getProperty("postcode"));
            lid.setWoonplaats((String) res.getProperty("woonplaats"));
            lid.setTelefoon((String) res.getProperty("telefoon"));
            lid.setEmail((String) res.getProperty("email"));
            lid.setGeboortedatum((String) res.getProperty("geboortedatum"));
            lid.setGeslacht((String) res.getProperty("geslacht"));

        } catch (EntityNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return lid;
    }
}