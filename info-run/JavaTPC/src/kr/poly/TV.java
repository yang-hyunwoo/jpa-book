package kr.poly;

public class TV implements RemoCon{
    public int currCH = 70;
    @Override
    public void chUp() {
        if(currCH<RemoCon.MAXCH) {
            currCH++;
        } else {
            currCH = RemoCon.MINCH;
        }
        System.out.println("TV의 채널이 올라간다.:"+currCH);

    }

    @Override
    public void chDown() {
        if(currCH>RemoCon.MINCH) {
            currCH--;
        } else {
            currCH = RemoCon.MAXCH;
        }
        System.out.println("TV의 채널이 내려간다. : "+currCH);

    }

    @Override
    public void internet() {
        System.out.println("인터넷이 된다.");
    }

    public int getCurrCH() {
        return currCH;
    }

    //추가적인 기능을 구현.
}
