package de.mmbbs.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.mmbbs.model.Gruppe;
import de.mmbbs.model.Mitglied;

public class istIn {

    public ArrayList<Gruppe> holeG(Mitglied m) {
        ArrayList a = new ArrayList<>();
        try {
            // Laden des JDBC Treibers
            Class.forName("org.sqlite.JDBC");
            ResultSet rs = DriverManager.getConnection("jdbc:sqlite:evplan.sqlite").createStatement().executeQuery(
                    "select gruppe.gid,bezeichnung from mitglied inner join ist_in on mitglied.mid=ist_in.mid inner join gruppe on ist_in.gid=gruppe.gid where mitglied.mid="
                            + m.getId());
            // lese solange was da ist
            while (rs.next()) {
                Gruppe g = new Gruppe(rs.getString("bezeichnung"));
                g.setId(rs.getInt("gid"));
                a.add(g);
            }
        } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
        }


        return a;
    }

    public ArrayList<Mitglied> getMemberOf(Gruppe g)  {
        ArrayList a = new ArrayList<>();
        try {
        // Laden des JDBC Treibers
        Class.forName("org.sqlite.JDBC");
        ResultSet rs = DriverManager.getConnection("jdbc:sqlite:evplan.sqlite").createStatement().executeQuery(
                "select mitglied.mid,nachname,vorname,gebDat,seit from mitglied inner join ist_in on mitglied.mid=ist_in.mid inner join gruppe on ist_in.gid=gruppe.gid where mitglied.mid="
                        + g.getId());
        // Lese solange was da ist
        while (rs.next()) {
            Mitglied m = new Mitglied(rs.getString("nachname"), rs.getString("vorname"),new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("gebDat")),new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("seit")));
            m.setId(rs.getInt("mid"));
            a.add(m);
        }
        
    }
    catch (Exception e) {
    System.err.println(e.getClass().getName() + ": " + e.getMessage());
    System.exit(0);
    }
    return a;
    }

    public void addMember(Mitglied m,Gruppe g) {
        try {
        // Laden des JDBC Treibers
        Class.forName("org.sqlite.JDBC");
        DriverManager.getConnection("jdbc:sqlite:evplan.sqlite").createStatement().execute("insert into ist_in(mid,gid) values ("+m.getId()+","+g.getId()+")");
        }
        catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
        }
            }

    public void entferneMember(Mitglied m,Gruppe g) {
        try {
        // Laden des JDBC Treibers
        Class.forName("org.sqlite.JDBC");
        DriverManager.getConnection("jdbc:sqlite:evplan.sqlite").createStatement()
                .execute("delete from ist_in where mid=" + m.getId() + " and gid=" + g.getId());
        //System.out.println("Kommando ausgeführt");
        } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
        }
    }





}
