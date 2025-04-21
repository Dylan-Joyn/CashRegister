import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPanel extends JPanel {
    private Register register;
    private JPanel inputPanel;
    private JTextField input;
    private PursePanel changePanel;
    private CommandHistory commandHistory; // Command pattern
    private JButton undoButton;

    public RegisterPanel() {
        register = new Register();
        commandHistory = new CommandHistory();
        setLayout(new BorderLayout(10, 10));

        setBackground(Color.WHITE);

        // Input panel
        inputPanel = new JPanel();
        inputPanel.setBackground(Color.GRAY);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Amount"));
        input = new JTextField(10);
        input.addActionListener(new InputListener());
        inputPanel.add(new JLabel("$"));
        inputPanel.add(input);

        // Add undo button
        undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> {
            if (commandHistory.canUndo()) {
                commandHistory.undo();
            }
        });
        undoButton.setEnabled(false);
        inputPanel.add(undoButton);

        changePanel = new PursePanel();
        changePanel.setBackground(Color.GRAY);

        add(inputPanel, BorderLayout.NORTH);
        add(changePanel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(800, 600));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private class InputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(input.getText());
                MakeChangeCommand command = new MakeChangeCommand(register, amount, changePanel);
                command.execute();
                commandHistory.add(command);
                undoButton.setEnabled(commandHistory.canUndo());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(RegisterPanel.this,
                        "Please enter a valid number", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}