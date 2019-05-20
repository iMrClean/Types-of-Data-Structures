package ru.bmstu.lab4.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.bmstu.lab4.utils.Utils.getIndexAN;

public class RingScheme {
    /**
     * Ненулевые элементы
     */
    private List<Integer> AN;
    /**
     * Индекс следующих элементов по строке
     */
    private List<Integer> NR;
    /**
     * Индекс следующих элементов по столбцу
     */
    private List<Integer> NC;
    /**
     * Индексы элементов, с которых начинаются строки
     */
    private List<Integer> JR;
    /**
     * Индексы элементов, с которых начинаются столбцы
     */
    private List<Integer> JC;
    /**
     * Матрица
     */
    private int[][] matrix;

    public RingScheme(int[][] matrix, int notNullElements) {
        AN = new ArrayList<>();
        NR = new ArrayList<>();
        NC = new ArrayList<>(Collections.nCopies(notNullElements, -1));
        JR = new ArrayList<>();
        JC = new ArrayList<>();
        this.matrix = matrix;
    }

    /**
     * Ненулевые элементы
     *
     * @return Список ненулевых элементов
     */
    public List<Integer> getNotNullElements() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    AN.add(matrix[i][j]);
                }
            }
        }
        return AN;
    }

    /**
     * Индекс следующих элементов по строке
     *
     * @return Список элементов по строке для AN
     */
    public List<Integer> getNextIndexByRow() {
        int indexAN = 0;
        for (int i = 0; i < matrix.length; i++) {
            int firstIndexAN = 0;
            boolean notNullElem = false;
            boolean moreThanOne = false;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                notNullElem = true;
                if (moreThanOne) {
                    NR.set(NR.size() - 1, indexAN);
                    NR.add(-1);
                } else {
                    NR.add(indexAN);
                    firstIndexAN = indexAN;
                    moreThanOne = true;
                }
                indexAN++;
            }
            if (notNullElem) {
                NR.set(NR.size() - 1, firstIndexAN);
            }
        }
        return NR;
    }

    /**
     * Индекс следующих элементов по столбцу
     *
     * @return Список элементов по столбцу для AN
     */
    public List<Integer> getNextIndexByCol() {
        int indexAN;
        for (int j = 0; j < matrix[0].length; j++) {
            int firstIndexAN = 0;
            int nextIndexAN = 0;
            boolean moreThanOne = false;
            boolean notNullElem = false;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                indexAN = getIndexAN(matrix, i, j);
                notNullElem = true;
                if (moreThanOne) {
                    NC.set(nextIndexAN, indexAN);
                    NC.set(indexAN, nextIndexAN);
                    nextIndexAN = indexAN;
                } else {
                    NC.set(indexAN, indexAN);
                    firstIndexAN = indexAN;
                    nextIndexAN = indexAN;
                    moreThanOne = true;
                }
            }

            if (notNullElem) {
                NC.set(nextIndexAN, firstIndexAN);
            }
        }
        return NC;
    }

    /**
     * Индексы элементов, с которых начинаются строки
     *
     * @return Список элементов по строке для AN
     */
    public List<Integer> getIndexElementsStartRow() {
        int indexAN = 0;
        for (int i = 0; i < matrix.length; i++) {
            int zero = 0;
            boolean moreThanOne = false;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    if (moreThanOne) {
                        indexAN++;
                    } else {
                        JR.add(indexAN);
                        indexAN++;
                        moreThanOne = true;
                    }
                } else {
                    zero++;
                    if (zero == matrix[0].length) {
                        JR.add(-1);
                    }
                }
            }
        }
        return JR;
    }

    /**
     * Индексы элементов, с которых начинаются столбцы
     *
     * @return Список элементов по столбцу для AN
     */
    public List<Integer> getIndexElementsStartCol() {
        for (int j = 0; j < matrix[0].length; j++) {
            int zero = 0;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] != 0) {
                    JC.add(getIndexAN(matrix, i, j));
                    break;
                } else {
                    zero++;
                    if (zero == matrix.length) {
                        JC.add(-1);
                    }
                }
            }
        }
        return JC;
    }

    /**
     * Сложение
     *
     * @param matrixRS Матрица
     * @return Результат сложения в запакованном виде
     */
    public int[][] plus(RingScheme matrixRS) {
        if (matrix.length != matrixRS.matrix.length || matrix[0].length != matrixRS.matrix[0].length) {
            throw new IllegalArgumentException("Невозможная операция");
        }

        int[][] result = new int[matrix.length][matrixRS.matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrixRS.matrix[i].length; j++) {
                result[i][j] = matrix[i][j] + matrixRS.matrix[i][j];
            }
        }

        return result;
    }

    /**
     * Умножение
     *
     * @param matrixRS Матрица
     * @return Результат умножения в запакованном виде
     */
    public int[][] multiply(RingScheme matrixRS) {

        if (matrix[0].length != matrixRS.matrix.length) {
            throw new IllegalArgumentException("Размер столбца матрицы А должен совпадать со сторокй матрицы Б");
        }

        int[][] result = new int[matrix.length][matrixRS.matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrixRS.matrix[i].length; j++) {
                for (int k = 0; k < matrixRS.matrix.length; k++) {
                    result[i][j] += matrix[i][k] * matrixRS.matrix[k][j];
                }
            }
        }

        return result;
    }

}