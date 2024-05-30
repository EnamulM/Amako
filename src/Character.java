import java.awt.Graphics;
import java.awt.Image;
public class Character {
    private int health;
    private int damage;
    private int x;
    private int y;
    private int h;
    private int w;
    private float speed;
    private float jumpStrength ;
    public Character(int health, int damage, int x, int y,int h, int w) {
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
    } public void moveLeft(int distance) {
        x -= distance;
    }

    public void moveRight(int distance) {
        x += distance;
    }

    public void moveUp(int distance) {
        y -= distance;
    }

    public void moveDown(int distance) {
        y += distance;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
