import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class BOJ_1713 {
    static int n, all;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        all = Integer.parseInt(br.readLine());
        arr = new int[all];

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < all; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        solve();

    }

    static void solve() {
        LinkedList<Student> list = new LinkedList<>();
        int idx = 0;
        int age = 0;

        while (true) {
            if (idx >= all)
                break;

            boolean flag = false;
            for (Student s : list) {
                if (s.num == arr[idx]) {
                    list.remove(s);
                    list.add(new Student(arr[idx++], s.recommend + 1, s.time));
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                if (list.size() >= n) {
                    Collections.sort(list);
                    list.poll();
                }
                list.add(new Student(arr[idx++], 1, age++));
            }
        }

        Collections.sort(list, Comparator.comparingInt(s -> s.num));

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).num);
            System.out.print(" ");
        }

    }

    static class Student implements Comparable<Student> {
        int num;
        int recommend;
        int time;

        Student(int num, int recommend, int time) {
            this.num = num;
            this.recommend = recommend;
            this.time = time;
        }

        @Override
        public int compareTo(Student s) {
            if (this.recommend == s.recommend)
                return this.time - s.time;
            return this.recommend - s.recommend;
        }

    }
}
