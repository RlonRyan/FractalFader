package fractal;

import java.awt.Graphics;

/**
 * ShapeFractal
 *
 * @author RlonRyan
 * @date Jun 28, 2015
 */
public class ShapeFractal extends Fractal {

    private boolean customSides = false;
    private int sides = 3;

    private double theta;
    private double x, xn;
    private double y, yn;
    private Graphics g;

    public ShapeFractal(int depth) {
        super(depth);
    }

    public int getSides() {
        return this.sides;
    }

    public void setSides(int sides) {
        this.sides = sides > 2 ? sides : 3;
        if (!this.customSides) {
            this.customSides = true;
        }
    }

    @Override
    public void paint(Graphics g) {
        if (!this.customSides) {
            this.sides = 3 + (int) (Math.random() * 7);
        }
        this.theta = 0;
        this.x = (int) this.anchor.getX();
        this.y = (int) this.anchor.getY();
        this.g = g;
        this.shape(this.zoom * 5, this.depth);
        this.g = null;
    }

    private void shape(int size, int iterations) {
        if (iterations > 0) {
            for (int i = 0; i < this.sides; i++) {
                theta += 360.0 / sides;
                xn = x + size * Math.sin(Math.toRadians(theta));
                yn = y + size * Math.cos(Math.toRadians(theta));
                g.drawLine((int) x, (int) y, (int) xn, (int) yn);
                x = xn;
                y = yn;
                shape(size / 2, iterations - 1);
            }
        }
    }
}
