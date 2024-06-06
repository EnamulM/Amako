import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class DrawPanel extends JPanel implements MouseListener, KeyListener {
    private static int talking;
    private static Image oldMan1;
    private static Image oldMan2;
    private static Image oldMan3;
    private static Image oldMan4;
    private static Image oldMan5;
    private long startTime;

    private Rectangle button;
    private Image background;
    private JPanel mainPanel;
    private boolean onStartingScreen = true;
    private boolean next1 = false;
    private boolean next2 = false;
    private Batman batman;
    private Sukuna sukuna;

    String filepath = "Sound/MKTheme.wav";
    public static void PlayMusic(String location){
        try {
            File musicPath = new File(location);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Can't find file");
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public DrawPanel() {
        button = new Rectangle(565, 310, 805, 265);
        addMouseListener(this);
        try {
            background = ImageIO.read(new File("OpeningBackGround.jpg"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry! (Error Here)");
        }
        batman = new Batman(200, 20, 50, 400);
        sukuna = new Sukuna(200,20,50,500);
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        setBackground(Color.black);
        g.setFont(new Font("Times New Roman", Font.BOLD, 200));
        g.drawString("AMAKO", 575, 500);
        g.drawRect((int) button.getX(), (int) button.getY(), (int) button.getWidth(), (int) button.getHeight());
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        if (!onStartingScreen && !next1 && !next2) {
            batman.paintComponent(g);
            sukuna.paintComponent(g);
            batman.dealDamage(sukuna);
            sukuna.dealDamage(batman);
        }
        if(batman.batmanLost()){
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
            g.setFont(new Font("Times New Roman", Font.BOLD, 100));
            g.drawString("Batman Has Lost", 375, 500);
        }
        if(sukuna.sukunaLost()){
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
            g.setFont(new Font("Times New Roman", Font.BOLD, 100));
            g.drawString("Sukuna Has Lost", 375, 500);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (button.contains(e.getPoint())) {
            try {
                if (onStartingScreen){
                    background = ImageIO.read(new File("BackgroundImages/OpeningBackGround.jpg"));
                    onStartingScreen = false;
                    next1 = true;
                    repaint();
                } else if(next1){
                    talk(getGraphics());
                    talk(getGraphics());
                    next1 = false;
                    next2 = true;
                } else if(next2){
                    System.out.println("Works");
                    introduction(getGraphics());
                    next2 = false;
                    repaint();
                }
            } catch (IOException ex) {
                System.err.println("Error loading background image: " + ex.getMessage());
            }
        }
    }

    public void talk(Graphics g){
        try {
            Graphics2D g2d = (Graphics2D) g;
            try {
                oldMan1 = ImageIO.read(new File("OldMan/Talking1.png"));
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            g2d.drawImage(oldMan1, 0, 0, 500, 500, null);
            g.setFont(new Font("Times New Roman", Font.BOLD, 30));
            g.drawString("Fight to the Death! ", 575, 500);
            g.drawString("Batman! Use W to Jump, A to Move Left, D to move Right. Punch Using E and Kick using R", 0, 600);
            g.drawString("Sukuna! Use I to crouch, J to move Left, L to move Right, and O to Punch!", 0, 700);

            Thread.sleep(500);
            try {
                oldMan2 = ImageIO.read(new File("OldMan/Talking2.png"));
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            g2d.drawImage(oldMan2, 0, 0, 500, 500, null);
            Thread.sleep(500);
            try {
                oldMan3 = ImageIO.read(new File("OldMan/Talking3.png"));
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            g2d.drawImage(oldMan3, 0, 0, 500, 500, null);
            Thread.sleep(500);
            try {
                oldMan4 = ImageIO.read(new File("OldMan/Talking4.png"));
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            g2d.drawImage(oldMan4, 0, 0, 500, 500, null);
            Thread.sleep(500);
            try {
                oldMan5 = ImageIO.read(new File("OldMan/Talking5.png"));
            } catch (IOException e) {
                System.out.println("Error Loading Image! Sorry!");
            }
            g2d.drawImage(oldMan5, 0, 0, 500, 500, null);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep error");
        }
    }

    public void introduction(Graphics g) {
        try{
            background = ImageIO.read(new File("BackgroundImages/Background1.jpg"));
        } catch(IOException e){
            System.out.println("Error Loading Image! Sorry!");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        batman.keyPressed(e);
        sukuna.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        batman.keyReleased(e);
        sukuna.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        batman.keyTyped(e);
        sukuna.keyTyped(e);
    }
    private boolean isColliding(Batman batman, Sukuna sukuna) {
        Rectangle batmanBounds = new Rectangle(batman.getX(), batman.getY(), 100, 100);
        Rectangle sukunaBounds = new Rectangle(sukuna.getX(), sukuna.getY(), 100, 100);
        return batmanBounds.intersects(sukunaBounds);
    }
}