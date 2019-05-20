package ru.bmstu.lab3;

import ru.bmstu.lab3.common.Functions;

import java.util.List;
import java.util.Scanner;

import static ru.bmstu.lab3.common.Functions.calc;

/**
 * Лабораторная работа № 3
 * Перевод из инфиксной записи в постфиксную (польскую) и вычислить ответ
 * Примеры:
 * 1)15*-(3-4)
 * 2)sqrt(4)+(8*0.5)
 * 3)-cube(-3)
 * 4)92/10
 * 5)pow10(2)-95+4*8
 * <p>
 * Реализовано унарный минус, деление и функции cube(), sqrt(), pow10().
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        List<String> expression = Functions.parse(text);
        boolean flag = Functions.flag;
        if (flag) {
            for (String x : expression) {
                System.out.print(x + " ");
            }
            System.out.println();
            System.out.println("Результат: " + calc(expression));
        }
    }
}
