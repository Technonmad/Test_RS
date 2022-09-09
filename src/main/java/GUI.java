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
        this.setBounds(250,250,700,200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setBounds(5, 5, 100, 100);

        tablesComboBox.setBounds(10,10,100,20);
        selectButton.setBounds(10,40,100,20);
        updateButton.setBounds(10,70,100,20);
        insertButton.setBounds(10,100,100,20);
        deleteButton.setBounds(10,130,100,20);

        controlPanel.add(tablesComboBox);
        controlPanel.add(selectButton);
        controlPanel.add(updateButton);
        controlPanel.add(insertButton);
        controlPanel.add(deleteButton);

        updateButton.setEnabled(false);
        insertButton.setEnabled(false);
        deleteButton.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);

        mainPanel.add(controlPanel);
        mainPanel.add(scrollPane);
        this.add(mainPanel);

    }
}
