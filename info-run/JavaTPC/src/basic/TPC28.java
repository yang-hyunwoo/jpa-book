package basic;

import kr.poly.A;

public class TPC28 {

    public static void main(String[] args) {

        A a = new A();
        a.display();
        System.out.println(a); // Object --> toString() : 번지

        Object o = new A();
        ((A)o).display();
        System.out.println(o);
    }
}
