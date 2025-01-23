import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PursePanel extends JPanel {
    private Purse purse;
    private static final int ICON_SIZE = 100;
    private static final int SPACING = 10;

    public PursePanel() {
        purse = new Purse();
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.BLACK);
        setLayout(new FlowLayout(FlowLayout.LEFT, SPACING, SPACING));
    }

    public void setPurse(Purse newPurse) {
        this.purse = newPurse;
        updatePanel();
    }

    private void updatePanel() {
        removeAll();
        for (Map.Entry<Denomination, Integer> entry : purse.getCash().entrySet()) {
            Denomination den = entry.getKey();
            int count = entry.getValue();

            for (int i = 0; i < count; i++) {

                    // Print debug information
                    System.out.println("Loading image: src/images/" + den.img());

                    ImageIcon icon = new ImageIcon(den.img());

                    int newWidth = 100;
                    int newHeight = 55;

                    Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    JLabel label = new JLabel(new ImageIcon(scaledImage));
                    label.setPreferredSize(new Dimension(ICON_SIZE, ICON_SIZE));
                    add(label);

            }
        }
        revalidate();
        repaint();
    }
}