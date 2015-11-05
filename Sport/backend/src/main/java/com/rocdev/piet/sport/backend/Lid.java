package com.rocdev.piet.sport.backend;



/**
 * Created by piet on 31-10-15.
 * Een zogenaamde Java Bean of POJO (Plain Old Java Object)
 * Hier kun je een endpoint van maken door er in de explorer met je rechtse muisknop op
 * te klikken en kies ... "Generate cloud endpoint from java class". In de endpoint
 * klasse kun je methodes aanpassen en toevoegen.
 * Na aanpassen van je endpoint klasse moet je in studio je project met Gradle synchroniseren.
 * Kies "Tools" --> "Android" --> "Sync project with Gradle Files"
 *
 */
public class Lid {
    String spelerscode;
    String roepnaam;
    String tussenvoegsels;
    String achternaam;
    String adres;
    String postcode;
    String woonplaats;
    String telefoon;
    String email;
    String geboortedatum;
    String geslacht;

    public String getSpelerscode() {
        return spelerscode;
    }

    public void setSpelerscode(String spelerscode) {
        this.spelerscode = spelerscode;
    }

    public String getRoepnaam() {
        return roepnaam;
    }

    public void setRoepnaam(String roepnaam) {
        this.roepnaam = roepnaam;
    }

    public String getTussenvoegsels() {
        return tussenvoegsels;
    }

    public void setTussenvoegsels(String tussenvoegsels) {
        this.tussenvoegsels = tussenvoegsels;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public Lid() {

    }

    public String getNaam() {
        String naam;
        if (tussenvoegsels.equals("")) {
            naam = roepnaam + " " + achternaam;
        } else {
            naam = roepnaam + " " + tussenvoegsels + " " + achternaam;
        }
        return naam;
    }

}
