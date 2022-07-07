import java.util.*;
import java.io.*;

public class BOJ_1260 {
    static int n, v, m;
    static ArrayList<Integer>[] list;
    static StringBuffer sb = new StringBuffer();
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 1; i < n + 1; i++) {
            Collections.sort(list[i]);
        }

        dfs(v);
        sb.append("\n");
        visited = new boolean[n + 1];
        bfs();

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void dfs(int idx) {
        visited[idx] = true;
        sb.append(idx).append(" ");
        for (int i : list[idx]) {
            if (visited[i])
                continue;
            dfs(i);
        }
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        visited[v] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            sb.append(now).append(" ");

            for (int next : list[now]) {
                if (visited[next])
                    continue;
                visited[next] = true;
                q.add(next);
            }
        }

    }
}