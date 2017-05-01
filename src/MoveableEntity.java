
import java.awt.Image;


public abstract class MoveableEntity extends Entity{
    
    float persicionX=0; float persicionY=0;
    float speedX = 0; float speedY = 0;
    float accelerationX=0; float accelerationY=0;
    
    //Handles movement
    @Override
    public void tic(long millis) {
        speedX += accelerationX*(millis/1000.0);
        speedY += accelerationY*(millis/1000.0);
        persicionX += speedX*(millis/1000.0);
        persicionY += speedY*(millis/1000.0);
        x += (int)persicionX;
        y += (int)persicionY;
        persicionX -= (int)persicionX;
        persicionY -= (int)persicionY;
    }

    public float getPersicionX() {
        return persicionX;
    }

    public void setPersicionX(float persicionX) {
        this.persicionX = persicionX;
    }

    public float getPersicionY() {
        return persicionY;
    }

    public void setPersicionY(float persicionY) {
        this.persicionY = persicionY;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
    
    
    
}
