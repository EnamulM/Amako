import java.awt.Graphics;
import java.awt.Image;
public class Character {
    private int health;
    private int damage;
    private int shortRange;
    private int longRange;
    private int x;
    private int y;
    public Character(int health, int damage, int shortRange, int longRange, int x, int y) {
        this.health = health;
        this.damage = damage;
        this.shortRange = shortRange;
        this.longRange = longRange;
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

    public int getShortRange() {
        return shortRange;
    }

    public void setShortRange(int shortRange) {
        this.shortRange = shortRange;
    }

    public int getLongRange() {
        return longRange;
    }

    public void setLongRange(int longRange) {
        this.longRange = longRange;
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
