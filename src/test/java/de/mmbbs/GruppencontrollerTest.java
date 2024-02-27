package de.mmbbs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.mmbbs.database.DBManager;
import de.mmbbs.database.GruppenController;
import de.mmbbs.model.Gruppe;
import de.mmbbs.model.Mitglied;

public class GruppencontrollerTest {
    private GruppenController controller;
    private Connection connection;
    private Statement statement;

     @BeforeEach
    void setUp() throws SQLException {
        // Initialisierung des Controllers und der Testdatenbank
        System.out.println("setUp");
        controller = new GruppenController();
        connection = DBManager.getInstance().getConnection();
        connection.setAutoCommit(false);
        statement = connection.createStatement();
    }
    
    @AfterEach
    void tearDown() throws Exception {
        System.out.println("tearDown");
        // Bereinigen Sie hier die Testdatenbank, um die Tests unabhängig zu machen
        connection.rollback();
    }

    @Test
    void testAddGruppe() throws SQLException {
        Gruppe newGruppe = new Gruppe(0, "Testgruppe"); // Angenommen, die ID wird automatisch generiert
        controller.addGruppe(newGruppe);
        
        ResultSet rs = statement.executeQuery("SELECT * FROM Gruppe where Gruppe.bezeichnung='Testgruppe';");
        assertTrue(rs.next());
    }

    @Test
    void testGetGruppen() throws SQLException {
        ArrayList<Gruppe> gruppen = controller.getGruppen();
        ResultSet rs = statement.executeQuery("SELECT * FROM Gruppe;");

        int found=0;
        int rows=0;        
        while (rs.next()) {
            for (Gruppe gruppe : gruppen) {
                if (gruppe.getBezeichnung().equals(rs.getString("Bezeichnung")) && gruppe.getId()==rs.getInt("gid")) {
                    found++;
                    break;
                }
            }            
            rows++;
        }
        assertEquals(found, rows);
    }

    @Test
    void testGetGruppe() throws SQLException {
        Gruppe g = controller.getGruppe(1);

        assertEquals("A-Jugend",g.getBezeichnung());
        assertEquals(1, g.getId());
    }

    @Test
    void testDeleteGruppe() throws SQLException {
        controller.deleteGruppe(2);
        ResultSet rs = statement.executeQuery("SELECT * FROM Gruppe where Gruppe.gid=2;");
        assertFalse(rs.next());
    }

    @Test
    void testSetGruppe() throws SQLException {
        Gruppe g = new Gruppe(1, "Neue Gruppe");
        controller.setGruppe(g);
        ResultSet rs = statement.executeQuery("SELECT * FROM Gruppe where Gruppe.gid=1;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt("gid"));
        assertEquals("Neue Gruppe", rs.getString("Bezeichnung"));
    }

    @Test
    void testAddMember() throws SQLException {
        Mitglied m = new Mitglied(1, null, null, null, null);
        Gruppe g = new Gruppe(1, null);
        controller.addMember(m, g);

        ResultSet rs = statement.executeQuery("SELECT * FROM ist_in where ist_in.gid=1 and ist_in.mid=1;");
        assertTrue(rs.next());
    }

    
}
