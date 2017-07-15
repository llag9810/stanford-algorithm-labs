/**
 * Created by yifan on 7/14/17.
 */
public class Edge implements Comparable<Edge>{
    private final int u;    //the first vertex
    private final int v;    //the other vertex
    private final int cost; //edge cost

    public Edge(int u, int v, int cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }

    public int cost(){
        return cost;
    }
    /**
     * returns one vertex of the edge
     */

    public int first() {
        return u;
    }

    public int second() {
        return v;
    }

    @Override
    public int compareTo(Edge arg0) {
        return this.cost - arg0.cost();

    }

}