import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements Runnable {

    private DrawPanel p;
    private Thread windowThread;
    private JFrame frame;

    public MainFrame(String display) {
        super(display);
        int frameWidth = 1500;
        int frameHeight = 1000;
        p = new DrawPanel();
        this.add(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(600, 100);
        this.setVisible(true);
        startThread();

    }

    public void startThread() {
        windowThread = new Thread(this);
        windowThread.start();
    }

    public void run() {
        while (true) {
            p.repaint();
        }
    }
}

