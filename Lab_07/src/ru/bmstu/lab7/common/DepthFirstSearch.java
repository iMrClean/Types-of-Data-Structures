package ru.bmstu.lab7.common;

import ru.bmstu.lab7.model.Node;

import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {

    /**
     * Поиск в глубину с использованием рекурсии
     *
     * @param node Узел
     */
    public void dfs(Node node) {
        System.out.print(node.getValue() + " ");
        List<Node> neighbours = node.getNeighbours();
        node.setVisited(true);
        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
            if (n != null && !n.isVisited()) {
                dfs(n);
            }
        }
    }

    /**
     * Поиск в глубину с использованием рекурсии
     *
     * @param node Узел
     */
    public void dfs(List<Node> nodes, int[][] adjacency_matrix, Node node) {
        System.out.print(node.getValue() + " ");
        List<Node> neighbours = node.findNeighbours(nodes, adjacency_matrix, node);
        node.setVisited(true);
        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
            if (n != null && !n.isVisited()) {
                dfs(nodes, adjacency_matrix, n);
            }
        }
    }

    /**
     * Поиск в глубину с использованием стека
     *
     * @param node Узел
     */
    public void dfsUsingStack(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        node.setVisited(true);
        while (!stack.isEmpty()) {
            Node element = stack.pop();
            System.out.print(element.getValue() + " ");

            List<Node> neighbours = element.getNeighbours();
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && !n.isVisited()) {
                    stack.add(n);
                    n.setVisited(true);
                }
            }
        }
    }

    /**
     * Поиск в глубину с использованием стека
     *
     * @param node Узел
     */
    public void dfsUsingStack(List<Node> nodes, int[][] adjacency_matrix, Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        node.setVisited(true);
        while (!stack.isEmpty()) {
            Node element = stack.pop();
            System.out.print(element.getValue() + " ");

            List<Node> neighbours = node.findNeighbours(nodes, adjacency_matrix, element);
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && !n.isVisited()) {
                    stack.add(n);
                    n.setVisited(true);
                }
            }
        }
    }

}