import java.sql.Connection;

public interface Database {
    public Connection connectDb();
	public void close(Connection conn);
}
