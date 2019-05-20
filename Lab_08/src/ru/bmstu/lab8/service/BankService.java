package ru.bmstu.lab8.service;

import ru.bmstu.lab8.enums.CashierState;
import ru.bmstu.lab8.enums.CashierType;
import ru.bmstu.lab8.enums.OperationType;
import ru.bmstu.lab8.model.Cashier;
import ru.bmstu.lab8.model.Client;

import java.time.LocalTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.SECONDS;
import static ru.bmstu.lab8.Main.*;
import static ru.bmstu.lab8.utils.Utils.getLocalTimeRandomMinute;
import static ru.bmstu.lab8.utils.Utils.getRandomNumberInRange;

public class BankService {

    /**
     * Метод, который будет имитировать работу банка
     *
     * @param clientList       список клиентов
     * @param cashierList      список касс
     * @param processedRequest список клиентов, которые получили услугу
     * @param q1               очередь 1
     * @param q2               очередь 2
     * @param q3               очередь 3
     * @param q4               очередь 4
     * @param q5               очередь 5
     */
    public void startWork(List<Client> clientList, List<Cashier> cashierList, List<Client> processedRequest, Queue<Client> q1, Queue<Client> q2, Queue<Client> q3, Queue<Client> q4, Queue<Client> q5) {
        LocalTime startTime = START_WORK;
        LocalTime stopTime = startTime.plusHours(DELTA);
        while (startTime.isBefore(STOP_WORK)) {
            List<Client> clients = sortByTime(clientList, startTime, stopTime);
            sendToQueues(cashierList, q1, q2, q3, q4, q5, clients);
            getService(cashierList, q1, q2, q3, q4, q5);
            freeCashier(cashierList, processedRequest);
            startTime = startTime.plusHours(DELTA);
            stopTime = startTime.plusHours(DELTA);
            totalReject = REQUEST_COUNT - processedRequest.size();
        }
        freeCashier(cashierList, processedRequest);
        recountReject(q1, q2, q3, q4, q5);
    }

    /**
     * Возможно очередь еще не освобилась под конец работы, добавим ее в список отказов
     *
     * @param q1 очередь 1
     * @param q2 очередь 2
     * @param q3 очередь 3
     * @param q4 очередь 4
     * @param q5 очередь 5
     */
    private void recountReject(Queue<Client> q1, Queue<Client> q2, Queue<Client> q3, Queue<Client> q4, Queue<Client> q5) {
        if (!q1.isEmpty()) {
            totalReject += q1.size();
        }
        if (!q2.isEmpty()) {
            totalReject += q2.size();
        }
        if (!q3.isEmpty()) {
            totalReject += q3.size();
        }
        if (!q4.isEmpty()) {
            totalReject += q4.size();
        }
        if (!q5.isEmpty()) {
            totalReject += q5.size();
        }
    }

    /**
     * Метод который имитирует приход клиента в банк
     *
     * @param clientList список клиентов
     * @param startTime  начало прихода клиентов
     * @param stopTime   до какого времени они должны быть
     */
    private List<Client> sortByTime(List<Client> clientList, LocalTime startTime, LocalTime stopTime) {
        List<Client> clientsInTime = new ArrayList<>();
        for (Client client : clientList) {
            if (client.getStartTime().isAfter(startTime) && client.getStopTime().isBefore(stopTime)) {
                clientsInTime.add(client);
            }
        }
        return clientsInTime;
    }

