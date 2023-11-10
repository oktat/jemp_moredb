import java.sql.Connection;

public interface iDatabase {
    public Connection connectDb();
	public void close(Connection conn);
}
