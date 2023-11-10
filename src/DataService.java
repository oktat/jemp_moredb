import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class DataService implements DataAccess {
    Database database;
    public DataService(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> empList = null;
        try {
            empList = tryGetEmployees();
        } catch (Exception e) {
            System.err.println("Hiba! a dolgozók lekérdezése sikertelen!");
            System.err.println(e.getMessage());
        }
        return empList;
    }
    private ArrayList<Employee> tryGetEmployees() 
            throws SQLException {        
        String sql = "select * from employee";
        Connection conn = database.connectDb();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return convertSetToList(rs);
    }    
    private ArrayList<Employee> convertSetToList(ResultSet rs) 
            throws SQLException {
        ArrayList<Employee> empList = new ArrayList<>();
        while(rs.next()) {
            Employee emp = new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("city"),
                rs.getDouble("salary")
            );
            empList.add(emp);
        }
        return empList;
    }

    @Override
    public void crateEmployee(Employee emp) {
        try {
            tryCrateEmployee(emp);
        } catch (SQLException e) {
            System.err.println("Hiba! A dolgozó létrehozása sikertelen!");
            System.err.println(e.getMessage());
        }
    }
    private void tryCrateEmployee(Employee emp) 
            throws SQLException {
        Connection conn = database.connectDb();
        String sql = "insert into employee " +
        "(name, city, salary) values " +
        "(?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, emp.name);
        pstmt.setString(2, emp.city);
        pstmt.setDouble(3, emp.salary);
        pstmt.execute();
        System.out.println(pstmt.getGeneratedKeys().getInt(1));
        database.close(conn);
        
    }

    @Override
    public void updateEmployee(Employee emp) {
        try {
            tryUpdateEmployee(emp);
        } catch (SQLException e) {
            System.err.println("Hiba! A frissítés sikertelen!");
            System.err.println(e.getMessage());
        }
    }
    public void tryUpdateEmployee(Employee emp)
            throws SQLException {
        Connection conn = database.connectDb();
        String sql = "update employee set " +
        "name=?, city=?, salary=?" +
        "where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, emp.name);
        pstmt.setString(2, emp.city);
        pstmt.setDouble(3, emp.salary);
        pstmt.setDouble(4, emp.id);
        pstmt.execute();
        System.out.println(pstmt.getUpdateCount());
        database.close(conn);
    }

    @Override
    public void deleteEmployee(int id) {
        try {
            tryDeleteEmployee(id);
        } catch (SQLException e) {
            System.err.println("Hiba! A törlés sikertelen!");
            System.err.println(e.getMessage());
        }
    }
    public void tryDeleteEmployee(int id) 
            throws SQLException {
        Connection conn = database.connectDb();
        String sql = "delete from employee " +
        "where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDouble(1, id);
        pstmt.executeUpdate();
        System.out.println(pstmt.getUpdateCount());
        database.close(conn);
    }
    
}