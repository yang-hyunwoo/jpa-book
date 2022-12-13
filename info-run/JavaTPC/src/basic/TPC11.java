package basic;

import kr.tpc.BookVO;

public class TPC11 {

    public static void main(String[] args) {
        // 책 1권을 저장하기 위해 객체를 생성하기.
        BookVO book = new BookVO();
        book.title="파이썬";
        book.price=16000;
        book.company = "에이콘";
        book.page = 700;

        System.out.print(book.title + "\t");
        System.out.print(book.price + "\t");
        System.out.print(book.company + "\t");
        System.out.println(book.page + "\t");

        BookVO book1 = new BookVO();
        book1.title="오라클";
        book1.price=16000;
        book1.company = "이지스퍼블리싱";
        book1.page = 560;

        System.out.print(book1.title + "\t");
        System.out.print(book1.price + "\t");
        System.out.print(book1.company + "\t");
        System.out.println(book1.page + "\t");

    }
}
