package util;

public class TestMain {
    public static void main(String[] args) {
        b();
    }
    
    void a() {
        int[] a = new int[]{1,2,3};
        int[] b = a;
        b[0] = 0;
        System.out.println(a[0]);
    }

    static void b() {
        System.out.println("hello,bye,1000".split(",")[2]);
    }
}