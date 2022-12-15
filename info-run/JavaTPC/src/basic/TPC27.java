package basic;

import kr.poly.RemoCon;
import kr.poly.TV;

public class TPC27 {

    public static void main(String[] args) {
        //RemoCon으로 TV 클래스를 구동.
        RemoCon tv = new TV();
        for (int i = 0; i < 40; i++) {
            tv.chUp();
        }
        for (int i = 0; i < 40; i++) {
            tv.chDown();
        }
        tv.internet();

    }


}
