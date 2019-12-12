package lib;

import java.util.ArrayList;

public class GraphNode {
    public int label;
    public ArrayList<Object[]> edges;

    public GraphNode(int label) {
        this.label = label;
        this.edges = new ArrayList<>();
    }

}
