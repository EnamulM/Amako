import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Sukuna extends JPanel implements KeyListener {
    private int health;
    private int damage;
    private int x;
    private int y;
    private int startY;
    private BufferedImage background;
    private BufferedImage idleFrame;
    private ArrayList<BufferedImage> walkFrames;
    private ArrayList<BufferedImage> jumpFrames;
    private ArrayList<BufferedImage> punchFrames;
    private int currentWalkFrame;
    private int currentJumpFrame;
    private int currentPunchFrame;
    private boolean isJumping;
    private boolean isPunching;
    private Timer jumpTimer;
    private Timer walkTimer;
    private Timer punchTimer;
    private int jumpFrameCount;

    private ArrayList<Integer> pressedKeys;

    public Sukuna(int health, int damage, int x, int y) {
        this.health = health;
        this.damage = damage;
        this.x = 700;
        this.y = 500;
        this.startY = 500;
        walkFrames = new ArrayList<>();
        jumpFrames = new ArrayList<>();
        punchFrames = new ArrayList<>();
        loadWalkFrames();
        loadJumpFrames();
        loadPunchFrames();
        currentWalkFrame = 0;
        currentJumpFrame = 0;
        currentPunchFrame = 0;
        isJumping = false;
        isPunching = false;
        jumpFrameCount = 0;
        pressedKeys = new ArrayList<>();
        try {
            background = ImageIO.read(new File("BackgroundImages/OpeningBackGround.jpg"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry! Background");
        }
        try {
            idleFrame = ImageIO.read(new File("Sukuna/SIdle.png"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry! Idle");
        }

        this.setFocusable(true);
        this.addKeyListener(this);
        Timer game = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                repaint();
            }
        });
        game.start();
    }

    private void loadWalkFrames() {
        for (int i = 1; i <= 10; i++) {
            String file = "Sukuna/SWalk" + i + ".png";
            try {
                BufferedImage frame = ImageIO.read(new File(file));
                walkFrames.add(frame);
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
        }
    }

    private void loadJumpFrames() {
        for (int i = 1; i <= 2; i++) {
            String file = "Sukuna/SJump" + i + ".png";
            try {
                BufferedImage frame = ImageIO.read(new File(file));
                jumpFrames.add(frame);
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
        }
    }

    private void loadPunchFrames() {
        for (int i = 1; i <= 3; i++) {
            String file = "Sukuna/SPunch" + i + ".png";
            try {
                BufferedImage frame = ImageIO.read(new File(file));
                punchFrames.add(frame);
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
        }
    }


    private void jump() {
        if (isJumping) {
            return;
        }
        isJumping = true;
        jumpFrameCount = 0;
        jumpTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jumpFrameCount < jumpFrames.size()) {
                    currentJumpFrame = jumpFrameCount;
                    y = startY - (int) (125 * Math.sin(Math.PI * jumpFrameCount / (jumpFrames.size() - 1)));
                    jumpFrameCount++;
                } else {
                    ((Timer) e.getSource()).stop();
                    isJumping = false;
                    y = startY;
                }
            }
        });
        jumpTimer.start();
    }
    private void startWalking() {
        if (walkTimer == null || !walkTimer.isRunning()) {
            walkTimer = new Timer(150, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!pressedKeys.isEmpty()) {
                        currentWalkFrame = (currentWalkFrame + 1) % walkFrames.size();
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            walkTimer.start();
        }
    }

    private void punch() {
        if (isPunching) {
            return;
        }
        isPunching = true;
        currentPunchFrame = 0;
        punchTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPunchFrame < punchFrames.size()) {
                    currentPunchFrame++;
                } else {
                    ((Timer) e.getSource()).stop();
                    isPunching = false;
                }
            }
        });
        punchTimer.start();
    }


    private void updateGame() {
        if (pressedKeys.contains(KeyEvent.VK_I)) {
            jump();
        }
        if (pressedKeys.contains(KeyEvent.VK_K)) {
            y += 5;
            startWalking();
        }
        if (pressedKeys.contains(KeyEvent.VK_J)) {
            x -= 5;
            startWalking();
        }
        if (pressedKeys.contains(KeyEvent.VK_L)) {
            x += 5;
            startWalking();
        }
        if (pressedKeys.contains(KeyEvent.VK_O)) {
            punch();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        if (isJumping && currentJumpFrame < jumpFrames.size()) {
            g.drawImage(jumpFrames.get(currentJumpFrame), x, y, 100, 100, null);
        } else if (isPunching && currentPunchFrame < punchFrames.size()) {
            g.drawImage(punchFrames.get(currentPunchFrame), x, y, 100, 100, null);
        } else if (!pressedKeys.isEmpty()) {
            g.drawImage(walkFrames.get(currentWalkFrame), x, y, 100, 100, null);
        } else {
            g.drawImage(idleFrame, x, y, 100, 100, null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedKeys.contains(e.getKeyCode())) {
            pressedKeys.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(Integer.valueOf(e.getKeyCode()));
        if (pressedKeys.isEmpty()) {
            isJumping = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}