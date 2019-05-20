package ru.bmstu.lab5;

import ru.bmstu.lab5.common.AntColony;

import java.util.Scanner;

import static ru.bmstu.lab5.utils.Utils.*;


/**
 * Лабораторная работа № 5.
 * Решаем задачу коммивояжера муравьиным алгоритмом.
 */
public class Main {
    //вес феромона
    private static final double ALPHA = 0.01;
    //след феромона
    private static final double BETA = 0.5;
    //коэффициент испарения феромона
    private static final double P = 0.5;
    //порядок длины оптимального пути (определяет откладывание феромона)
    private static final double Q = 1;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество городов:");
        int cityCount = scanner.nextInt();
        System.out.println("Введите количество муравьев:");
        int antCount = scanner.nextInt();
        System.out.println("Максимальное время для расчета алгоритма:");
        int timeMax = scanner.nextInt();
        System.out.println("Матрица расстояний между городами");
        // Ввод матрицы расстояний, Инициализация параметров алгоритма – α,β,e,Q
        double[][] dists = getMatrixBetweenCities(cityCount);
        printArray(dists);
        // Случай выхода из последнего города без возврата в первый.
        antColonyTrace(cityCount, antCount, timeMax, dists);
    }


    // Случай выхода из последнего города, без возврата
    private static void antColonyTrace(int cityCount, int antCount, int timeMax, double[][] dists) throws Exception {
        System.out.println("Рассмотрим случай выхода из последнего города без возврата в первый.");
        //инициализация колонии
        AntColony antColony = new AntColony(cityCount, antCount, timeMax, dists, ALPHA, BETA, P, Q);
        //путь с минимальной длиной
        int[] bestTrace = antColony.getTrace();
        System.out.print(("Оптимальный маршрут: "));
        printArray(bestTrace);
        //длина минимального следования
        System.out.println(("Длина минимального следования: " + decimalFormat.format(antColony.getBestLength())));
    }

}
