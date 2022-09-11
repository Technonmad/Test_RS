import DAO.*;
import DTO.Department;
import DTO.Employee;
import DTO.Position;
import TableFactory.EmployeesFactory;
import TableFactory.DepartmentsFactory;
import TableFactory.PositionsFactory;
import TableFactory.TableFactory;
import TableFactory.Table;

import javax.swing.*;
import java.sql.SQLException;


public class GUI extends JFrame {
    private JButton selectButton = new JButton("Показать");
    private JButton updateButton = new JButton("Изменить");
    private JButton insertButton = new JButton("Добавить");
    private JButton deleteButton = new JButton("Удалить");
    private String[] tablesList = {"Должности", "Отделы", "Сотрудники"};
    private JComboBox tablesComboBox = new JComboBox(tablesList);
    private TableFactory tableFactory = null;
    private Table table = null;
    private JScrollPane scrollPane = null;

    public GUI() {
        super("Test_RS");
        this.setBounds(250, 250, 600, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 600, 200);
        mainPanel.setBorder(BorderFactory.createEtchedBorder());

        tablesComboBox.setBounds(10, 10, 100, 20);
        selectButton.setBounds(10, 40, 100, 20);
        insertButton.setBounds(10, 70, 100, 20);
        updateButton.setBounds(10, 100, 100, 20);
        deleteButton.setBounds(10, 130, 100, 20);

        updateButton.setEnabled(false);
        insertButton.setEnabled(false);
        deleteButton.setEnabled(false);

        mainPanel.add(tablesComboBox);
        mainPanel.add(selectButton);
        mainPanel.add(updateButton);
        mainPanel.add(insertButton);
        mainPanel.add(deleteButton);


        this.add(mainPanel);

        selectButton.addActionListener(e -> {

            makeTable(mainPanel);

            updateButton.setEnabled(true);
            insertButton.setEnabled(true);
            deleteButton.setEnabled(true);
        });

        deleteButton.addActionListener(e -> {

            removeRow(mainPanel);
            makeTable(mainPanel);
            
        });

    }

    public void makeTable(JPanel mainPanel) {

        switch (String.valueOf(tablesComboBox.getSelectedItem())) {
            case ("Сотрудники"):
                tableFactory = new EmployeesFactory();
                table = tableFactory.createTable();
                if (scrollPane != null) {
                    mainPanel.remove(scrollPane);
                }
                scrollPane = table.makeTable();
                mainPanel.add(scrollPane);
                break;
            case ("Отделы"):
                tableFactory = new DepartmentsFactory();
                table = tableFactory.createTable();
                if (scrollPane != null) {
                    mainPanel.remove(scrollPane);
                }
                scrollPane = table.makeTable();
                mainPanel.add(scrollPane);
                break;
            case ("Должности"):
                tableFactory = new PositionsFactory();
                table = tableFactory.createTable();
                if (scrollPane != null) {
                    mainPanel.remove(scrollPane);
                }
                scrollPane = table.makeTable();
                mainPanel.add(scrollPane);
                break;
        }
    }

    public void removeRow(JPanel mainPanel) {

        JViewport viewport;
        JTable current_tbl;
        int id;

        switch (String.valueOf(tablesComboBox.getSelectedItem())) {
            case ("Сотрудники"):
                EmployeeDAO employeeDAO = new EmployeeDAOImp();
                viewport = scrollPane.getViewport();
                current_tbl = (JTable) viewport.getView();
                id = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 0)));
                try {
                    Employee employee = employeeDAO.get(id);
                    employeeDAO.delete(employee);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case ("Отделы"):
                DepartmentDAO departmentDAO = new DepartmentDAOImp();
                viewport = scrollPane.getViewport();
                current_tbl = (JTable) viewport.getView();
                id = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 0)));
                try {
                    Department department = departmentDAO.get(id);
                    departmentDAO.delete(department);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case ("Должности"):
                PositionDAO positionDAO = new PositionDAOImp();
                viewport = scrollPane.getViewport();
                current_tbl = (JTable) viewport.getView();
                id = Integer.parseInt(String.valueOf(current_tbl.getValueAt(current_tbl.getSelectedRow(), 0)));
                try {
                    Position position = positionDAO.get(id);
                    positionDAO.delete(position);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
    }
}
