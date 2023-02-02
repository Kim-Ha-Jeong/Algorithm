import java.util.*;
import java.io.*;

public class BOJ_16947 {
    static int n;
    static ArrayList<Integer>[] edge;
    static boolean[] v;
    static int[] parent;
    static int[] depth;
    static ArrayList<Integer> cycle;
    static boolean flag = false;
    static int cnt = 0;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        edge = new ArrayList[n + 1];
        v = new boolean[n + 1];
        parent = new int[n + 1];
        depth = new int[n + 1];
        cycle = new ArrayList<>();

        for (int i = 1; i < n + 1; i++) {
            edge[i] = new ArrayList<>();
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge[a].add(b);
            edge[b].add(a);
        }

        bfs();

        if (flag || cnt != n) {
            depth = new int[n + 1];
            v = new boolean[n + 1];
            getDist();
        }

        if (cnt == n) {
            depth = new int[n + 1];
        }

        for (int i = 1; i < n + 1; i++) {
            sb.append(depth[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void getDist() {
        Queue<Integer> q = new LinkedList<>();
        for (int x : cycle) {
            q.add(x);
            v[x] = true;
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : edge[now]) {
                if (v[next])
                    continue;
                v[next] = true;
                depth[next] = depth[now] + 1;
                q.add(next);
            }
        }
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        v[1] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : edge[now]) {
                if (v[next]) {
                    if (parent[now] != next) {
                        findCycle(now, next);
                        flag = true;
                        return;
                    }
                    continue;
                }

                depth[next] = depth[now] + 1;
                parent[next] = now;
                v[next] = true;
                q.add(next);
            }
        }
    }

    static void findCycle(int a, int b) {
        if (depth[a] < depth[b]) {
            findCycle(b, a);
            return;
        }

        int sub = Math.abs(depth[a] - depth[b]);

        for (int i = 0; i < sub; i++) {
            cycle.add(a);
            a = parent[a];
            cnt++;
        }

        while (true) {
            if (a != b) {
                cycle.add(a);
                cycle.add(b);
                a = parent[a];
                b = parent[b];
                cnt += 2;
            } else {
                cycle.add(a);
                cnt++;
                break;
            }
        }

    }

}
