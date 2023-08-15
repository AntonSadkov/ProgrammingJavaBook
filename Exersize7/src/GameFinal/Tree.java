package GameFinal;

import java.awt.*;

public class Tree implements GamePiece{

    int x, y;

    private Color leafColor = Color.GREEN.darker();
    private Color  trunkColor = new Color(101, 67, 33);
    private int trunkWidth = (int)(Field.TREE_WIDTH_IN_PIXELS * 0.2);
    private int trunkHeight = (int)(Field.TREE_WIDTH_IN_PIXELS * 1.1);
    private int trunkX, trunkY;

    private Rectangle boundingBox;

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        trunkX = x + (Field.TREE_WIDTH_IN_PIXELS - trunkWidth) / 2;
        trunkY = y + 2 * Field.TREE_WIDTH_IN_PIXELS - trunkHeight;
        boundingBox = new Rectangle(x, y, Field.TREE_WIDTH_IN_PIXELS, Field.TREE_HEIGHT_IN_PIXELS);
    }

    @Override
    public int getPositionX() {
        return x;
    }

    @Override
    public int getPositionY() {
        return y;
    }

    @Override
    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(trunkColor);
        g.fillRect(trunkX, trunkY, trunkWidth, trunkHeight);
        g.setColor(leafColor);
        g.fillOval(x, y, Field.TREE_WIDTH_IN_PIXELS, Field.TREE_WIDTH_IN_PIXELS);
    }

    @Override
    public boolean isTouching(GamePiece otherPiece) {
        if (this == otherPiece){
            return false;
        }
        return GameUtilities.doesBoxIntersect(boundingBox, otherPiece.getBoundingBox());
    }
}