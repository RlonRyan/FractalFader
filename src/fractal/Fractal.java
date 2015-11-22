package fractal;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Fractal
 *
 * @author RlonRyan
 * @date Jun 19, 2015
 */
public abstract class Fractal {

    protected Point2D anchor;
    protected int zoom;
    protected int depth;

    public abstract void paint(Graphics g);

    public void randomizeAnchor(Rectangle2D rect) {
        this.setAnchor(
                (int) (Math.random() * rect.getWidth() * 0.75),
                (int) (Math.random() * rect.getHeight() * 0.75)
        );
    }

    public final Point2D getAnchor() {
        return anchor;
    }

    public int getZoom() {
        return zoom;
    }

    public int getDepth() {
        return depth;
    }

    public final void setAnchor(int x, int y) {
        this.anchor = new Point(x, y);
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Fractal(int depth) {
        this.anchor = new Point(0, 0);
        this.zoom = 10;
        this.depth = depth;
    }

}
