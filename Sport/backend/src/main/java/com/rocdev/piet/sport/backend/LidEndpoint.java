package com.rocdev.piet.sport.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "lidApi",
        version = "v1",
        resource = "lid",
        namespace = @ApiNamespace(
                ownerDomain = "backend.sport.piet.rocdev.com",
                ownerName = "backend.sport.piet.rocdev.com",
                packagePath = ""
        )
)
public class LidEndpoint {

    private static final Logger logger = Logger.getLogger(LidEndpoint.class.getName());

    /**
     * This method gets the <code>Lid</code> object associated with the specified <code>id</code>.
     *
     * @param spelerscode The id of the object to be returned.
     * @return The <code>Lid</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getLid")
    public Lid getLid(@Named("spelerscode") String spelerscode) {
        Lid lid = new DatastoreIO().getLid(spelerscode);
        logger.info("Calling getLid method");
        return lid;
    }

    /**
     * This inserts a new <code>Lid</code> object.
     *
     * @param lid The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertLid")
    public Lid insertLid(Lid lid) {
        new DatastoreIO().voegLidToe(lid);
        logger.info("Calling insertLid method");
        return lid;
    }

    /**
     * haal de lijst met leden
     * @return ArrayList met leden
     */
    @ApiMethod(name = "listLid")
    public List<Lid> listLid() {
        ArrayList<Lid> leden = new DatastoreIO().getLeden();
        return leden;
    }
}