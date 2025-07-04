package hu.ingatlan.nyilvantartas.adatbazis;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Böröczky Csaba
 */
public class SQLKapcsolat {

    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_NAME = "celbalovesVerseny-adatbazis";
    private static final String USER = "root";
    private static final String PASS = "$%^1Rt!&*d!21";
    private static final String DB_URL = "jdbc:derby:memory:"
            + DB_NAME + ";create=true;territory=hu_HU;collation=TERRITORY_BASED:PRIMARY";
    private static Connection connection;

    public SQLKapcsolat() {

    }

    public static void csatlakozas() {
        try {

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, "", "");
            System.out.println("Csatlakozás sikerült");
        }catch(Exception e) {

            System.err.println("A csatlakozás sikertelen, a program bezárul! "+e.getMessage());
            System.exit(1);

        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void kapcsolatBezarasa()  {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Csatlakozás bezárása sikerült");
            } catch (SQLException e) {
                System.err.println("A csatlakozás bezárása sikertelen! "+e.getMessage());
            }
        }
    }
}
