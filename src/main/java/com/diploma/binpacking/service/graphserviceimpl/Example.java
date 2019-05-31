package com.diploma.binpacking.service.graphserviceimpl;

import com.diploma.binpacking.model.Edge;
import com.diploma.binpacking.model.Graph;

/**
 * реалізувати для міст. назву можна і не змінювати.
 */
public class Example {
    public String runExample(String startNode, String endNode) {
        Edge[] edges = {
                new Edge(0, 1, 400),
                new Edge(0, 2, 102),
                new Edge(0, 3, 225),
                new Edge(0, 4, 446),
                new Edge(1, 3, 180),
                new Edge(1, 4, 195),
                new Edge(1, 5, 800),
                new Edge(2, 4, 100),
                new Edge(3, 5, 450),
                new Edge(4, 5, 380),
                new Edge(4, 6, 905),
                new Edge(4, 7, 228),
                new Edge(5, 6, 569),
                new Edge(6, 7, 583)
        };
        Graph g = new Graph(edges, startNode, endNode);
        g.calculateShortestDistances();
        int i = 0;
        for (Edge edge : edges) {
            i++;
            System.out.println(i + ") " + edge.getFromNodeIndex() + " -> " + edge.getToNodeIndex());
        }
        return (g.printResult());
    }
}
