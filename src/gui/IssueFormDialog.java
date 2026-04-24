import javax.swing.*;
import java.awt.*;

public class IssueFormDialog extends JDialog {
    private ServerConnection connection;

    public IssueFormDialog(MainFrame parent, ServerConnection connection) {
        super(parent, "Создать задачу", true);
        this.connection = connection;

        setSize(400, 320);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Тип:"));
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"BUG", "TASK", "STORY"});
        panel.add(typeBox);

        panel.add(new JLabel("Название:"));
        JTextField titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Описание:"));
        JTextField descField = new JTextField();
        panel.add(descField);

        panel.add(new JLabel("Приоритет:"));
        JComboBox<String> priorityBox = new JComboBox<>(new String[]{"LOW", "MEDIUM", "HIGH", "CRITICAL"});
        panel.add(priorityBox);

        panel.add(new JLabel("ID проекта:"));
        JTextField projectField = new JTextField("1");
        panel.add(projectField);

        JButton createBtn = new JButton("Создать");
        JButton cancelBtn = new JButton("Отмена");
        panel.add(createBtn);
        panel.add(cancelBtn);

        createBtn.addActionListener(e -> {
            String payload = typeBox.getSelectedItem() + "|"
                + titleField.getText() + "|"
                + descField.getText() + "|"
                + priorityBox.getSelectedItem() + "|"
                + projectField.getText();
            try {
                Response resp = connection.send(new Request("CREATE_ISSUE", payload));
                JOptionPane.showMessageDialog(this,
                    resp.getStatus().equals("OK") ? "Создано: " + resp.getData() : resp.getMessage());
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
            }
        });

        cancelBtn.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }
}