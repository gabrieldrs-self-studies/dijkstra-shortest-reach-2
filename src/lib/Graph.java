package lib;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<GraphNode> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public void insertNode(GraphNode n) {
        if (!nodes.contains(n)) {
            nodes.add(n);
        }
    }

    public GraphNode getNode(GraphNode n) {
        int i = nodes.indexOf(n);
        return nodes.get(i);
    }

    public GraphNode getNodeFromLabel(int i) {
        GraphNode startNode = null;
        for (GraphNode n : nodes) {
            if (n.label == i) {
                startNode = n;
                break;
            }
        }
        return startNode;
    }

    public void insertEdge(GraphNode from, GraphNode to, int weight){
        from.edges.add(new Object[]{to, weight});
        to.edges.add(new Object[]{from, weight});
    }

    public void print() {
        for (GraphNode g : nodes) {
            System.out.println("node " + g.label + ":");
            for (Object[] gg : g.edges) {
                GraphNode node = (GraphNode) gg[0];
                int weight = (int) gg[1];
                System.out.println(" - node " + node.label + ": "+ weight);
            }
        }
    }

    public int[] getDistances(int start) {
        GraphNode startNode = getNodeFromLabel(start);
        int[] distance = new int[nodes.size()];
        boolean[] visited = new boolean[nodes.size()];
        Arrays.fill(distance, -1);
        Arrays.fill(visited, false);
        distance[start-1] = 0;
        visited[start-1] = true;

        ArrayList<Object[]> toVisit = new ArrayList<>();
        for (Object[] n : startNode.edges) {
            GraphNode node = (GraphNode) n[0];
            int weight = (int) n[1];
            toVisit.add(new Object[]{weight, node});
        }

        while (toVisit.size() > 0) {
            Object next = null;
            int nextDistance = Integer.MAX_VALUE;
            GraphNode nextNode = null;
            for (Object[] tv : toVisit) {
                if ((int) tv[0] < nextDistance) {
                    nextNode = (GraphNode) tv[1];
                    nextDistance = (int) tv[0];
                    next = tv;
                }
            }
            toVisit.remove(next);
            if (visited[nextNode.label-1]) continue;

            distance[nextNode.label-1] = nextDistance;
            visited[nextNode.label-1] = true;

            for (Object[] n : nextNode.edges) {
                GraphNode node = (GraphNode) n[0];
                int weight = (int) n[1];
                toVisit.add(new Object[]{nextDistance + weight, node});
            }

        }

        return distance;
    }
}
