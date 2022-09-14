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
        frame.setBounds(250, 250, 500, 200);
        ImageIcon img = new ImageIcon("src/main/resources/Images/edit.png");
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

        JLabel positionNameLabel = new JLabel("position_name");
        positionNameLabel.setBounds(150, 50, 150, 20);
        JTextField positionNameText = new JTextField();
        positionNameText.setBounds(150, 70, 150, 20);

        JLabel salaryLabel = new JLabel("salary");
        salaryLabel.setBounds(320, 50, 80, 20);
        JTextField salaryText = new JTextField();
        salaryText.setBounds(320, 70, 100, 20);

        panel.add(acceptButton);
        panel.add(oldIdLabel);
        panel.add(oldIdText);
        panel.add(id_label);
        panel.add(positionNameLabel);
        panel.add(salaryLabel);
        panel.add(idText);
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

        oldIdText.setText(String.valueOf(oldId));
        idText.setText(String.valueOf(id));
        positionNameText.setText(String.valueOf(positionName));
        salaryText.setText(String.valueOf(salary));

        acceptButton.addActionListener(e -> {

            id = Integer.parseInt(idText.getText());
            positionName = positionNameText.getText();
            salary = Integer.parseInt(salaryText.getText());

            position = new Position(id, positionName, salary);
            try {
                positionDAO.update(position, oldId);
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
