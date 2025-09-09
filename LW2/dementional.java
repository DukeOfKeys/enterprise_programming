package LW2;

import java.util.Scanner;

public class dementional 
{
    static  Scanner scanner = new Scanner(System.in);
  
    public static void main(String[] args) 
    {
        MyMatrix eng = new MyMatrix();
        eng.readMatrix(scanner);
        boolean working = true;
        String commnad = "";
        System.err.println("Commands: \nq - quit\n1 - first problem\n2 - second problem\n3 - third problem");
        while (working) {
        if (!scanner.hasNext()) 
        {
            System.out.println("No more input. Exiting...");
        } 
            else
              commnad = scanner.next();
             switch (commnad) 
             {
                 case "q":
                     working = false;
                     break;
                 case "1":
                 {
                     System.out.println(eng.sameStrings());
                 }
                     break;
                 case "2":
                 
                     MyMatrix.printArray(eng.changeArray());
                     break;
                 case "fr": 
                 {
                     String filename = scanner.next();
                     eng.readMatrixFromFile(filename);
                 }
                     break;
                 case "fw": 
                 {
                     String filename = scanner.next();
                     eng.printMatrixToFile(filename);
                 }
                     break;
                 case "print":
                     eng.printMatrix();
                     break;
                 case "read":
                     eng.readMatrix(scanner);
                     break;
            case "3":
                 MyMatrix.printArray(eng.changeArrayWithMin());
                break;
                 default:
                     break;
             }

        }
        scanner.close();
    }
}
