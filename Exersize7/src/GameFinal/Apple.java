package GameFinal;

import java.awt.*;

public class Apple implements GamePiece{
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;

    int size;
    double diameter;
    double mass;
    int centerX, centerY;
    Physicist myPhysicist;

    long lastStep;
    float velocityX, velocityY;

    int x, y;
    int scaledLength;

    Rectangle boundingBox;

    GamePiece collided;

    public Apple(Physicist owner){
        this(owner, MEDIUM);
    }

    public Apple(Physicist owner, int size){
        myPhysicist = owner;
        setSize(size);
    }

    public void setSize(int size){
        if (size < SMALL){
            size = SMALL;
        }
        if (size > LARGE){
            size = LARGE;
        }
        this.size = size;
        switch (size){
            case SMALL:
                diameter = 0.9;
                mass = 0.5;
                break;
            case MEDIUM:
                diameter = 1.0;
                mass = 1.0;
                break;
            case LARGE:
                diameter = 1.1;
                mass = 1.8;
                break;
        }
        scaledLength = (int)(diameter * Field.APPLE_SIZE_IN_PIXELS + 0.5);
        boundingBox = new Rectangle(x, y, scaledLength, scaledLength);
    }

    public double getDiameter(){
        return diameter;
    }

    @Override
    public void setPosition(int x, int y) {
        int offset = (int)(diameter * Field.APPLE_SIZE_IN_PIXELS / 2);

        this.centerX = x;
        this.centerY = y;
        this.x = x - offset;
        this.y = y - offset;
        boundingBox = new Rectangle(x, y, scaledLength, scaledLength);
    }

    @Override
    public int getPositionX() {
        return centerX;
    }

    @Override
    public int getPositionY() {
        return centerY;
    }

    @Override
    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, scaledLength, scaledLength);
    }

    @Override
    public boolean isTouching(GamePiece otherPiece) {
        if (this == otherPiece || myPhysicist == otherPiece || collided != null){
            return false;
        }
        if (otherPiece instanceof Apple) {
            Apple otherApple = (Apple) otherPiece;
            int v = this.y - otherPiece.getPositionY();
            int h = this.x - otherPiece.getPositionX();
            double distance = Math.sqrt(v * v + h * h);

            double myRadius = diameter * Field.APPLE_SIZE_IN_PIXELS / 2;
            double otherRadius = otherApple.getDiameter() * Field.APPLE_SIZE_IN_PIXELS / 2;

            if (distance < (myRadius + otherRadius)) {
                setCollided(otherPiece);
                otherApple.setCollided(this);
                return true;
            }
            return false;
        }
        if (GameUtilities.doesBoxIntersect(boundingBox, otherPiece.getBoundingBox())){
            setCollided(otherPiece);
            return true;
        }
        return false;
    }

    public GamePiece getCollidedPiece() {
        return collided;
    }

    public void setCollided(GamePiece otherPiece){
        this.collided = otherPiece;
    }

    public void toss(float angle, float velocity){
        lastStep = System.currentTimeMillis();
        double radians = angle / 180 * Math.PI;
        velocityX = (float)(velocity * Math.cos(radians) / mass);
        velocityY = (float)(-velocity * Math.sin(radians) / mass);
    }

    public void step() {
        if (lastStep > 0) {
            long now = System.currentTimeMillis();
            float slice = (now - lastStep) / 1000.0f;
            velocityY = velocityY + (slice * Field.GRAVITY);
            int newX = (int)(centerX + velocityX);
            int newY = (int)(centerY + velocityY);
            setPosition(newX, newY);
        }
    }
}