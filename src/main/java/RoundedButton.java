import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class RoundedButton extends JButton {
    private final Color backgroundColor;
    private final Color foregroundColor;
    private final Color hoverColor;
    private final Color pressedColor;

    public RoundedButton(String text, Color backgroundColor, Color foregroundColor) {
        super(text);
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.hoverColor = brighten(backgroundColor, 25);
        this.pressedColor = darken(backgroundColor, 18);

        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setForeground(foregroundColor);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFont(getFont().deriveFont(java.awt.Font.BOLD, 14f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 22;
        int width = getWidth();
        int height = getHeight();

        if (getModel().isPressed()) {
            g2.setColor(pressedColor);
        } else if (getModel().isRollover()) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(backgroundColor);
        }

        g2.fillRoundRect(0, 0, width, height, arc, arc);

        if (getModel().isRollover() || getModel().isPressed()) {
            g2.setColor(new Color(255, 255, 255, 30));
            g2.fillRoundRect(0, 0, width, height, arc, arc);
        }

        super.paintComponent(g2);
        g2.dispose();
    }

    private static Color brighten(Color color, int amount) {
        return new Color(Math.min(255, color.getRed() + amount),
                Math.min(255, color.getGreen() + amount),
                Math.min(255, color.getBlue() + amount));
    }

    private static Color darken(Color color, int amount) {
        return new Color(Math.max(0, color.getRed() - amount),
                Math.max(0, color.getGreen() - amount),
                Math.max(0, color.getBlue() - amount));
    }
}
