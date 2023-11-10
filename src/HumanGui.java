import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HumanGui extends JFrame {
    JTable table;
    JScrollPane pane;
    DefaultTableModel model;

    public HumanGui() {        
        this.table = new JTable();
        this.pane = new JScrollPane(table);
        this.model = new DefaultTableModel();

        String[] fields = {"Az", "Név", "Település", "Fizetés"};
        DataService dt = new DataService(new Sqlite());
        ArrayList<Employee> empList = dt.getEmployees();

        table.setModel(model);
        model.setColumnIdentifiers(fields);

        for(Employee emp: empList) {
            Vector<String> row = new Vector<>();
            row.add(emp.name);
            row.add(emp.city);
            row.add(emp.salary.toString());
            model.addRow(row);
        }

        this.add(pane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setVisible(true);
    }
}
