import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class OldMan {
    private static int talking;
    private Image oldMan1;
    public void talk(Graphics g){
        if(DrawPanel.next){
            try {
            oldMan1 = ImageIO.read(new File("OldMan/Talking1.jpg"));
            }
            catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
            }

            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(oldMan1, 0,0, null);

        }
    }
}
