package sfg;

import java.util.ArrayList;
import java.util.HashMap;

public class Delta {
    private ArrayList<ArrayList<String>> untouched;
    private HashMap<String, Float> map;
    private Pair[] loops;
    private HashMap<String, Float> paths;
    public ArrayList<Float> delta;

    public Delta(HashMap<String, Float> p, Pair[] l, HashMap<String, Float> map, ArrayList<ArrayList<String>> un) {
        loops = l;
        paths = p;
        untouched = un;
        this.map = map;
        delta = new ArrayList<Float>();
        calcDelta(loops, untouched);
        for (String k : paths.keySet())
            pathDelta(k);
    }

    private void calcDelta(Pair[] loops, ArrayList<ArrayList<String>> untouched) {
        float d = 1;
        for (int i = 0; i < loops.length; i++) {
            d -= loops[i].getGain();
        }
        HashMap<Integer, Float> terms = new HashMap<Integer, Float>();
        for (int i = 0; i < untouched.size(); i++) {
            float product = 1;
            for (String crnt : untouched.get(i)) {
                product *= map.get(crnt);
            }
            if (!terms.containsKey(untouched.get(i).size()))
                terms.put(untouched.get(i).size(), (float) 0);
            terms.put(untouched.get(i).size(), terms.get(untouched.get(i).size()) + product);
        }
        int sign = 1;
        for (int i = 0; i < terms.size(); i++) {
            d += (sign * terms.get(i + 2));
            sign *= -1;
        }
        delta.add(d);
    }

    private void pathDelta(String path) {
        ArrayList<Pair> tmp = new ArrayList<Pair>();
        for (int i = 0; i < loops.length; i++) {
            if (!touched(path, loops[i].getS()))
                tmp.add(loops[i]);
        }
        Pair[] notTouching = new Pair[tmp.size()];
        notTouching = tmp.toArray(notTouching);
        NonTouchinLoops nt = new NonTouchinLoops(notTouching);
        calcDelta(notTouching, nt.getNonTouchLoops());
    }

    private boolean touched(String p, String l) {
        for (int i = 0; i < p.length(); i++)
            for (int j = 0; j < l.length(); j++)
                if (p.charAt(i) == l.charAt(j))
                    return true;

        return false;
    }

    public float getDelta(int i) {
        return delta.get(i);
    }

    public ArrayList<Float> getList() {
        return delta;
    }
}
