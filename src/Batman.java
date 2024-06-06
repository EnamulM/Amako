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
    private ArrayList<BufferedImage> punchFrames;
    private ArrayList<BufferedImage> kickFrames;
    private int currentWalkFrame;
    private int currentJumpFrame;
    private int currentPunchFrame;
    private int currentKickFrame;
    private boolean isJumping;
    private boolean isPunching;
    private boolean isKicking;
    private Timer jumpTimer;
    private Timer walkTimer;
    private Timer punchTimer;
    private Timer kickTimer;
    private int jumpFrameCount;

    private ArrayList<Integer> pressedKeys;
    private boolean punchHandled = false;
    private boolean kickHandled = false;
    private boolean batmanLost = false;

    public Batman(int health, int damage, int x, int y) {
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = 510;
        this.startY = 510;
        walkFrames = new ArrayList<>();
        jumpFrames = new ArrayList<>();
        punchFrames = new ArrayList<>();
        kickFrames = new ArrayList<>();
        loadWalkFrames();
        loadJumpFrames();
        loadPunchFrames();
        loadKickFrames();
        currentWalkFrame = 0;
        currentJumpFrame = 0;
        currentPunchFrame = 0;
        currentKickFrame = 0;
        isJumping = false;
        isPunching = false;
        isKicking = false;
        jumpFrameCount = 0;
        pressedKeys = new ArrayList<>();
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

    private void loadPunchFrames() {
        for (int i = 1; i <= 2; i++) {
            String file = "Batman/Punch" + i + ".png";
            try {
                BufferedImage frame = ImageIO.read(new File(file));
                punchFrames.add(frame);
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
        }
    }

    private void loadKickFrames() {
        for (int i = 1; i <= 2; i++) {
            String file = "Batman/Kick" + i + ".png";
            try {
                BufferedImage frame = ImageIO.read(new File(file));
                kickFrames.add(frame);
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
                    punchHandled = false;
                }
            }
        });
        punchTimer.start();
    }

    private void kick() {
        if (isKicking) {
            return;
        }
        isKicking = true;
        currentKickFrame = 0;
        kickTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentKickFrame < kickFrames.size()) {
                    currentKickFrame++;
                } else {
                    ((Timer) e.getSource()).stop();
                    isKicking = false;
                    kickHandled = false;
                }
            }
        });
        kickTimer.start();
    }

    private void updateGame() {
        if (pressedKeys.contains(KeyEvent.VK_W)) {
            jump();
        }
        if (pressedKeys.contains(KeyEvent.VK_S)) {
            y += 5;
            startWalking();
        }
        if (pressedKeys.contains(KeyEvent.VK_A)) {
            x -= 5;
            startWalking();
        }
        if (pressedKeys.contains(KeyEvent.VK_D)) {
            x += 5;
            startWalking();
        }
        if (pressedKeys.contains(KeyEvent.VK_E)) {
            punch();
        }
        if (pressedKeys.contains(KeyEvent.VK_R)) {
            kick();
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
        } else if (isKicking && currentKickFrame < kickFrames.size()) {
            g.drawImage(kickFrames.get(currentKickFrame), x, y, 100, 100, null);
        } else if (!pressedKeys.isEmpty()) {
            g.drawImage(walkFrames.get(currentWalkFrame), x, y, 100, 100, null);
        } else {
            g.drawImage(idleFrame, x, y, 100, 100, null);
        }
    }
    public void dealDamage(Sukuna sukuna) {
        if (isHitting(sukuna)) {
            if (isPunching && !punchHandled) {
                sukuna.takeDamage(damage);
                punchHandled = true;
            }
            if (isKicking && !kickHandled) {
                sukuna.takeDamage(damage);
                kickHandled = true;
            }
        }
    }
    private boolean isHitting(Sukuna sukuna) {
        Rectangle batmanBounds = new Rectangle(x, y, 100, 100);
        Rectangle sukunaBounds = new Rectangle(sukuna.getX(), sukuna.getY(), 100, 100);
        return batmanBounds.intersects(sukunaBounds);
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
    public boolean isPunching() {
        return isPunching;
    }

    public boolean isKicking() {
        return isKicking;
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(health);
        if (health <= 0) {
            System.out.println("Batman has been defeated");
            batmanLost = true;
        }
    }

    public int getDamage() {
        return damage;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean batmanLost(){
       return batmanLost;
    }
}