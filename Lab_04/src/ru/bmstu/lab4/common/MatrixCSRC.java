package ru.bmstu.lab4.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixCSRC {
    /**
     * Ненулевые элементы
     */
    private List<Integer> AN;
    /**
     * Индексы элементов, по столбцам
     */
    private List<Integer> JA;
    /**
     * Указатель позиции массивов AN и JA.
     */
    private List<Integer> IR;
    /**
     * Матрица
     */
    private int[][] matrix;

    public MatrixCSRC(int[][] matrix) {
        AN = new ArrayList<>();
        JA = new ArrayList<>();
        IR = new ArrayList<>(Collections.nCopies(1, 0));
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
     * Индексы элементов, по столбцам
     *
     * @return Список элементов по столбцу для AN
     */
    public List<Integer> getIndexElementsByCol() {
        int indexAN = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    JA.add(indexAN);
                }
                indexAN++;
                if (indexAN == matrix[i].length) {
                    indexAN = 0;
                }
            }
        }
        return JA;
    }

    /**
     * Указатель свободной позиции в JA и AN по строке (количество ненулевых элементов - индекс.
     *
     * @return Список индексов свободных позиций
     */
    public List<Integer> getIndexElementsANByRow() {
        int buf = 0;

        for (int i = 0; i < matrix.length; i++) {
            int count = 0;
            int rawSize = 1;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    count++;
                }
                if (rawSize == matrix[i].length) {
                    if (count == 0) {
                        IR.add(buf);
                    } else {
                        IR.add(count);
                    }
                    buf = count;
                }
                ++rawSize;
            }
            if (i == matrix.length - 1) {
                if (count != 0) {
                    int lastIndex = AN.size();
                    IR.set(IR.size() - 1, lastIndex);
                } else {
                    IR.set(IR.size() - 1, IR.get(IR.size() - 2));
                }
            }
        }

        return IR;
    }

    /**
     * Сложение
     *
     * @param matrixCSRC Матрица
     * @return Результат сложения в запакованном виде
     */
    public int[][] plus(MatrixCSRC matrixCSRC) {
        if (matrix.length != matrixCSRC.matrix.length || matrix[0].length != matrixCSRC.matrix[0].length) {
            throw new IllegalArgumentException("Невозможная операция");
        }

        int[][] result = new int[matrix.length][matrixCSRC.matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrixCSRC.matrix[i].length; j++) {
                result[i][j] = matrix[i][j] + matrixCSRC.matrix[i][j];
            }
        }

        return result;
    }

}
