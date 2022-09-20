package UpdateFactory;

import DAO.*;
import DTO.Employee;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateEmployee implements Update {

    JViewport viewport;
    JTable current_tbl;
    Employee employee;
    int id, bossId, salary;
    String firstName, lastName, dep_name, position;

    @Override
    public JFrame makeFrame(JScrollPane dataTable) {

        EmployeeDAO employeeDAO = new EmployeeDAOImp();
        DepartmentDAO departmentDAO = new DepartmentDAOImp();
        PositionDAO positionDAO = new PositionDAOImp();
        String[] depNames;

        try {
            ArrayList<String> list = new ArrayList<>(departmentDAO.getDepartmentsName());
            depNames = list.toArray(new String[list.size()]);
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }

        JFrame frame = new JFrame("Изменить запись");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(250, 250, 500, 200);
        ImageIcon img = new ImageIcon("src/main/resources/Images/edit.png");
        frame.setIconImage(img.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 200);
        panel.setBorder(BorderFactory.createEtchedBorder());

        JButton acceptButton = new JButton("Применить");
        acceptButton.setBounds(180, 100, 120, 30);

        JLabel first_name_label = new JLabel("first_name");
        first_name_label.setBounds(30, 50, 80, 20);
        JTextField firstNameText = new JTextField();
        firstNameText.setBounds(30, 70, 80, 20);

        JLabel last_name_label = new JLabel("last_name");
        last_name_label.setBounds(130, 50, 80, 20);
        JTextField lastNameText = new JTextField();
        lastNameText.setBounds(130, 70, 80, 20);

        JLabel departmentLabel = new JLabel("department");
        departmentLabel.setBounds(230, 50, 80, 20);
        JComboBox departmentText = new JComboBox(depNames);
        departmentText.setBounds(230, 70, 100, 20);

        JLabel positionLabel = new JLabel("position");
        positionLabel.setBounds(350, 50, 80, 20);
        JComboBox positionText = new JComboBox();
        positionText.setBounds(350, 70, 100, 20);

        departmentText.addItemListener(e -> {
            positionText.removeAllItems();
            try {
                ArrayList<String> list = new ArrayList<>(departmentDAO.getPositions(String.valueOf(departmentText.getSelectedItem())));
                String[] posNames = list.toArray(new String[list.size()]);
                for (String item: posNames) {
                    positionText.addItem(item);
                }
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(first_name_label);
        panel.add(firstNameText);
        panel.add(last_name_label);
        panel.add(lastNameText);
        panel.add(departmentLabel);
        panel.add(departmentText);
        panel.add(positionLabel);
        panel.add(positionText);
        panel.add(acceptButton);

        frame.add(panel);
        frame.setVisible(true);

        viewport = dataTable.getViewport();
        current_tbl = (JTable) viewport.getView();

        id = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 0)));
        firstName = String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 2));
        lastName = String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 3));

        firstNameText.setText(firstName);
        lastNameText.setText(lastName);

        acceptButton.addActionListener(e -> {

            firstName = firstNameText.getText();
            lastName = lastNameText.getText();
            dep_name = String.valueOf(departmentText.getSelectedItem());
            position = String.valueOf(positionText.getSelectedItem());
            try {
                bossId = departmentDAO.getBossId(dep_name);
                salary = positionDAO.getSalary(position);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            employee = new Employee(id, bossId, firstName, lastName, dep_name, position, salary);
            try {
                employeeDAO.update(employee, id);
                JOptionPane.showMessageDialog(null, "Запись успешно изменена!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка!");
                throw new RuntimeException(ex);
            }

            dataTable.repaint();
        });

        return null;
    }
}
