package sfg;

import java.util.HashMap;

public class ForwardPaths {
    private HashMap<String, Float> paths;
    private float[][] g;
    int source, dist;

    public ForwardPaths(float[][] g, int s, int d) {
      paths = new HashMap<String, Float>();
       this.g = g;
       this.source = s - 1;
       this.dist = d - 1;
       calcPaths(source, new boolean[g.length], 1,Integer.toString(source));
    }

    private void calcPaths(int s,boolean visited[], float gain, String p) {
        if(s == dist) {
            paths.put(p, gain);
            return;
        }
        visited[s] = true;
        for(int i = s; i < g.length; i++) {
            if(g[s][i] != 0 && !visited[i])
                calcPaths(i, visited, gain * g[s][i], p.concat(Integer.toString(i)));
        }
        visited[s] = false;
    }

    public HashMap<String, Float> getPaths() {
        return paths;
    }

    public int nomOfPaths() {
        return paths.size();
    }
}
