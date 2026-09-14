import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
    private final Color backgroundColor;
    private final int cornerRadius;

    public RoundedPanel(LayoutManager layout, int radius) {
        this(layout, radius, new Color(15, 23, 42, 210));
    }

    public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(new Color(15, 23, 42, 55));
        graphics.fillRoundRect(12, 12, getWidth() - 12, getHeight() - 12, cornerRadius + 18, cornerRadius + 18);

        graphics.setColor(backgroundColor);
        graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        super.paintComponent(graphics);
        graphics.dispose();
    }
}
