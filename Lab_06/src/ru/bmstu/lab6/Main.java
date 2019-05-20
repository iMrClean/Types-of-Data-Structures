package ru.bmstu.lab6;

import ru.bmstu.lab6.common.DijkstraAlgorithm;
import ru.bmstu.lab6.model.Edge;
import ru.bmstu.lab6.model.Graph;
import ru.bmstu.lab6.model.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Лабораторная работа № 6
 * Алгоритм Дейкстры
 */
public class Main {

    private static List<Vertex> nodes = new ArrayList<>();

    private static List<Edge> edges = new ArrayList<>();

    private static int COUNT = 12;

    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
            Vertex location = new Vertex("Node_Id - " + i, "Node_Name - " + i);
            nodes.add(location);
        }
        addLane("Edge_0", 0, 1, 85);
        addLane("Edge_1", 0, 2, 217);
        addLane("Edge_2", 0, 4, 173);
        addLane("Edge_3", 2, 6, 186);
        addLane("Edge_4", 2, 7, 103);
        addLane("Edge_5", 3, 7, 183);
        addLane("Edge_6", 5, 8, 250);
        addLane("Edge_7", 8, 9, 84);
        addLane("Edge_8", 7, 9, 167);
        addLane("Edge_9", 4, 9, 502);
        addLane("Edge_10", 9, 10, 40);
        addLane("Edge_11", 1, 10, 600);

        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));
        for (Vertex vertex : path) {
            System.out.println(vertex.getName());
        }
    }

    private static void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }
}
