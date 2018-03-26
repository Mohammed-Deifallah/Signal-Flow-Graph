package sfg;

import java.util.ArrayList;

public class Trials {

	public static void main(String[] args) {

		// MainModule.getInstance().startGUI();
		Graph x = new Graph(10);
		x.addEdge(0, 1, 1);
		x.addEdge(1, 2, 1);
		x.addEdge(2, 3, 1);
		x.addEdge(3, 4, 1);
		x.addEdge(4, 5, 1);
		x.addEdge(5, 6, 1);
		x.addEdge(6, 7, 1);
		x.addEdge(7, 8, 1);
		x.addEdge(8, 9, 1);
		x.addEdge(1, 0, -1);
		x.addEdge(4, 3, -1);
		x.addEdge(6, 5, -1);
		x.addEdge(8, 7, -1);
		// ForwardPaths fp = new ForwardPaths(x.getEdges(), 1, 10);
		Loops l = new Loops(x);
		l.makeLoops();
		NonTouchinLoops n = new NonTouchinLoops(l.getLoops());
		ArrayList<ArrayList<String>> a = n.getNonTouchLoops();
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i).toString());
		}

	}
}
