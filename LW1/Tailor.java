package LW1;

public class Tailor {
    public static double ecsponent(int k, double x) {
        double answer = 1;
        double zi = 1;
        for (int index = 1; zi > Math.pow(0.1, k); index++) {
            zi = (zi * x) / index;
            answer += zi;
        }
        return answer;
    }

    public static double sin(int k, double x) {
        double new_x = x % (2 * Math.PI);
         double answer = new_x;
         double zi = new_x;
         double multip = -(new_x) * (new_x);
        for (int index = 3; (zi < 0 ? zi*(-1) : zi) > Math.pow(0.1, k); index += 2) {
            zi = ((zi * multip) / (index*(index-1)));
            answer += zi;   
        }
        return answer;
    }
}
