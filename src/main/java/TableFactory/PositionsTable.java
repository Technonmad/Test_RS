package TableFactory;

import DAO.PositionDAO;
import DAO.PositionDAOImp;
import DTO.Position;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class PositionsTable implements Table{

    JTable table;
    PositionDAO positionDAO = new PositionDAOImp();
    @Override
    public JScrollPane makeTable() {
        String[] columnNames = {"id", "position_name", "salary"};
        DefaultTableModel model = new DefaultTableModel(0, columnNames.length);
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);

        try {
            for (Position pos : positionDAO.getAll()){
                int id = pos.getId();
                String position_name = pos.getPosition_name();
                int salary = pos.getSalary();
                String[] tmp = {String.valueOf(id), position_name, String.valueOf(salary)};
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
