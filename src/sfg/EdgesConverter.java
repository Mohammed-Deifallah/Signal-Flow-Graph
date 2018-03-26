package sfg;

import java.util.ArrayList;

public class EdgesConverter {
    private ArrayList<Pair> arr[];
    private float[][] edges;

    @SuppressWarnings("unchecked")
    public EdgesConverter(int n, float edges[][]) {
        this.edges = edges;
        arr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayList<>();
        }
    }

    public ArrayList<Pair>[] getAdjList() {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                if (edges[i][j] != 0) {
                    arr[i].add(new Pair(j, edges[i][j]));
                }
            }
        }
        return arr;
    }
}
