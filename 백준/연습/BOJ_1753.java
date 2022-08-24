import java.util.*;
import java.io.*;

public class BOJ_1753 {
    static int v, e;
    static ArrayList<Node>[] list;
    static int[] dist;
    static boolean[] visited;
    static int INF = Integer.MAX_VALUE;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new ArrayList[v + 1];

        for (int i = 1; i < v + 1; i++) {
            list[i] = new ArrayList<>();
        }

        dist = new int[v + 1];
        visited = new boolean[v + 1];
        Arrays.fill(dist, INF);

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
        }

        dijkstra(start);

        for (int i = 1; i < v + 1; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
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
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.idx;

            if (visited[now])
                continue;
            visited[now] = true;

            for (Node n : list[now]) {
                int next = n.idx;
                int cost = n.cost;

                if (dist[next] > dist[now] + cost) {
                    dist[next] = dist[now] + cost;
                    pq.add(new Node(next, dist[next]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

}
