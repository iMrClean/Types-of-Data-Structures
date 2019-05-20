package ru.bmstu.lab7.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Узел
 */
public class Node {

    private int value;

    private boolean visited;

    private List<Node> neighbours;

    public Node(int value) {
        this.value = value;
        this.neighbours = new ArrayList<>();
    }

    public void addNeighbours(Node neighbourNode) {
        this.neighbours.add(neighbourNode);
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * Найти соседей узла, используя матрицу смежности
     * Если adjacencyMatrix [i] [j] == 1, то узлы с индексом i и индексом j связаны
     *
     * @param nodes           Список узлов
     * @param adjacencyMatrix Матрица смежности
     * @param node            Узел
     * @return Список узлов
     */
    public List<Node> findNeighbours(List<Node> nodes, int[][] adjacencyMatrix, Node node) {
        int nodeIndex = -1;

        List<Node> neighbours = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).equals(node)) {
                nodeIndex = i;
                break;
            }
        }

        if (nodeIndex != -1) {
            for (int j = 0; j < adjacencyMatrix[nodeIndex].length; j++) {
                if (adjacencyMatrix[nodeIndex][j] == 1) {
                    neighbours.add(nodes.get(j));
                }
            }
        }
        return neighbours;
    }

}
