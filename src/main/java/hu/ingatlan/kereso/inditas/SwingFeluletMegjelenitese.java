package hu.ingatlan.kereso.inditas;

import hu.ingatlan.nyilvantartas.adatbazis.IngatlanNyilvantartasDDL;
import hu.ingatlan.nyilvantartas.adatbazis.IngatlanNyilvantartasDML;
import hu.ingatlan.nyilvantartas.adatbazis.SQLKapcsolat;
import hu.ingatlan.nyilvantartas.swing.IngatlanKeresoKepernyo;
import java.sql.SQLException;

/**
 *
 * @author Böröczky Csaba
 */
public class SwingFeluletMegjelenitese {
    
    public static void main(String[] args) {
        try {
            SQLKapcsolat.csatlakozas();
            IngatlanNyilvantartasDDL ingatlanNyilvantartasDDL = new IngatlanNyilvantartasDDL(SQLKapcsolat.getConnection());
            ingatlanNyilvantartasDDL.tablaLetrehozas();
            IngatlanNyilvantartasDML.ingatlanAdatFeltoltes();
            java.awt.EventQueue.invokeLater(() -> new IngatlanKeresoKepernyo().setVisible(true));
        } catch (SQLException e) {
            System.err.println("SQL hiba történt! " + e.getMessage());
        } finally {
            SQLKapcsolat.kapcsolatBezarasa();
        }
    }
}
