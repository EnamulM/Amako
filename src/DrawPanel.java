import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DrawPanel extends JPanel implements MouseListener{
    private static int talking;
    private static Image oldMan1;
    private static Image oldMan2;
    private static Image oldMan3;
    private static Image oldMan4;
    private static Image oldMan5;
    private Image introduction1;


    private Rectangle button;
    private Image background;
    private JPanel mainPanel;
    private boolean onStartingScreen = true;
    private boolean next1 = false;
    private boolean next2 = false;



    public DrawPanel() {
        button = new Rectangle(565, 310, 805, 265);
        addMouseListener(this);
        try {
            background = ImageIO.read(new File("OpeningBackGround.jpg"));
        } catch (IOException e) {
            System.out.println("Error Loading Image! Sorry! (Error Here)");
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        setBackground(Color.black);
        g.setFont(new Font("Times New Roman", Font.BOLD, 200));
        g.drawString("AMAKO", 575, 500);
        g.drawRect((int) button.getX(), (int) button.getY(), (int) button.getWidth(), (int) button.getHeight());
        g.drawImage(background, 0, 0, getWidth(), getHeight(),null);
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
                }
                else if(next1){
                    talk(getGraphics());
                    talk(getGraphics());
                    next1 = false;
                    next2 = true;
                }
                else if(next2){
                    System.out.println("Works");
                    introduction(getGraphics());
                    next2 = false;
                    Batman batman = new Batman(200,200, 20, 50, 0,0,200, 200);
                    batman.bWalk(getGraphics());
                }
            } catch (IOException | InterruptedException ex) {
                // Handle exception
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
                g.setFont(new Font("Times New Roman", Font.BOLD, 100));
                g.drawString("The Realm Needs Your Help! ", 575, 500);
                g.drawString("An evil being has overtaken the land!", 200, 600);
                g.drawString("We need you to free us! Press to Continue", 0, 700);

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
        }
        catch(IOException e){
            System.out.println("Error Loading Image! Sorry!");
        }
        g.drawImage(introduction1, 0, 0, getWidth(), getHeight(),null);


    }

        @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}

