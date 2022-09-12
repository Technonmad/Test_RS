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
        oldIdLabel.setBounds(20, 30, 100, 20);
        JTextField oldIdText = new JTextField();
        oldIdText.setBounds(20, 10, 30, 20);

        JLabel id_label = new JLabel("id");
        id_label.setBounds(20, 50, 30, 20);
        JTextField idText = new JTextField();
        idText.setBounds(20, 70, 30, 20);

        JLabel depNamelabel = new JLabel("dep_name");
        depNamelabel.setBounds(70, 50, 120, 20);
        JTextField depNameText = new JTextField();
        depNameText.setBounds(70, 70, 120, 20);

        JLabel emailLabel = new JLabel("email");
        emailLabel.setBounds(210, 50, 120, 20);
        JTextField emailText = new JTextField();
        emailText.setBounds(210, 70, 120, 20);

        JLabel phoneLabel = new JLabel("phone");
        phoneLabel.setBounds(350, 50, 120, 20);
        JTextField phoneText = new JTextField();
        phoneText.setBounds(350, 70, 120, 20);

        panel.add(acceptButton);
        panel.add(oldIdLabel);
        panel.add(oldIdText);
        panel.add(id_label);
        panel.add(depNamelabel);
        panel.add(emailLabel);
        panel.add(phoneLabel);
        panel.add(idText);
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

        oldIdText.setText(String.valueOf(oldId));
        idText.setText(String.valueOf(id));
        depNameText.setText(String.valueOf(depName));
        emailText.setText(email);
        phoneText.setText(phone);

        acceptButton.addActionListener(e -> {

            id = Integer.parseInt(idText.getText());
            depName = depNameText.getText();
            email = emailText.getText();
            phone = phoneText.getText();

            department = new Department(id, depName, email, phone);
            try {
                departmentDAO.update(department, oldId);
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
