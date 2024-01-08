package de.mmbbs.menu;

import java.io.IOException;

public class Menue {

    public static void cls() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printMenue(evPlanState s) {
        System.out.println(ConsoleColors.BLACK_BOLD_BRIGHT+ "evPlan - die Vereinsverwaltung V0.1");
        if (s instanceof MainState) {
            System.out.println(ConsoleColors.YELLOW+"----------- Hauptmenu -------------"+ConsoleColors.GREEN);
            System.out.println("("+cmd("B")+"enutzerverwaltung\r\n("+cmd("G")+")ruppenverwaltung\r\n("
                    + cmd("R") + ")echnung drucken\r\n("+cmd("X")+")Ende");
        }
        if (s instanceof BenutzerState) {
            System.out.println(ConsoleColors.YELLOW +"------- Benutzerverwaltung --------"+ ConsoleColors.GREEN);
            System.out.println("("+cmd("L")+")iste der Mitglieder\r\n("+cmd("N")+")eues Mitglied anlegen\r\nMitglied ("+cmd("E")+")ditieren\r\nMitglied l("+cmd("ö")+")schen\r\n("+cmd("G")+")ruppen eines Mitglied anzeigen\r\nMitglied einer Gruppe ("+cmd("h")+")inzufügen\r\n("+cmd("X")+") zurück zum Hauptmenü\r\n");
            
        }
        if (s instanceof GruppenState) {
            System.out.println(ConsoleColors.YELLOW +"------- Gruppenverwaltung ---------"+ ConsoleColors.GREEN);
            System.out.println("("+cmd("L")+")iste der Gruppe\r\nGruppe ("+cmd("a")+")nlegen\r\nGruppe l("+cmd("ö")+")schen\r\nGruppenbezeichnung ("+cmd("ä")+")ndern\r\n("+cmd("X")+") zurück zum Hauptmenu\r\n");

        }


    }

    private static String cmd(String string) {
        return ConsoleColors.CYAN_BOLD_BRIGHT+string+ConsoleColors.GREEN;
    }

	public static void printErr(String string) {
        System.out.println(ConsoleColors.RED_BOLD_BRIGHT+string+ConsoleColors.GREEN);
	}
    
}
