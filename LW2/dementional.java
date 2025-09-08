package LW2;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class dementional 
{
      static  Scanner scanner = new Scanner(System.in);
    // task 27
    public static int sameStrings(int[][] mtx) 
    {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        for (int index = 1; index < mtx.length; index++) 
        {
            boolean flag = true;
            Arrays.sort(mtx[index]);
            for (Integer number : list) 
            {
                if (Arrays.equals(mtx[index], mtx[number])) 
                {
                    flag = false;
                    break;
                }
            }
            if (flag) 
            {
                list.add(index);
            }
        }
        return list.size();
    }
    // task 41
    public static int[][] changeArray(int[][] mtx) 
    {
        int[][] answer = new int[mtx[0].length][mtx.length];
        ArrayList<SimpleEntry<Integer, Integer>> list = new ArrayList<>();
        for (int index = 0; index < mtx.length; index++) 
        {
            int charecter = 0;
            for (int number = 0; number < mtx[0].length; number+=2) 
                charecter += mtx[index][number] > 0 ? mtx[index][number] : 0;
            list.add(new SimpleEntry<>(index, charecter));
        }
        list.sort((a, b) -> a.getValue().compareTo(b.getValue()));
        int index = 0;
        for (SimpleEntry<Integer, Integer> pair : list) 
        {
            System.arraycopy(mtx[pair.getKey()], 0, answer[index], 0, mtx[0].length);
             index++;
        }
        return answer;
    }
    // task 39 (randomly picked) 
   public static int[][] changeArrayWithMin(int[][] mtx) {
        int n = mtx.length;
        int min = mtx[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mtx[i][j] < min) {
                    min = mtx[i][j];
                }
            }
        }

        
        int[][] answer = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            System.arraycopy(mtx[i], 0, answer[i], 0, n);
        }

        for (int i = 0; i < n; i++) 
        {
            answer[i][n] = min + 1;
            answer[n][i] = min + 1;
        }

        answer[n][n] = min;
        return answer;
    }


  
    public static int[][] readArray() {
        System.out.print("Введите количество строк: ");
        int rows = scanner.nextInt();

        System.out.print("Введите количество столбцов: ");
        int cols = scanner.nextInt();
        scanner.nextLine(); 

        int[][] array = new int[rows][cols];

        System.out.println("Введите элементы массива построчно через пробел:");
        for (int i = 0; i < rows; i++) {
            System.out.print("Строка " + i + ": ");
            String[] input = scanner.nextLine().split(" ");
            for (int j = 0; j < cols; j++) {
                array[i][j] = Integer.parseInt(input[j]);
            }
        }
        return array;
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

    public static void main(String[] args) 
    {
       
        Scanner scanner = new Scanner(System.in);
         boolean working = true;
         String commnad = "";
        System.err.println("Commands: \nq - quit\n1 - first problem\n2 - second problem\n3 - third problem");
        while (working) {
               if (!scanner.hasNext()) {
        System.out.println("No more input. Exiting...");
      //  break;
    }else
              commnad = scanner.next();
             switch (commnad) 
             {
                 case "q":
                     working = false;
                     break;
                 case "1":
                 {
                     System.out.println(sameStrings( readArray()));
                 }
                     break;
                 case "2":
                     printArray(changeArray(readArray()));
                break;
            case "3":
                printArray(changeArrayWithMin(readArray()));
                break;
                 default:
                     break;
             }

        }
        scanner.close();
    }
}
