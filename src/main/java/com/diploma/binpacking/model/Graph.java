package com.diploma.binpacking.model;


import java.util.ArrayList;

/**
 * Предаставлення графу у програмі.
 */
public class Graph {

    private Node[] nodes;
    private int noOfNodes;
    private Edge[] edges;
    private int noOfEdges;
    private String startNode;
    private String endNode;

    public Graph(Edge[] edges, String startNode, String endNode) {
        this.edges = edges;
        this.startNode = startNode;
        this.endNode = endNode;
        // create all nodes ready to be updated with the edges
        this.noOfNodes = calculateNoOfNodes(edges);
        this.nodes = new Node[this.noOfNodes];
        for (int n = 0; n < this.noOfNodes; n++) {
            this.nodes[n] = new Node();
        }
        // add all the edges to the nodes, each edge added to two nodes (to and from)
        this.noOfEdges = edges.length;
        for (int edgeToAdd = 0; edgeToAdd < this.noOfEdges; edgeToAdd++) {
            this.nodes[edges[edgeToAdd].getFromNodeIndex()].getEdges().add(edges[edgeToAdd]);
            this.nodes[edges[edgeToAdd].getToNodeIndex()].getEdges().add(edges[edgeToAdd]);
        }


        String[] cities = {"Kyiv", "Polyana", "Vinnytsia", "Myrhorod", "Yaremche", "Lviv", "Poltava", "Kherson"};
        for (int i = 0; i < this.nodes.length; i++) {
            this.nodes[i].setName(cities[i]);
        }
    }

    private int calculateNoOfNodes(Edge[] edges) {
        int noOfNodes = 0;
        for (Edge e : edges) {
            if (e.getToNodeIndex() > noOfNodes)
                noOfNodes = e.getToNodeIndex();
            if (e.getFromNodeIndex() > noOfNodes)
                noOfNodes = e.getFromNodeIndex();
        }
        noOfNodes++;
        return noOfNodes;
    }

    /**
     * Реалізація алгоритму Дейкстри
     */
    public void calculateShortestDistances() {
        //Початкова вершина - 0
        int nextNode = 0;
        for (int i = 0; i < this.nodes.length; i++) {
            if (this.nodes[i].getName().equals(this.startNode)) {
                this.nodes[i].setDistanceFromSource(i);
                nextNode = i;
            }

        }


        // Проходимо по всім вершинам
        for (int i = 0; i < this.nodes.length; i++) {

            // Перевіряємо всі ребра, які виходять з цієї вершини
            ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();

            for (Edge currentNodeEdge : currentNodeEdges) {
                int neighbourIndex = currentNodeEdge.getNeighbourIndex(nextNode);

                // ідемо на ту вершину, де ще не були
                if (!this.nodes[neighbourIndex].isVisited()) {
                    int tentative = this.nodes[nextNode].getDistanceFromSource() + currentNodeEdge.getLength();
                    if (tentative < nodes[neighbourIndex].getDistanceFromSource()) {
                        nodes[neighbourIndex].setDistanceFromSource(tentative);
                    }
                }
            }

            // Всі сусіди перевірені, тому дану вершину відвідували
            nodes[nextNode].setVisited(true);

            // Наступна мершина має містити коротший шлях
            nextNode = getNodeShortestDistanced();
        }
    }

    /**
     * Пошук найкоротшої відстані між вершинами
     */
    private int getNodeShortestDistanced() {
        int storedNodeIndex = 0;
        int storedDist = Integer.MAX_VALUE;
        for (int i = 0; i < this.nodes.length; i++) {
            int currentDist = this.nodes[i].getDistanceFromSource();
            if (!this.nodes[i].isVisited() && currentDist < storedDist) {
                storedDist = currentDist;
                storedNodeIndex = i;
            }
        }
        return storedNodeIndex;
    }

    /**
     * Виведення результату
     */
    public String printResult() {
        System.out.println();
        Node node = new Node();
        String output = "Number of nodes = " + this.noOfNodes;
        output += "\nNumber of edges = " + this.noOfEdges;
        for (Node value : this.nodes) {
            if (value.getName().equals(endNode)) {
                node = value;
                break;
            }
        }
        output += ("\nThe shortest distance from node " + this.startNode + " to node " +node.getName() + " is " + node.getDistanceFromSource());
        return output;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public int getNoOfNodes() {
        return noOfNodes;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public int getNoOfEdges() {
        return noOfEdges;
    }
}
