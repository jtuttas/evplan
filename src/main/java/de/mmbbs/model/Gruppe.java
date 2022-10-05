package de.mmbbs.model;

import java.io.ObjectInputStream.GetField;

public class Gruppe {

    private int id;
    private String bezeichnung;

    public Gruppe(String bez) {
        bezeichnung = bez;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public String toString() {
        return id + ":" + bezeichnung;
    }
}
