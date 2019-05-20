package ru.bmstu.lab7.common;

import ru.bmstu.lab7.model.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    public void bfs(List<Node> nodes, int[][] adjacency_matrix, Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        node.setVisited(true);
        while (!queue.isEmpty()) {

            Node element = queue.remove();
            System.out.print(element.getValue() + " ");
            List<Node> neighbours = node.findNeighbours(nodes, adjacency_matrix, element);
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && !n.isVisited()) {
                    queue.add(n);
                    n.setVisited(true);
                }
            }
        }
    }

}
