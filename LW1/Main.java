package LW1;
import java.util.Scanner;

public class Main {
        static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
        int precision = scanner.nextInt();
        double variable_x = scanner.nextDouble();
        String format = "%." + precision + "f";
        System.out.println("Calculation: " + String.format(format, Tailor.ecsponent(precision, variable_x))
                + "\nStandart: " + String.format(format, Math.exp(variable_x)));
        precision = scanner.nextInt();
        variable_x = scanner.nextDouble();
        System.out.println("Calculation: " + String.format(format, Tailor.sin(precision, variable_x))
                + "\nStandart: " + String.format(format, Math.sin(variable_x)));
        scanner.close();
    }
}
