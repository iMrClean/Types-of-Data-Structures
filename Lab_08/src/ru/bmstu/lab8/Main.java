package ru.bmstu.lab8;

import ru.bmstu.lab8.model.Cashier;
import ru.bmstu.lab8.model.Client;
import ru.bmstu.lab8.service.BankService;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static ru.bmstu.lab8.utils.Utils.*;

/**
 * Лабораторная работа №6.
 * <p>
 * В 5 кассах сидят менеджеры, являющиеся предпочтительными для клиентов.
 * Клиент с любым типом заявки из четырех встает в очередь к "своему" менеджеру.
 * Если к нужному большая очередь он будет направлен туда, где очередь меньше.
 * Моделировать разные соотношения клиентов по менеджерам.
 */
public class Main {
    /**
     * Общее количество заявок
     */
    public static final int REQUEST_COUNT = 50;
    /**
     * Общее количество касс по условию 5
     */
    public static final int CASHIER_COUNT = 5;
    /**
     * Максимальное время на обслуживание клиента
     */
    public static final int MAX_REQUEST_TIME = 20;
    /**
     * Максимальная длина очереди
     */
    public static final int MAX_QUEUE_SIZE = 5;
    /**
     * Время начало работы
     */
    public static final LocalTime START_WORK = LocalTime.of(9, 0);
    /**
     * Время конца работы
     */
    public static final LocalTime STOP_WORK = LocalTime.of(18, 0);
    /**
     * Дельта часов.
     */
    public static final int DELTA = 1;
    /**
     * Количество отказов
     */
    public static int totalReject = 0;

    public static void main(String[] args) {
        List<Client> clientList = new ArrayList<>();
        List<Cashier> cashierList = new ArrayList<>();
        List<Client> processedRequest = new ArrayList<>();
        Queue<Client> q1 = new ArrayDeque<>();
        Queue<Client> q2 = new ArrayDeque<>();
        Queue<Client> q3 = new ArrayDeque<>();
        Queue<Client> q4 = new ArrayDeque<>();
        Queue<Client> q5 = new ArrayDeque<>();

        BankService bankService = new BankService();
        bankService.generateRequests(clientList);
        printRequest(clientList);

        bankService.generateCashier(cashierList);
        printCashier(cashierList);

        bankService.startWork(clientList, cashierList, processedRequest, q1, q2, q3, q4, q5);
        printRequest(clientList); //printRequest(processedRequest);
        printTotalReject();
    }
}
