package GUI;

import Database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    public LoginFrame() throws HeadlessException {

        super("Подключение к базе данных");
        this.setBounds(250, 250, 400, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("src/main/resources/Images/sql.png");
        this.setIconImage(img.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 400, 250);
        panel.setBorder(BorderFactory.createEtchedBorder());

        JLabel connect_label = new JLabel("Путь");
        connect_label.setBounds(20, 35, 50, 20);

        JTextField connectText = new JTextField();
        connectText.setBounds(70, 35, 240, 20);

        JButton selectFileButton = new JButton("📄");
        selectFileButton.setBounds(310, 35, 50, 20);

        JRadioButton localRadioButton = new JRadioButton("Локально");
        localRadioButton.setBounds(100, 60, 90, 20);

        JRadioButton remoteRadioButton = new JRadioButton("Удаленно");
        remoteRadioButton.setBounds(190, 60, 90, 20);

        ButtonGroup group = new ButtonGroup();
        group.add(localRadioButton);
        group.add(remoteRadioButton);
        group.setSelected(localRadioButton.getModel(), true);

        JLabel user_label = new JLabel("Пользователь");
        user_label.setBounds(50, 90, 100, 20);
        JTextField userText = new JTextField();
        userText.setBounds(150, 90, 150, 20);

        JLabel password_label = new JLabel("Пароль");
        password_label.setBounds(50, 120, 50, 20);
        JTextField passwordText = new JTextField();
        passwordText.setBounds(150, 120, 150, 20);

        JButton acceptButton = new JButton("Подключиться");
        acceptButton.setBounds(100, 165, 150, 30);

        panel.add(user_label);
        panel.add(userText);
        panel.add(password_label);
        panel.add(passwordText);
        panel.add(acceptButton);
        panel.add(localRadioButton);
        panel.add(remoteRadioButton);
        panel.add(connect_label);
        panel.add(connectText);
        panel.add(selectFileButton);

        this.add(panel);

        remoteRadioButton.addActionListener(e -> {
            selectFileButton.setEnabled(false);
        });

        localRadioButton.addActionListener(e -> {
            selectFileButton.setEnabled(true);
        });

        selectFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                connectText.setText(String.valueOf(file));
            }
        });

        acceptButton.addActionListener(e -> {
            Database.getInstance();
            connectText.setText(connectText.getText().replace("\\", "/"));
            //System.out.println(connectText.getText());
            Database.getInstance().setUrl("jdbc:firebirdsql://localhost:3050" + '/' + connectText.getText());
            Database.getInstance().setUser(userText.getText());
            Database.getInstance().setPassword(passwordText.getText());

            try {
                Database.getInstance().getConnection();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка подключения к базе данных");
                throw new RuntimeException(ex);
            }

            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);

            this.dispose();
        });
    }
}
