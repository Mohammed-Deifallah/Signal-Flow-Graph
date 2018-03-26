package sfg;

import java.util.ArrayList;
import java.util.Collections;

public class NonTouchinLoops {
	private ArrayList<ArrayList<String>> nonTouch;
	private Pair[] loops;
	private ArrayList<String> temp;

	public NonTouchinLoops(Pair[] loops) {
		nonTouch = new ArrayList<>();
		this.loops = loops;
	}

	public ArrayList<ArrayList<String>> getNonTouchLoops() {
		makePairs();
		if (nonTouch.size() == 0) {
			return nonTouch;
		}
		int index = 0;
		boolean flag = true;
		int len;
		boolean take;
		while (flag) {
			flag = false;
			len = nonTouch.size();
			for (int i = 0; i < loops.length; i++) {
				// String s = loops[i].getS();
				for (int j = index; j < len; j++) {
					take = true;
					for (int k = 0; k < nonTouch.get(j).size() && take; k++) {
						if (areTouching(loops[i].getS(), nonTouch.get(j).get(k))) {
							take = false;
						}
					}
					if (!take) {
						continue;
					}
					temp = new ArrayList<>(nonTouch.get(j));
					temp.add(loops[i].getS());
					nonTouch.add(temp);
					// if (!flag) {
					// index = nonTouch.size() - 1;
					// }
					flag = true;
				}
			}
			index = nonTouch.size() - 1;
		}
		filter();
		return nonTouch;
	}

	private void makePairs() {
		for (int i = 0; i < loops.length - 1; i++) {
			for (int j = i + 1; j < loops.length; j++) {
				if (!areTouching(loops[i].getS(), loops[j].getS())) {
					temp = new ArrayList<>();
					temp.add(loops[i].getS());
					temp.add(loops[j].getS());
					nonTouch.add(temp);
				}
			}
		}
	}

	private boolean areTouching(String s1, String s2) {
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					return true;
				}
			}
		}
		return false;
	}

	private void filter() {
		ArrayList<ArrayList<String>> temp = new ArrayList<>();
		boolean found;
		temp.add(nonTouch.get(0));
		for (int i = 1; i < nonTouch.size(); i++) {
			found = false;
			for (int j = 0; j < temp.size() && !found; j++) {
				if (same(nonTouch.get(i), temp.get(j))) {
					found = true;
				}
			}
			if (!found) {
				temp.add(nonTouch.get(i));
			}
		}
		nonTouch = new ArrayList<>(temp);
	}

	private boolean same(ArrayList<String> a, ArrayList<String> b) {
		if (a.size() != b.size())
			return false;
		Collections.sort(a);
		Collections.sort(b);
		for (int i = 0; i < a.size(); i++) {
			if (!(a.get(i)).equals(b.get(i))) {
				return false;
			}
		}
		return true;
	}

}
