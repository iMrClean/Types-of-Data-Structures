package ru.bmstu.lab2.common;

import java.util.Random;

public class Matrix {

    /**
     * Выводим матрицу в консоль
     *
     * @param array матрица для вывода
     */
    public void printMatrix(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print("\t" + array[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Создать матрицу заполненную случайными значениями < 100
     *
     * @param array пустая матрица
     */
    public void fillMatrix(int[][] array) {
        int maxValue = 100;
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = random.nextInt(maxValue);
            }
        }
    }

    /**
     * Стандартный способ умножения матрицы
     *
     * @param arrayA Матрица А
     * @param arrayB Матрица Б
     * @return Умноженная матрица.
     */
    public int[][] multiplication(int[][] arrayA, int[][] arrayB) {

        if (arrayA[0].length != arrayB.length) {
            throw new IllegalArgumentException("Размер столбца матрицы А должен совпадать со сторокй матрицы Б");
        }

        int[][] result = new int[arrayA.length][arrayB[0].length];

        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayB[i].length; j++) {
                for (int k = 0; k < arrayB.length; k++) {
                    result[i][j] += arrayA[i][k] * arrayB[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Умножение матрицы алгоритмом Винограда
     *
     * @param arrayA Матрица А
     * @param arrayB Матрица Б
     * @return Умноженная матрица.
     */
    public int[][] multiplicationVinograd(int[][] arrayA, int[][] arrayB) {

        if (arrayA[0].length != arrayB.length) {
            throw new IllegalArgumentException("Размер столбца матрицы А должен совпадать со сторокй матрицы Б");
        }

        int[][] result = new int[arrayA.length][arrayB.length];

        int[] mulA = new int[arrayA.length];
        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayB.length / 2; j++) {
                mulA[i] = mulA[i] + arrayA[i][j * 2] * arrayA[i][j * 2 + 1];
            }
        }

        int[] mulB = new int[arrayB[0].length];
        for (int i = 0; i < arrayB[0].length; i++) {
            for (int j = 0; j < arrayB.length / 2; j++) {
                mulB[i] = mulB[i] + arrayB[j * 2][i] * arrayB[j * 2 + 1][i];
            }
        }

        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayB[0].length; j++) {
                result[i][j] = -mulA[i] - mulB[j];
                for (int k = 0; k < arrayB.length / 2; k++) {
                    result[i][j] = result[i][j] + (arrayA[i][2 * k] + arrayB[2 * k + 1][j]) * (arrayA[i][2 * k + 1] + arrayB[2 * k][j]);
                }
            }
        }

        if (arrayB.length % 2 == 1) {
            for (int i = 0; i < arrayA.length; i++) {
                for (int j = 0; j < arrayB[0].length; j++) {
                    result[i][j] = result[i][j] + arrayA[i][arrayB.length - 1] * arrayB[arrayB.length - 1][j];
                }
            }
        }
        return result;
    }

    /**
     * Умножение матрицы оптимизированный алгоритм Винограда.
     *
     * @param arrayA Матрица А
     * @param arrayB Матрица Б
     * @return Умноженная матрица.
     */
    public int[][] multiplicationOptimizeVinograd(int[][] arrayA, int[][] arrayB) {

        if (arrayA[0].length != arrayB.length) {
            throw new IllegalArgumentException("Размер столбца матрицы А должен совпадать со сторокй матрицы Б");
        }

        int[][] result = new int[arrayA.length][arrayB.length];

        int[] mulA = new int[arrayA.length];
        for (int i = 0; i < arrayA.length; i++) {
            int buf = 0;
            for (int j = 1; j < arrayB.length; j += 2) {
                buf += arrayA[i][j - 1] * arrayA[i][j];
            }
            mulA[i] = buf;
        }

        int[] mulB = new int[arrayB[0].length];
        for (int i = 0; i < arrayB[0].length; i++) {
            int buf = 0;
            for (int j = 1; j < arrayB.length; j += 2) {
                buf += arrayB[j][i] * arrayB[j - 1][i];
            }
            mulB[i] = buf;
        }

        if (arrayB.length % 2 == 1) {
            for (int i = 0; i < arrayA.length; i++) {
                for (int j = 0; j < arrayB[0].length; j++) {
                    int buf = -mulA[i] - mulB[j] + arrayA[i][arrayB.length - 1] * arrayB[arrayB.length - 1][j];
                    for (int k = 1; k < arrayB.length; k += 2) {
                        buf += (arrayA[i][k - 1] + arrayB[k][j]) * (arrayA[i][k] + arrayB[k - 1][j]);
                    }
                    result[i][j] = buf;
                }
            }
        } else {
            for (int i = 0; i < arrayA.length; i++) {
                for (int j = 0; j < arrayB[0].length; j++) {
                    int buf = -mulA[i] - mulB[j];
                    for (int k = 1; k < arrayB.length; k += 2) {
                        buf += (arrayA[i][k - 1] + arrayB[k][j]) * (arrayA[i][k] + arrayB[k - 1][j]);
                    }
                    result[i][j] = buf;
                }
            }
        }

        return result;
    }
}