
import java.awt.Graphics;
import java.awt.Image;


public abstract class Entity {
    
    int x; int y;
    
    
    public abstract void draw(Graphics g);
    
    public abstract void tic(long millis);

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
