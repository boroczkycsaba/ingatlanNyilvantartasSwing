package hu.ingatlan.nyilvantartas.adatbazis;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Böröczky Csaba
 */
public class IngatlanNyilvantartasDDL {
  
    final Connection connection;

    final static String ingatlanNyilvantartasTablaNev = "ingatlan_db";
    private String ingatlanNyilvantartasTablaNevTablaLetrehozoSQL = null;

    public IngatlanNyilvantartasDDL(final Connection connection) {
        this.connection = connection;
        ingatlanNyilvantartasTablaNevTablaLetrehozoSQL = "create table " + ingatlanNyilvantartasTablaNev + " (  IngatlanNyilvantartasID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)"
                + ", TULAJDONOS VARCHAR(200) NOT NULL, HELYRAJZISZAM VARCHAR(200) NOT NULL "
                + ", ALAPTERULET INTEGER NOT NULL, BECSULTERTEK INTEGER NOT NULL, PRIMARY KEY (IngatlanNyilvantartasID))";
    }

    public void tablaLetrehozas() throws SQLException {
        tablaLetrehozasaSQLAdatbazisban(ingatlanNyilvantartasTablaNevTablaLetrehozoSQL);
    }

    private void tablaLetrehozasaSQLAdatbazisban(final String tablaLetrehozoSQL) throws SQLException {
        System.out.println(tablaLetrehozoSQL);
        Statement stmt = connection.createStatement();       
        stmt.executeUpdate(tablaLetrehozoSQL);
    }
}
