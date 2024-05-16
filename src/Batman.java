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
    private static Image bIdle;
    private static Image bWalk1;
    private static Image bWalk2;
    private static Image bWalk3;
    private static Image bWalk4;
    private static Image bWalk5;

    public Batman(int health, int damage, int shortRange, int longRange, int x, int y, int h, int w) {
        super(health, damage, shortRange, longRange, x, y, h, w);
    }

    public  void bWalk(Graphics g)  throws InterruptedException {
        Graphics2D g2d = (Graphics2D) g;
        try {
            bIdle = ImageIO.read(new File("Batman/bIdle.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bIdle, 0, 0, 500, 500, null);

        try {
            bWalk1 = ImageIO.read(new File("Batman/1.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk1, 20, 0, 500, 500, null);
        try {
            bWalk2 = ImageIO.read(new File("Batman/2.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk2, 40, 0, 500, 500, null);
        Thread.sleep(500);
        try {
            bWalk3 = ImageIO.read(new File("Batman/3.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk3, 80, 0, 500, 500, null);
        Thread.sleep(500);
        try {
            bWalk4 = ImageIO.read(new File("Batman/4.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk4, 160, 0, 500, 500, null);
        Thread.sleep(500);
        try {
            bWalk5 = ImageIO.read(new File("Batman/5.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk5, 200, 0, 500, 500, null);
        Thread.sleep(500);}





    }

