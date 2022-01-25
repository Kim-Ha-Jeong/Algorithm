import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.ArrayList;

public class BOJ_1854_2 {
    static int v, e, k;
    static PriorityQueue<Integer> dist[];
    static ArrayList<Node> list[];

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dist = new PriorityQueue[v + 1];
        list = new ArrayList[v + 1];

        for (int i = 1; i < v + 1; i++) {
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, cost));
        }

        dijkstra(1);

        for (int i = 1; i < v + 1; i++) {
            if (k == dist[i].size())
                sb.append(dist[i].peek());
            else
                sb.append(-1);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start].add(0);

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (Node nextNode : list[now.end]) {
                int next = nextNode.end;
                int cost = nextNode.cost;

                int sum = cost + now.cost;
                if (dist[next].size() < k) {
                    dist[next].add(sum);
                    pq.add(new Node(next, sum));
                } else if (dist[next].peek() > sum) {
                    dist[next].poll();
                    dist[next].add(sum);
                    pq.add(new Node(next, sum));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int end;
        int cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

}
