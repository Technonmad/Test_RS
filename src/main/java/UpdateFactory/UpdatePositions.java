package UpdateFactory;

import DAO.PositionDAO;
import DAO.PositionDAOImp;
import DTO.Position;

import javax.swing.*;
import java.sql.SQLException;

public class UpdatePositions implements Update {

    JViewport viewport;
    JTable current_tbl;
    Position position;
    int id, salary, oldId;
    String positionName;

    @Override
    public JFrame makeFrame(JScrollPane dataTable) {

        PositionDAO positionDAO = new PositionDAOImp();

        JFrame frame = new JFrame("Изменить запись");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(250, 250, 400, 200);
        ImageIcon img = new ImageIcon("src/main/resources/Images/edit.png");
        frame.setIconImage(img.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 400, 200);
        panel.setBorder(BorderFactory.createEtchedBorder());

        JButton acceptButton = new JButton("Применить");
        acceptButton.setBounds(120, 100, 120, 30);

        JLabel positionNameLabel = new JLabel("position_name");
        positionNameLabel.setBounds(50, 50, 150, 20);
        JTextField positionNameText = new JTextField();
        positionNameText.setBounds(50, 70, 150, 20);

        JLabel salaryLabel = new JLabel("salary");
        salaryLabel.setBounds(210, 50, 80, 20);
        JTextField salaryText = new JTextField();
        salaryText.setBounds(210, 70, 100, 20);

        panel.add(acceptButton);
        panel.add(positionNameLabel);
        panel.add(salaryLabel);
        panel.add(positionNameText);
        panel.add(salaryText);

        frame.add(panel);
        frame.setVisible(true);

        viewport = dataTable.getViewport();
        current_tbl = (JTable) viewport.getView();

        oldId = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 0)));
        id = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 0)));
        positionName = String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 1));
        salary = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 2)));

        positionNameText.setText(String.valueOf(positionName));
        salaryText.setText(String.valueOf(salary));

        acceptButton.addActionListener(e -> {

            positionName = positionNameText.getText();
            salary = Integer.parseInt(salaryText.getText());

            position = new Position(id, positionName, salary);
            try {
                positionDAO.update(position, oldId);
                JOptionPane.showMessageDialog(null, "Запись успешно изменена!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка!");
                throw new RuntimeException(ex);
            }

        });

        return null;
    }
}
