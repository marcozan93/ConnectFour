import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CirclePanel extends JPanel {

    private Color color = Color.WHITE;

    public CirclePanel() {
        setBorder(new LineBorder(Color.BLUE, 5));
        setBackground(Color.WHITE);
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill the background with blue
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the outer white circle
        g2d.setColor(Color.WHITE);
        int diameter = Math.min(getWidth(), getHeight()) - 10;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;
        g2d.fillOval(x, y, diameter, diameter);

        // Draw the inner colored circle
        g2d.setColor(color);
        int innerDiameter = diameter - 10; // Make the inner circle slightly smaller
        int innerX = (getWidth() - innerDiameter) / 2;
        int innerY = (getHeight() - innerDiameter) / 2;
        g2d.fillOval(innerX, innerY, innerDiameter, innerDiameter);
    }


}
