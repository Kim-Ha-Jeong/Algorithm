import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class BOJ_1753 {
    static int v, e, start, ans = 0;
    static ArrayList<Connect> list[];
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

        list = new ArrayList[v + 1];
        dist = new int[v + 1];

        Arrays.fill(dist, INF);

        for (int i = 1; i < v + 1; i++) {
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Connect(b, c));
        }

        dijkstra();

        for (int i = 1; i < v + 1; i++) {
            if (dist[i] != INF)
                sb.append(dist[i]);
            else
                sb.append("INF");
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dijkstra() {
        PriorityQueue<Connect> pq = new PriorityQueue<>();
        pq.add(new Connect(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Connect nowNode = pq.poll();
            int now = nowNode.end;

            if (nowNode.cost > dist[now])
                continue;

            for (Connect edge : list[now]) {
                int next = edge.end;
                int cost = edge.cost;

                if (dist[next] > dist[now] + cost) {
                    dist[next] = dist[now] + cost;
                    pq.add(new Connect(next, dist[next]));
                }
            }
        }

    }

    static class Connect implements Comparable<Connect> {
        int end;
        int cost;

        Connect(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Connect c) {
            return this.cost - c.cost;
        }
    }

}
