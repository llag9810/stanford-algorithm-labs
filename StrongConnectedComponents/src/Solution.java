import jdk.internal.util.xml.impl.Pair;
import sun.awt.image.ImageWatched;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by yifan on 4/6/17.
 */
public class Solution {

    public static HashMap<Integer, Integer> leader = new HashMap<>();
    public static HashMap<Integer, Integer> f = new HashMap<>();
    static Stack<Integer> stack2 = new Stack<>();
    static int s = 0;
    static int t = 0;

    public static void dfs(Map<Integer, ArrayList<Integer>> graph, int startNode, Map<Integer, Boolean> isExplored) {
        Stack<Integer> stack = new Stack<>();

        int count;

        stack.push(startNode);
        //stack2.push(startNode);
        if (!isExplored.containsKey(startNode)) {
            isExplored.put(startNode, true);
            List<Integer> list;

            int node;
            while (!stack.empty()) {
                node = stack.pop();
                // isExplored.put(node, true);
                count = 0;

                list = graph.get(node);

                if (list != null) {
                    for (int i : list) {
                        if (!isExplored.containsKey(i)) {
                            isExplored.put(i, true);
                            stack.push(i);
                            stack2.push(i);
                            count++;
                        }
                    }
                }
            }

            ///if (count == 0) {
              //  f.put(++t, node);
                while (!stack2.empty()) {
                    f.put(++t, stack2.pop());
                }
           // }
        }
        f.put(++t, startNode);
        //System.out.println("size of stack2 is: " + stack2.size());
    }

    public static void dfs2(Map<Integer, ArrayList<Integer>> graph, int startNode, Map<Integer, Boolean> isExplored) {
        Stack<Integer> stack = new Stack<>();
        //Stack<Integer> stack2 = new Stack<>();
        stack.push(startNode);

        isExplored.put(startNode, true);
        List<Integer> list;

        int node;
        while (!stack.empty()) {
            node = stack.pop();
            isExplored.put(node, true);
            leader.put(node, s);

            list = graph.get(node);

            if (list != null) {
                for (int i : list) {
                    if (!isExplored.containsKey(i)) {
                        stack.push(i);
                        // stack2.push(i);
                    }
                }
            }
        }
        //leader.put(startNode, s);

 /*       while (stack2.size() > 0) {
            int t = 0;
            f.put(++t, stack2.pop());
        }*/
    }

    public static void main(String[] args) {

        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> revGraph = new HashMap<>();
        FileInputStream is = null;
       /* try {
            is = new FileInputStream("/home/yifan/IdeaProjects/untitled4/src/SCC.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        Scanner lineScanner = new Scanner(System.in);
        Map<Integer, Boolean> isExplored = new HashMap<>();


        while (lineScanner.hasNext()) {

            String line = lineScanner.nextLine();
            Scanner intScanner = new Scanner(line);

            if (intScanner.hasNextInt()) {
                int v1 = intScanner.nextInt();
                int v2 = intScanner.nextInt();
                ArrayList<Integer> list;

                if (!graph.containsKey(v1)) {
                    list = new ArrayList<>();
                    list.add(v2);
                    graph.put(v1, list);
                } else {
                    list = graph.get(v1);
                    list.add(v2);
                }

                if (!revGraph.containsKey(v2)) {
                    list = new ArrayList<>();
                    list.add(v1);
                    revGraph.put(v2, list);
                } else {
                    list = revGraph.get(v2);
                    list.add(v1);
                }
            }

        }

        lineScanner.close();

        int size = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : revGraph.entrySet()) {
            if (entry.getKey() > size) {
                size = entry.getKey();
            }
        }

        for (int i = 1; i < size; i++) {
            if (!revGraph.containsKey(i)) {
                revGraph.put(i, new ArrayList<>());
            }
        }


        int t = 0;
        for (int i = size; i > 0; i--) {
            if (!isExplored.containsKey(i)) {
                //s = i;
                dfs(revGraph, i, isExplored);

            }

            /*while (stack2.size() > 0) {
                f.put(++t, stack2.pop());
            }*/
        }

        size = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : graph.entrySet()) {
            if (entry.getKey() > size) {
                size = entry.getKey();
            }
        }

        for (int i = 1; i < size; i++) {
            if (!graph.containsKey(i)) {
                graph.put(i, new ArrayList<>());
            }
        }

        size = graph.size();
        isExplored = new HashMap<>();

        Iterator<Map.Entry<Integer, Integer>> testit = f.entrySet().iterator();
        while (testit.hasNext()) {
            Map.Entry<Integer, Integer> a = testit.next();
            System.out.println(a.getKey() + "  " + a.getValue());
        }

        for (int i = size; i > 0; i--) {
            if (!isExplored.containsKey(f.get(i))) {
                s = f.get(i);
                dfs2(graph, f.get(i), isExplored);
            }

        }

        HashMap<Integer, Integer> count = new HashMap<>();
        Iterator<Map.Entry<Integer, Integer>> iterator = leader.entrySet().iterator();
        while (iterator.hasNext()) {

            Map.Entry<Integer, Integer> a = iterator.next();
            if (!count.containsKey(a.getValue())) {
                count.put(a.getValue(), 1);
            } else {
                count.put(a.getValue(), count.get(a.getValue()) + 1);
            }
        }

        Iterator<Map.Entry<Integer, Integer>> aa = count.entrySet().iterator();
        ArrayList<Integer> list = new ArrayList<>();
        while (aa.hasNext()) {
            Map.Entry<Integer, Integer> bb = aa.next();
            // System.out.println(bb.getValue());
            list.add(bb.getValue());
        }

        Collections.sort(list);
        for (int i : list) {

            System.out.println(i);
        }
    }

}
