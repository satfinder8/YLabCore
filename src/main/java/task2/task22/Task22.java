package task2.task22;

import java.util.Arrays;

public class Task22 {

    public static void main(String[] args) {

        findSummands(new int[]{3, 4, 2, 7}, 10);
        findSummands(new int[]{5, 4, 5, 5, 5}, 10);
        findSummands(new int[]{0, 0, 0, 0}, 10);
        findSummands(new int[]{10}, 10);
        findSummands(null, 10);

    }

    private static void findSummands(int[] input, int sum) {

        boolean isResult = false;
        if (validateInputArray(input) == true) {
            Arrays.sort(input);
            for (int i = 0, j = input.length - 1; i < j; ) {
                if (input[i] + input[j] == sum) {
                    System.out.println("[" + input[i] + "," + input[j] + "]");
                    isResult = true;
                    break;
                } else if (input[i] + input[j] > sum) {
                    j--;
                } else {
                    i++;
                }
            }
            if (isResult == false) {
                System.out.println("Массив не содержит подходящей пары элементов");
            }
        }
    }

    private static boolean validateInputArray(int[] inputArray) {
        if (inputArray == null) {
            System.out.println("Отсутствует массив для анализа");
        } else if (inputArray.length < 2) {
            System.out.println("Массив должен содержать не менее 2-ух элементов");
        } else {
            return true;
        }
        return false;
    }

}
