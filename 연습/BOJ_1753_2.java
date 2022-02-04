import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class BOJ_1753_2 {
    static int v, e, k;
    static ArrayList<Edge>[] list;
    static int[] dist;
    static int INF = Integer.MAX_VALUE;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        dist = new int[v + 1];

        list = new ArrayList[v + 1];
        for (int i = 1; i < v + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int a, b, c;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list[a].add(new Edge(b, c));
        }

        Arrays.fill(dist, INF);
        dijkstra(k);

        for (int i = 1; i < v + 1; i++) {
            if (dist[i] == INF) {
                sb.append("INF");
            } else {
                sb.append(dist[i]);
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
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge node = pq.poll();
            int now = node.node;

            if (node.cost > dist[now])
                continue;

            for (Edge nextNode : list[now]) {
                int next = nextNode.node;
                int cost = nextNode.cost;

                if (dist[next] > node.cost + cost) {
                    dist[next] = dist[now] + cost;
                    pq.add(new Edge(next, dist[next]));
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
