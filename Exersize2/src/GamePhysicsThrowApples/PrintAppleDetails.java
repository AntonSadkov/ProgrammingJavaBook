package GamePhysicsThrowApples;

import java.io.File;

public class PrintAppleDetails {
    public static void main(String[] args) {

        Field f = new Field();

        f.setupApples();
        System.out.println("Apple a1:");
        f.a1.printDetails();

        System.out.println("Apple a2:");
        f.a2.printDetails();
        f.detectCollisions();
    }
}