package sfg;

import java.util.ArrayList;
import java.util.HashMap;

public class MainModule {

	private static MainModule mainM = new MainModule();
	private GUI gui;
	private Logic logic;

	private MainModule() {
	}

	public void startGUI() {
		gui = new GUI(mainM);
		gui.setVisible(true);
	}

	public void setNodeNumber(int n) {
	    logic = new Logic(mainM);
	    gui.clearText();
		logic.Graph(n);
	}

	public static MainModule getInstance() {
		return mainM;
	}

	public void addEdge(int f, int t, float g) {
		logic.add(f, t, g);
	}

	public void solve() {
		logic.solve();
	}

	public void removeEdge(int f, int t) {
		logic.remove(f, t);

	}

	public void showOp() {
		gui.showPath(showPaths());
		gui.showLoops(showLoops());
		gui.showNontouching(showNontouching());
		gui.showDelta(showDelta());
		gui.showTF(showTF());
	}

	private Float showTF() {
        return logic.getTF();
    }

    private ArrayList<Float> showDelta() {
        return logic.getDelta();
    }

    private ArrayList<ArrayList<String>> showNontouching() {
       return logic.getNonTouching();
    }

    private HashMap<String, Float> showLoops() {
        return logic.getLoops();
    }

    private HashMap<String, Float> showPaths() {
		return logic.getForwardPaths();

	}
}
