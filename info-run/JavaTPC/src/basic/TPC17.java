package basic;

import kr.tpc.MovieVO;

public class TPC17 {

    public static void main(String[] args) {
        int[] a = new int[5];
        a[0] = 10;
        a[1] = 20;
        a[2] = 30;
        a[3] = 40;
        a[4] = 50;

        int[] b = {10, 20, 30, 40, 50};
        int[] c = new int[]{10, 20, 30, 40, 50};

        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
            
        }
        //영화(제목,가격,주인공,등급,시간)
        MovieVO mv = new MovieVO("비트", 12000, "연기장", 12, 1.3f);
        System.out.println(mv);

        //영화 3편을 저장 -> 객체배열
        MovieVO[] marr = new MovieVO[3];
        marr[0] =  new MovieVO("비트1", 12000, "연기장1", 12, 1.3f);
        marr[1] =  new MovieVO("비트2", 12000, "연기장2", 11, 1.4f);
        marr[2] =  new MovieVO("비트3", 13000, "연기장3", 13, 1.5f);

        for (int i = 0; i < marr.length; i++) {
            System.out.println(marr[i]);
        }

    }
}
