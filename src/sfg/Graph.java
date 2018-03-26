package sfg;

public class Graph {
    private float[][] graph;
    private int v;

    public Graph(int n) {
        v = n;
        graph = new float[n][n];
    }

    public void addEdge(int u, int v, float g){
        graph[u][v] = g;
    }

    public void removeEdge(int u, int v){
        graph[u][v] = 0;
    }

    public float[][] getEdges(){
        return graph;
    }
    public int getV(){
        return v;
    }
}