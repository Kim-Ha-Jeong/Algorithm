import java.io.*;
import java.util.*;

class BOJ_1922 {
    static int n, m, cnt = 0, ans = 0;
    static PriorityQueue<Edge> pq;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        parent = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == b)
                continue;

            pq.add(new Edge(a, b, c));
        }

        dijkstra();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        parent[rootA] = rootB;
    }

    static void dijkstra() {
        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (find(e.start) == find(e.end))
                continue;

            union(e.start, e.end);
            ans += e.cost;
            cnt++;

            if (cnt == n - 1)
                return;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }
}