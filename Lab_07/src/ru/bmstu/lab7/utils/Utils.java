package ru.bmstu.lab7.utils;

import ru.bmstu.lab7.model.Node;

import java.util.List;

public class Utils {

    /**
     * Подчищаем флаги
     *
     * @param nodes Узлы
     */
    public static void clearVisitedFlags(List<Node> nodes) {
        nodes.forEach(node -> node.setVisited(false));
    }

}
