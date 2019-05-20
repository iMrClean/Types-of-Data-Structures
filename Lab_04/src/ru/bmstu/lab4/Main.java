package ru.bmstu.lab4;

import ru.bmstu.lab4.common.MatrixCSRC;
import ru.bmstu.lab4.common.RingScheme;

import static ru.bmstu.lab4.utils.Utils.*;

/**
 * Лабораторная работа № 4
 * 1. Реализация кольцевой КРМ схемы
 * -Упаковка/распаковка матрицы
 * -Сложение, умножение
 * 2. Реализация разреженно-строчного формата (схема Чанга и Густавсона)
 * -Упаковка/распаковка матрицы
 * -Сложение
 * Примечание: Упакованные матрицы без распаковки в процессе выполнения операций
 */
public class Main {

    private static final int[][] A = {
            {0, 10, 0, 20, 0, 0},
            {0, 11, 100, 0, 12, 0},
            {30, 0, 0, 0, 13, 0},
            {0, 0, 0, 15, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {40, 14, 0, 0, 0, 0}
    };
    //Умножение =
    //    {0, 110, 1000, 300, 120, 0},
    //    {3000, 121, 1100, 0, 1432, 0},
    //    {0, 300, 0, 600, 0, 0},
    //    {0, 0, 0, 225, 0, 0},
    //    {0, 0, 0, 0, 0, 0},
    //    {0, 554, 1400, 800, 168, 0}
    //Сложение =
    //    {0, 20, 0, 40, 0, 0},
    //    {0, 22, 200, 0, 24, 0},
    //    {60, 0, 0, 0, 26, 0},
    //    {0, 0, 0, 30, 0, 0},
    //    {0, 0, 0, 0, 0, 0},
    //    {80, 28, 0, 0, 0, 0}
    private static final int[][] B = {
            {0, 0, 1, 3, 0, 0, 0, 5, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 7, 0, 1, 0, 0}
    };

    public static void main(String[] args) {

        System.out.println("Кольцевая схема");
        RingScheme matrixA = new RingScheme(A, getCountNotNullElements(A));
        printResult("AN = ", matrixA.getNotNullElements());
        printResult("NR = ", matrixA.getNextIndexByRow());
        printResult("NC = ", matrixA.getNextIndexByCol());
        printResult("JR = ", matrixA.getIndexElementsStartRow());
        printResult("JC = ", matrixA.getIndexElementsStartCol());
        printMatrix("Сложение = ", matrixA.plus(matrixA));
        printMatrix("Умножение = ", matrixA.multiply(matrixA));
        //============================================================
        System.out.println("Разреженно строчный формат");
        MatrixCSRC matrixB = new MatrixCSRC(B);
        printResult("AN = ", matrixB.getNotNullElements());
        printResult("JA = ", matrixB.getIndexElementsByCol());
        printResult("IA = ", matrixB.getIndexElementsANByRow());
        printMatrix("Сложение = ", matrixB.plus(matrixB));
    }

}