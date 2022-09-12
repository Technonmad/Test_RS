package InsertFactory;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOImp;
import DTO.Employee;

import javax.swing.*;
import java.sql.SQLException;

public class InsertEmployee implements Insert{
    Employee employee;
    int id, bossId;
    String firstName, lastName;
    @Override
    public JFrame makeFrame(JScrollPane dataTable) {

        EmployeeDAO employeeDAO = new EmployeeDAOImp();

        JFrame frame = new JFrame("Добавить сотрудника");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(250, 250, 500, 200);
        ImageIcon img = new ImageIcon("src/main/java/Images/add.png");
        frame.setIconImage(img.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 200);
        panel.setBorder(BorderFactory.createEtchedBorder());

        JButton acceptButton = new JButton("Добавить");
        acceptButton.setBounds(180,100,120,30);

        JLabel id_label = new JLabel("id");
        id_label.setBounds(50, 50, 80, 20);
        JTextField idText = new JTextField();
        idText.setBounds(50, 70, 80, 20);

        JLabel boss_label = new JLabel("boss_id");
        boss_label.setBounds(150, 50, 80, 20);
        JTextField bossText = new JTextField();
        bossText.setBounds(150, 70, 80, 20);

        JLabel first_name_label = new JLabel("first_name");
        first_name_label.setBounds(250, 50, 80, 20);
        JTextField firstNameText = new JTextField();
        firstNameText.setBounds(250, 70, 80, 20);

        JLabel last_name_label = new JLabel("last_name");
        last_name_label.setBounds(350, 50, 80, 20);
        JTextField lastNameText = new JTextField();
        lastNameText.setBounds(350, 70, 80, 20);

        panel.add(acceptButton);
        panel.add(id_label);
        panel.add(boss_label);
        panel.add(first_name_label);
        panel.add(last_name_label);
        panel.add(idText);
        panel.add(bossText);
        panel.add(firstNameText);
        panel.add(lastNameText);

        frame.add(panel);
        frame.setVisible(true);

        acceptButton.addActionListener(e -> {

            dataTable.repaint();

            id = Integer.parseInt(idText.getText());
            bossId = Integer.parseInt(bossText.getText());
            firstName = firstNameText.getText();
            lastName = lastNameText.getText();

            employee = new Employee(id, bossId, firstName, lastName);
            try {
                employeeDAO.insert(employee);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        return null;
    }

}
