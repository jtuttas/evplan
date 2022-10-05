package de.mmbbs.menu;

import java.lang.reflect.Member;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import de.mmbbs.App;
import de.mmbbs.database.GruppenController;
import de.mmbbs.database.MitgliedController;
import de.mmbbs.model.Gruppe;
import de.mmbbs.model.Mitglied;

public class BenutzerState implements evPlanState {

    private App app;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    MitgliedController mc = new MitgliedController();
    GruppenController gc = new GruppenController();

    public BenutzerState(App a) {
        app = a;
    }

    @Override
    public void enterCommand(String cmd) {
        if (cmd.equals("x")) {
            Menue.cls();
            app.setState(app.getMainState());
            Menue.printMenue(app.getCurrentState());
        } else if (cmd.equals("l")) {
            listMitglieder();
            Menue.cls();
            Menue.printMenue(app.getCurrentState());
        } else if (cmd.equals("n")) {
            createMitglied();
            Menue.cls();
            Menue.printMenue(app.getCurrentState());
        } else if (cmd.equals("e")) {
            editMitglied();
            Menue.cls();
            Menue.printMenue(app.getCurrentState());
        } else if (cmd.equals("ö")) {
            deleteMitglied();
            Menue.cls();
            Menue.printMenue(app.getCurrentState());
        } else if (cmd.equals("h")) {
            addGroup();
            Menue.cls();
            Menue.printMenue(app.getCurrentState());
        } else if (cmd.equals("g")) {
            showGroups();
            Menue.cls();
            Menue.printMenue(app.getCurrentState());
        } else {
            Menue.printErr("Falsche Eingabe");
        }

    }

    private void addGroup() {
        System.out.print("ID des MitgMitgliedes?: ");
        try {
            String sid = System.console().readLine();
            int id = Integer.parseInt(sid);
            Mitglied m = mc.getMitglied(id);
            if (m == null) {
                Menue.printErr("Benutzer mit ID " + id + " nicht gefunden");
                System.out.println("Weiter mit Return");
                System.console().readLine();
                return;
            }
            System.out.print("ID der Gruppe?:");
            String sgid = System.console().readLine();
            int gid = Integer.parseInt(sgid);
            Gruppe g = gc.getGruppe(gid);
            if (g == null) {
                Menue.printErr("Gruppe mit ID " + gid + " nicht gefunden");
                System.out.println("Weiter mit Return");
                System.console().readLine();
                return;
            }
            gc.addMember(m,g);
        } catch (NumberFormatException pe) {
            Menue.printErr("Fehlerhafte Eingabe");
        } catch (SQLException e) {
            Menue.printErr("SQL Fehler:"+e.getMessage());
        }
        System.out.println("Weiter mit Return");
        System.console().readLine();
    }

    private void showGroups() {
        System.out.print("ID des MitgMitgliedes?: ");
        try {
            String sid = System.console().readLine();
            int id = Integer.parseInt(sid);
            Mitglied m = mc.getMitglied(id);
            if (m == null) {
                Menue.printErr("Benutzer mit ID " + id + " nicht gefunden");
                System.out.println("Weiter mit Return");
                System.console().readLine();
                return;
            }
            for (Gruppe g:gc.getGruppen(m)) {
                System.out.println(g.toString());
            }
        } catch (NumberFormatException pe) {
            Menue.printErr("Fehlerhafte Eingabe");
        } catch (SQLException e) {
            Menue.printErr("SQL Fehler" + e.getMessage());
        }
        System.out.println("Weiter mit Return");
        System.console().readLine();
    }

    private void deleteMitglied() {
        System.out.print("ID des MitgMitgliedes?: ");
        try {
            String sid = System.console().readLine();
            int id = Integer.parseInt(sid);
            Mitglied m = mc.getMitglied(id);
            if (m == null) {
                Menue.printErr("Benutzer mit ID " + id + " nicht gefunden");
                System.out.println("Weiter mit Return");
                System.console().readLine();
                return;
            }
            mc.deleteMitglied(id);
            System.out.println("Mitglied "+m.toString()+" gelöscht!");
        } catch (NumberFormatException pe) {
            Menue.printErr("Fehlerhafte Eingabe");
        } catch (SQLException e) {
            Menue.printErr("SQL Fehler" + e.getMessage());
        }
        System.out.println("Weiter mit Return!");
        System.console().readLine();
    }

    private void editMitglied() {
        System.out.print("ID des MitgMitgliedes?: ");
        try {
            String sid = System.console().readLine();
            int id = Integer.parseInt(sid);
            Mitglied m = mc.getMitglied(id);
            if (m==null) {
                Menue.printErr("Benutzer mit ID "+id+" nicht gefunden");
                System.out.println("Weiter mit Return");
                System.console().readLine();
                return;
            }
            System.out.print("Nachname des Mitgliedes? ["+m.getNachname()+"]: ");
            String nn = System.console().readLine();
            if (nn.length()!=0) {
                m.setNachname(nn);
            }
            System.out.print("Vorname des Mitgliedes? [" + m.getVorname() + "]: ");
            String vn = System.console().readLine();
            if (vn.length()!=0) {
                m.setVorname(vn);
            }
            System.out.print("Geburtsdatum in der For (yyyy-MM-dd) ["+sdf.format(m.getGebDat())+"]?: ");
            String gDats= System.console().readLine();
            if (gDats.length()!=0) {
                Date gebDat = sdf.parse(gDats);
                m.setGebDat(gebDat);
            }
            System.out.print("Mitglied seit (yyyy-MM-dd) [" + sdf.format(m.getSeit()) + "]?: ");
            String ssince = System.console().readLine();
            if (ssince.length()!=0) {
                Date since = sdf.parse(ssince);
                m.setSeit(since);
            }
            mc.setMitglied(m);
            System.out.println("Mitglied "+m.toString()+" geändert !");

        } catch (java.text.ParseException pe) {
            Menue.printErr("Fehlerhafte Eingabe");
        } catch (SQLException e) {
            Menue.printErr("SQL Fehler" + e.getMessage());        
        }
        System.out.println("Weiter mit Return!");
        System.console().readLine();

    }

    private void createMitglied() {
        System.out.print("Nachname des Mitgliedes?: ");
        String nn = System.console().readLine();
        System.out.print("Vorname des Mitgliedes?: ");
        String vn = System.console().readLine();
        System.out.print("Geburtsdatum in der For (yyyy-MM-dd)?: ");
        try {
            Date gebDat = sdf.parse(System.console().readLine());
            System.out.print("Mitglied seit (yyyy-MM-dd)?: ");
            Date since = sdf.parse(System.console().readLine());
            Mitglied m = new Mitglied(nn, vn, gebDat, since);
            mc.addMitglied(m);
            System.out.println("Mitglied "+m.toString()+" angelegt!");

        } catch (ParseException e) {
            Menue.printErr("Fehlerhafte Eingabe");
        } catch (SQLException e) {
            Menue.printErr("SQL Fehler" + e.getMessage());
        }
        System.out.println("Weiter mit Return!");
        System.console().readLine();

    }

    private void listMitglieder() {
        try {
            for (Mitglied m : mc.getMitglieder()) {
                System.out.println(m.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Weiter mit Return!");
        System.console().readLine();

    }

}
