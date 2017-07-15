import java.util.*;

/**
 * Created by yifan on 4/16/17.
 */
class Pair implements Comparable<Pair> {
    public int node;
    public int length;

    public Pair(int node, int length) {
        this.node = node;
        this.length = length;
    }

    @Override
    public int compareTo(Pair o) {
        return this.length - o.length;
    }

    public boolean equals(Pair obj) {
        return this.length == obj.length;
    }
}

public class Solution {

    public static void main(String[] args) {

        Map<Integer, List<Pair>> graph = new HashMap<>();
        Scanner lineScanner = new Scanner(System.in);
        Scanner nodeScanner;
        while (lineScanner.hasNext()) {
            String line = lineScanner.nextLine();
            nodeScanner = new Scanner(line);

            if (nodeScanner.hasNextInt()) {
                int vertex = nodeScanner.nextInt();
                List<Pair> list = new LinkedList<>();
                graph.put(vertex, list);
                while (nodeScanner.hasNext()) {
                    String pairStr = nodeScanner.next();
                    int node = Integer.parseInt(pairStr.substring(0, pairStr.indexOf(",")));
                    int length = Integer.parseInt(pairStr.substring(pairStr.indexOf(",") + 1, pairStr.length()));
                    list.add(new Pair(node, length));
                }
            }

        }

        Map<Integer, Integer> keys = new HashMap<>();
        keys.put(1, 0);

        int count = 0;
        while (true) {
            int len = Integer.MAX_VALUE;
            int temp = 0;
            for (Map.Entry<Integer, Integer> e : keys.entrySet()) {
                int v = e.getKey();
                List<Pair> list = graph.get(v);
                for (Pair p : list) {
                    if (!keys.containsKey(p.node)) {
                        if (e.getValue() + p.length < len) {
                            len = e.getValue() + p.length;
                            temp = p.node;
                        }
                        count++;
                    }
                }
            }

            if (temp != 0) {
                keys.put(temp, len);
            }

            if (count == 0) {
                break;
            } else {
                count = 0;
            }
        }

        List<Integer> output = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : keys.entrySet()) {
            System.out.println(e.getKey() + "  " + e.getValue());
        }


    }
}
