package InsertFactory;

import DAO.*;
import DTO.Department;
import DTO.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertEmployee implements Insert{
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



        JFrame frame = new JFrame("Добавить сотрудника");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(250, 250, 500, 200);
        ImageIcon img = new ImageIcon("src/main/resources/Images/add.png");
        frame.setIconImage(img.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 200);
        panel.setBorder(BorderFactory.createEtchedBorder());

        JButton acceptButton = new JButton("Добавить");
        acceptButton.setBounds(180,100,120,30);


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

        panel.add(acceptButton);
        panel.add(positionLabel);
        panel.add(departmentLabel);
        panel.add(first_name_label);
        panel.add(last_name_label);
        panel.add(positionText);
        panel.add(departmentText);
        panel.add(firstNameText);
        panel.add(lastNameText);

        frame.add(panel);
        frame.setVisible(true);

        acceptButton.addActionListener(e -> {

            dataTable.repaint();

            firstName = firstNameText.getText();
            lastName = lastNameText.getText();
            dep_name = String.valueOf(departmentText.getSelectedItem());
            try {
                id = employeeDAO.getMaxId() + 1;
                bossId = departmentDAO.getBossId(String.valueOf(departmentText.getSelectedItem()));
                position = String.valueOf(positionText.getSelectedItem());
                salary = positionDAO.getSalary(String.valueOf(positionText.getSelectedItem()));
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (position.contains("Главный") || position.contains("Начальник")){
                JOptionPane.showMessageDialog(null, "Вы не можете добавлять руководителей!");
            }
            else {
                employee = new Employee(id, bossId, firstName, lastName, dep_name, position, salary);

                try {
                    employeeDAO.insert(employee);
                    JOptionPane.showMessageDialog(null, "Запись успешно добавлена!");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка!");
                    throw new RuntimeException(ex);
                }
            }


        });

        return null;
    }

}
