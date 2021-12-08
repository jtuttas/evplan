package de.mmbbs.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.mmbbs.model.Gruppe;
import de.mmbbs.model.Mitglied;

public class MemberController {
    private Statement st;

    public MemberController() {
        try {
            st = DBManager.getInstance().getConnection().createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<Gruppe> getGroupsOf(Mitglied m) throws SQLException {
        ArrayList<Gruppe> results = new ArrayList<>();
        ResultSet rs = st.executeQuery(
                "select gruppe.gid,bezeichnung from mitglied inner join ist_in on mitglied.mid=ist_in.mid inner join gruppe on ist_in.gid=gruppe.gid where mitglied.mid="
                        + m.getId());
        while (rs.next()) {
            results.add(new Gruppe(rs.getInt("gid"), rs.getString("bezeichnung")));
        }
        return results;
    }

    public ArrayList<Mitglied> getMemberOf(Gruppe g) throws SQLException {
        ArrayList<Mitglied> results = new ArrayList<>();
        ResultSet rs = st.executeQuery(
                "select mitglied.mid,nachname,vorname,gebDat,seit from mitglied inner join ist_in on mitglied.mid=ist_in.mid inner join gruppe on ist_in.gid=gruppe.gid where mitglied.mid="
                        + g.getId());
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

    public void addMember(Mitglied m,Gruppe g) throws SQLException {
        String sql="insert into ist_in(mid,gid) values ("+m.getId()+","+g.getId()+")";
        st.execute(sql);
    }

    public void removeMember(Mitglied m,Gruppe g) throws SQLException {
        String sql="delete from ist_in where mid="+m.getId()+" and gid="+g.getId();
        st.execute(sql);
    }

}
