
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class EightBall extends MoveableEntity {

    private final long shakeTime = 2000;
    private final int maxRadius = 150;
    private final int ballRadius = 200;
    private final int triangleLength = 150;
    private final Color[] ballColor = {Color.BLACK, Color.PINK, Color.BLUE, Color.CYAN, Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA};
    private final Color innerColor = new Color(33,0,102);
    
    long strobeTime = 100;
    int colorIndex = 0;
    int centerX, centerY;
    int destX, destY;
    long activeTimer = 0;
    boolean active = false;
    boolean strobe = false;
    long strobeTimer = 0;
    int height;
    BufferedImage image;

    private final String[] answers = {"Try Again", "Absolutely", "It Might.Be So", "Closer Than.You Think", "Ask Again",
        "Definitely.Not", "Why Not", "You.Know It", "Obviously","Yes","No","Bitch.Please", "Yeah Right", "Fuck No",
    "Inconceivable"};
    String answer = "";

    public EightBall(int x, int y) {
        centerX = x;
        centerY = y;
        destX = x;
        destX = centerX;
        destX = centerY;
        this.x = centerX;
        this.y = centerY;

        refreshImage();

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x - image.getWidth(null) / 2, y - image.getHeight(null) / 2, null);
        g.setColor(Color.BLACK);
        int height = y - (this.height/4) + 5 + g.getFontMetrics().getHeight();
        String[] split = answer.split("[.]");
        for (String s : split) {
            g.drawString(s, x - g.getFontMetrics().stringWidth(s) / 2, height);
            height += g.getFontMetrics().getHeight();
        }
    }

    @Override
    public void tic(long millis) {
        super.tic(millis);
        if(strobe){
            strobeTimer += millis;
            if(strobeTimer >= strobeTime){
                right();
                strobeTimer -= strobeTime;
            }
        }
        //Shaking
        if (activeTimer <= 0) {
            if (active) {
                active = false;
                moveTo(centerX, centerY);
                randomAnswer();
            }
            if (arrived()) {
                activeTimer = 0;
                x = centerX;
                y = centerY;
                speedX = 0;
                speedY = 0;
            }
        }
        if (activeTimer > 0) {
            activeTimer -= millis;

            if (arrived()) {
                if (destX != centerX) {
                    destX = centerX;
                    destY = centerY;
                    moveTo(destX, destY);
                } else {
                    moveToRandom();
                }
            }
        }
    }

    private boolean arrived() {
        int signSX = (int) (speedX / Math.abs(speedX));
        int signSY = (int) (speedY / Math.abs(speedY));

        int signX = -destX + x;
        if (signX != 0 && Math.abs(signX) < maxRadius) {
            signX = signX / Math.abs(signX);
        } else {
            return true;
        }
        int signY = -destY + y;
        if (signY != 0 && Math.abs(signY) < maxRadius) {
            signY = signY / Math.abs(signY);
        } else {
            return true;
        }

        return (signY == signSY && signX == signSX);
    }

    private void moveTo(int x, int y) {
        double velocity = Math.random() * 500 + 500;

        double dx = -this.x + x;
        double dy = -this.y + y;

        double angle = Math.atan2(dy, dx);

        this.speedX = (float) (Math.cos(angle) * velocity);
        this.speedY = (float) (Math.sin(angle) * velocity);
    }

    private void moveToRandom() {
        int radius = (int) (Math.random() * maxRadius);
        double angle = Math.random() * 2 * Math.PI;
        destX = (int) (radius * Math.cos(angle)) + centerX;
        destY = (int) (radius * Math.sin(angle)) + centerY;
        moveTo(destX, destY);
    }

    public void right() {
        colorIndex++;
        if (colorIndex >= ballColor.length) {
            colorIndex = 0;
        }
        refreshImage();

    }

    public void left() {
        colorIndex--;
        if (colorIndex < 0) {
            colorIndex = ballColor.length - 1;
        }
        refreshImage();
    }

    public void refreshImage() {
        image = new BufferedImage(2 * ballRadius, 2 * ballRadius, BufferedImage.SCALE_SMOOTH);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 2 * ballRadius, 2 * ballRadius);
        g.setColor(ballColor[colorIndex]);
        g.fillOval(0, 0, 2 * ballRadius, 2 * ballRadius);
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, 2 * ballRadius, 2 * ballRadius);
        height = (int)(Math.pow(Math.pow(triangleLength,2)-Math.pow(triangleLength/2, 2),.5));
        g.setColor(innerColor);
        int spacing = 5;
        g.fillOval(ballRadius-height/2-spacing, ballRadius-height/2-spacing, height+2*spacing, height+2*spacing);
        g.setColor(Color.BLACK);
        g.drawOval(ballRadius-height/2-spacing, ballRadius-height/2-spacing, height+2*spacing, height+2*spacing);
        Polygon p = new Polygon();
        double startA = Math.PI/2;
        for(int i=0; i<3; i++){
            double angle = startA + ((i*2*Math.PI)/3);
            p.addPoint(ballRadius+(int)((height/2)*Math.cos(angle)), ballRadius+(int)((height/2)*Math.sin(angle)));
        }
        g.setColor(Color.WHITE);
        g.fillPolygon(p);
        g.setColor(Color.BLACK);
        g.drawPolygon(p);
        g.dispose();
    }

    private void clearAnswer() {
        answer = "";
    }

    private void randomAnswer() {
        answer = answers[(int) (Math.random() * answers.length)];
    }

    public void activate() {
        activeTimer = shakeTime;
        moveToRandom();
        active = true;
        clearAnswer();
    }

    public void strobe(){
        strobe = !strobe;
        if(!strobe){
            colorIndex = 0;
            refreshImage();
        }
    }
    
    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.x = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.y = centerY;
    }

    void strobeUp() {
        if(strobeTime < 40000)
            strobeTime += 50;
    }

    void strobeDown() {
        strobeTime -= 50;
        if(strobeTime <= 50)
            strobeTime = 50;
    }

}
