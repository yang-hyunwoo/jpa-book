package basic;

import kr.tpc.MemberVO;

public class TPC14 {

    public static void main(String[] args) {
        MemberVO m = new MemberVO();

        m.setAddr("홍길동");
        m.setAge(50);
        m.setTel("010-1111-1111");
        m.setAddr("서울");

        System.out.println(m.getName());
        System.out.println(m.getAge());
        System.out.println(m.getTel());
        System.out.println(m.getAddr());
    }
}
