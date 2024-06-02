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
    private int startY;
    private BufferedImage background;
    private BufferedImage idleFrame;

    private ArrayList<BufferedImage> walkFrames;
    private ArrayList<BufferedImage> jumpFrames;

    private int currentFrame;
    private long startTime;
    private boolean isMoving;
    private boolean isJumping;
    private int jumpHeight = 30;
    private int jumpSpeed = 5;
    private int jumpFrameCount;

    public Batman(int health, int damage, int x, int y) {
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.startY = y;
        walkFrames = new ArrayList<>();
        jumpFrames = new ArrayList<>();
        loadWalkFrames();
        loadJumpFrames();
        currentFrame = 0;
        startTime = System.currentTimeMillis();
        isMoving = false;
        isJumping = false;
        jumpFrameCount = 0;

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

    private void loadJumpFrames() {
        for (int i = 1; i <= 5; i++) {
            String file = "Batman/Jump" + i + ".png";
            try {
                BufferedImage frame = ImageIO.read(new File(file));
                jumpFrames.add(frame);
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
        }
    }

    private void nextFrame(ArrayList<BufferedImage> frames) {
        currentFrame++;
        if (currentFrame == frames.size()) {
            currentFrame = 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        if (isJumping) {
            long currentTime = System.currentTimeMillis();
            double timeElapsed = (double) (currentTime - startTime) / 1000;
            if (timeElapsed > 0.1) {
                startTime = System.currentTimeMillis();
                nextFrame(jumpFrames);
                jumpFrameCount++;
                if (jumpFrameCount < jumpFrames.size() / 2) {
                    y -= jumpSpeed;
                } else if (jumpFrameCount < jumpFrames.size()) {
                    y += jumpSpeed;
                } else {
                    y = startY;
                    isJumping = false;
                    jumpFrameCount = 0;
                }
            }
            g.drawImage(jumpFrames.get(currentFrame), x, y, 100, 100, null);
        } else if (isMoving) {
            long currentTime = System.currentTimeMillis();
            double timeElapsed = (double) (currentTime - startTime) / 1000;
            if (timeElapsed > 0.3) {
                startTime = System.currentTimeMillis();
                nextFrame(walkFrames);
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
                if (!isJumping) {
                    isJumping = true;
                    currentFrame = 0;
                    startTime = System.currentTimeMillis();
                }
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
