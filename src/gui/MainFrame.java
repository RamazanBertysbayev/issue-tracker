import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ServerConnection connection;
    private JTextArea issueArea;

    public MainFrame(ServerConnection connection) {
        this.connection = connection;

        setTitle("Issue Tracker");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Верхняя панель с кнопками
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton refreshBtn = new JButton("Обновить");
        JButton createBtn = new JButton("Создать задачу");
        JButton deleteBtn = new JButton("Удалить");
        topPanel.add(refreshBtn);
        topPanel.add(createBtn);
        topPanel.add(deleteBtn);

        // Центр — список задач
        issueArea = new JTextArea();
        issueArea.setEditable(false);
        issueArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(issueArea);

        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        refreshBtn.addActionListener(e -> loadIssues());
        createBtn.addActionListener(e -> {
            new IssueFormDialog(this, connection);
            loadIssues();
        });
        deleteBtn.addActionListener(e -> deleteIssue());

        loadIssues();
        setVisible(true);
    }

    void loadIssues() {
        try {
            Response resp = connection.send(new Request("GET_ALL_ISSUES", ""));
            issueArea.setText(resp.getData() == null ? "Нет задач" : resp.getData());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка загрузки: " + ex.getMessage());
        }
    }

    private void deleteIssue() {
        String input = JOptionPane.showInputDialog(this, "Введите ID задачи для удаления:");
        if (input == null || input.isBlank()) return;
        try {
            Response resp = connection.send(new Request("DELETE_ISSUE", input.trim()));
            JOptionPane.showMessageDialog(this, 
                resp.getStatus().equals("OK") ? "Удалено!" : resp.getMessage());
            loadIssues();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
        }
    }
}