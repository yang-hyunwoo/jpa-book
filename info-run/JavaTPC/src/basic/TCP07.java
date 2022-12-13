package basic;

public class TCP07 {

    public static void main(String[] args) {
        int a = 20;
        float b = 56.7f;
        float v = sum(a, b); //Call By Value 값 전달
        System.out.println(v);

        int[] arr = {1, 2, 3, 4, 5};
        //배열의 총합을 구하기

        int vv = arrSum(arr); //Call By Reference(번지)
        System.out.println(vv);


    }

    private static int arrSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static float sum(int a , float b) {
        float v = a+b;
        return v;
    }
}
