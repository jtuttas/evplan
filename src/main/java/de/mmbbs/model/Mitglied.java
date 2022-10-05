package de.mmbbs.model;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.mmbbs.menu.ConsoleColors;

public class Mitglied {
    private int id;
    private String nachname;
    private String vorname;
    private Date gebDat;
    private Date seit;

    public Mitglied(String n, String v, Date gd, Date s) {
        nachname=n;
        vorname=v;
        gebDat=gd;
        seit=s;
    }

    public void setId(int id) {
        this.id = id;
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
        nachname = nachname;
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


    public int getAlter() {
        LocalDate bith = gebDat.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(bith, LocalDate.now()).getYears();
    }

    public int getMitgliedsjahre() {
        LocalDate s = seit.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(s, LocalDate.now()).getYears();
    }
   
}
