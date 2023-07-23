package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;

public class Physicist implements GamePiece{
    Color color;
    int centerX, centerY;
    float aimingAngle;
    float aimingForce;
    Field field;

    int x, y;

    private final int physicistHeight = (int)(1.5 * Field.PHYSICIST_SIZE_IN_PIXELS);
    private Rectangle boundingBox;
    public Physicist() {
        this(BLUE);
    }
    public Physicist(Color color) {
        setColor(color);
        aimingAngle = 90.0f;
        aimingForce = 50.0f;
    }

    public void setAimingAngle(Float angle) {
        aimingAngle = angle;
    }
    public void setAimingForce(Float force) {
        if (force < 0) {
            force = 0.0f;
        }
        aimingForce = force;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    @Override
    public void setPosition(int x, int y) {
        int offset = (int)(Field.PHYSICIST_SIZE_IN_PIXELS / 2.0f);

        this.centerX = x;
        this.centerY= y;
        this.x = x - offset;
        this.y = y - offset;
        boundingBox = new Rectangle(x, y, Field.PHYSICIST_SIZE_IN_PIXELS, physicistHeight);
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
    public void setField(Field field) {
        this.field = field;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillArc(x, y, Field.PHYSICIST_SIZE_IN_PIXELS, Field.PHYSICIST_SIZE_IN_PIXELS, 0, 180);
        g.fillRect(x, centerY, Field.PHYSICIST_SIZE_IN_PIXELS, Field.PHYSICIST_SIZE_IN_PIXELS);
    }
    @Override
    public boolean isTouching(GamePiece otherPiece) {
        return false;
    }
}