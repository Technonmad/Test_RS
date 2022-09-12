package InsertFactory;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOImp;
import DAO.PositionDAO;
import DAO.PositionDAOImp;
import DTO.Employee;
import DTO.Position;

import javax.swing.*;
import java.sql.SQLException;

public class InsertPosition implements Insert{
    Position position;
    int id, salary;
    String positionName;
    @Override
    public JFrame makeFrame(JScrollPane dataTable) {

        PositionDAO positionDAO = new PositionDAOImp();

        JFrame frame = new JFrame("Добавить должность");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(250, 250, 500, 200);
        ImageIcon img = new ImageIcon("src/main/java/Images/add.png");
        frame.setIconImage(img.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 200);
        panel.setBorder(BorderFactory.createEtchedBorder());

        JButton acceptButton = new JButton("Применить");
        acceptButton.setBounds(180,100,120,30);

        JLabel id_label = new JLabel("id");
        id_label.setBounds(50, 50, 80, 20);
        JTextField idText = new JTextField();
        idText.setBounds(50, 70, 80, 20);

        JLabel positionNameLabel = new JLabel("position_name");
        positionNameLabel.setBounds(150, 50, 150, 20);
        JTextField positionNameText = new JTextField();
        positionNameText.setBounds(150, 70, 150, 20);

        JLabel salaryLabel = new JLabel("salary");
        salaryLabel.setBounds(320, 50, 80, 20);
        JTextField salaryText = new JTextField();
        salaryText.setBounds(320, 70, 100, 20);

        panel.add(acceptButton);
        panel.add(id_label);
        panel.add(positionNameLabel);
        panel.add(salaryLabel);
        panel.add(idText);
        panel.add(positionNameText);
        panel.add(salaryText);

        frame.add(panel);
        frame.setVisible(true);

        acceptButton.addActionListener(e -> {

            id = Integer.parseInt(idText.getText());
            positionName = positionNameText.getText();
            salary = Integer.parseInt(salaryText.getText());

            position = new Position(id, positionName, salary);
            try {
                positionDAO.insert(position);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        return null;
    }

}
