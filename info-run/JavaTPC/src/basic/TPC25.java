package basic;

import kr.poly.Animal;
import kr.poly.Cat;
import kr.poly.Dog;

public class TPC25 {

    public static void main(String[] args) {
        Animal ani = new Dog();
        ani.eat();

        Animal ani2 = new Cat();
        ani2.eat();

    }
}
