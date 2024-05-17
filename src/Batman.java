import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Batman extends Character {
    private int health;
    private int damage;
    private int shortRange;
    private int longRange;
    private int x;
    private int y;
    private int h;
    private int w;
    private static Image bWalk;
    public Batman(int health, int damage, int shortRange, int longRange, int x, int y, int h, int w) {
        super(health, damage, shortRange, longRange, x, y, h, w);
    }

    public  void bWalk(Graphics g)  throws InterruptedException {
        Graphics2D g2d = (Graphics2D) g;
        try {
            bWalk = ImageIO.read(new File("Batman/bIdle.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk, 0, 0, 500, 500, null);
        try {
            bWalk = ImageIO.read(new File("Batman/1.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk, 20, 0, 500, 500, null);
        try {
            bWalk = ImageIO.read(new File("Batman/2.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk, 40, 0, 500, 500, null);
        Thread.sleep(500);
        try {
            bWalk = ImageIO.read(new File("Batman/3.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk, 80, 0, 500, 500, null);
        Thread.sleep(500);
        try {
            bWalk = ImageIO.read(new File("Batman/4.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk, 160, 0, 500, 500, null);
        Thread.sleep(500);
        try {
            bWalk = ImageIO.read(new File("Batman/5.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk, 200, 0, 500, 500, null);
        Thread.sleep(500);}





    }

