import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by yifan on 7/14/17.
 */

public class Clustering {
    public static void main(String[] args) {
        ArrayList<Edge> list = new ArrayList<>();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream("/home/yifan/Downloads/clustering1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner input = new Scanner(fileInputStream);
        int size = input.nextInt();
        UnionFind uf = new UnionFind(size + 1);

        while (input.hasNextInt()) {
            list.add(new Edge(input.nextInt(), input.nextInt(), input.nextInt()));
        }
        Collections.sort(list);

        while (uf.size() > 5) {
            Edge e = list.get(0);
            if (uf.find(e.first()) != uf.find(e.second())) {
                uf.union(e.first(), e.second());
            }
            list.remove(0);
        }

        Iterator<Edge> it = list.iterator();
        while(it.hasNext()){
            Edge x = it.next();
            if(uf.find(x.first()) == uf.find(x.second())){
                it.remove();
            }
        }

        System.out.println(list.get(0).cost());
    }
}