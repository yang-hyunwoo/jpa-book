package basic;

import kr.tpc.BookVO;

public class TPC12 {

    public static void main(String[] args) {
        //생성자 -> 생성+초기화 -> 중복 정의

        BookVO bookVO = new BookVO("자바",14000,"이지스",780);
        BookVO bookVO2 = new BookVO("파이썬",18000,"대림",920);

//        BookVO bookVO = new BookVO();

        System.out.println(bookVO);

//        BookVO bookVO2 = new BookVO();

        System.out.println(bookVO2);



    }
}
