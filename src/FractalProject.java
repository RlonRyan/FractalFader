
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import view.FractalView;

/**
 * FractalProject
 *
 * @author RlonRyan
 * @date Jun 19, 2015
 */
public final class FractalProject {

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createWindow());
    }

    public static void createWindow() {
        JFrame f = new JFrame("Fractal Project Viewer");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new FractalView());
        f.setLocationRelativeTo(null);
        f.setMinimumSize(new Dimension(640, 480));
        f.setSize(640, 480);
        f.pack();
        f.setVisible(true);

        f.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.printf("Keypress: %1$s.%n", KeyEvent.getKeyText(e.getKeyCode()));
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }

        });
    }

}
