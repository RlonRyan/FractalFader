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
public class TriangleFractal extends Fractal {

    public TriangleFractal(int depth) {
        super(depth);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        paintBranch(
                g,
                new Point((int) this.getAnchor().getX() - zoom * 5, (int) this.getAnchor().getY()),
                new Point((int) this.getAnchor().getX() + zoom * 5, (int) this.getAnchor().getY()),
                0
        );
        g.setColor(Color.WHITE);
        paintBranch(
                g,
                new Point((int) this.getAnchor().getX() - zoom * 5, (int) this.getAnchor().getY()),
                new Point((int) this.getAnchor().getX(), (int) this.getAnchor().getY() + zoom * 10),
                0
        );
        g.setColor(Color.WHITE);
        paintBranch(
                g,
                new Point((int) this.getAnchor().getX(), (int) this.getAnchor().getY() + zoom * 10),
                new Point((int) this.getAnchor().getX() + zoom * 5, (int) this.getAnchor().getY()),
                0
        );
        g.setColor(Color.WHITE);
//        g.drawString(Integer.toString(this.depth), (int) this.anchor.getX(), (int) this.anchor.getY() + zoom * 5);
    }

    protected void paintBranch(Graphics g, Point2D p1, Point2D p2, int num) {
        if (num == this.depth) {
            g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
        } else if (num < this.depth) {

            Point2D mid = midpoint(p1, p2);

            double dx = p1.getX() - mid.getX();
            double dy = p1.getY() - mid.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);
            dx /= dist;
            dy /= dist;

            mid = new Point((int) (mid.getX() + (zoom / depth) * dy), (int) (mid.getY() - (zoom / depth) * dx));

            paintBranch(g, p1, mid, num + 1);
            paintBranch(g, mid, p2, num + 1);
        }
    }

    private Point2D midpoint(Point2D p1, Point2D p2) {
        return new Point((int) (p1.getX() + p2.getX()) / 2, (int) (p1.getY() + p2.getY()) / 2);
    }

}
