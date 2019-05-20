package ru.bmstu.lab2;

import ru.bmstu.lab2.common.Matrix;

/**
 * Лабораторная работа №2
 * Реализовать и померить насколько быстро будут работать
 * Алгоритмы умножения матриц.
 * Алгоритмы: Стандартный, Винограда, Оптимизированный Винограда.
 * Значение рандомные, до 100.
 * Добавить:
 * 1) ПО для однократных экспериментов
 * 2) Оценка трудоемкости каждого
 * 3) Провести серию экспериментов и замерить время выполнения каждой реализации. (10 раз)
 * Матрицы: 100х100, 101х101, 1000х1000, 1001х1001
 * Отключить оптимизацию компилятора.
 * Вывод: Кто на практике быстрее? Всегда ли, либо начиная с некоторого порогового значения размеров матрицы?
 */
public class Main {

    private static final int SIZE_MATRIX_1 = 100;

    private static final int SIZE_MATRIX_2 = 101;

    private static final int SIZE_MATRIX_3 = 1000;

    private static final int SIZE_MATRIX_4 = 1001;

    public static void main(String[] args) {
        //100x100
        int[][] a = new int[SIZE_MATRIX_1][SIZE_MATRIX_1];
        int[][] b = new int[SIZE_MATRIX_1][SIZE_MATRIX_1];
        //101x101
        int[][] c = new int[SIZE_MATRIX_2][SIZE_MATRIX_2];
        int[][] d = new int[SIZE_MATRIX_2][SIZE_MATRIX_2];
        //1000x1000
        int[][] e = new int[SIZE_MATRIX_3][SIZE_MATRIX_3];
        int[][] f = new int[SIZE_MATRIX_3][SIZE_MATRIX_3];
        //1001x1001
        int[][] g = new int[SIZE_MATRIX_4][SIZE_MATRIX_4];
        int[][] h = new int[SIZE_MATRIX_4][SIZE_MATRIX_4];

        Matrix matrix = new Matrix();
        //100x100
        matrix.fillMatrix(a);
        matrix.fillMatrix(b);
        //101x101
        matrix.fillMatrix(c);
        matrix.fillMatrix(d);
        //1000x1000
        matrix.fillMatrix(e);
        matrix.fillMatrix(f);
        //1001x1001
        matrix.fillMatrix(g);
        matrix.fillMatrix(h);
        //============================= Стандартный алгоритм =============================
        for (int i = 0; i < 10; i++) {
            System.out.println("Тест:" + (i + 1));
            System.out.println("Стандартный алгоритм");
            long start = System.currentTimeMillis();
            matrix.multiplication(a, b);
            System.out.printf("\tМатрица 100х100, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            matrix.multiplication(c, d);
            System.out.printf("\tМатрица 101х101, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            matrix.multiplication(e, f);
            System.out.printf("\tМатрица 1000х1000, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            matrix.multiplication(g, h);
            System.out.printf("\tМатрица 1001х1001, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);

            //============================= Алгоритм Винограда =============================
            System.out.println("Алгоритм Винограда");
            start = System.currentTimeMillis();
            matrix.multiplicationVinograd(a, b);
            System.out.printf("\tМатрица 100х100, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            matrix.multiplicationVinograd(c, d);
            System.out.printf("\tМатрица 101х101, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            matrix.multiplicationVinograd(e, f);
            System.out.printf("\tМатрица 1000х1000, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            matrix.multiplicationVinograd(g, h);
            System.out.printf("\tМатрица 1001х1001, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);

            //============================= Оптимизированный алгоритм Винограда =============================
            System.out.println("Оптимизированный алгоритм Винограда");
            start = System.currentTimeMillis();
            matrix.multiplicationOptimizeVinograd(a, b);
            System.out.printf("\tМатрица 100х100, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            matrix.multiplicationOptimizeVinograd(c, d);
            System.out.printf("\tМатрица 101х101, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            matrix.multiplicationOptimizeVinograd(e, f);
            System.out.printf("\tМатрица 1000х1000, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            matrix.multiplicationOptimizeVinograd(g, h);
            System.out.printf("\tМатрица 1001х1001, выполнилось за %d миллисекунд \n", System.currentTimeMillis() - start);
        }
    }
}