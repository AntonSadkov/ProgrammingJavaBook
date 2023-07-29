package GameUpdate;

import java.awt.*;

public class GameUtilities {
    static boolean isPointInsideBox(int x, int y, Rectangle box){

        if (x >= box.x && x <= (box.x + box.width)) {
            if (y >= box.y && y <= (box.y + box.height)) {
                return true;
            }
        }
        return false;
    }
    static boolean doesBoxIntersect(Rectangle box, Rectangle other){
        int x1 = box.x;
        int y1 = box.y;
        int x2 = x1 + box.width;
        int y2 = y1 + box.height;
        if (isPointInsideBox(x1, y1, other)) {
            // upper left
            return true;
        } else if (isPointInsideBox(x1, y2, other)){
            // lower left
            return true;
        } else if (isPointInsideBox(x2, y1, other)){
            //upper right
            return true;
        } else if (isPointInsideBox(x2, y2, other)){
            // lower right
            return true;
        }
        return false;
    }
    public static boolean doBoxesIntersect(Rectangle box1, Rectangle box2){
        if (doesBoxIntersect(box1, box2)){
            return true;
        } else if (doesBoxIntersect(box2, box1)){
            return true;
        }
        return false;
    }
}