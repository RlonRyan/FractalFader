package view;

import fractal.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * FractalProject
 *
 * @author RlonRyan
 * @date Jun 19, 2015
 */
public class FractalView extends JPanel {

    public static enum ViewerMode {

        invalid, stopped, screensaver, manual;
    }

    public static final Color defaultColor = Color.WHITE;
    public static final Color defaultBGColor = Color.BLACK;

//    private static final Fractal[] fractals = new Fractal[]{
//        new TreeFractal(10),
//        new AsymTreeFractal(10),
//        new SpiralFractal(25),
//        new TriangleFractal(5),
//        new ShapeFractal(4),
//        new SnowflakeFractal(5),
//        new DecayingTreeFractal(5)
//    }; // Bad Idea, static makes no sense here.
    private HashMap<String, Fractal> fractals;
    private ViewerMode mode;
    private Color color, bgcolor;
    private Timer timer;
    private Timer fader;

    public FractalView() {
        this(defaultColor, defaultBGColor, 4000);
    }

    public FractalView(Color color, Color bgcolor, int delay) {
        this.setPreferredSize(new Dimension(640, 480));
        this.setSize(640, 480);
        this.setBackground(bgcolor);

        this.mode = ViewerMode.stopped;
        this.color = color;
        this.bgcolor = bgcolor;

        this.fractals = new HashMap<>(10);
        this.fractals.put("tree", new TreeFractal(10));
        this.fractals.put("asym", new AsymTreeFractal(10));
        this.fractals.put("spiral", new SpiralFractal(25));
        //this.fractals.put("triangle", new TriangleFractal(5));
        this.fractals.put("shape", new ShapeFractal(4));
        this.fractals.put("snow", new SnowflakeFractal(5));
        this.fractals.put("decay", new DecayingTreeFractal(5));

        this.fader = new Timer(delay / 50, (ActionEvent e) -> this.fade());
        this.timer = new Timer(delay, (ActionEvent e) -> this.repaint());

        this.fader.start();
        this.timer.start();

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mode = ViewerMode.manual;
                timer.stop();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mode = ViewerMode.screensaver;
                timer.restart();
                repaint();
            }
        });

        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
//                for (Fractal f : fractals) {
//                    f.setZoom(getHeight() / 24);
//                }
                fractals.forEach((k, v) -> v.setZoom(getHeight() / 24));
            }

        });

        this.mode = ViewerMode.screensaver;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        this.clear(g);

        this.paintFractals(g);

        this.paintInfo(g);

    }

    public void paintFractals(Graphics g) {
//        fractals[2].setDepth(25 + (int) (Math.random() * 75)); //Spiral
//        fractals[3].setDepth((int) (Math.random() * 7)); //Triangle
//        fractals[4].setDepth((int) (Math.random() * 5)); //Shape

        this.fractals.get("spiral").setDepth(25 + (int) (Math.random() * 75));
        //this.fractals.get("triangle").setDepth((int) (Math.random() * 7));
        this.fractals.get("shape").setDepth((int) (Math.random() * 5));

//        for (Fractal f : fractals) {
//            f.randomizeAnchor(this.getBounds());
//            f.paint(g);
//        }
        fractals.forEach((k, v) -> {
            v.randomizeAnchor(this.getBounds());
            v.paint(g);
        });
    }

    public void paintInfo(Graphics g) {
        g.setColor(new Color(100, 100, 100));

        g.fillRoundRect(
                (int) (this.getWidth() * .10),
                this.getHeight() - 25,
                (int) (this.getWidth() * .80),
                50,
                15,
                15
        );

        g.setColor(color);

        g.drawRoundRect(
                (int) (this.getWidth() * .10),
                this.getHeight() - 25,
                (int) (this.getWidth() * .80),
                50,
                15,
                15
        );

        g.drawString("Mode: " + this.mode, (int) (this.getWidth() * .15), (int) (this.getHeight() - 10));
    }

    public final void fade() {
        Graphics g = this.getGraphics();
        g.setColor(new Color(0, 0, 0, 10));
        g.fillRect(0, 0, this.getWidth(), this.getHeight() - 25);
        g.fillRect(0, this.getHeight() - 25, (int) (this.getWidth() * .10), 25);
        g.fillRect((int) (this.getWidth() * .90) + 1, this.getHeight() - 25, (int) (this.getWidth() * .10), 25);
    }

    public void clear(Graphics g) {
        g.setColor(bgcolor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(color);
    }

}
