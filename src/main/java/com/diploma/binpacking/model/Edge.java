package com.diploma.binpacking.model;

/**
 * Клас "Ребро".
 * Містить вершину початку, кінця та довжину ребра(завжди 1)
 * */
public class Edge {

    private int fromNodeIndex;
    private int toNodeIndex;
    private int length;

    public Edge(int fromNodeIndex, int toNodeIndex, int length) {
        this.fromNodeIndex = fromNodeIndex;
        this.toNodeIndex = toNodeIndex;
        this.length = length;
    }

    public int getFromNodeIndex() {
        return fromNodeIndex;
    }

    public int getToNodeIndex() {
        return toNodeIndex;
    }

    public int getLength() {
        return length;
    }

    // Визначення сусідніх вершин
    public int getNeighbourIndex(int nodeIndex) {
        if (this.fromNodeIndex == nodeIndex) {
            return this.toNodeIndex;
        } else {
            return this.fromNodeIndex;
        }
    }
}
