import java.util.*;
import java.io.*;

public class BOJ_1800 {
    static int n, p, k;
    static ArrayList<Edge>[] list;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int end = 0;
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Edge(b, c));
            list[b].add(new Edge(a, c));

            end = Math.max(end, c);
        }

        int start = 0;
        int ans = -1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (dijkstra(mid)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static boolean dijkstra(int x) {
        int[] dist = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int now = e.idx;
            int cost = e.cost;

            if (dist[now] < cost)
                continue;

            for (Edge n : list[now]) {
                int next = n.idx;
                int nextCost = cost;

                if (n.cost > x) {
                    nextCost++;
                }

                if (nextCost < dist[next]) {
                    dist[next] = nextCost;
                    pq.add(new Edge(next, nextCost));
                }
            }
        }

        return dist[n] <= k;
    }

    static class Edge implements Comparable<Edge> {
        int idx;
        int cost;

        Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }

}
