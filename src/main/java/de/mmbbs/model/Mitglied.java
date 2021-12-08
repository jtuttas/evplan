package de.mmbbs.model;




import java.text.SimpleDateFormat;
import java.util.Date;

import de.mmbbs.menu.ConsoleColors;

public class Mitglied {
    private int id;
    private String nachname;
    private String vorname;
    private Date gebDat;
    private Date seit;

    public Mitglied(int id, String n, String v, Date gd, Date s) {
        this.id=id;
        nachname=n;
        vorname=v;
        gebDat=gd;
        seit=s;
    }

    public int getId() {
        return id;
    }
    public Date getGebDat() {
        return gebDat;
    }
    public String getNachname() {
        return nachname;
    }
    public String getVorname() {
        return vorname;
    }
    public Date getSeit() {
        return seit;
    }
    public void setGebDat(Date gebDat) {
        this.gebDat = gebDat;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    public void setSeit(Date seit) {
        this.seit = seit;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }


    public String toString() {
        SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy");
        return id+":"+vorname+" "+nachname+ " (Geb. "+dt.format(gebDat)+") Mitglied seit "+ dt.format(seit);
    }
}
