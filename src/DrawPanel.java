import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DrawPanel extends JPanel {

    private Rectangle button;

    public DrawPanel() {
        button = new Rectangle(565, 310, 805, 265);

    }

    protected void paintComponent(Graphics g) {
        Image img = null;
        try {
            img = ImageIO.read(new File("OpeningBackGround.jpg"));
        } catch (IOException e) {
            //do something
        }
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        g.setFont(new Font("Times New Roman", Font.BOLD, 200));
        g.drawString("AMAKO", 575, 500);
        g.drawRect((int) button.getX(), (int) button.getY(), (int) button.getWidth(), (int) button.getHeight());
        g.drawImage(img, 500, 500, null);
    }
}

