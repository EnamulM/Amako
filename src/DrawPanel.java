import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DrawPanel extends JPanel implements MouseListener{
    private static int talking;
    private static Image oldMan1;
    private static Image oldMan2;
    private static Image oldMan3;
    private static Image oldMan4;
    private static Image oldMan5;

    private Rectangle button;
    private Image background;
    private JPanel mainPanel;
    private boolean onStartingScreen = true;

    public DrawPanel() {
        button = new Rectangle(565, 310, 805, 265);
        addMouseListener(this);
        try {
            background = ImageIO.read(new File("OpeningBackGround.jpg"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry! (Error Here)");
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        setBackground(Color.black);
        g.setFont(new Font("Times New Roman", Font.BOLD, 200));
        g.drawString("AMAKO", 575, 500);
        g.drawRect((int) button.getX(), (int) button.getY(), (int) button.getWidth(), (int) button.getHeight());
        g.drawImage(background, 0, 0, getWidth(), getHeight(),null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (button.contains(e.getPoint())) {
            try {
                if (onStartingScreen){
                    background = ImageIO.read(new File("BackgroundImages/OpeningBackGround.jpg"));
                    onStartingScreen = false;
                    repaint();
                }
                else {
                    talk(getGraphics());
                }
            } catch (IOException ex) {
                // Handle exception
                System.err.println("Error loading background image: " + ex.getMessage());
            }
        }

    }
    public void talk(Graphics g){
        try {
            Graphics2D g2d = (Graphics2D) g;
            try {
                oldMan1 = ImageIO.read(new File("OldMan/Talking1.png"));
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            g2d.drawImage(oldMan1, getWidth() / 2, getHeight() / 2, 500, 500, null);
            Thread.sleep(500);
            try {
                oldMan2 = ImageIO.read(new File("OldMan/Talking2.png"));
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            g2d.drawImage(oldMan2, getWidth() / 2, getHeight() / 2, 500, 500, null);
            Thread.sleep(500);
            try {
                oldMan3 = ImageIO.read(new File("OldMan/Talking3.png"));
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            g2d.drawImage(oldMan3, getWidth() / 2, getHeight() / 2, 500, 500, null);
            Thread.sleep(500);
            try {
                oldMan4 = ImageIO.read(new File("OldMan/Talking4.png"));
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            g2d.drawImage(oldMan4, getWidth() / 2, getHeight() / 2, 500, 500, null);
            Thread.sleep(500);
            try {
                oldMan5 = ImageIO.read(new File("OldMan/Talking5.png"));
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            g2d.drawImage(oldMan5, getWidth() / 2, getHeight() / 2, 500, 500, null);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep error");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}

