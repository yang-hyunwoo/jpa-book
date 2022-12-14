package basic;

import kr.tpc.Animal;
import kr.tpc.Cat;
import kr.tpc.Dog;

public class TPC19 {

    public static void main(String[] args) {
        // Dog , Cat -> Animal

        Dog d = new Dog();
        d.eat();
        Cat c = new Cat();
        c.eat();

        //Animal <--- [Dog.class , Cat.class]

//        Animal ani = new Dog();
//        ani.eat();
//        Animal ani2 = new Cat();
//        ani2.eat();

    }
}
