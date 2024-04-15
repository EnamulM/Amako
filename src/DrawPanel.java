import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DrawPanel extends JPanel implements MouseListener{

    private Rectangle button;
    private Image background;


    public DrawPanel() {
        button = new Rectangle(565, 310, 805, 265);
        addMouseListener(this);
        try {
            background = ImageIO.read(new File("OpeningBackGround.jpg"));
        } catch (IOException e) {
            // Handle exception
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        g.setFont(new Font("Times New Roman", Font.BOLD, 200));
        g.drawString("AMAKO", 575, 500);
        g.drawRect((int) button.getX(), (int) button.getY(), (int) button.getWidth(), (int) button.getHeight());
        g.drawImage(background, 1000, 500, null);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (button.contains(e.getPoint())) {
            try {
                background = ImageIO.read(new File("BackgroundImages/OpeningBackGround.jpg"));
                repaint();
            } catch (IOException ex) {
                // Handle exception
                System.err.println("Error loading background image: " + ex.getMessage());
            }
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

