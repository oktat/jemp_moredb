import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mariadb implements iDatabase {

    public Connection connectDb() {
        Connection conn = null;
        try {
            conn = tryConnect();
        } catch (SQLException e) {
            System.err.println("Hiba! A kapcsolat sikerteln");
            System.err.println(e.getMessage());
        }
        return conn;
    }
    public Connection tryConnect() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/emp";
        Connection conn = null;
        String user = "emp";
        String pass = "titok";
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
    public void close(Connection conn) {
        try {
            tryClose(conn);
        } catch (SQLException e) {
            System.err.println("Hiba! A kapcsolat bezárása sikerteln");
            System.err.println(e.getMessage());
        }
    }
    public void tryClose(Connection conn) throws SQLException {
        conn.close();
    }
}






