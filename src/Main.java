import lib.Graph;
import lib.GraphNode;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int testCases = s.nextInt();
        for (int i = 0; i < testCases; i++) {
            int nodeCount = s.nextInt();
            int edgeCount = s.nextInt();
            int[][] edges = new int[edgeCount][3];
            for (int j = 0; j < edgeCount; j ++) {
                edges[j][0] = s.nextInt();
                edges[j][1] = s.nextInt();
                edges[j][2] = s.nextInt();
            }
            int start = s.nextInt();
            Graph g = buildGraph(nodeCount, edges);
            g.print();
            int[] distances = g.getDistances(start);

            for (int d :distances) {
                if (d != 0) {
                    System.out.print(d+ " ");
                }
            }
            System.out.println();
        }
    }

    private static Graph buildGraph(int nodeCount, int[][] edges) {
        Graph g = new Graph();
        GraphNode[] nodes = new GraphNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new GraphNode(i+1);
            g.insertNode(nodes[i]);
        }
        for (int[] edge : edges) {
            GraphNode from = nodes[edge[0] - 1];
            GraphNode to = nodes[edge[1] - 1];
            g.insertEdge(from, to, edge[2]);
        }

        return g;
    }
}
