public class Test {

    public static void main(String[] args) {
        int i = 1990;
        System.out.println(i + ":\t" + Integer.toBinaryString(i));
        System.out.println((i >> 2) + ":\t" + Integer.toBinaryString(i >> 2));
        System.out.println((i << 2) + ":\t" + Integer.toBinaryString(i << 2));
        System.out.println((i << 30) + ":\t" + Integer.toBinaryString(i << 2));
        i = -1990;
        System.out.println((i >>> 2) + ":\t" + Integer.toBinaryString(i >>> 2));
        System.out.println((i >>> 31) + ":\t" + Integer.toBinaryString(i >>> 2));
    }
}
