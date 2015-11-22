package fractal;

import java.awt.Graphics;

/**
 * TreeFractal
 *
 * @author RlonRyan
 * @date Jun 20, 2015
 */
public class AsymTreeFractal extends TreeFractal {

    public AsymTreeFractal(int depth) {
        super(depth);
    }

    @Override
    protected void paintBranch(Graphics g, int x, int y, int num) {
        g.drawLine(x, y, x - zoom, y + zoom);
        g.drawLine(x, y, x + zoom, y + zoom);
        if (num < this.depth) {
            paintBranch(g, x - zoom, y + zoom, num + 1);
            paintBranch(g, x + zoom, y + zoom, num + 3);
        }
    }

}
