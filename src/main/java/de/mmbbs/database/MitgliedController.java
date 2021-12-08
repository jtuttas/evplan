package de.mmbbs.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.mmbbs.model.Gruppe;
import de.mmbbs.model.Mitglied;

public class MitgliedController {
    private Statement st;

    public MitgliedController() {
        try {
            st = DBManager.getInstance().getConnection().createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<Mitglied> getMitglieder() throws SQLException {
        ArrayList<Mitglied> results = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from mitglied");
        while (rs.next()) {

            try {
                results.add(new Mitglied(rs.getInt("mid"), rs.getString("nachname"), rs.getString("vorname"),
                        new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("gebDat")),
                        new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("seit"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public Mitglied getMitglied(int id) throws SQLException {
        Mitglied result = null;

        ResultSet rs = st.executeQuery("select * from mitglied where mid=" + id);
        while (rs.next()) {
            try {
                result = new Mitglied(rs.getInt("mid"), rs.getString("nachname"), rs.getString("vorname"),
                        new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("gebDat")),
                        new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("seit")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public void deleteMitglied(int id) throws SQLException {
        String statement = "delete from mitglied where mid=" + id;
        st.execute(statement);
    }

    public void addMitglied(Mitglied m) throws SQLException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String statement = "insert into mitglied(nachname,vorname,gebDat,seit) VALUES ('" + m.getNachname() + "','"
                + m.getVorname() + "','" + sd.format(m.getGebDat()) + "','" + sd.format(m.getSeit()) + "')";
        st.execute(statement);
    }

    public void setMitglied(Mitglied m) throws SQLException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String statement = "update mitglied set nachname='" + m.getNachname() + "',vorname='" + m.getVorname()
                + "',gebDat='" + sd.format(m.getGebDat()) + "',seit='" + sd.format(m.getSeit()) + "' WHERE mid="
                + m.getId();
        st.execute(statement);
    }

}
