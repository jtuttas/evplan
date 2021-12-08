package de.mmbbs.menu;

import de.mmbbs.App;

public class MainState implements evPlanState {

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
        else if (cmd.equals("x")) {
            System.exit(0);
        }
        else {
            Menue.printErr("Falsche Eingabe");
        }
        
    }
    
}
