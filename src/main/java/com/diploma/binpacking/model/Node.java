package com.diploma.binpacking.model;

import java.util.ArrayList;

/**
 * Клас "Вершина". Зберігає значення, чи було відвідана, відстань від джерела.
 * Записується в масив вершин та відправляється в клас "Граф" для обчислення.
 * */
public class Node {

    private int distanceFromSource = Integer.MAX_VALUE;
    private boolean visited;
    private String name;
    private ArrayList<Edge> edges = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDistanceFromSource() {
        return distanceFromSource;
    }

    public void setDistanceFromSource(int distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
}
