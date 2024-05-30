import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;



public class Batman extends JPanel {
    private int health;
    private int damage;
    private int x;
    private int y;
    private int h;
    private int w;
    private BufferedImage image;
    private static Image bWalk;
    private static Image background;
    private final String walkFrames = "Batman/Walk";
    private BufferedImage bigImage;
    private ArrayList<BufferedImage> playerFrames;
    private int currentFrame;
    private long startTime;

    public Batman(int health, int damage, int x, int y, int h, int w) {
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
        playerFrames = new ArrayList<>();
        bigImage = loadImage(walkFrames);
        for (int i = 1; i < 5; i++) {
            String file = walkFrames + i + ".png";
            loadFrames(file);
        }
    }


    public void bWalk(Graphics g) throws InterruptedException {
        try {
            background = ImageIO.read(new File("OpeningBackGround.jpg"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry! (Error Here)");
        }
        Graphics2D g2d = (Graphics2D) g;
        try {
            bWalk = ImageIO.read(new File("Batman/bIdle.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2d.drawImage(bWalk, 0, 0, 500, 500, null);
        try {
            bWalk = ImageIO.read(new File("Batman/Walk.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2d.drawImage(bWalk, 20, 0, 500, 500, null);
        try {
            bWalk = ImageIO.read(new File("Batman/Walk2.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2d.drawImage(bWalk, 40, 0, 500, 500, null);
        Thread.sleep(500);
        try {
            bWalk = ImageIO.read(new File("Batman/Walk3.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2d.drawImage(bWalk, 80, 0, 500, 500, null);
        Thread.sleep(500);
        try {
            bWalk = ImageIO.read(new File("Batman/Walk4.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2d.drawImage(bWalk, 160, 0, 500, 500, null);
        Thread.sleep(500);
        try {
            bWalk = ImageIO.read(new File("Batman/Walk5.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry!");
        }
        g2d.drawImage(bWalk, 200, 0, 500, 500, null);
        Thread.sleep(500);
    }

    public BufferedImage loadImage(String fileName) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(fileName));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadFrames(String fileName) {
        playerFrames.add(loadImage(fileName));
    }

    public void nextFrame() {
        currentFrame++;
        if (currentFrame == playerFrames.size()) {
            currentFrame = 0;
        }
    }

    public void bWalk2() {
        long currentTime = System.currentTimeMillis();
        double timeElapsed = (double) (currentTime - startTime) / 1000;
        if (timeElapsed > 0.3) {
            startTime = System.currentTimeMillis();
            // Load Next frame
        }
    }
}

