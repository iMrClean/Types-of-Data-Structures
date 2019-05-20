package ru.bmstu.lab6.model;

import java.util.Objects;

public class Edge {

    private final String id;

    private final Vertex source;

    private final Vertex destination;

    private final int weight;

    public Edge(String id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return weight == edge.weight &&
                Objects.equals(id, edge.id) &&
                Objects.equals(source, edge.source) &&
                Objects.equals(destination, edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, destination, weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "id='" + id + '\'' +
                ", source=" + source +
                ", destination=" + destination +
                ", weight=" + weight +
                '}';
    }
}