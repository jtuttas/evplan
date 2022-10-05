package de.mmbbs.menu;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import de.mmbbs.App;
import de.mmbbs.database.GruppenController;
import de.mmbbs.database.MitgliedController;
import de.mmbbs.model.Gruppe;

public class GruppenState implements evPlanState{

    private App app;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    MitgliedController mc = new MitgliedController();
    GruppenController gc = new GruppenController();

    public GruppenState(App a) {
        app=a;
    }

    @Override
    public void enterCommand(String cmd) {
        if (cmd.equals("x")) {
            Menue.cls();
            app.setState(app.getMainState());
            Menue.printMenue(app.getCurrentState());
        } else if (cmd.equals("l")) {
            listGruppen();
            Menue.cls();
            Menue.printMenue(app.getCurrentState());
        } else if (cmd.equals("a")) {
            addGruppe();
            Menue.cls();
            Menue.printMenue(app.getCurrentState());
        } else if (cmd.equals("ö")) {
            deleteGruppe();
            Menue.cls();
            Menue.printMenue(app.getCurrentState());
        } else if (cmd.equals("ä")) {
            editGruppe();
            Menue.cls();
            Menue.printMenue(app.getCurrentState());
        } else {
            Menue.printErr("Falsche Eingabe");
        }
        
    }

    private void editGruppe() {
        System.out.print("ID der Gruppe?: ");
        try {
            String gsid = System.console().readLine();
            int gid = Integer.parseInt(gsid);
            Gruppe g = gc.getGruppe(gid);
            if (g == null) {
                Menue.printErr("Gruppe mit ID " + gid + " nicht gefunden");
                System.out.println("Weiter mit Return");
                System.console().readLine();
                return;
            }
            System.out.print("Bezeichnung der Gruppe? [" + g.getBezeichnung() + "]: ");
            String bez = System.console().readLine();
            if (bez.length() != 0) {
                g.setBezeichnung(bez);
            }
            gc.setGruppe(g);
            System.out.println("Gruppe " + g.toString() + " geändert !");

        } catch (SQLException e) {
            Menue.printErr("SQL Fehler" + e.getMessage());
        }
        System.out.println("Weiter mit Return!");
        System.console().readLine();

    }

    private void deleteGruppe() {
        System.out.print("ID der Gruppe?: ");
        try {
            String gsid = System.console().readLine();
            int gid = Integer.parseInt(gsid);
            Gruppe g = gc.getGruppe(gid);
            if (g == null) {
                Menue.printErr("Gruppe mit ID " + gid + " nicht gefunden");
                System.out.println("Weiter mit Return");
                System.console().readLine();
                return;
            }
            gc.deleteGruppe(gid);
            System.out.println("Gruppe " + g.toString() + " gelöscht!");
        } catch (NumberFormatException pe) {
            Menue.printErr("Fehlerhafte Eingabe");
        } catch (SQLException e) {
            Menue.printErr("SQL Fehler" + e.getMessage());
        }
        System.out.println("Weiter mit Return!");
        System.console().readLine();
    }

    private void addGruppe() {
        System.out.print("Name der Gruppe?: ");
        String gn = System.console().readLine();
        try {
            Gruppe g = new Gruppe(gn);
            gc.addGruppe(g);
            System.out.println("Gruppe " + g.getBezeichnung() + " angelegt!");

        } catch (SQLException e) {
            Menue.printErr("SQL Fehler" + e.getMessage());
        }
        System.out.println("Weiter mit Return!");
        System.console().readLine();
    }

    private void listGruppen() {
        try {
            for (Gruppe g : gc.getGruppen()) {
                System.out.println(g.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Weiter mit Return!");
        System.console().readLine();

    }
    
}
