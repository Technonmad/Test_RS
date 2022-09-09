import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{
    private JButton selectButton = new JButton("Показать");
    private JButton updateButton = new JButton("Изменить");
    private JButton insertButton = new JButton("Добавить");
    private JButton deleteButton = new JButton("Удалить");
    String[] tablesList = {"Должности", "Отделы", "Сотрудники"};
    private JComboBox tablesComboBox = new JComboBox(tablesList);
    private String[] columnNames = {"id", "boss_id","first_name", "last_name"};
    private Object[][] columnData = {
            {1, 1, "Пися", "Камушкин"}
    };
    private JTable table = new JTable(columnData, columnNames);

    public GUI(){
        super("Test_RS");
        this.setBounds(100,100,800,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        JPanel controlPanel = new JPanel(new GridLayout(5,1));
        JPanel tablePanel = new JPanel();

        controlPanel.add(tablesComboBox);
        controlPanel.add(selectButton);
        controlPanel.add(updateButton);
        controlPanel.add(insertButton);
        controlPanel.add(deleteButton);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        tablePanel.add(scrollPane);
        mainPanel.add(controlPanel);
        mainPanel.add(tablePanel);
        this.add(mainPanel);

        /*Container control_container = this.getContentPane();
        control_container.setLayout(new GridLayout(1,5,2,2));
        Container list_container = this.getContentPane();
        list_container.setLayout(new GridLayout(1,1,2,2));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        control_container.add(tablesComboBox);
        control_container.add(selectButton);
        control_container.add(updateButton);
        control_container.add(insertButton);
        control_container.add(deleteButton);
        list_container.add(scrollPane);*/
    }
}
