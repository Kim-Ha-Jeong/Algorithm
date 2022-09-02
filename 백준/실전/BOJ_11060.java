import java.util.*;
import java.io.*;

public class BOJ_11060 {
    static int n;
    static int[] map;
    static int[] dist;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());

        map = new int[n];
        dist = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        ans = -1;
        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0] = 0;
        pq.add(new Node(0, map[0], 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.idx == n - 1) {
                ans = now.cnt;
                return;
            }

            int idx = now.idx;
            int num = now.num;
            for (int i = idx + 1; i < idx + num + 1; i++) {
                if (i >= n)
                    break;
                if (dist[i] > now.cnt + 1) {
                    dist[i] = now.cnt + 1;
                    pq.add(new Node(i, map[i], now.cnt + 1));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int idx;
        int num;
        int cnt;

        Node(int idx, int num, int cnt) {
            this.idx = idx;
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n) {
            return this.cnt - n.cnt;
        }
    }
}
