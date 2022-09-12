package UpdateFactory;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOImp;
import DTO.Employee;

import javax.swing.*;
import java.sql.SQLException;

public class UpdateEmployee implements Update {

    JViewport viewport;
    JTable current_tbl;
    Employee employee;
    int id, bossId, oldId;
    String firstName, lastName;

    @Override
    public JFrame makeFrame(JScrollPane dataTable) {

        EmployeeDAO employeeDAO = new EmployeeDAOImp();

        JFrame frame = new JFrame("Изменить запись");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(250, 250, 500, 200);
        ImageIcon img = new ImageIcon("src/main/java/Images/edit.png");
        frame.setIconImage(img.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 200);
        panel.setBorder(BorderFactory.createEtchedBorder());

        JButton acceptButton = new JButton("Применить");
        acceptButton.setBounds(180, 100, 120, 30);

        JLabel oldIdLabel = new JLabel("Текущий id");
        oldIdLabel.setBounds(50, 30, 100, 20);
        JTextField oldIdText = new JTextField();
        oldIdText.setBounds(50, 10, 30, 20);

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
        panel.add(oldIdLabel);
        panel.add(oldIdText);
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

        viewport = dataTable.getViewport();
        current_tbl = (JTable) viewport.getView();

        oldId = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 0)));
        id = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 0)));
        bossId = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 1)));
        firstName = String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 2));
        lastName = String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 3));

        oldIdText.setText(String.valueOf(oldId));
        idText.setText(String.valueOf(id));
        bossText.setText(String.valueOf(bossId));
        firstNameText.setText(firstName);
        lastNameText.setText(lastName);

        acceptButton.addActionListener(e -> {

            dataTable.repaint();

            id = Integer.parseInt(idText.getText());
            bossId = Integer.parseInt(bossText.getText());
            firstName = firstNameText.getText();
            lastName = lastNameText.getText();

            employee = new Employee(id, bossId, firstName, lastName);
            try {
                employeeDAO.update(employee, oldId);
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 335544665) ;
                {
                    JOptionPane.showMessageDialog(null, "Запись с таким id уже существует!");
                }
                throw new RuntimeException(ex);
            }
        });

        return null;
    }
}
