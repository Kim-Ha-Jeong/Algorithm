import java.util.*;
import java.io.*;

public class BOJ_10021 {
    static ArrayList<Edge>[] list;
    static int n, c;
    static int ret = -1;
    static boolean[] v;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        Node[] arr = new Node[n];
        list = new ArrayList[n];
        v = new boolean[n];

        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Node(x, y);
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            Node a = arr[i];
            for (int j = i + 1; j < n; j++) {
                Node b = arr[j];

                int subX = a.x - b.x;
                int subY = a.y - b.y;

                int sum = subX * subX + subY * subY;

                if (sum >= c) {
                    list[i].add(new Edge(j, sum));
                    list[j].add(new Edge(i, sum));
                    cnt++;
                }
            }
        }

        if (cnt >= n - 1)
            bfs();

        bw.write(sb.append(ret).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        int sum = 0;
        int cnt = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            int now = e.end;
            if (v[now])
                continue;

            sum += e.cost;
            v[now] = true;

            for (Edge nextE : list[now]) {
                if (!v[nextE.end]) {
                    pq.add(nextE);
                }
            }

            if (++cnt == n) {
                ret = sum;
                break;
            }
        }

    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int end;
        int cost;

        Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }
}
