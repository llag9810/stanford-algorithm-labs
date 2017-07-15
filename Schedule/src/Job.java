/**
 * Created by yifan on 6/22/17.
 */
public class Job implements Comparable<Job> {
    private int weight;
    private int length;

    public Job(int weight, int length) {

        this.weight = weight;
        this.length = length;
    }

    @Override
    public int compareTo(Job o) {
/*        if (this.weight - this.length != o.weight - o.length) {
            return (this.weight - this.length) - (o.weight - o.length);
        }

        return this.weight - o.weight;*/
        if ((double)this.weight / this.length - (double)o.getWeight() / o.getLength() > 0) {
            return 1;
        } else if ((double)this.weight / this.length - (double)o.getWeight() / o.getLength() == 0) {
            return this.weight - o.getWeight();
        } else {
            return -1;
        }
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (weight != job.weight) return false;
        return length == job.length;
    }

    @Override
    public int hashCode() {
        int result = weight;
        result = 31 * result + length;
        return result;
    }
}
