package kr.tpc;

public class Dog extends Animal{
    //이름 , 나이 , 종 : 상태정보

    //재정의(override)
    @Override
    public void eat() {
        System.out.println("개처럼먹다.");
    }

    public Dog() {
        super(); //new Animal();

    }
}
