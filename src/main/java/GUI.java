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

    public GUI(){
        super("Test_RS");
        this.setBounds(100,100,250,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container control_container = this.getContentPane();
        control_container.setLayout(new GridLayout(5,1,2,2));
        Container list_container = this.getContentPane();
        list_container.setLayout(new GridLayout(1,1));
        control_container.add(tablesComboBox);
        control_container.add(selectButton);
        control_container.add(updateButton);
        control_container.add(insertButton);
        control_container.add(deleteButton);
    }
}
