package de.mmbbs.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.mmbbs.model.Gruppe;
import de.mmbbs.model.Mitglied;

public class GruppenController {
    private Statement st;

    public GruppenController() {
        try {
            st = DBManager.getInstance().getConnection().createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<Gruppe> getGruppen() throws SQLException {
        ArrayList<Gruppe> results = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from gruppe");
        while (rs.next()) {
            results.add(new Gruppe(rs.getInt("gid"), rs.getString("bezeichnung")));
        }
        return results;
    }
    
    public ArrayList<Gruppe> getGruppen(Mitglied m) throws SQLException {
        ArrayList<Gruppe> results = new ArrayList<>();
        ResultSet rs = st.executeQuery("select gruppe.gid,gruppe.bezeichnung from gruppe join ist_in on gruppe.gid=ist_in.gid where ist_in.mid="+m.getId());
        while (rs.next()) {
            results.add(new Gruppe(rs.getInt("gid"), rs.getString("bezeichnung")));
        }
        return results;
    }

    public Gruppe getGruppe(int id) throws SQLException {
        Gruppe result = null;

        ResultSet rs = st.executeQuery("select * from gruppe where gid=" + id);
        while (rs.next()) {
            result = new Gruppe(rs.getInt("gid"), rs.getString("bezeichnung"));
        }

        return result;
    }

    public void deleteGruppe(int id) throws SQLException {
        String statement = "delete from gruppe where gid=" + id;
        st.execute(statement);
    }

    public void addGruppe(Gruppe g) throws SQLException {
        String statement = "insert into gruppe(bezeichnung) VALUES ('" + g.getBezeichnung() + "')";
        st.execute(statement);
    }

    public void setGruppe(Gruppe g) throws SQLException {
        String statement = "update gruppe set bezeichnung='" + g.getBezeichnung() + "' WHERE gid="
                + g.getId();
        st.execute(statement);
    }

    public void addMember(Mitglied m, Gruppe g) throws SQLException {
        String statement = "insert into ist_in(mid,gid) VALUES (" + m.getId()+","+g.getId() + ")";
        st.execute(statement);
    }

}