    /**
     * Метод который имитирует отправку клиента в очередь
     *
     * @param cashierList список касс
     * @param q1          очередь 1
     * @param q2          очередь 2
     * @param q3          очередь 3
     * @param q4          очередь 4
     * @param q5          очередь 5
     * @param clientList  список клиентов
     */
    private void sendToQueues(List<Cashier> cashierList, Queue<Client> q1, Queue<Client> q2, Queue<Client> q3, Queue<Client> q4, Queue<Client> q5, List<Client> clientList) {
        for (Client client : clientList) {
            for (Cashier cashier : cashierList) {
                if (cashier.getType() == CashierType.A1 && client.getOperation() == OperationType.ACCOUNT) {
                    q1.offer(client);
                    break;
                }
                if (cashier.getType() == CashierType.E1 && client.getOperation() == OperationType.EXCHANGE) {
                    q2.offer(client);
                    break;
                }
                if (cashier.getType() == CashierType.C12 && client.getOperation() == OperationType.CARD) {
                    if (q3.size() <= MAX_QUEUE_SIZE) {
                        q3.offer(client);
                        break;
                    } else {
                        q5.offer(client);
                        break;
                    }
                }
                if (cashier.getType() == CashierType.C12 && client.getOperation() == OperationType.CREDIT) {
                    if (q4.size() <= MAX_QUEUE_SIZE) {
                        q4.offer(client);
                        break;
                    } else {
                        q5.offer(client);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Метод, который имитирует получение услуг на кассе
     *
     * @param cashierList список касс
     * @param q1          очередь 1
     * @param q2          очередь 2
     * @param q3          очередь 3
     * @param q4          очередь 4
     * @param q5          очередь 5
     */
    private void getService(List<Cashier> cashierList, Queue<Client> q1, Queue<Client> q2, Queue<Client> q3, Queue<Client> q4, Queue<Client> q5) {
        for (Cashier cashier : cashierList) {
            if (cashier.getState() == CashierState.VACANT && cashier.getType() == CashierType.A1 && q1.size() != 0) {
                cashier.setClient(q1.peek());
                cashier.setState(CashierState.OCCUPIED);
                q1.poll();
            }
            if (cashier.getState() == CashierState.VACANT && cashier.getType() == CashierType.E1 && q2.size() != 0) {
                cashier.setClient(q2.peek());
                cashier.setState(CashierState.OCCUPIED);
                q2.poll();
            }
            if (cashier.getState() == CashierState.VACANT && cashier.getType() == CashierType.C12 && q3.size() != 0) {
                cashier.setClient(q3.peek());
                cashier.setState(CashierState.OCCUPIED);
                q3.poll();
            }
            if (cashier.getState() == CashierState.VACANT && cashier.getType() == CashierType.C12 && q4.size() != 0) {
                cashier.setClient(q4.peek());
                cashier.setState(CashierState.OCCUPIED);
                q4.poll();
            }
            if (cashier.getState() == CashierState.VACANT && cashier.getType() == CashierType.C12 && q5.size() != 0) {
                cashier.setClient(q5.peek());
                cashier.setState(CashierState.OCCUPIED);
                q5.poll();
            }
        }
    }

    /**
     * Метод который освобождает кассы
     *
     * @param cashierList      список касс
     * @param processedRequest список клиентов, которые были обслужены
     */
    private void freeCashier(List<Cashier> cashierList, List<Client> processedRequest) {
        for (Cashier cashier : cashierList) {
            if (cashier.getState() == CashierState.OCCUPIED) {
                Client client = cashier.getClient();
                processedRequest.add(client);
                cashier.setClient(null);
                cashier.setState(CashierState.VACANT);
                client.setRequestState(true);
            }
        }
    }

    /**
     * Генерация банковских касс разделенных по типам услуг
     *
     * @param cashierList список касс
     */
    public void generateCashier(List<Cashier> cashierList) {
        Long id = 1L;
        for (int i = 0; i < CASHIER_COUNT; i++) {
            Cashier cashier = new Cashier();
            if (i == 0) {
                cashier.setId(id);
                cashier.setType(CashierType.A1);
                cashier.setState(CashierState.VACANT);
            } else if (i == 1) {
                cashier.setId(id);
                cashier.setType(CashierType.E1);
                cashier.setState(CashierState.VACANT);
            } else {
                cashier.setId(id);
                cashier.setType(CashierType.C12);
                cashier.setState(CashierState.VACANT);
            }
            cashierList.add(cashier);
            id++;
        }
    }

    /**
     * Генерация случайных заявок отсортированных по времени
     *
     * @param clientList список клиентов
     */
    public void generateRequests(List<Client> clientList) {
        Long id = 1L;
        for (int i = 0; i < REQUEST_COUNT; i++) {
            int type = new Random().nextInt(4);
            Client client = new Client();
            LocalTime startTime = LocalTime.ofSecondOfDay(getRandomNumberInRange(START_WORK.toSecondOfDay(), STOP_WORK.toSecondOfDay()));
            LocalTime stopTime = startTime.plusMinutes(getLocalTimeRandomMinute(MAX_REQUEST_TIME));
            LocalTime serviceTime = LocalTime.ofSecondOfDay(SECONDS.between(startTime, stopTime));
            switch (type) {
                case 0:
                    client.setId(id);
                    client.setOperation(OperationType.ACCOUNT);
                    client.setStartTime(startTime);
                    client.setServiceTime(serviceTime);
                    client.setStopTime(stopTime);
                    client.setRequestState(false);
                    break;
                case 1:
                    client.setId(id);
                    client.setOperation(OperationType.CREDIT);
                    client.setStartTime(startTime);
                    client.setServiceTime(serviceTime);
                    client.setStopTime(stopTime);
                    client.setRequestState(false);
                    break;
                case 2:
                    client.setId(id);
                    client.setOperation(OperationType.CARD);
                    client.setStartTime(startTime);
                    client.setServiceTime(serviceTime);
                    client.setStopTime(stopTime);
                    client.setRequestState(false);
                    break;
                case 3:
                    client.setId(id);
                    client.setOperation(OperationType.EXCHANGE);
                    client.setStartTime(startTime);
                    client.setServiceTime(serviceTime);
                    client.setStopTime(stopTime);
                    client.setRequestState(false);
                    break;
                default:
                    client.setOperation(OperationType.NONE);
            }
            clientList.add(client);
            id++;
        }
        clientList.sort(Comparator.comparing(Client::getStartTime));
    }
}
