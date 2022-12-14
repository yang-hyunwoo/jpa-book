package basic;

import kr.tpc.Animal;
import kr.tpc.Cat;
import kr.tpc.Dog;

public class TPC21 {

    public static void main(String[] args) {

        Dog d = new Dog();
        d.eat();

        Cat c = new Cat();
        c.eat();
        c.night();

        // Dog.class , Cat.class
        Animal ani = new Cat(); //upcasting
        ani.eat();
        ((Cat)ani).night(); //downcasting

    }
}
