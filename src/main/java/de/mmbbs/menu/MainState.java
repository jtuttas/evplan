package de.mmbbs.menu;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.mmbbs.App;
import de.mmbbs.database.MitgliedController;
import de.mmbbs.model.Mitglied;

public class MainState implements evPlanState {
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    MitgliedController mc = new MitgliedController();

    private App app;

    public MainState(App a) {
        app=a;
    }

    @Override
    public void enterCommand(String cmd) {
        if (cmd.equals("b")) {
            Menue.cls();
            app.setState(app.getBenutzerState());
            Menue.printMenue(app.getCurrentState());
        }
        else if (cmd.equals("g")) {
            Menue.cls();
            app.setState(app.getGruppenState());
            Menue.printMenue(app.getCurrentState());
        }
        else if (cmd.equals("r")) {
            printRechnung();
            Menue.cls();
            Menue.printMenue(app.getCurrentState());
        }
        else if (cmd.equals("x")) {
            System.exit(0);
        }
        else {
            Menue.printErr("Falsche Eingabe");
        }
        
    }

    private void printRechnung() {
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
            System.out.println("Beitragrechnung für "+m.getVorname()+" "+m.getNachname());
            System.out.println("Geb. ........:" +sdf.format(m.getGebDat()));
            System.out.println("Mitglied seit:" +sdf.format(m.getSeit()));
            System.out.println("--------------------------");
            Calendar c = new GregorianCalendar();
            c.setTime(new Date());
            System.out.println("Beitragsjahr: "+c.get(Calendar.YEAR));
            System.out.println("\r\rIhr Jahresbeitrag beträgt: "+getBeitrag(m.getAlter(),m.getMitgliedsjahre())+" EUR\r\n");
            
        } catch (NumberFormatException pe) {
            Menue.printErr("Fehlerhafte Eingabe");
        } catch (SQLException e) {
            Menue.printErr("SQL Fehler" + e.getMessage());
        }
        System.out.println("Weiter mit Return!");
        System.console().readLine();
    }

    public double getBeitrag(int alter, int mitgliedsjahre) {
        double beitrag = 0;
        if (alter < 18) {
            beitrag = 30.0;
        } else if (alter <= 12) {
            beitrag = 15.0;
        } else {
            beitrag = 50.0;
        }
        if (mitgliedsjahre > 5 && mitgliedsjahre <= 15) {
            beitrag = beitrag * 0.9;
        } else if (mitgliedsjahre > 15) {
            beitrag = beitrag * 0.8;
        }
        return beitrag;
    }
    
}
