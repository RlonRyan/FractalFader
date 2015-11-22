package fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * TreeFractal
 *
 * @author RlonRyan
 * @date Jun 20, 2015
 */
public class SnowflakeFractal extends Fractal {

    double x;
    double y;
    int theta;
    Graphics g;

    public SnowflakeFractal(int depth) {
        super(depth);
    }

    @Override
    public void paint(Graphics g) {
        this.x = this.anchor.getX();
        this.y = this.anchor.getY();
        this.theta = 0;
        this.g = g;
        this.g.setColor(Color.WHITE);
        for (int i = 0; i < 6; i++) {
            this.paintBranch(depth, this.zoom * 20);
            this.theta += 60;
        }
        this.g = null;
    }

    protected void paintBranch(int level, double length) {
        if (level == 1) {
            double xn = x + length * Math.cos(Math.toRadians(theta));
            double yn = y + length * Math.sin(Math.toRadians(theta));
            g.drawLine((int) x, (int) y, (int) (xn), (int) (yn));
            this.x = xn;
            this.y = yn;
        } else {
            this.paintBranch(level - 1, length / 4);
            this.theta += 60;
            this.paintBranch(level - 1, length / 4);
            this.theta -= 120;
            this.paintBranch(level - 1, length / 4);
            this.theta += 60;
            this.paintBranch(level - 1, length / 4);
        }
    }

}
