import java.sql.Connection;

public class App {
    public static void main(String[] args) throws Exception {
        // Mariadb mariadb = new Mariadb();
        // Connection conn = mariadb.connectDb();
        // conn.close();
        Sqlite sqlite = new Sqlite();
        Connection conn = sqlite.connectDb();
        conn.close();
        System.out.println("OK");
        
    }
}
