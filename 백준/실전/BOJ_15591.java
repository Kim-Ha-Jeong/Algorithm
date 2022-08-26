import java.util.*;
import java.io.*;

public class BOJ_15591 {
    static int n;
    static ArrayList<Edge>[] list;
    static int[][] dist;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];
        dist = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Edge(b, c));
            list[b].add(new Edge(a, c));
        }

        for (int i = 1; i < n + 1; i++) {
            bfs(i);
        }

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cnt = 0;

            for (int i = 1; i < n + 1; i++) {
                if (v == i)
                    continue;
                if (dist[v][i] >= k)
                    cnt++;
            }

            sb.append(cnt).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void bfs(int start) {
        boolean[] v = new boolean[n + 1];
        Queue<Edge> q = new LinkedList<>();
        q.add(new Edge(start, Integer.MAX_VALUE));
        v[start] = true;

        while (!q.isEmpty()) {
            Edge now = q.poll();

            for (Edge e : list[now.end]) {
                int next = e.end;
                if (v[next])
                    continue;

                int min = Math.min(e.cost, now.cost);
                if (dist[start][next] == 0 || dist[start][next] > min)
                    dist[start][next] = min;

                if (dist[next][start] == 0 || dist[next][start] > min)
                    dist[next][start] = min;

                v[next] = true;
                q.add(new Edge(next, min));
            }
        }

    }

    static class Edge {
        int end;
        int cost;

        Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}
