package basic;

import kr.tpc.Animal;
import kr.tpc.Cat;
import kr.tpc.Dog;

public class TPC23 {

    public static void main(String[] args) {
        // 1.다형성 인수
        Dog d = new Dog();
        display(d);
        Cat c = new Cat();
        display(c);
    }

    private static void display(Animal r) { //다형성 인수
        r.eat();
        if(r instanceof Cat) {
            ((Cat)r).night(); //downcasting
        }
    }

}
