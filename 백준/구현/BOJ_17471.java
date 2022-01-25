import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17471 {
    static int n;
    static int[] people;
    static int ans = Integer.MAX_VALUE;
    static ArrayList<Integer>[] list;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        people = new int[n + 1];
        list = new ArrayList[n + 1];
        String[] s = br.readLine().split(" ");

        for (int i = 1; i < n + 1; i++) {
            people[i] = Integer.parseInt(s[i - 1]);
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < n + 1; i++) {
            s = br.readLine().split(" ");
            for (int j = 1; j < Integer.parseInt(s[0]) + 1; j++) {
                list[i].add(Integer.parseInt(s[j]));
            }
        }

        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 1; i <= n / 2; i++) {
            combination(1, i, a);
        }

        if (ans == Integer.MAX_VALUE)
            ans = -1;

        StringBuilder sb = new StringBuilder();
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void combination(int start, int r, ArrayList<Integer> a) {
        if (r == 0) {
            solve(a);
            return;
        }

        for (int i = start; i < n + 1; i++) {
            a.add(i);
            combination(i + 1, r - 1, a);
            a.remove(a.size() - 1);
        }
    }

    static void solve(ArrayList<Integer> a) {
        if (!isConnect(a))
            return;

        ArrayList<Integer> b = new ArrayList<>();

        for (int i = 1; i < n + 1; i++) {
            if (!a.contains(i))
                b.add(i);
        }

        if (!isConnect(b))
            return;

        int sumA = 0, sumB = 0;
        for (int i : a) {
            sumA += people[i];
        }

        for (int i : b) {
            sumB += people[i];
        }

        ans = Math.min(Math.abs(sumA - sumB), ans);
    }

    static boolean isConnect(ArrayList<Integer> group) {
        int size = group.size();
        int start = group.get(0);
        int cnt = 1;
        boolean[] visited = new boolean[n + 1];

        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i : list[now]) {
                if (!visited[i] && group.contains(i)) {
                    cnt++;
                    visited[i] = true;
                    q.add(i);
                }
            }
        }

        if (cnt == size)
            return true;

        return false;
    }

}
