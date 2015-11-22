package fractal;

import java.awt.Color;
import java.awt.Graphics;

/**
 * TreeFractal
 *
 * @author RlonRyan
 * @date Jun 20, 2015
 */
public class TreeFractal extends Fractal {

    public TreeFractal(int depth) {
        super(depth);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        paintBranch(g, (int) this.getAnchor().getX(), (int) this.getAnchor().getY(), 0);
        g.setColor(Color.WHITE);
    }

    protected void paintBranch(Graphics g, int x, int y, int num) {
        if (num % (depth / 10) == 0) {
            Color c = g.getColor();
            int cr = c.getRed();
            int cg = c.getGreen();
            int cb = c.getBlue();

            cr -= 20;
            cg -= 20;
            cb -= 20;

            cr = cr < 0 ? 0 : cr;
            cg = cg < 0 ? 0 : cg;
            cb = cb < 0 ? 0 : cb;

            g.setColor(new Color(cr, cg, cb));
        }
        g.drawLine(x, y, x - zoom, y + zoom);
        g.drawLine(x, y, x + zoom, y + zoom);
        if (num < this.depth) {
            Color c = g.getColor();
            paintBranch(g, x - zoom, y + zoom, num + 1);
            g.setColor(c);
            paintBranch(g, x + zoom, y + zoom, num + 1);
        }
    }

}
