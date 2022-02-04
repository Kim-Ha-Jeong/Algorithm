import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;

public class BOJ_1854_3 {
    static int n, m, k;
    static ArrayList<Edge> list[];
    static PriorityQueue<Integer> dist[];

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];
        dist = new PriorityQueue[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        int a, b, c;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            list[a].add(new Edge(b, c));
        }

        dijkstra(1);

        for (int i = 1; i < n + 1; i++) {
            if (dist[i].size() == k) {
                sb.append(dist[i].peek());
            } else {
                sb.append(-1);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start].add(0);

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            for (Edge nextEdge : list[now.node]) {
                int next = nextEdge.node;
                int cost = nextEdge.cost;

                int sum = now.cost + cost;
                if (dist[next].size() < k) {
                    dist[next].add(sum);
                    pq.add(new Edge(next, sum));
                } else if (dist[next].peek() > sum) {
                    dist[next].poll();
                    dist[next].add(sum);
                    pq.add(new Edge(next, sum));
                }
            }
        }

    }

    static class Edge implements Comparable<Edge> {
        int node;
        int cost;

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }

}
