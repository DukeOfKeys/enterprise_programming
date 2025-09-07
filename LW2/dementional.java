package LW2;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class dementional 
{
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

    public static int[][] changeArrayWithMin(int[][] mtx) 
     {
        int[][] answer = new int[mtx[0].length+1][mtx.length+1];
        int min = mtx[0][0];
        for (int[] mtx1 : mtx) 
        {
            int nmin = Arrays.stream(mtx1).min().getAsInt();
            if (nmin < min) 
            {
                min = nmin;
            }
        }
        answer[mtx.length][mtx.length] = min;
        for (int index = 0; index < mtx.length; index++) 
        {
            answer[mtx.length][index] = min + 1;
            answer[index][mtx.length] = min + 1; 
        }
        return answer;
    }

    public static int[][] readArray() 
    {
         Scanner scanner = new Scanner(System.in);
         System.out.print("Введите количество строк: ");
        int rows = scanner.nextInt();

        System.out.print("Введите количество столбцов: ");
        int cols = scanner.nextInt();

        int[][] array = new int[rows][cols];

        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < cols; j++) 
            {
                System.out.print("Введите элемент [" + i + "][" + j + "]: ");
                array[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
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
         // Ввод размеров массива
         boolean working = true;
        System.err.println("Commands: \nq - quit\n1 - first problem\n2 - second problem\n3 - third problem");
         while (working) {
             String commnad = scanner.next();
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
