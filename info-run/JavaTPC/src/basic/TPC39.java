package basic;

public class TPC39 {

    public static void main(String[] args) {
        int a = 1;
        Integer b = new Integer(1); //Boxing
        System.out.println(b.intValue()); // 1
        System.out.println(b.toString()); // "1"

        Object[] obj = new Object[3];
        // 1 , 2 , 3
        obj[0] = new Integer(1);
        obj[1] = new Integer(2);
        obj[2] = new Integer(3);

        System.out.println(obj[0].toString());
        System.out.println(obj[1].toString());
        System.out.println(obj[2].toString());

        // "100" + "100"  = 200
        String x = "100";
        String y = "100";
        int va1 = Integer.parseInt(x);
        int va2 = Integer.parseInt(y);
        System.out.println(va1+va2);
        
    }
}
