package basic;

import kr.poly.Radio;
import kr.poly.RemoCon;
import kr.poly.TV;

public class TPC26 {

    public static void main(String[] args) {
        RemoCon tv = new TV();
        tv.chUp();
        System.out.println(((TV)tv).getCurrCH());
        tv.chUp();
        System.out.println(((TV)tv).getCurrCH());
        tv.chDown();
        System.out.println(((TV)tv).getCurrCH());
        tv.internet();


        RemoCon radio = new Radio();
        radio.chUp();
        radio.chDown();
        radio.internet();
    }
}
