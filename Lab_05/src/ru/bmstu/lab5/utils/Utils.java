package ru.bmstu.lab5.utils;

import java.text.DecimalFormat;
import java.util.Random;

public class Utils {
    //генерация псевдослучайных чисел
    private static final Random RND = new Random();
    //формат вывода
    public static final DecimalFormat decimalFormat = new DecimalFormat("##");

    //матрица расстояний между городами
    public static double[][] getMatrixBetweenCities(int cityCount) {
        double[][] distances = new double[cityCount][cityCount];

        //генерацмя матрицы расстояний между городами
        for (int i = 0; i < cityCount; i++) {
            //отключаем переходы из города в самого себя (диоганальные элементы матрицы)
            distances[i][i] = 0.0;
            for (int j = i + 1; j < cityCount; j++) {
                //путь (из А в Б) = путь (из Б в А)
                distances[i][j] = distances[j][i] = getRandomNumberInRange(1, 100);
            }
        }

        return distances;
    }

    // генератор рандомных чисел от [min; max)
    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Максимальное значение должно быть больше минимального");
        }

        return RND.nextInt(max - min) + min;
    }

    // утилка для вывода в консоль
    public static void printArray(double[][] mas) {
        int iMax = mas.length;
        int jMax = mas[0].length;

        for (int i = 0; i < iMax; i++) {
            for (int j = 0; j < jMax; j++) {
                System.out.print(" " + decimalFormat.format(mas[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    // утилка для вывода в консоль
    public static void printArray(int[] mas) {
        int iMax = mas.length;

        for (int i = 0; i < iMax; i++) {
            System.out.print(decimalFormat.format(mas[i] + 1) + " ");
        }
        System.out.println();
    }

}
