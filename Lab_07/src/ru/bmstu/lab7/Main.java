package ru.bmstu.lab7;

import ru.bmstu.lab7.common.BreadthFirstSearch;
import ru.bmstu.lab7.common.DepthFirstSearch;
import ru.bmstu.lab7.model.Node;

import java.util.ArrayList;
import java.util.List;

import static ru.bmstu.lab7.utils.Utils.clearVisitedFlags;

/**
 * Лабораторная работа № 7
 * Реализовтаь обход графа в ширину и глубину.
 */
public class Main {

    private static List<Node> nodes = new ArrayList<>();

    private static int[][] adjacencyMatrix = {
            {0, 1, 1, 0, 0, 0, 0},  // Node 1: 40
            {0, 0, 0, 1, 0, 0, 0},  // Node 2 :10
            {0, 1, 0, 1, 1, 1, 0},  // Node 3: 20
            {0, 0, 0, 0, 1, 0, 0},  // Node 4: 30
            {0, 0, 0, 0, 0, 0, 1},  // Node 5: 60
            {0, 0, 0, 0, 0, 0, 1},  // Node 6: 50
            {0, 0, 0, 0, 0, 0, 0},  // Node 7: 70
    };

    public static void main(String[] arg) {

        Node node40 = new Node(40);
        Node node10 = new Node(10);
        Node node20 = new Node(20);
        Node node30 = new Node(30);
        Node node60 = new Node(60);
        Node node50 = new Node(50);
        Node node70 = new Node(70);

        nodes.add(node40);
        nodes.add(node10);
        nodes.add(node20);
        nodes.add(node30);
        nodes.add(node60);
        nodes.add(node50);
        nodes.add(node70);

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
        System.out.println("Обход графа DFS с использованием стека");
        depthFirstSearch.dfsUsingStack(nodes, adjacencyMatrix, node40);
        System.out.println();
        clearVisitedFlags(nodes);

        System.out.println("Обход графа DFS с использованием рекурсии");
        depthFirstSearch.dfs(nodes, adjacencyMatrix, node40);
        System.out.println();
        clearVisitedFlags(nodes);

        System.out.println("Обход графа BFS");
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        breadthFirstSearch.bfs(nodes, adjacencyMatrix, node40);

    }
}