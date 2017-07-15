import java.util.BitSet;

/**
 * Created by yifan on 7/14/17.
 */
public class UnionFind {

    private int[] id;
    private int[] rank;
    private int size;

    public UnionFind(int size) {
        id = new int[size];
        rank = new int[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    public int find(int x) {
        if (id[x] != x) {
            id[x] = find(id[x]);
        }
        return id[x];
    }

    public void union(int u, int v) {
        int findu = find(u);
        int findv = find(v);

        if (findu == findv) {
            return;
        }

        if (rank[findu] <= rank[findv]) {
            id[findu] = findv;
            if (rank[findu] == rank[findv]) {
                rank[findv]++;
            }
        } else {
            id[findv] = findu;
        }

        size--;
    }

    public int size() {
        return size;
    }
}

