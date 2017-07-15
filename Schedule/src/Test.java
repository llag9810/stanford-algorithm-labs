import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by yifan on 6/21/17.
 */
public class Test {

    public static void main(String[] args) {
        ArrayList<Job> list = new ArrayList<>();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream("/home/yifan/Desktop/jobs.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner input = new Scanner(fileInputStream);
        int count = input.nextInt();
        while (input.hasNextInt()) {
            list.add(new Job(input.nextInt(), input.nextInt()));
        }

        Collections.sort(list);

        long time = 0;
        long res = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            time += list.get(i).getLength();
            res += time * list.get(i).getWeight();
        }

        System.out.println(res);
    }
}
