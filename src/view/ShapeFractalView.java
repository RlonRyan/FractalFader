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
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * FractalProject
 *
 * @author RlonRyan
 * @date Jun 19, 2015
 */
public class ShapeFractalView extends JPanel {

    public static enum ViewerMode {

        invalid, stopped, screensaver, manual;
    }

    public static final Color defaultColor = Color.WHITE;
    public static final Color defaultBGColor = Color.BLACK;

    private ViewerMode mode;
    private Color color, bgcolor;
    private Timer timer;
    private Timer fader;

    private ShapeFractal sf;
    boolean isGrowing = false;
    boolean isFirstRound = true;

    public ShapeFractalView() {
        this(defaultColor, defaultBGColor, 4000);
    }

    public ShapeFractalView(Color color, Color bgcolor, int delay) {
        this.setPreferredSize(new Dimension(640, 480));
        this.setSize(640, 480);
        this.setBackground(bgcolor);

        this.mode = ViewerMode.stopped;
        this.color = color;
        this.bgcolor = bgcolor;

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
                sf.setZoom(getHeight() / 24);
                sf.setAnchor((getWidth() / 2) - ((sf.getSides() - 2) * sf.getZoom()), getHeight() / 2);
            }

        });

        this.sf = new ShapeFractal(0);
        this.sf.setSides(3);
        this.sf.setAnchor((this.getWidth() / 2) - ((this.sf.getSides() - 2) * this.sf.getZoom()), this.getHeight() / 2);

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
        if (this.sf.getDepth() == 1 || this.sf.getDepth() == 5) {
            this.isGrowing = !this.isGrowing;
            if (this.isGrowing) {
                if (this.isFirstRound) {
                    this.isFirstRound = false;
                } else {
                    this.sf.setSides(this.sf.getSides() + 1);
                    this.sf.setAnchor((this.getWidth() / 2) - ((this.sf.getSides() - 2) * this.sf.getZoom()), this.getHeight() / 2);
                }
            }
        }
        if (this.isGrowing || this.sf.getDepth() < 1) {
            this.sf.setDepth(this.sf.getDepth() + 1);
        } else {
            this.sf.setDepth(this.sf.getDepth() - 1);
        }
        this.sf.paint(g);
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
