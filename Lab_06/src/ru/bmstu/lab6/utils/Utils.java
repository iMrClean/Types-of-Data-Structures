package ru.bmstu.lab6.utils;

import java.util.Random;

public class Utils {

    private static final Random RND = new Random();

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Максимальное значение должно быть больше минимального");
        }

        return RND.nextInt(max - min) + min;
    }

}
