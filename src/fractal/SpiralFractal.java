package fractal;

import java.awt.Color;
import java.awt.Graphics;

/**
 * TreeFractal
 *
 * @author RlonRyan
 * @date Jun 20, 2015
 */
public class SpiralFractal extends Fractal {

    protected int delta;

    public SpiralFractal(int depth) {
        super(depth);
        this.setDepth(depth);
    }

    @Override
    public final void setDepth(int depth) {
        this.depth = depth;
        this.delta = 1440 / depth;
    }

    @Override
    public void paint(Graphics g) {
        //Color c = new Color((int) (256 * Math.random()), (int) (256 * Math.random()), (int) (256 * Math.random()));
        Color c = Color.WHITE;

        for (int i = 0; i < 360; i = i + 90) {
            g.setColor(c);
            paint(g, (int) this.getAnchor().getX(), (int) this.getAnchor().getY(), i, 0);
        }

        g.setColor(Color.white);
    }

    protected void paint(Graphics g, int x, int y, int theta, int num) {

        int xx = x + (int) ((zoom / (num / 2 + 1)) * Math.cos(Math.toRadians(theta)));
        int yy = y + (int) ((zoom / (num / 2 + 1)) * Math.sin(Math.toRadians(theta)));

        if (num % (depth / 10) == 0) {
            g.setColor(g.getColor().darker());
        }

        g.drawLine(x, y, xx, yy);

        if (num < this.depth) {
            paint(g, xx, yy, theta + delta, num + 1);
        }
    }

}
