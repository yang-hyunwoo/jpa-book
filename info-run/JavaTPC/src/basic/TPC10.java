package basic;

import kr.tpc.BookDTO;

public class TPC10 {

    public static void main(String[] args) {
        //int , float , char , boolean -> PDT
        int a;
        a = 10;

        //책(BookDTO) 이라는 자료형을 만들자. --> class
        // 객체 생성
        BookDTO book = new BookDTO();
        book.title="자바";
        book.price=17000;
        book.company = "영진";
        book.page = 670;

        System.out.print(book.title + "\t");
        System.out.print(book.price + "\t");
        System.out.print(book.company + "\t");
        System.out.println(book.page + "\t");

    }
}
