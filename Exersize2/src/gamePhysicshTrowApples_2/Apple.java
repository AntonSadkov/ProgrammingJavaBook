package gamePhysicshTrowApples_2;

class Apple {

    static final float EARTH_ACCEL = 9.8f;
    float mass;
    private float diameter = 1.0f;
    int x,y;

    public void setDiameter(float newDiameter) {
        diameter = newDiameter;
    }
    public float getDiameter() {
        return diameter;
    }
    float getWeight () {
        return mass * EARTH_ACCEL;
    }

    public void moveTo(int x, int y) {
        System.out.println("Moving apple to "+ x +", " + y +"\n_________");
        this.x = x;
        if (y > diameter / 2) {
            this.y = y;
        } else {
            this.y = (int)(diameter / 2);
        }
    }

    public void printDetails() {
        System.out.println("mass: " + mass);
        System.out.println("diameter: " + getDiameter());
        String size[] = AppleSize.getAppleSizes();
        if (diameter < 5.0f) {
            System.out.println("size: " + size[0]);
        } else if (diameter < 10.0f) {
            System.out.println("size: " + size[1]);
        } else {
            System.out.println("size: " + size[2]);
        }
        System.out.println("position: ("+ x +", "+ y +")");
        System.out.println("trajectories: " + getWeight());
    }

    public boolean isTouching(Apple other){
        double xdiff = x - other.x;
        double ydiff = y - other.y;
        double distance = Math.sqrt(xdiff * xdiff + ydiff * ydiff);
        if (distance < diameter / 2 + other.diameter / 2) {
            return true;
        }else {
            return false;
        }
    }
}