package ru.bmstu.lab1;

import ru.bmstu.lab1.common.DamerauLevenshtein;
import ru.bmstu.lab1.common.Levenshtein;

/**
 * Лабораторная работа №1
 * <p>
 * Две строки на вход.
 * Расстояние Левенштейна и Дамерау-Левенштейна
 */
public class Main {
    public static void main(String[] args) {
        // ===========
        // Levenshtein
        // ===========
        System.out.println("\nLevenshtein");
        Levenshtein levenshtein = new Levenshtein();
        System.out.println(levenshtein.distance("My string", "My $tring"));
        System.out.println(levenshtein.distance("My string", "M string2"));
        System.out.println(levenshtein.distance("My string", "My $tring"));
        // =======
        // Damerau
        // =======
        System.out.println("\nDamerau");
        DamerauLevenshtein damerau = new DamerauLevenshtein();

        // 1 замена
        System.out.println(damerau.distance("ABCDEF", "ABDCEF"));

        // 2 замены
        System.out.println(damerau.distance("ABCDEF", "BACDFE"));

        // 1 удаление
        System.out.println(damerau.distance("ABCDEF", "ABCDE"));
        System.out.println(damerau.distance("ABCDEF", "BCDEF"));
        System.out.println(damerau.distance("ABCDEF", "ABCGDEF"));

    }
}
