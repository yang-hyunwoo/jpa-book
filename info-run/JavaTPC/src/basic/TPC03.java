package basic;

import kr.bit.Book;
import kr.bit.PersonVO;

public class TPC03 {

    public static void main(String[] args) {
        // 관계를 이해하기. PDT VS UDDT
        // 정수 1개를 저장하기 위한 변수를 선언.
        int a;
        a = 10;

        //책 1권을 저장하기 위한 변수를 선언.
        Book book;
        book = new Book();
        book.title="자바";
        book.price=15000;
        book.company = "한빛미디어";
        book.page=700;

        System.out.println(book.title);
        System.out.println(book.price);
        System.out.println(book.company);
        System.out.println(book.page);

        PersonVO person;
        person = new PersonVO();
        person.name="양현우";
        person.age=28;
        person.weight=68.4f;
        person.height=168.2f;

        System.out.println(person.name);
        System.out.println(person.age);
        System.out.println(person.weight);
        System.out.println(person.height);
    }


}
