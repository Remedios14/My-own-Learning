import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your last test grade: ");
        int last = Integer.parseInt(scanner.nextLine());
        System.out.print("Input your current test grade: ");
        int curr = Integer.parseInt(scanner.nextLine());
        double rate = ((curr - last) / last) * 100;
        System.out.printf("Hello, your increase rate is %.2f %%", rate);
        scanner.close();
    }
}