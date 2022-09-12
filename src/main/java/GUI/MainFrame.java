package GUI;

import DAO.*;
import DTO.*;
import InsertFactory.InsertFactory;
import UpdateFactory.UpdateFactory;
import TableFactory.*;
import UpdateFactory.*;
import  InsertFactory.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class MainFrame extends JFrame {
    private String[] tablesList = {"Должности", "Отделы", "Сотрудники"};
    private JComboBox tablesComboBox = new JComboBox(tablesList);
    private TableFactory tableFactory = null;
    private InsertFactory insertFactory = null;
    private UpdateFactory updateFactory = null;
    private Update additionalUpdate = null;
    private Insert additionalInsert = null;
    private Table table = null;
    private JScrollPane scrollPane = null;
    public MainFrame() {
        super("Учёт сотрудников предприятия");
        this.setBounds(250, 250, 600, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("src/main/java/Images/sql.png");
        this.setIconImage(img.getImage());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 600, 200);
        mainPanel.setBorder(BorderFactory.createEtchedBorder());

        JButton selectButton = new JButton("Показать");
        JButton updateButton = new JButton("Изменить");
        JButton insertButton = new JButton("Добавить");
        JButton deleteButton = new JButton("Удалить");
        JButton helpButton = new JButton("Помощь");

        tablesComboBox.setBounds(10, 10, 100, 20);
        selectButton.setBounds(10, 40, 100, 20);
        insertButton.setBounds(10, 70, 100, 20);
        updateButton.setBounds(10, 100, 100, 20);
        deleteButton.setBounds(10, 130, 100, 20);
        helpButton.setBounds(10, 180, 100, 20);

        updateButton.setEnabled(false);
        insertButton.setEnabled(false);
        deleteButton.setEnabled(false);

        mainPanel.add(tablesComboBox);
        mainPanel.add(selectButton);
        mainPanel.add(updateButton);
        mainPanel.add(insertButton);
        mainPanel.add(deleteButton);
        mainPanel.add(helpButton);


        this.add(mainPanel);

        selectButton.addActionListener(e -> {

            makeTable(mainPanel);

            updateButton.setEnabled(true);
            insertButton.setEnabled(true);
            deleteButton.setEnabled(true);

        });

        deleteButton.addActionListener(e -> {

            removeRow();
            makeTable(mainPanel);

        });

        updateButton.addActionListener(e -> {
            JViewport viewport;
            JTable current_tbl;

            viewport = scrollPane.getViewport();
            current_tbl = (JTable) viewport.getView();
            if (current_tbl.getSelectedRow() != -1) {
                updateRow();
            }
            else{
                JOptionPane.showMessageDialog(null, "Выберите строку!");
            }
        });

        insertButton.addActionListener(e -> {
            insertRow();
        });

        helpButton.addActionListener(e -> {
            HelpFrame helpFrame = new HelpFrame();
            helpFrame.setVisible(true);
        });

    }

    private void insertRow() {

        switch (String.valueOf(tablesComboBox.getSelectedItem())) {
            case ("Сотрудники"):
                insertFactory = new InsertEmployeeFactory();
                additionalInsert = insertFactory.createFrame();
                additionalInsert.makeFrame(scrollPane);
                break;
            case ("Отделы"):
                insertFactory = new InsertDepartmentFactory();
                additionalInsert = insertFactory.createFrame();
                additionalInsert.makeFrame(scrollPane);
                break;
            case ("Должности"):
                insertFactory = new InsertPositionFactory();
                additionalInsert = insertFactory.createFrame();
                additionalInsert.makeFrame(scrollPane);
                break;
        }

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

    public void removeRow() {

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

    public void updateRow() {

        switch (String.valueOf(tablesComboBox.getSelectedItem())) {
            case ("Сотрудники"):
                updateFactory = new UpdateEmployeeFactory();
                additionalUpdate = updateFactory.createFrame();
                additionalUpdate.makeFrame(scrollPane);
                break;
            case ("Отделы"):
                updateFactory = new UpdateDepartmentFactory();
                additionalUpdate = updateFactory.createFrame();
                additionalUpdate.makeFrame(scrollPane);
                break;
            case ("Должности"):
                updateFactory = new UpdatePositionsFactory();
                additionalUpdate = updateFactory.createFrame();
                additionalUpdate.makeFrame(scrollPane);
                break;
        }
    }
}
