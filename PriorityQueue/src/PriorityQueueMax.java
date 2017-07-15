import java.util.EmptyStackException;

/**
 * Created by yifan on 4/22/17.
 */
public class PriorityQueueMax<T extends Comparable<T>> {

    private Object[] objs;
    private int size = 0;
    private int capacity;

    public PriorityQueueMax(int capacity) {
        objs = new Object[capacity];
        this.capacity = capacity;
    }

    public PriorityQueueMax() {
        this(200000000);
    }

    public int size() {
        return size;
    }

    public boolean insert(T obj) {
        if (size == capacity) {
            return false;
        }

        objs[size] = obj;

        int pos = size;
        while (((T)objs[pos]).compareTo((T)objs[(pos - 1) / 2]) > 0 && pos > 0) {
            Object temp = objs[pos];
            objs[pos] = objs[(pos - 1) / 2];
            objs[(pos - 1) / 2] = temp;
            pos = (pos - 1) / 2;
        }

        size++;
        return true;
    }

    public T extract() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        Object val = objs[0];
        objs[0] = objs[--size];
        objs[size] = null;
        int pos = 0;

        while (objs[2 * pos + 1] != null || objs[2 * pos + 2] != null) {
            if (objs[2 * pos + 1] != null && objs[2 * pos + 2] != null) {
                boolean flag = ((T) objs[2 * pos + 1]).compareTo((T) objs[2 * pos + 2]) < 0;

                if (flag && ((T)objs[pos]).compareTo((T)objs[2 * pos + 1]) < 0) {
                    Object temp = objs[2 * pos + 2];
                    objs[2 * pos + 2] = objs[pos];
                    objs[pos] = temp;
                    pos = 2 * pos + 2;
                } else if (!flag && ((T)objs[pos]).compareTo((T)objs[2 * pos + 1]) < 0) {
                    Object temp = objs[2 * pos + 1];
                    objs[2 * pos + 1] = objs[pos];
                    objs[pos] = temp;
                    pos = 2 * pos + 1;
                } else {
                    break;
                }

            } else if (objs[2 * pos + 1] != null) {
                if (((T)objs[pos]).compareTo((T)objs[2 * pos + 1]) < 0) {
                    Object temp = objs[2 * pos + 1];
                    objs[2 * pos + 1] = objs[pos];
                    objs[pos] = temp;
                    pos = 2 * pos + 1;
                } else {
                    break;
                }

            } else {
                if (((T)objs[pos]).compareTo((T)objs[2 * pos + 2]) < 0) {
                    Object temp = objs[2 * pos + 2];
                    objs[2 * pos + 2] = objs[pos];
                    objs[pos] = temp;
                    pos = 2 * pos + 2;
                } else {
                    break;
                }
            }
        }

        return (T)val;
    }

    public T top() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        return (T)objs[0];
    }

    public boolean empty() {
        return size == 0;
    }

}
