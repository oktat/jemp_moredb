import java.util.ArrayList;

public interface DataAccess {
    public ArrayList<Employee> getEmployees();
    public void crateEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(int id);
}
