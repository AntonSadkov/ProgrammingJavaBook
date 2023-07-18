package GamePhysicsThrowApples_2;

public class PrintAppleDetails_1 {
    public static void main(String[] args) {

        Field_1 f = new Field_1();

        f.setupApples();

        System.out.println("Apple a1:\n_________");
        f.a1.printDetails();
        f.a1.moveTo(f.a1.x, f.a1.y);

        System.out.println("\nApple a2:\n_________");
        f.a2.printDetails();
        f.a2.moveTo(f.a2.x, f.a2.y);

        f.detectCollisions();
    }
}