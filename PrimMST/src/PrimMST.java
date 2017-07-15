import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by yifan on 6/22/17.
 */

class Node {
    int vertex;
    int length;

    Node(int vertex, int length) {
        this.vertex = vertex;
        this.length = length;
    }
}
public class PrimMST {



    public static void main(String[] args) {

        HashMap<Integer, List<Node>> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        FileInputStream fis = null;
        try {
           fis = new FileInputStream("/home/yifan/Desktop/edges.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner input = new Scanner(fis);
        int nodes = input.nextInt();
        int edges = input.nextInt();

        while (input.hasNextInt()) {
            int arg1 = input.nextInt();
            int arg2 = input.nextInt();
            int length = input.nextInt();

            List<Node> list = map.getOrDefault(arg1, new LinkedList<>());
            List<Node> list2 = map.getOrDefault(arg2, new LinkedList<>());
            list2.add(new Node(arg1, length));
            list.add(new Node(arg2, length));
            map.put(arg1, list);
            map.put(arg2, list2);
        }

        for (int i = 1; i <= nodes; i++) {
            if (!map.containsKey(i)) {
                map.put(i, new LinkedList<>());
            }
        }



        int start = 1;
        set.add(1);
        long cost = 0;
        while (set.size() < nodes) {
            Node minNode = new Node(0, Integer.MAX_VALUE);
            for (int s : set) {
                List<Node> list = map.get(s);
                for (Node n : list) {
                    if (n.length < minNode.length && !set.contains(n.vertex)) {
                        minNode = n;
                    }
                }
            }
            set.add(minNode.vertex);
            cost += minNode.length;
        }

        System.out.println(cost);
    }
}
