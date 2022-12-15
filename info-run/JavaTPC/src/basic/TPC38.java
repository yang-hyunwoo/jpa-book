package basic;

import kr.tpc.BookDTO;

import java.util.ArrayList;
import java.util.List;

public class TPC38 {

    public static void main(String[] args) {
//        List<BookDTO> list = new ArrayList<BookDTO>();
        List<BookDTO> list = new ArrayList<BookDTO>();
        list.add(new BookDTO("자바1", 12000, "이지스1", 600));
        list.add(new BookDTO("자바2", 13000, "이지스2", 700));
        list.add(new BookDTO("자바3", 14000, "이지스3", 800));

        for (int i = 0; i < list.size(); i++) {
//            Object o = list.get(i);
//            BookDTO vo =(BookDTO)o;
            System.out.println(list.get(i).toString());

        }
    }
}
