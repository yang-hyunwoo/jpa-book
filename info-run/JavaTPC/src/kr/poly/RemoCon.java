package kr.poly;

public interface RemoCon { // 객체생성X ; RemoCon r = new RemoCon();

    //상수를 사용가능
    public static final int MAXCH = 100; //public static final 생략되어 있음
    int MINCH=1;
    //추상메서드
    public void chUp();

    public void chDown();

    public void internet();

}
