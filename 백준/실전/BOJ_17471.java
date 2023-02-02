import java.util.*;
import java.io.*;

public class BOJ_17471 {
    static int n;
    static ArrayList<Integer>[] list;
    static int[] people;
    static int all = 0, ans = Integer.MAX_VALUE;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        people = new int[n + 1];
        list = new ArrayList[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
            people[i] = Integer.parseInt(st.nextToken());
            all += people[i];
        }

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            for (int j = 0; j < num; j++) {
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i <= n / 2; i++) {
            boolean[] v = new boolean[n + 1];
            combination(0, i, 1, v);
        }

        if (ans == Integer.MAX_VALUE)
            ans = -1;

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();

    }

    static void combination(int depth, int m, int idx, boolean[] v) {
        if (depth == m) {
            ArrayList<Integer> a = new ArrayList<>();
            ArrayList<Integer> b = new ArrayList<>();
            for (int i = 1; i < n + 1; i++) {
                if (v[i])
                    a.add(i);
                else
                    b.add(i);
            }

            boolean flag = bfs(a, m) && bfs(b, n - m);
            if (flag)
                cal(a);
            return;
        }

        for (int i = idx; i < n + 1; i++) {
            v[i] = true;
            combination(depth + 1, m, i + 1, v);
            v[i] = false;
        }
    }

    static void cal(ArrayList<Integer> a) {
        int sum = 0;
        for (int now : a) {
            sum += people[now];
        }

        int oposite = all - sum;

        ans = Math.min(Math.abs(oposite - sum), ans);
    }

    static boolean bfs(ArrayList<Integer> section, int num) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] v = new boolean[n + 1];
        q.add(section.get(0));
        v[section.get(0)] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            cnt++;

            if (cnt == num) {
                return true;
            }

            for (int next : list[now]) {
                if (v[next])
                    continue;
                if (!section.contains(next))
                    continue;
                q.add(next);
                v[next] = true;
            }
        }

        return false;
    }

}
