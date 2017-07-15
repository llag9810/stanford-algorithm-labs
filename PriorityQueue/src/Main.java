import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Created by yifan on 4/22/17.
 */
public class Main {

    public static void main(String[] args) {
        PriorityQueueMax<Integer> maxheap = new PriorityQueueMax<>(30000000);
        PriorityQueue<Integer> minheap = new PriorityQueue<>(30000000);
        int[] medians = new int[10002];
        Scanner input = new Scanner(System.in);
        int i = 0;
        while (input.hasNextInt()) {

            if (maxheap.empty() && minheap.empty()) {
                int a = input.nextInt();
                int b = input.nextInt();
                minheap.insert(Math.max(a, b));
                maxheap.insert(Math.min(a, b));
                medians[i++] = a;
            } else {
                int val = input.nextInt();
                if (val > minheap.top()) {
                    minheap.insert(val);
                } else if (val < maxheap.top()) {
                    maxheap.insert(val);
                } else {
                    minheap.insert(val);
                }

                if (minheap.size() - maxheap.size() == 2) {
                    maxheap.insert(minheap.extract());
                }

                if (minheap.size() - maxheap.size() == -2) {
                    minheap.insert(maxheap.extract());
                }
            }

            if (minheap.size() == maxheap.size()) {
                medians[i++] = Math.min(minheap.top(), maxheap.top());
            } else if (minheap.size() < maxheap.size()) {
                medians[i++] = maxheap.top();
            } else {
                medians[i++] = minheap.top();
            }

        }


        int sum = 0;
        for (int m : medians) {
            sum += m;
        }
        System.out.println(sum % 10000);
    }
}
