package basic;

import kr.tpc.Animal;
import kr.tpc.Cat;
import kr.tpc.Dog;

public class TCP20 {

    public static void main(String[] args) {
        Animal d = new Dog(); //upcastion(자동형변환)
        d.eat();

        Animal c = new Cat();
        c.eat();
        ((Cat)c).night(); //--> downcasting(강제 형변환)
    }
}
