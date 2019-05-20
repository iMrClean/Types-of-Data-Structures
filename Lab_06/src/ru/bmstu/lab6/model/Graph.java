package ru.bmstu.lab6.model;

import java.util.List;
import java.util.Objects;

public class Graph {

    private final List<Vertex> vertexes;

    private final List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(vertexes, graph.vertexes) &&
                Objects.equals(edges, graph.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexes, edges);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertexes=" + vertexes +
                ", edges=" + edges +
                '}';
    }

}