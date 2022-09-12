package GUI;

import javax.swing.*;
import java.awt.*;

public class HelpFrame extends JFrame {

    public HelpFrame() throws HeadlessException {
        super("Помощь");
        this.setBounds(350, 250, 400, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon img = new ImageIcon("src/main/java/Images/question.png");
        this.setIconImage(img.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 400, 250);

        String txt = "После добавления новой записи или изменения существующей не забудьте снова создать таблицу кнопкой Показать";
        JLabel textLabel = new JLabel("<html><div style='text-align: center;'>" + txt + "</div></html>");
        textLabel.setBounds(42,50,300,100);
        panel.add(textLabel);
        this.add(panel);

    }
}
