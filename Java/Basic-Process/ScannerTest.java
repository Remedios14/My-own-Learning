import java.util.Scanner;

public class ScannerTest {
    public public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your last test grade: ");
        int last = scanner.nextLine();
        System.out.print("Input your current test grade: ");
        int curr = scanner.nextLine();
        double rate = (curr - last) / last;
        System.out.printf("Hello, your increase rate is %.2f %%", rate);
    }
}