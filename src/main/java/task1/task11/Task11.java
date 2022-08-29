package task1.task11;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Task11 {

    private static int minMatrixItem = 0;
    private static int maxMatrixItem = 0;

    public static void main(String[] args) throws InterruptedException {

        Scanner console = new Scanner(System.in);
        int dimention = 0, range = 0;

        // Инициализация начальных параметров
        try {
            System.out.print("Введите размер квадратной матрицы (целое положительное число): ");
            dimention = console.nextInt();
            if (dimention <= 0) throw new InputMismatchException();
            System.out.print("Введите желаемый диапазон значений случайных чисел для заполнения матрицы (-Х; Х), Х не равен 0: ");
            range = console.nextInt();
            if (range == 0) {
                System.out.println("Диапазон не может быть равен 0");
                System.exit(1);
            }
        } catch (InputMismatchException e) {
            System.out.println("Введены некорректные данные!" +
                    System.lineSeparator() +
                    "Используйте только положительные целочисленные значения не более 2*10^9!");
            System.exit(1);
        }

        //Создание и заполнение двумерного массива
        int[][] unsorted = new int[dimention][dimention];
        int sum = 0;
        for (int i = 0; i < dimention; i++) {
            for (int j = 0; j < dimention; j++) {
                unsorted[i][j] = randomInt(range);
                evaluateParameters(unsorted[i][j]);
                sum += unsorted[i][j];
            }
        }

        //Вывод результатов в консоль
        System.out.println("-----------------------------------------------------");
        System.out.println("Несортированная матрица");
        toString(unsorted);
        System.out.println("-----------------------------------------------------");
        System.out.println("Отсортированная матрица");
        toString(sortMatrix(unsorted));
        System.out.println("-----------------------------------------------------");
        System.out.println("Минимальное значение в матрице: " + minMatrixItem);
        System.out.println("Максимальное значение в матрице: " + maxMatrixItem);
        System.out.printf("Среднее значение элемента матрицы: %f%n", (double) sum / (dimention * dimention));
        System.out.println("-----------------------------------------------------");


    }

    private static int randomInt(int range) throws InterruptedException {
        long timer = System.currentTimeMillis();
        int count = (int) (timer ^ (timer >> 13) * 17);
        Thread.sleep(7L);
        timer = System.currentTimeMillis();
        int mul = (int) (timer ^ (timer >>> 31) * 23);
        count = (count * mul + 1) % range;
        return count;
    }

    //вывод двумерного массива в консоль
    private static void toString(int[][] array) {
        int x = array.length;
        int y = array[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    //сохранение параметров матрицы: минимальное и максимальное значение элементов
    private static void evaluateParameters(int item) {
        if (maxMatrixItem == minMatrixItem && maxMatrixItem == 0) {
            maxMatrixItem = item;
            minMatrixItem = item;
        }
        if (item < minMatrixItem) minMatrixItem = item;
        if (item > maxMatrixItem) maxMatrixItem = item;
    }

    //Линеаризация и обратное преобразование матрицы с сортировкой
    private static int[][] sortMatrix(int[][] unsorted) {

        int rows = unsorted.length;
        int columns = unsorted[0].length;

        int[][] sorted = new int[rows][columns];
        int[] linearMatrix = new int[rows * columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                linearMatrix[i * columns + j] = unsorted[i][j];
            }
        }

        linearMatrix = quickSort(linearMatrix, 0, rows * columns - 1);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sorted[i][j] = linearMatrix[i * columns + j];
            }
        }

        return sorted;
    }

    //Алгоритм бысрой сотрировки линейного массива int[]
    private static int[] quickSort(int[] array, int low, int high) {

        if (array.length == 0) {
            return array;
        }

        if (low >= high) {
            return array;
        }

        int middle = low + (high - low) / 2;
        int opora = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);

        return array;
    }

}
