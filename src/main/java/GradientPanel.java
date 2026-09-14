import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

class GradientPanel extends JPanel {
    private final Color topColor;
    private final Color bottomColor;

    public GradientPanel() {
        this(new Color(15, 23, 42), new Color(37, 99, 235));
    }

    public GradientPanel(Color topColor, Color bottomColor) {
        this.topColor = topColor;
        this.bottomColor = bottomColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, topColor, 0, height, bottomColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        g2d.setPaint(new Color(255, 255, 255, 30));
        g2d.fillRoundRect(28, 28, width - 56, height - 56, 36, 36);
        g2d.dispose();
    }
}
