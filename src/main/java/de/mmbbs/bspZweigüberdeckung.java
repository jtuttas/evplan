package de.mmbbs;

public class bspZweigüberdeckung {

    public static void main(String[] args) {
        System.out.println("a=3");
        calc(3);
        System.out.println("\r\na=4");
        calc(4  );
        System.out.println("\r\na=6");
        calc(6  );
    }

    public static int calc(int a) {
        System.out.println("Zweig 1 (Start)");
        int b = 0;
        System.out.println("Zweig 2");
        if (a % 4 != 0) {
            System.out.println("Zweig 4");
        } else {
            while (a % 4 == 0) {
                System.out.println("Zweig 3");
                a = a + 2;
                System.out.println("Zweig 5");
                if (a % 2 == 0) {
                    System.out.println("Zweig 6");
                    b = b + a;
                    System.out.println("Zweig 8");
                } else {
                    System.out.println("Zweig 7");
                    b = b - a;
                    System.out.println("Zweig 9");
                }
                System.out.println("Zweig 10");
                
            }
        }
        return b;
    }
}
