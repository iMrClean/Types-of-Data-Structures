package ru.bmstu.lab5.common;

import java.util.Random;

import static ru.bmstu.lab5.utils.Utils.getRandomNumberInRange;

public class AntColony {
    //начальное количество феромона на ребрах
    private static final double INITIAL_PHEROMONE = 0.01;
    //количество городов
    private int cityCount;
    //количество муравьев
    private int antCount;
    //время жизни колонии (максимальное время для расчета алгоритма)
    private int timeMax;
    //матрица расстояний между городами
    private double[][] distances;
    //вес феромона
    private double alpha;
    //след феромона
    private double beta;
    //коэффициент испарения феромона
    private double P;
    //порядок длины оптимального пути (определяет откладывание феромона)
    private double Q;
    //длина минимального следования
    private double bestLength;

    public AntColony(int cityCount, int antCount, int timeMax, double[][] distances, double alpha, double beta, double p, double q) {
        this.cityCount = cityCount;
        this.antCount = antCount;
        this.timeMax = timeMax;
        this.distances = distances;
        this.alpha = alpha;
        this.beta = beta;
        this.P = p;
        this.Q = q;
    }

    public double getBestLength() {
        return bestLength;
    }

    //инициализации колонии псевдослучайными маршрутами
    public int[] getTrace() throws Exception {
        // инициализация муравьев
        int[][] ants = initializeAnts();
        //инициализация феромонов
        double[][] pheromones = initializePheromones();
        //определение начального значения оптимального пути
        int[] bestTrace = calculateBestTrace(ants);
        //вычисление длины оптимального пути
        double bestLength = calculateLength(bestTrace);
        //цикл по времени жизни колонии
        int time = 0;

        while (time < timeMax) {
            //цикл по всем муравьям и перестроение маршрутов
            updateAnts(ants, pheromones);
            //определение текущего значения оптимального пути
            int[] currentBestTrace = calculateBestTrace(ants);
            //вычисление текущей длины оптимального пути
            double currentBestLength = calculateLength(currentBestTrace);
            //обновляем при улучшении результа
            if (currentBestLength < bestLength) {
                bestLength = currentBestLength;
                bestTrace = currentBestTrace;
            }
            //цикл по всем ребрам графа, чтобы обновить феромоны
            updatePheromones(pheromones, ants);
            time++;
        }
        //длина лучшего маршрута
        this.bestLength = bestLength;
        //выводим лучший ответ
        return bestTrace;
    }

    //инициализация маршрутов муравьев колонии
    private int[][] initializeAnts() {
        int[][] ants = new int[antCount][];

        for (int i = 0; i < antCount; i++) {
            //генерируем начальный город для муравья
            int startCity = getRandomNumberInRange(0, cityCount);
            //генерируем маршрут для муравья
            ants[i] = initializeTrace(startCity);
        }
        return ants;
    }

    //инициализация маршрута муравья (startCity - начальный город маршрута)
    private int[] initializeTrace(int startCity) {
        int[] trace = new int[cityCount];
        //заполним массив значениями
        for (int i = 0; i < cityCount; i++) {
            trace[i] = i;
        }

        //Тасование Фишера-Йетса
        for (int i = 0; i < cityCount; i++) {
            int r = getRandomNumberInRange(i, cityCount);
            int temp = trace[r];
            trace[r] = trace[i];
            trace[i] = temp;
        }

        //начальный город маршрута ставим на первое место
        for (int i = 0; i < cityCount; i++) {
            if (trace[i] == startCity) {
                int temp = trace[0];
                trace[0] = trace[i];
                trace[i] = temp;
            }
        }
        return trace;
    }

    //инициализация феромонов на всех маршрутах
    private double[][] initializePheromones() {
        double[][] pheromones = new double[cityCount][cityCount];

        for (int i = 0; i < cityCount; i++) {
            //не существует перехода из города в самого себя
            pheromones[i][i] = 0.0;

            for (int j = i + 1; j < cityCount; j++) {
                //феромоны на пути (из А в Б) = феромонам на пути (из Б в А)
                pheromones[i][j] = pheromones[j][i] = INITIAL_PHEROMONE;
            }
        }
        return pheromones;
    }

    //вычисление длины маршрута
    private double calculateLength(int[] trace) {
        double result = 0.0;

        for (int i = 0; i < trace.length - 1; i++) {
            result += distances[trace[i]][trace[i + 1]];
        }
        return result;
    }

    //вычисление оптимального маршрута (маршрута с минимальной длиной)
    private int[] calculateBestTrace(int[][] ants) {
        //выберем для начала в качестве лучшего маршрута - маршрут первого муравья
        double bestLength = calculateLength(ants[0]);
        int idBestLength = 0;
        //находим минимальный маршрут из существующих
        for (int i = 1; i < ants.length; i++) {
            double currentLength = calculateLength(ants[i]);

            if (currentLength < bestLength) {
                bestLength = currentLength;
                idBestLength = i;
            }
        }
        return ants[idBestLength];
    }

