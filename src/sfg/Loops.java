package sfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Loops {
	private int nodes;
	private float edges[][];
	private HashMap<String, Float> m;
	private Queue<Pair> q;
	private Pair[] loops;
	private EdgesConverter conv;
	private ArrayList<Pair> adjList[];
	private ArrayList<String> checkLoops;

	public Loops(Graph graph) {
		nodes = graph.getV();
		edges = graph.getEdges();
		m = new HashMap<>();
		conv = new EdgesConverter(nodes, edges);
		adjList = conv.getAdjList();
		checkLoops = new ArrayList<>();
	}

	public HashMap<String, Float> getMap() {
		return m;
	}

	public void makeLoops() {
		for (int i = 0; i < nodes; i++) {
			BFS(i);
		}
		parseMap();
	}

	public Pair[] getLoops() {
		return loops;
	}

	private void BFS(int x) {
		q = new LinkedList<>();
		Pair p = new Pair();
		p.updateS(x);
		// boolean vis[] = new boolean[nodes];
		q.add(p);
		while (!q.isEmpty()) {
			String path = q.peek().getS();
			float gain = q.peek().getGain();
			q.poll();
			int u = Integer.parseInt(path.charAt(path.length() - 1) + "");
			for (int i = 0; i < adjList[u].size(); i++) {
				int v = (int) Float.parseFloat(adjList[u].get(i).getS());
				String newPath = path + v;
				float newGain = gain * adjList[u].get(i).getGain();
				if (v == Integer.parseInt(path.charAt(0) + "")) {
					if (checkLoops.size() == 0 || !exist(newPath)) {
						m.put(newPath, newGain);
						checkLoops.add(newPath);
					}
				} else if (!path.contains(v + "")) {
					p = new Pair(newPath, newGain);
					q.add(p);
				}
			}
		}
	}

	private void parseMap() {
		String line = m.toString();
		line = line.replace('{', ' ');
		line = line.replace('}', ' ');
		line = line.trim();
		if (line.equals("")) {
			loops = new Pair[0];
			return;
		}
		line = line.replace(", ", ",");
		String temp[] = line.split(",");
		Scanner in;
		ArrayList<Pair> a = new ArrayList<>();
		for (int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].replace("=", " ");
			in = new Scanner(temp[i]);
			Pair p = new Pair(in.next(), in.nextFloat());
			a.add(p);
		}
		loops = new Pair[a.size()];
		for (int i = 0; i < a.size(); i++) {
			loops[i] = a.get(i);
		}
	}

	private boolean exist(String loop) {
		for (int i = 0; i < checkLoops.size(); i++) {
			if (isSame(loop, checkLoops.get(i))) {
				return true;
			}
		}
		return false;
	}

	private boolean isSame(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		boolean letters1[] = new boolean[nodes];
		boolean letters2[] = new boolean[nodes];
		for (int i = 0; i < s1.length(); i++) {
			letters1[s1.charAt(i) - '0'] = true;
			letters2[s2.charAt(i) - '0'] = true;
		}
		for (int i = 0; i < letters1.length; i++) {
			if (letters1[i] != letters2[i]) {
				return false;
			}
		}
		return true;
	}
}
