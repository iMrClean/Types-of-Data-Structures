package ru.bmstu.lab4.utils;

import java.util.Arrays;
import java.util.List;

public class Utils {
    /**
     * Получить индекс в AN
     *
     * @param matrix Матрица
     * @param rowI   номер строки
     * @param colJ   номер столбца
     * @return Индекс AN
     */
    public static int getIndexAN(int[][] matrix, int rowI, int colJ) {
        int indexAN = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == rowI && j == colJ) {
                    return indexAN;
                }
                indexAN++;

            }
        }
        throw new RuntimeException("Непредвиденный косячок, который никто не отменял");
    }

    /**
     * Количество ненулевых элементов в матрице
     *
     * @param matrix Матрица
     * @return Количество ненулевых элементов в матрице
     */
    public static int getCountNotNullElements(int[][] matrix) {
        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Выводим по красоте
     *
     * @param text     Название массива
     * @param listItem Название списка
     */
    public static void printResult(String text, List<Integer> listItem) {
        System.out.print(text);
        listItem.forEach(elements -> System.out.print(elements + " "));
        System.out.println();
    }

    /**
     * Выводим по красоте
     *
     * @param matrix Матрица
     */
    public static void printMatrix(String text, int[][] matrix) {
        System.out.println(text);
        Arrays.stream(matrix).forEach(elements -> System.out.println(Arrays.toString(elements)));
    }

}
