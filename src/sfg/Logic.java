package sfg;

import java.util.ArrayList;
import java.util.HashMap;

public class Logic {

	private MainModule mainM;
	private Graph graph;
	private ForwardPaths fp;
	private Loops l;
	private Pair loops[];
	private NonTouchinLoops n;
	private Delta d;
	private ArrayList<ArrayList<String>> a;
	private float TF = 0;

	public Logic(MainModule mainM) {
		this.mainM = mainM;
	}

	public void Graph(int n) {
		graph = new Graph(n);
	}

	public void add(int f, int t, float g) {
		graph.addEdge(f, t, g);
	}

	public void remove(int f, int t) {
		graph.removeEdge(f, t);
	}

	public void solve() {
		calcForwardPaths();
		calcLoops();
		calcNonTouching();
		calcDelta();
		calcTF();
		mainM.showOp();
	}

	private void calcTF() {
		int i = 1;
		for (String x : fp.getPaths().keySet())
			TF += (getForwardPaths().get(x)) * d.getDelta(i++);
		TF /= d.getDelta(0);
	}

	private void calcDelta() {
		d = new Delta(fp.getPaths(), loops, l.getMap(), a);
	}

	private void calcNonTouching() {
		n = new NonTouchinLoops(loops);
		a = n.getNonTouchLoops();
		for(int i = 0; i < a.size(); i++){
		    for(String crnt : a.get(i))
		        System.out.print(crnt + " ");
		    System.out.println();
		}
	}

	private void calcLoops() {
		l = new Loops(graph);
		l.makeLoops();
		loops = l.getLoops();
	}

	private void calcForwardPaths() {
		fp = new ForwardPaths(graph.getEdges(), 1, graph.getV());
	}

	public HashMap<String, Float> getForwardPaths() {
		return fp.getPaths();
	}

	public float getTF() {
		return TF;
	}

	public HashMap<String, Float> getLoops() {
		return l.getMap();
	}

	public ArrayList<ArrayList<String>> getNonTouching() {
		return a;
	}

	public ArrayList<Float> getDelta() {
		return d.getList();
	}
}
