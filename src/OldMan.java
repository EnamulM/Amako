import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class OldMan {
    private static int talking;
    private Image oldMan1;
    private Image oldMan2;
    private Image oldMan3;
    private Image oldMan4;
    private Image oldMan5;

    public void talk(Graphics g){
        if(DrawPanel.next){
            try {
            oldMan1 = ImageIO.read(new File("OldMan/Talking1.png"));
            }
            catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
            }

            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(oldMan1, 0,0, null);
            try {
                oldMan2 = ImageIO.read(new File("OldMan/Talking2.png"));
            }
            catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            try {
                oldMan3 = ImageIO.read(new File("OldMan/Talking3.png"));
            }
            catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            try {
                oldMan4 = ImageIO.read(new File("OldMan/Talking4.png"));
            }
            catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            try {
                oldMan5 = ImageIO.read(new File("OldMan/Talking5.png"));
            }
            catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
        }
    }
}
