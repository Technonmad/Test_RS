package UpdateFactory;

import DAO.DepartmentDAO;
import DAO.DepartmentDAOImp;
import DTO.Department;
import GUI.MainFrame;


import javax.swing.*;
import java.sql.SQLException;

public class UpdateDepartment implements Update {
    JViewport viewport;
    JTable current_tbl;
    Department department;
    int id, oldId;
    String depName, email, phone;

    @Override
    public JFrame makeFrame(JScrollPane dataTable) {

        DepartmentDAO departmentDAO = new DepartmentDAOImp();

        JFrame frame = new JFrame("Изменить запись");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(250, 250, 450, 200);
        ImageIcon img = new ImageIcon("src/main/resources/Images/edit.png");
        frame.setIconImage(img.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 450, 200);
        panel.setBorder(BorderFactory.createEtchedBorder());

        JButton acceptButton = new JButton("Применить");
        acceptButton.setBounds(150, 100, 120, 30);

        JLabel depNamelabel = new JLabel("dep_name");
        depNamelabel.setBounds(20, 50, 120, 20);
        JTextField depNameText = new JTextField();
        depNameText.setBounds(20, 70, 120, 20);

        JLabel emailLabel = new JLabel("email");
        emailLabel.setBounds(150, 50, 120, 20);
        JTextField emailText = new JTextField();
        emailText.setBounds(150, 70, 120, 20);

        JLabel phoneLabel = new JLabel("phone");
        phoneLabel.setBounds(280, 50, 120, 20);
        JTextField phoneText = new JTextField();
        phoneText.setBounds(280, 70, 120, 20);

        panel.add(acceptButton);
        panel.add(depNamelabel);
        panel.add(emailLabel);
        panel.add(phoneLabel);
        panel.add(depNameText);
        panel.add(emailText);
        panel.add(phoneText);

        frame.add(panel);
        frame.setVisible(true);

        viewport = dataTable.getViewport();
        current_tbl = (JTable) viewport.getView();

        oldId = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 0)));
        id = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 0)));
        depName = String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 1));
        email = String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 2));
        phone = String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 3));

        depNameText.setText(String.valueOf(depName));
        emailText.setText(email);
        phoneText.setText(phone);

        acceptButton.addActionListener(e -> {

            depName = depNameText.getText();
            email = emailText.getText();
            phone = phoneText.getText();

            department = new Department(id, depName, email, phone);
            try {
                departmentDAO.update(department, oldId);
                JOptionPane.showMessageDialog(null, "Запись успешно изменена!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка!");
                throw new RuntimeException(ex);
            }
        });

        return null;
    }
}
