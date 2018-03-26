package sfg;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField nodes, f, t, g;
    private JButton btnSolve, btnEnter, btnAdd, btnRemove;
    private MainModule mainM;
    private JTextArea path, loops, delta, non;
    private JLabel lblNodesNumber, lblFrom, lblTo, lblGain, lblLoops, lblForwardPaths, label, ans, lblTransferFunction, lblDelta_1;
    private Graph graph;
    private Viewer viewer;
    private View view;
    private int nodesNum;

    /**
     * Create the application.
     *
     * @param mainM
     */
    public GUI(MainModule mainM) {
        getContentPane().setFont(new Font("Hobo Std", Font.PLAIN, 16));
        getContentPane().setForeground(new Color(255, 255, 51));
        getContentPane().setBackground(new Color(0, 0, 0));
        this.mainM = mainM;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setTitle("Signal Flow Graph");
        setResizable(false);
        setBounds(10, 10, 1146, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph = new SingleGraph("I can see dead pixels");
        // graph.addAttribute("ui.stylesheet", "node { fill-color: red; }");
        graph.addAttribute("ui.stylesheet", "edge { fill-color: green; }");
        viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        view = viewer.addDefaultView(false);
        viewer.enableAutoLayout();
        ((Component) view).setBounds(57, 200, 700, 450);
        ((Component) view).setBackground(Color.white);
        getContentPane().add((Component) view);

        path = new JTextArea("");
        path.setBackground(new Color(255, 255, 255));
        path.setBounds(811, 40, 279, 114);
        getContentPane().add(path);
        path.setRows(10);
        path.setColumns(55);

        loops = new JTextArea();
        loops.setBackground(new Color(255, 255, 255));
        loops.setBounds(811, 184, 279, 114);
        getContentPane().add(loops);
        loops.setRows(10);
        loops.setColumns(55);

        non = new JTextArea();
        non.setBackground(new Color(255, 255, 255));
        non.setBounds(811, 338, 279, 114);
        getContentPane().add(non);
        non.setRows(10);
        non.setColumns(55);

        lblDelta_1 = new JLabel("Delta");
        lblDelta_1.setForeground(new Color(255, 255, 51));
        lblDelta_1.setBounds(935, 463, 51, 18);
        getContentPane().add(lblDelta_1);
        lblDelta_1.setFont(new Font("Hobo Std", Font.BOLD, 14));

        lblForwardPaths = new JLabel("Forward Paths");
        lblForwardPaths.setForeground(new Color(255, 255, 51));
        lblForwardPaths.setBounds(891, 10, 114, 18);
        getContentPane().add(lblForwardPaths);
        lblForwardPaths.setFont(new Font("Hobo Std", Font.BOLD, 14));

        label = new JLabel("Non Touching Loops");
        label.setForeground(new Color(255, 255, 51));
        label.setBounds(879, 309, 162, 18);
        getContentPane().add(label);
        label.setFont(new Font("Hobo Std", Font.BOLD, 14));

        lblLoops = new JLabel("Loops");
        lblLoops.setForeground(new Color(255, 255, 51));
        lblLoops.setBounds(935, 157, 70, 18);
        getContentPane().add(lblLoops);
        lblLoops.setFont(new Font("Hobo Std", Font.BOLD, 14));

        delta = new JTextArea();
        delta.setBackground(new Color(255, 255, 255));
        delta.setBounds(811, 492, 279, 114);
        getContentPane().add(delta);
        delta.setRows(10);
        delta.setColumns(55);

        ans = new JLabel("0.0");
        ans.setBounds(998, 627, 92, 26);
        getContentPane().add(ans);
        ans.setForeground(new Color(255, 255, 51));
        ans.setFont(new Font("Hobo Std", Font.PLAIN, 16));

        lblTransferFunction = new JLabel("Transfer Function");
        lblTransferFunction.setBounds(811, 627, 259, 26);
        getContentPane().add(lblTransferFunction);
        lblTransferFunction.setForeground(new Color(255, 255, 51));
        lblTransferFunction.setFont(new Font("Hobo Std", Font.PLAIN, 16));

        g = new JTextField();
        g.setBounds(309, 76, 86, 20);
        getContentPane().add(g);
        g.setText("Gain");
        g.setForeground(Color.gray);
        g.setColumns(10);
        g.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent arg0) {
                if (g.getText().trim().equals("")) {
                    g.setText("Gain");
                    g.setForeground(Color.gray);
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                if (g.getText().trim().equals("")) {
                    g.setText("Gain");
                    g.setForeground(Color.gray);
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (g.getText().equals("Gain")) {
                    g.setText("");
                }
                g.setForeground(Color.darkGray);
            }
        });

        t = new JTextField();
        t.setBounds(213, 76, 86, 20);
        t.setText("To");
        t.setForeground(Color.gray);
        getContentPane().add(t);
        t.setColumns(10);
        t.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent arg0) {
                if (t.getText().trim().equals("")) {
                    t.setText("To");
                    t.setForeground(Color.gray);
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                if (t.getText().trim().equals("")) {
                    t.setText("To");
                    t.setForeground(Color.gray);
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (t.getText().equals("To")) {
                    t.setText("");
                }
                t.setForeground(Color.darkGray);
            }
        });

        f = new JTextField();
        f.setBounds(117, 76, 86, 20);
        f.setText("From");
        f.setForeground(Color.gray);
        getContentPane().add(f);
        f.setColumns(10);
        f.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent arg0) {
                if (f.getText().trim().equals("")) {
                    f.setText("From");
                    f.setForeground(Color.gray);
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                if (f.getText().trim().equals("")) {
                    f.setText("From");
                    f.setForeground(Color.gray);
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (f.getText().equals("From")) {
                    f.setText("");
                }
                f.setForeground(Color.darkGray);
            }
        });

        nodes = new JTextField();
        nodes.setBounds(309, 11, 86, 20);
        nodes.setForeground(Color.gray);
        nodes.setText("Nodes");
        getContentPane().add(nodes);
        nodes.setColumns(10);
        nodes.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent arg0) {
                if (nodes.getText().trim().equals("")) {
                    nodes.setText("Nodes");
                    nodes.setForeground(Color.gray);
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                if (nodes.getText().trim().equals("")) {
                    nodes.setText("Nodes");
                    nodes.setForeground(Color.gray);
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (nodes.getText().equals("Nodes")) {
                    nodes.setText("");
                }
                nodes.setForeground(Color.darkGray);
            }
        });

        lblGain = new JLabel("          Gain        ");
        lblGain.setForeground(new Color(255, 255, 51));
        lblGain.setBounds(296, 47, 128, 18);
        getContentPane().add(lblGain);
        lblGain.setFont(new Font("Hobo Std", Font.BOLD, 14));

        lblFrom = new JLabel("         From        ");
        lblFrom.setForeground(new Color(255, 255, 51));
        lblFrom.setBounds(110, 47, 138, 18);
        getContentPane().add(lblFrom);
        lblFrom.setFont(new Font("Hobo Std", Font.BOLD, 14));

        lblTo = new JLabel("           To          ");
        lblTo.setForeground(new Color(255, 255, 51));
        lblTo.setBounds(204, 47, 128, 18);
        getContentPane().add(lblTo);
        lblTo.setFont(new Font("Hobo Std", Font.BOLD, 14));

        lblNodesNumber = new JLabel("Number of Nodes:");
        lblNodesNumber.setForeground(new Color(255, 255, 51));
        lblNodesNumber.setBounds(117, 11, 153, 18);
        getContentPane().add(lblNodesNumber);
        lblNodesNumber.setFont(new Font("Hobo Std", Font.BOLD, 16));

        btnEnter = new JButton("Set Nodes");
        btnEnter.setBackground(new Color(255, 255, 51));
        btnEnter.setBounds(447, 11, 129, 27);
        getContentPane().add(btnEnter);
        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = nodes.getText().trim();
                try {
                    nodesNum = Integer.parseInt(text);
                } catch (Exception p) {
                    JOptionPane.showMessageDialog(null, "Enter an integer !!");
                    clear();
                    return;
                }
                clearGraph();
                createGraph();
                mainM.setNodeNumber(Integer.parseInt(text));
                btnAdd.setEnabled(true);
                btnSolve.setEnabled(true);
            }
        });
        btnEnter.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

        btnAdd = new JButton("      ADD      ");
        btnAdd.setBackground(new Color(255, 255, 51));
        btnAdd.setForeground(new Color(0, 0, 0));
        btnAdd.setBounds(448, 44, 128, 27);
        getContentPane().add(btnAdd);
        btnAdd.setEnabled(false);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t.getText().trim().equals("") || f.getText().trim().equals("") || g.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter complete data");
                    clear();
                    return;
                }
                int from, to = 0;
                float gain = 0;
                try {
                    from = Integer.valueOf(f.getText().trim());
                    to = Integer.valueOf(t.getText().trim());
                } catch (Exception p) {
                    JOptionPane.showMessageDialog(null, "Enter integers !!");
                    clear();
                    return;
                }
                if (from < 0 || from >= nodesNum || to < 0 || to >= nodesNum) {
                    JOptionPane.showMessageDialog(null, "Out-of-range numbers !!");
                    clear();
                    return;
                }
                String gn = g.getText().trim();
                try {
                    gain = Float.valueOf(gn);
                } catch (Exception p) {
                    JOptionPane.showMessageDialog(null, "Invalid gain !!");
                    clear();
                    return;
                }
                mainM.addEdge(from, to, gain);
                btnRemove.setEnabled(true);
                graph.addAttribute("ui.stylesheet", "edge { fill-color: green; }");
                clear();
                String s = "" + from;
                s += to;
                Edge ed = graph.addEdge(s, "" + from, "" + to, true);
                ed.setAttribute("ui.label", gn);

            }
        });
        btnAdd.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

        btnRemove = new JButton("REMOVE");
        btnRemove.setBackground(new Color(255, 255, 51));
        btnRemove.setForeground(new Color(0, 0, 0));
        btnRemove.setBounds(448, 76, 128, 27);
        getContentPane().add(btnRemove);
        btnRemove.setEnabled(false);
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t.getText().trim().equals("") || f.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter complete data !!");
                    clear();
                    return;
                }
                int from, to = 0;
                try {
                    from = Integer.valueOf(f.getText());
                    to = Integer.valueOf(t.getText());
                } catch (Exception p) {
                    JOptionPane.showMessageDialog(null, "Enter integers !!");
                    clear();
                    return;
                }
                if (from < 0 || from >= nodesNum || to < 0 || to >= nodesNum) {
                    JOptionPane.showMessageDialog(null, "Out-of-range numbers !!");
                    clear();
                    return;
                }
                mainM.removeEdge(from, to);
                clear();
                String s = "" + from;
                s += to;
                graph.removeEdge(s);

            }
        });
        btnRemove.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnSolve = new JButton("       SOLVE      ");
        btnSolve.setBackground(new Color(255, 255, 51));
        btnSolve.setForeground(new Color(0, 0, 0));
        btnSolve.setBounds(447, 113, 130, 27);
        getContentPane().add(btnSolve);
        btnSolve.setEnabled(false);
        btnSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                mainM.solve();
                clear();
            }
        });
        btnSolve.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    }

    public void showPath(HashMap<String, Float> hm) {
        for (String crnt : hm.keySet())
            path.append(crnt + "\t" + hm.get(crnt).toString() + "\n");
    }

    public void showLoops(HashMap<String, Float> hm) {
        for (String crnt : hm.keySet())
            loops.append(crnt + "\t" + hm.get(crnt).toString() + "\n");
    }

    public void showNontouching(ArrayList<ArrayList<String>> arr) {
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).size(); j++)
                non.append(arr.get(i).get(j) + "\t");
            non.append("\n");
        }
    }

    public void showDelta(ArrayList<Float> arr) {
        delta.append("Delta:\t" + arr.get(0).toString() + "\n");
        for (int i = 1; i < arr.size(); i++)
            delta.append("Delta " + i + "\t" + arr.get(i).toString() + "\n");
    }

    public void showTF(Float x) {
        ans.setText(x.toString());
    }

    private void clear() {
        f.setText("From");
        f.setForeground(Color.gray);
        t.setText("To");
        t.setForeground(Color.gray);
        g.setText("Gain");
        g.setForeground(Color.gray);
    }

    private void clearGraph() {
        for (Node n : graph)
            graph.removeNode(n);
        graph.clear();
    }

    private void createGraph() {
        graph.addAttribute("ui.stylesheet", "node { fill-color: red; size:20px, 20px; shape: circle;}");
        graph.addAttribute("ui.stylesheet", "edge { fill-color: green; }");
        for (int i = 0; i < nodesNum; i++) {
            Node n = graph.addNode("" + i);
            n.setAttribute("ui.label", "" + i);
        }
        Node extra = graph.addNode("" + (nodesNum - 1) + "'");
        extra.setAttribute("ui.label", "" + (nodesNum - 1) + "'");
        Edge e = graph.addEdge("", "" + (nodesNum - 1), (nodesNum - 1) + "'", true);
        e.setAttribute("ui.label", "1");
    }

    public void clearText() {
        non.setText("");
        path.setText("");
        delta.setText("");
        loops.setText("");
        ans.setText("0.0");

    }
}