    //обновление маршрутов каждого муравья
    private void updateAnts(int[][] ants, double[][] pheromones) throws Exception {
        for (int i = 0; i < antCount; i++) {
            //генерируем начальный город для муравья
            int startCity = getRandomNumberInRange(0, cityCount);
            //вычисления нового маршрута для муравья
            ants[i] = buildTrace(startCity, pheromones);
        }
    }

    //вычисления нового маршрута для муравья
    private int[] buildTrace(int startCity, double[][] pheromones) throws Exception {
        int[] trace = new int[cityCount];
        boolean[] visited = new boolean[cityCount];
        //маршрут начинается со случайного города
        trace[0] = startCity;
        //начальный город сразу помечаем как посещенный
        visited[startCity] = true;

        for (int i = 0; i < cityCount - 1; i++) {
            int currentCity = trace[i];
            //вычисляем следующий город при условии, что находимся в текущем городе. При этом учитываем феромоны на пути и список уже посещенных городов
            int nextCity = nextCity(currentCity, visited, pheromones);
            trace[i + 1] = nextCity;
            visited[nextCity] = true;
        }
        return trace;
    }

    //выбор следующего города
    private int nextCity(int currentCity, boolean[] visited, double[][] pheromones) throws Exception {
        //вычисляем вероятности пойти в каждый из городов, при условии что ряд город уже посетили и находися в текущем городе
        double[] probabilities = calcProbabilitiesMoveToCity(currentCity, visited, pheromones);
        //строим функцию распределения (формула 7.3.1 - из учебника М.В. Ульянова - стр.205)
        double[] distribution = new double[probabilities.length + 1];

        //переход в (0-город)
        distribution[0] = 0;

        //переходы в (0-город + 1-ый город), в (0-город + 1-ый город + 2-ой город), в и т.д.
        for (int i = 0; i < probabilities.length; i++) {
            distribution[i + 1] = distribution[i] + probabilities[i];
        }

        //подкидываем монетку и выбираем город, в который пойдем дальше
        double p = new Random().nextDouble();

        for (int i = 0; i < distribution.length - 1; i++) {
            if (p >= distribution[i] && p < distribution[i + 1]) {
                return i;
            }
        }
        throw new Exception("Ошибка при выборе следующего города");
    }

    //вычисление вероятностно-пропорционального правила для определения вероятности перехода k-го муравья из города i в город j
    private double[] calcProbabilitiesMoveToCity(int currentCity, boolean[] visited, double[][] pheromones) {
        //произведение тау на эта (числитель формулы 7.3.1 - из учебника М.В. Ульянова - стр.205)
        double[] tau = new double[cityCount];
        //сумма произведений тау на эта (знаменатель формулы 7.3.1 - из учебника М.В. Ульянова - стр.205)
        double sum = 0.0;

        for (int i = 0; i < tau.length; i++) {
            //вероятность пойти в текущий город или город, который уже посещали = 0
            if (i == currentCity || visited[i]) {
                tau[i] = 0.0;
            } else {
                //вычисляем вероятность пойти в оставшиеся города
                tau[i] = Math.pow(pheromones[currentCity][i], alpha) * Math.pow((1.0 / distances[currentCity][i]), beta);
            }
            sum += tau[i];
        }

        double[] probabilities = new double[cityCount];

        for (int i = 0; i < probabilities.length; i++) {
            //отношение числителя к знаменатилю (формулы 7.3.1 - из учебника М.В. Ульянова - стр.205 )
            probabilities[i] = tau[i] / sum;
        }
        return probabilities;
    }

    // Обновление феромонов на дугах графа. decrease во внешний цикл, иначе как шахматы
    private void updatePheromones(double[][] pheromones, int[][] ants) {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = i + 1; j < pheromones[i].length; j++) {
                //испарение феромона в среде (первая часть от формулы 7.3.2 - из учебника  М.В. Ульянова - стр.205 ) - вынести наружу!!
                double decrease = (1.0 - P) * pheromones[i][j];
                //если муравей прошел по этому пути, то он отложил какое-то количество феромона (вторая часть от формулы 7.3.2 - из учебника  М.В. Ульянова - стр.205 )
                double increase = 0.0;
                for (int[] ant : ants) {
                    //длина пути k-го муравья
                    double length = calculateLength(ant);
                    if (edgeInTrace(i, j, ant)) {
                        increase += (Q / length);
                    }
                }
                //(формула 7.3.2 - из учебника  М.В. Ульянова - стр.205 )
                pheromones[i][j] = decrease + increase;
                pheromones[j][i] = pheromones[i][j];
            }
        }
    }

    //встречалось ли ребро на пути
    private static boolean edgeInTrace(int cityX, int cityY, int[] trace) {
        //необходимо определить, встретились ли в маршруте последовательно города cityX и cityY или наоборот
        //находим индексы этих городов
        int idCityX = -1;
        int idCityY = -1;

        for (int i = 0; i < trace.length; i++) {
            if (trace[i] == cityX) idCityX = i;
            if (trace[i] == cityY) idCityY = i;
        }
        //если индексы отличаются на единицу, то муравей прошел по этому ребру
        return Math.abs(idCityX - idCityY) == 1;
    }

}
