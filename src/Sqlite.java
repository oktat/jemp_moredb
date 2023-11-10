import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Sqlite implements iDatabase {
    
    @Override
    public Connection connectDb() {
        Connection conn = null;
        try {
            conn = tryConnectDb();
        } catch (SQLException e) {
            System.err.println("Hiba! A kapcsolódás sikertelen!");
            System.err.println(e.getMessage());
        }
        return conn;
    }
    private Connection tryConnectDb() 
            throws SQLException {
        String url = "jdbc:sqlite:emp.sqlite";
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }

    @Override
    public void close(Connection conn) {
        try {
            tryClose(conn);
        } catch (SQLException e) {
            System.err.println("Hiba! A kapcsolat zárása sikertelen!");
            System.err.println(e.getMessage());
        }
    }
    private void tryClose(Connection conn) 
            throws SQLException {
        conn.close();
    }
    
}