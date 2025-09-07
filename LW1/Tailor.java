package LW1;
import java.util.Scanner;

public class Tailor {
    public static double  ecsponent(int k, double x) {
        double answer =1, current = 1;
        int factorial =1;
        for(int index = 1 ; current > Math.pow(0.1, k); index++){
            factorial*= index;
            current = Math.pow(x, (double) index) / factorial;
            answer += current;
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int precision = scanner.nextInt();
        double variable_x = scanner.nextDouble();
        String format = "%." + precision + "f";
        System.out.println("Calculation: "+String.format(format, ecsponent(precision, variable_x)) +"\nStandart: " + String.format(format, Math.exp(variable_x)));
        scanner.close();

    }
}
