package GamePhysicsThrowApples_2;

public class Field_1 {
    Apple a1 = new Apple();
    Apple a2 = new Apple();

    public void setupApples() {
        a1.setDiameter(4.0f);
        a1.mass = 5.0f;
        a1.x = 25;
        a1.y = 45;

        a2.setDiameter(10.5f);
        a2.mass = 10.0f;
        a2.x = 20;
        a2.y = 45;
    }

    public void detectCollisions() {
        if(a1.isTouching(a2)) {
            System.out.println("\nCollision detected!\n_________");
        } else {
            System.out.println("\nApples are not touching\n_________");
        }
    }
}