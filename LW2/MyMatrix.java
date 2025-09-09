package LW2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MyMatrix {
    // task 27
    int[][] matrix;
    int rows, colums;

    public MyMatrix() {
        matrix = null;
        rows = 0;
        colums = 0;
    }

    public MyMatrix(int[][] _matrix) {
        matrix = new int[_matrix.length][];
        for (int i = 0; i < _matrix.length; i++) {
            matrix[i] = new int[_matrix[i].length];
            System.arraycopy(_matrix[i], 0, matrix[i], 0, _matrix[i].length);
        }
        rows = matrix.length;
        colums = matrix[0].length;
    }
    
    public void readMatrix(Scanner sc) {
        System.out.print("Enter number of rows: ");
        rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        colums = sc.nextInt();
        
        matrix = new int[rows][colums];
        
        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        System.out.println("Matrix read successfully!");
    }

      public void readMatrixFromFile(String _filename) {
        try {
            File file = new File(_filename);
            Scanner sc = new Scanner(file);

            if (sc.hasNextInt()) rows = sc.nextInt();
            if (sc.hasNextInt()) colums = sc.nextInt();

            matrix = new int[rows][colums];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < colums; j++) {
                    if (sc.hasNextInt()) {
                        matrix[i][j] = sc.nextInt();
                    } else {
                        System.err.println("bad");
                    }
                }
            }

            sc.close();
            System.out.println("Matrix read successfully from file: " + _filename);

        } catch (Exception e) {
            System.out.println("Error reading matrix: " + e.getMessage());
        }
    }

    public void printMatrix() {
         for (int[] ar : matrix) 
        {
            for (int num : ar) 
            {
                System.err.print(num + " ");
            }
            System.err.println("");
        }
    }

   
    public void printMatrixToFile(String _filename) {
        if (matrix == null || rows == 0 || colums == 0) {
            System.out.println("Matrix is empty. Nothing to write.");
            return;
        }

        try {
            PrintWriter writer = new PrintWriter(new File(_filename));
            writer.println(rows + " " + colums);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < colums; j++) {
                    writer.print(matrix[i][j]);
                    if (j < colums - 1) writer.print(" "); 
                }
                writer.println(); // new line after each row
            }

            writer.close();
            System.out.println("Matrix successfully written to file: " + _filename);

        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot write to file " + _filename);
        }
    }
     public static void printArray(int[][] array) 
    {
        for (int[] ar : array) 
        {
            for (int num : ar) 
            {
                System.err.print(num + " ");
            }
            System.err.println("");
        }
    }


    public  int sameStrings() 
    {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        for (int index = 1; index < matrix.length; index++) {
            boolean flag = true;
            Arrays.sort(matrix[index]);
            for (Integer number : list) {
                if (Arrays.equals(matrix[index], matrix[number])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                list.add(index);
            }
        }
        return list.size();
    }
    
    // task 41
    public  int[][] changeArray() 
    {
        int[][] answer = new int[matrix[0].length][matrix.length];
        ArrayList<SimpleEntry<Integer, Integer>> list = new ArrayList<>();
        for (int index = 0; index < matrix.length; index++) 
        {
            int charecter = 0;
            for (int number = 0; number < matrix[0].length; number+=2) 
                charecter += matrix[index][number] > 0 ? matrix[index][number] : 0;
            list.add(new SimpleEntry<>(index, charecter));
        }
        list.sort((a, b) -> a.getValue().compareTo(b.getValue()));
        int index = 0;
        for (SimpleEntry<Integer, Integer> pair : list) 
        {
            System.arraycopy(matrix[pair.getKey()], 0, answer[index], 0, matrix[0].length);
             index++;
        }
        return answer;
    }
    // task 39 (randomly picked) 
   public  int[][] changeArrayWithMin() {
        int n = matrix.length;
        int min = matrix[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
        }

        
        int[][] answer = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, answer[i], 0, n);
        }

        for (int i = 0; i < n; i++) 
        {
            answer[i][n] = min + 1;
            answer[n][i] = min + 1;
        }

        answer[n][n] = min;
        return answer;
    }

}
