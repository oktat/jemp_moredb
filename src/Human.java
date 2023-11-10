import java.util.ArrayList;

public class Human {
    DataService ds;
    public Human() {
        ds = new DataService(new Sqlite());
        // add();
        // getAll();
        // update();
        delete();
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
        System.out.println("Hozzáadás VÉGE");
    }
    public void update() {
        Employee emp = new Employee(2, "Csendes Mária", "Szolnok", 392.0);
        ds.updateEmployee(emp);
        System.out.println("Frissítés VÉGE");
    }
    public void delete() {
        ds.deleteEmployee(4);
        System.out.println("Törlés VÉGE");
    }
}
