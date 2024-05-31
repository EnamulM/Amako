import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Batman extends JPanel implements KeyListener {
    private int health;
    private int damage;
    private int x;
    private int y;
    private BufferedImage background;
    private BufferedImage idleFrame;

    private ArrayList<BufferedImage> walkFrames;
    private static Image bWalk;

    private int currentFrame;
    private long startTime;
    private boolean isMoving;

    public Batman(int health, int damage, int x, int y) {
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
        walkFrames = new ArrayList<>();
        loadWalkFrames();
        currentFrame = 0;
        startTime = System.currentTimeMillis();
        isMoving = false;

        try {
            background = ImageIO.read(new File("BackgroundImages/OpeningBackGround.jpg"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry! Background");
        }
        try {
            idleFrame = ImageIO.read(new File("Batman/Idle.png"));

        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry! Idle");
        }

        this.setFocusable(true);
        this.addKeyListener(this);
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
    private void loadWalkFrames() {
        for (int i = 1; i <= 5; i++) {
            String file = "Batman/Walk" + i + ".png";
            try {
                BufferedImage frame = ImageIO.read(new File(file));
                walkFrames.add(frame);
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
        }
    }

    private void nextFrame() {
        currentFrame++;
        if (currentFrame == walkFrames.size()) {
            currentFrame = 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        if (isMoving) {
            long currentTime = System.currentTimeMillis();
            double timeElapsed = (double) (currentTime - startTime) / 1000;
            if (timeElapsed > 0.3) {
                startTime = System.currentTimeMillis();
                nextFrame();
            }
            g.drawImage(walkFrames.get(currentFrame), x, y, 100, 100, null);
        } else {
            g.drawImage(idleFrame, x, y, 100, 100, null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                y -= 10;
                isMoving = true;
                break;
            case KeyEvent.VK_S:
                y += 10;
                isMoving = true;
                break;
            case KeyEvent.VK_A:
                x -= 10;
                isMoving = true;
                break;
            case KeyEvent.VK_D:
                x += 10;
                isMoving = true;
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isMoving = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

