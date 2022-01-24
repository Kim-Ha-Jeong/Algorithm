import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class BOJ_1922 {
    static int n, m;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int ans = 0, cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());

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

        solve();

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static int find(int a) {
        if (parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        parent[rootA] = rootB;
    }

    static void solve() {
        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (find(e.start) == find(e.end))
                continue;

            union(e.start, e.end);
            ans += e.value;
            cnt++;

            if (cnt == n - 1)
                break;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int value;

        Edge(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Edge c) {
            return this.value - c.value;
        }
    }
}
