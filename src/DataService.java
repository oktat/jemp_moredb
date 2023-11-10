import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class DataService implements iDataService {
    iDatabase database;
    public DataService(iDatabase database) {
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
            System.err.println("Hiba! A dolgozók létrehozása sikertelen!");
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
        database.close(conn);
    }

    @Override
    public void updateEmployee(Employee employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEmployee'");
    }

    @Override
    public void deleteEmployee(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployee'");
    }
    
}