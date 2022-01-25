import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ_1854 {
    static int v, e, k, start = 1, ans = 0;
    static ArrayList<Connect> list[];
    static PriorityQueue<Integer>[] dist;
    static int INF = Integer.MAX_VALUE;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        list = new ArrayList[v + 1];
        dist = new PriorityQueue[v + 1];

        for (int i = 1; i < v + 1; i++) {
            list[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Connect(b, c));
        }

        dijkstra();

        for (int i = 1; i < v + 1; i++) {
            if (dist[i].size() == k)
                sb.append((-1) * dist[i].peek());
            else
                sb.append(-1);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dijkstra() {
        PriorityQueue<Connect> pq = new PriorityQueue<>();
        pq.add(new Connect(start, 0));
        dist[start].add(0);

        while (!pq.isEmpty()) {
            Connect now = pq.poll();

            for (Connect edge : list[now.end]) {
                int next = edge.end;
                int cost = edge.cost;

                int sumCost = now.cost + cost;
                if (dist[next].size() < k) {
                    dist[next].add(sumCost * (-1));
                    pq.add(new Connect(next, sumCost));
                } else if (dist[next].peek() * (-1) > sumCost) {
                    dist[next].poll();
                    dist[next].add(sumCost * (-1));
                    pq.add(new Connect(next, sumCost));
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
