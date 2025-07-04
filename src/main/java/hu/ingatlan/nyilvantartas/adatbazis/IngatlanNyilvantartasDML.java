package hu.ingatlan.nyilvantartas.adatbazis;

import hu.ingatlan.kereso.entity.IngatlanAdat;
import hu.ingatlan.kereso.entity.IngatlanMeglevoAdatok;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Böröczky Csaba
 */
public class IngatlanNyilvantartasDML {

    public static void ingatlanAdatFeltoltes() throws SQLException {
        final Connection connection = SQLKapcsolat.getConnection();
        List<IngatlanAdat> IngatlanAdatok = IngatlanMeglevoAdatok.ingatlanAdatokLetrehozasa();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO " + IngatlanNyilvantartasDDL.ingatlanNyilvantartasTablaNev + "(TULAJDONOS, HELYRAJZISZAM, ALAPTERULET, BECSULTERTEK) VALUES(?, ?, ?, ?)");
        for (IngatlanAdat ingatlanAdat : IngatlanAdatok) {
            ps.setString(1, ingatlanAdat.getTulajdonos());
            ps.setString(2, ingatlanAdat.getHelyrajziSzam());
            ps.setLong(3, ingatlanAdat.getAlapterulet());
            ps.setLong(4, ingatlanAdat.getBecsultErtek());
            ps.executeUpdate();
        }
    }

    public static List<IngatlanAdat> ingatlanAdatLekerdezesSQL() throws SQLException {
        SQLKapcsolat.csatlakozas();
        final Connection connectionLekerdezes = SQLKapcsolat.getConnection();

        List<IngatlanAdat> ingatlanAdatok = new ArrayList<>();

        PreparedStatement ps = connectionLekerdezes.prepareStatement("SELECT * FROM " + IngatlanNyilvantartasDDL.ingatlanNyilvantartasTablaNev + "");
        ResultSet res = ps.executeQuery();
        createIngatlanAdatok(res, ingatlanAdatok);

        return ingatlanAdatok;
    }

    public static List<IngatlanAdat> ingatlanAdatHelyrajziSzamAlapjánLekerdezesSQL(final String helyrajziSzam) throws IllegalArgumentException, SQLException {
        SQLKapcsolat.csatlakozas();
        final Connection connectionLekerdezes = SQLKapcsolat.getConnection();

        if (helyrajziSzam == null || helyrajziSzam.equals("")) {
            return ingatlanAdatLekerdezesSQL();
        }
        PreparedStatement ps = connectionLekerdezes.prepareStatement("SELECT * FROM " + IngatlanNyilvantartasDDL.ingatlanNyilvantartasTablaNev + " WHERE HELYRAJZISZAM = ?");
        ps.setString(1, helyrajziSzam);

        ResultSet res = ps.executeQuery();
        List<IngatlanAdat> ingatlanAdatok = new ArrayList<>();
        createIngatlanAdatok(res, ingatlanAdatok);

        if (ingatlanAdatok.isEmpty()) {
            throw new IllegalArgumentException("nincs ilyen ingatlan");
        }

        return ingatlanAdatok;
    }

    public static void torlesIngatlanAdatHelyrajziSzamAlapjánLekerdezesSQL(final String helyrajziSzam) throws IllegalArgumentException, SQLException {
        SQLKapcsolat.csatlakozas();
        final Connection connectionLekerdezes = SQLKapcsolat.getConnection();

        List<IngatlanAdat> ingatlanAdatok = ingatlanAdatHelyrajziSzamAlapjánLekerdezesSQL(helyrajziSzam);
        if (ingatlanAdatok == null || ingatlanAdatok.isEmpty()) {
            throw new IllegalArgumentException("nincs ilyen ingatlan");
        }

        PreparedStatement ps = connectionLekerdezes.prepareStatement("DELETE FROM " + IngatlanNyilvantartasDDL.ingatlanNyilvantartasTablaNev + " WHERE HELYRAJZISZAM = ?");
        ps.setString(1, helyrajziSzam);
        ps.executeUpdate();
    }

    public static void bescsultErtekIngatlanAdatHelyrajziSzamAlapjánLekerdezesSQL(final IngatlanAdat ingatlan, String becsultErtekString) throws IllegalArgumentException, SQLException {
        SQLKapcsolat.csatlakozas();
        final Connection connectionLekerdezes = SQLKapcsolat.getConnection();

        final String helyrajziSzam = ingatlan.getHelyrajziSzam();
          if (helyrajziSzam == null || helyrajziSzam.equals("")) {
            throw new IllegalArgumentException("nincs ilyen ingatlan");
        }
        List<IngatlanAdat> ingatlanAdatok = ingatlanAdatHelyrajziSzamAlapjánLekerdezesSQL(helyrajziSzam);
        if (ingatlanAdatok == null || ingatlanAdatok.isEmpty()) {
            throw new IllegalArgumentException("nincs ilyen ingatlan");
        }
        Long becsultErtek = null;
        try {
            becsultErtek = Long.valueOf(becsultErtekString);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Becsült érték csak szám lehet!");
        }
        PreparedStatement ps = connectionLekerdezes.prepareStatement("UPDATE " + IngatlanNyilvantartasDDL.ingatlanNyilvantartasTablaNev + " SET BECSULTERTEK = ? WHERE HELYRAJZISZAM = ?");
        ps.setLong(1, becsultErtek);
        ps.setString(2, helyrajziSzam);
        ps.executeUpdate();
    }

    private static void createIngatlanAdatok(ResultSet res, List<IngatlanAdat> ingatlanAdatok) throws SQLException {
        while (res.next()) {
            ingatlanAdatok.add(new IngatlanAdat(res.getString("TULAJDONOS"), res.getString("HELYRAJZISZAM"), res.getLong("ALAPTERULET"), res.getLong("BECSULTERTEK")));
        }
    }

}
