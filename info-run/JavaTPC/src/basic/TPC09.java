package basic;

public class TPC09 {

    public static void main(String[] args) {
        int a = 56;
        int b = 40;
        TPC09 tpc = new TPC09();    //heap Area(힙)
        int result = tpc.sum(a, b);

        System.out.println(result);


    }

    public int sum(int a , int b) {
        int v = a+b;
        return v;
    }
}
