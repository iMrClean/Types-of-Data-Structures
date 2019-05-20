package ru.bmstu.lab8.utils;

import ru.bmstu.lab8.model.Cashier;
import ru.bmstu.lab8.model.Client;

import java.time.LocalTime;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import static ru.bmstu.lab8.Main.totalReject;

public class Utils {

    private static final Random RND = new Random();

    /**
     * Генератор рандомных чисел от [min; max)
     */
    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Максимальное значение должно быть больше минимального");
        }

        return RND.nextInt(max - min) + min;
    }

    /**
     * Генератор рандомного времени от [1; max)
     */
    public static int getLocalTimeRandomMinute(int max) {
        return LocalTime.of(0, getRandomNumberInRange(1, max)).getMinute();
    }

    /**
     * Выводим список заявок в консоль
     *
     * @param clientList список клиентов
     */
    public static void printRequest(List<Client> clientList) {
        clientList.forEach(client ->
                System.out.println("Client Id = " + client.getId() +
                        " " + "Start time = " + client.getStartTime() +
                        " " + "Stop time = " + client.getStopTime() +
                        " " + "Service time = " + client.getServiceTime() +
                        " " + "Request state = " + client.getRequestState() +
                        " " + "Operation = " + client.getOperation()
                ));

    }

    /**
     * Выводим список касс в консоль
     *
     * @param cashierList список касс
     */
    public static void printCashier(List<Cashier> cashierList) {
        cashierList.forEach(cashier ->
                System.out.println("Cashier Id = " + cashier.getId() + " " + "Type = " + cashier.getType() + " " + " " + "State = " + cashier.getState()));
    }

    /**
     * Выводим количество отказов
     */
    public static void printTotalReject() {
        System.out.println("Total reject = " + totalReject);
    }
}
