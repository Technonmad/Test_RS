package TableFactory;

import DAO.DepartmentDAO;
import DAO.DepartmentDAOImp;
import DTO.Department;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class DepartmentsTable implements Table{
    JTable table = null;
    DepartmentDAO departmentDAO = new DepartmentDAOImp();
    @Override
    public JScrollPane makeTable() {
        String[] columnNames = {"id", "dep_name", "email", "phone"};
        DefaultTableModel model = new DefaultTableModel(0, columnNames.length);
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);

        try {
            for (Department dep : departmentDAO.getAll()){
                int id = dep.getId();
                String dep_name = dep.getDep_name();
                String email = dep.getEmail();
                String phone = dep.getPhone();
                String[] tmp = {String.valueOf(id), dep_name, email, phone};
                model.addRow(tmp);
            }
            table.setModel(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(120,10,450,180);
            return scrollPane;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
