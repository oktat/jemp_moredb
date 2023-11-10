import java.util.ArrayList;

public class Human {
    DataService ds;
    public Human() {
        ds = new DataService(new Sqlite());
        // add();
        getAll();
    }
    public void getAll() {
        ArrayList<Employee> empList = new ArrayList<>();
        empList = ds.getEmployees();
        for(Employee emp: empList) {
            System.out.printf(
                "| %20s | %15s |\n",
                emp.name, 
                emp.city
                );
        }
    }
    public void add() {
        Employee emp = new Employee(0, "Erős István", "Szeged", 389.0);
        ds.crateEmployee(emp);
        System.out.println("OK");
    }    
}
