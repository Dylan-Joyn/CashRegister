import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPanel extends JPanel {
    private Register register;
    private JPanel inputPanel;
    private JTextField input;
    private PursePanel changePanel;

    public RegisterPanel() {
        register = new Register();
        setLayout(new BorderLayout(10, 10));

        setBackground(Color.WHITE);

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.GRAY);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Amount"));
        input = new JTextField(10);
        input.addActionListener(new InputListener());
        inputPanel.add(new JLabel("$"));
        inputPanel.add(input);

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
            double amount = Double.parseDouble(input.getText());
            Purse change = register.makeChange(amount);
            changePanel.setPurse(change);
            changePanel.repaint();
        }
    }
}