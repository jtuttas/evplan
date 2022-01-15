package de.mmbbs;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import de.mmbbs.database.GruppenController;
import de.mmbbs.database.istIn;
import de.mmbbs.database.MitgliedController;
import de.mmbbs.menu.BenutzerState;
import de.mmbbs.menu.GruppenState;
import de.mmbbs.menu.MainState;
import de.mmbbs.menu.Menue;
import de.mmbbs.menu.evPlanState;
import de.mmbbs.model.Gruppe;
import de.mmbbs.model.Mitglied;

/**
 * Hello world!
 */
public final class App implements evPlanState{

    private MainState mainState;
    private BenutzerState benutzerState;
    private GruppenState gruppenState;
    private evPlanState currentState;


    public App() {
        mainState = new MainState(this);
        benutzerState = new BenutzerState(this);
        gruppenState = new GruppenState(this);
        currentState=mainState;
        Menue.cls();
        Menue.printMenue(currentState);

        while (true) {
            System.out.print("Anweisung:");            
            this.enterCommand(System.console().readLine());
        }
    }

    public BenutzerState getBenutzerState() {
        return benutzerState;
    }
    public GruppenState getGruppenState() {
        return gruppenState;
    }
    public MainState getMainState() {
        return mainState;
    }
    public evPlanState getCurrentState() {
        return currentState;
    }
    public void setState(evPlanState currentState) {
        this.currentState = currentState;
    }

    
    @Override
    public void enterCommand(String cmd) {
        currentState.enterCommand(cmd.toLowerCase());
        
    }

    public static void main(String[] args) {
        new App();
    }
}
