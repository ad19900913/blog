import java.util.Random;

public class Test {

    public static void main(String[] args) {
        int x = new Random().nextInt();
        switch (x) {
            case 1 -> System.out.println("1");
            case 2 -> {
                System.out.println("2");
                System.out.println("3");
            }
            default -> System.out.println("default");
        }

    }
}
