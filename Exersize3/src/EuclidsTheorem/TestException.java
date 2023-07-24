package EuclidsTheorem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestException {
    public static void main(String[] args) {
        int a, b;

        try {
            Scanner in = new Scanner(System.in);
            System.out.print("a = ");
            a = in.nextInt();
            System.out.print("b = ");
            b = in.nextInt();
        } catch (InputMismatchException ime) {
            System.err.println("Пиши цифры!");
            return;
        }

        if (args.length == 2) {
            try {
                a = Integer.parseInt(args[0]);
                b = Integer.parseInt(args[1]);
            } catch (NumberFormatException nfe) {
                System.err.println("""
                        Arguments were not both numbers.
                        Using defaults.""");
            } finally {
                System.out.println("Всё.");
            }
        } else {
            System.err.println("""
                    Wrong number of arguments (expected 2).
                    Using defaults.""");
        }
        System.out.println("The GCD of " + a + " and " + b + " is ");
        while (b != 0) {
            if (a > b) {
                a = a -b;
            } else {
                b = b - a;
            }
        }
        System.out.println(a);
    }
}