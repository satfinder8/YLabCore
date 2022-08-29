package task1.task12;

import java.util.Scanner;
import java.util.stream.Stream;

public class Task12 {
    public static void main(String[] args) {

        int[] inputDefault = {5, 6, 3, 2, 5, 1, 4, 9};
        System.out.println("Введите массив целых чисел через запятую иначе нажмите 'Ввод' " +
                "для сортировки массива по умолчанию [5,6,3,2,5,1,4,9]:");
        Scanner console = new Scanner(System.in);
        String inputString;
        int[] input = null;

        try {
            if (!(inputString = console.nextLine()).equals("")) {
                input = Stream.of(inputString.split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                System.out.println("Сортируем пользовательский массив:");
                System.out.println(toString(input));
            } else {
                input = inputDefault;
                System.out.println("Сортируем массив по умолчанию:");
                System.out.println(toString(input));
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат вводимых данных, сортируем массив по умолчанию: ");
            input = inputDefault;
            System.out.println(toString(input));
        }

        //сортировка массива и вывод результата в консоль
        System.out.println("-------------------------------------------------------");
        int[] output = bubbleSort(input);
        System.out.println("Результат сортировки:");
        System.out.println(toString(output));
    }

    private static int[] bubbleSort(int[] array) {
        for (int out = array.length - 1; out >= 1; out--) {
            for (int in = 0; in < out; in++) {
                if (array[in] > array[in + 1]) {
                    int dummy = array[in];
                    array[in] = array[in+1];
                    array[in+1] = dummy;
                }
            }
        }
        return array;
    }

    private static String toString(int[] array) {
        StringBuilder sb = new StringBuilder("int[] - [");
        boolean isFirstItem = true;
        for (int i = 0; i < array.length; i++) {
            if (isFirstItem == true) {
                sb.append(array[i]);
                isFirstItem = false;
                continue;
            }
            sb.append(", " + array[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}