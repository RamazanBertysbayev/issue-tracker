import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginFrame extends JFrame {
    private ServerConnection connection;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        connection = new ServerConnection();
        try {
            connection.connect();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Не удалось подключиться к серверу!");
        }

        setTitle("Issue Tracker — Вход");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Логин:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Пароль:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginBtn = new JButton("Войти");
        JButton registerBtn = new JButton("Регистрация");
        panel.add(loginBtn);
        panel.add(registerBtn);

        loginBtn.addActionListener(e -> doLogin());
        registerBtn.addActionListener(e -> doRegister());

        add(panel);
        setVisible(true);
    }

    private void doLogin() {
        String payload = usernameField.getText() + ":" + new String(passwordField.getPassword());
        try {
            Response resp = connection.send(new Request("LOGIN", payload));
            if (resp.getStatus().equals("OK")) {
                dispose();
                new MainFrame(connection);
            } else {
                JOptionPane.showMessageDialog(this, resp.getMessage());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
        }
    }

    private void doRegister() {
        String payload = usernameField.getText() + ":" + new String(passwordField.getPassword());
        try {
            Response resp = connection.send(new Request("REGISTER", payload));
            JOptionPane.showMessageDialog(this, 
                resp.getStatus().equals("OK") ? resp.getData() : resp.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
        }
    }
}