package Game;

import java.awt.*;

public class Apple implements GamePiece {
    float mass;
    float diameter = 1.0f;
    int x, y;
    int size;

    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;

    int centerX, centerY;
    int scaledLength;
    Rectangle boundingBox;
    GamePiece collided;
    public Apple(){
        this(MEDIUM);
    }
    public Apple(int size) {
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
        switch (size) {
            case SMALL:
                diameter = 0.9f;
                mass = 0.5f;
                break;
            case MEDIUM:
                diameter = 1.0f;
                mass = 1.0f;
                break;
            case LARGE:
                diameter = 1.1f;
                mass = 1.8f;
                break;
        }
        scaledLength = (int)(diameter * Field.APPLE_SIZE_IN_PIXELS + 0.5);
        boundingBox = new Rectangle(x, y, scaledLength, scaledLength);
    }
    public double getDiameter(){
        return diameter;
    }
    @Override
    public void setPosition(int x, int y){
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
    public boolean isTouching(GamePiece other) {
        double xdiff = x - other.getPositionX();
        double ydiff = y - other.getPositionY();
        double distance = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

        if (distance < diameter) {
            return true;
        } else {
            return false;
        }
    }
    public void printDetails(){
        System.out.println(" mass: " + mass);
        String[] niceNames = getAppleSizes();
        if (diameter < 0.5f){
            System.out.println(niceNames[SMALL]);
        } else if (diameter < 10.0f) {
            System.out.println(niceNames[MEDIUM]);
        } else {
            System.out.println(niceNames[LARGE]);
        }
        System.out.println(" position: (" + x +"," + y +")");
    }
    public static String[] getAppleSizes() {
        return new String[] { "SMALL", "MEDIUM", "LARGE" };
    }
}