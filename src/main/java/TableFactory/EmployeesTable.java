package TableFactory;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOImp;
import DTO.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class EmployeesTable implements Table {

    JTable table;
    EmployeeDAO employeeDAO = new EmployeeDAOImp();

    @Override
    public JScrollPane makeTable() {
        String[] columnNames = {"id", "boss_id", "first_name", "last_name", "dep_name", "position", "salary"};
        DefaultTableModel model = new DefaultTableModel(0, columnNames.length);
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);

        try {
            for (Employee emp : employeeDAO.getAll()){
                int id = emp.getId();
                int boss_id = emp.getBoss_id();
                String first_name = emp.getFirst_name();
                String last_name = emp.getLast_name();
                String dep_name = emp.getDep_name();
                String position = emp.getPosition();
                int salary = emp.getSalary();
                String[] tmp = {
                        String.valueOf(id),
                        String.valueOf(boss_id),
                        first_name,
                        last_name,
                        dep_name,
                        position,
                        String.valueOf(salary)
                };
                model.addRow(tmp);
            }
            table.setModel(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(120,10,550,180);
            return scrollPane;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
