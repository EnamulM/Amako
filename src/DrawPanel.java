import java.awt.event.MouseListener;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Font;

public class DrawPanel extends JPanel {

    private Rectangle button;

    public DrawPanel() {
        button = new Rectangle(575, 0, 500, 200);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        g.setFont(new Font("Times New Roman", Font.BOLD, 200));
        g.drawString("AMAKO", 575, 500);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());

    }
}

