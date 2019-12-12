import java.io.*;
import java.util.*;

public class ProblemSolution {

    // Complete the shortestReach function below.
    static int[] shortestReach(int n, int[][] edges, int s) {
        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        distance[s-1] = 0;
        visited[s-1] = true;

        ArrayList<int[]> subEdges = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] == s || edges[i][1] == s) {
                subEdges.add(edges[i]);
            }
        }

        while (subEdges.size() > 0){
            int[] nextEdge = null;
            int minimumDistance = Integer.MAX_VALUE;

            for (int[] edge : subEdges) {
                int weight = edge[2];

                if (weight < minimumDistance) {
                    minimumDistance = weight;
                    nextEdge = edge;
                }
            }

            subEdges.remove(nextEdge);

            int nextNode;

            if (visited[nextEdge[0]-1] && visited[nextEdge[1]-1]) continue;
            if (!visited[nextEdge[0]-1]) {
                distance[nextEdge[0]-1] = minimumDistance;
                visited[nextEdge[0]-1] = true;
                nextNode = nextEdge[0];
            }else {
                distance[nextEdge[1] - 1] = minimumDistance;
                visited[nextEdge[1] - 1] = true;
                nextNode = nextEdge[1];
            }

            for (int[] edge : edges) {
                if (edge[0] == nextNode) {
                    if (!visited[edge[1] - 1]) subEdges.add(new int[]{edge[0], edge[1], minimumDistance + edge[2]});
                } else if (edge[1] == nextNode) {
                    if (!visited[edge[0] - 1]) subEdges.add(new int[]{edge[0], edge[1], minimumDistance + edge[2]});
                }
            }
        }
        return distance;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][3];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 3; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = shortestReach(n, edges, s);

            for (int i = 0; i < result.length; i++) {
                if (result[i] == 0) continue;
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
