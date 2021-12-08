package de.mmbbs.model;

public class Gruppe {

    private int id;
    private String bezeichnung;

    public Gruppe(int id, String bez) {
        this.id = id;
        bezeichnung = bez;
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
