import java.util.*;
import java.io.*;

public class BOJ_10021_2 {
    static PriorityQueue<Edge> pq;
    static int n, c;
    static int ret = -1;
    static boolean[] v;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        Node[] arr = new Node[n];
        v = new boolean[n];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            pq = new PriorityQueue<>();
            parent[i] = i;
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
                    pq.add(new Edge(i, j, sum));
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

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            int start = e.start;
            int end = e.end;

            if (find(start) == find(end))
                continue;
            else {
                union(start, end);
                cnt += (2 - count(start, end));
                v[start] = true;
                v[end] = true;
                sum += e.cost;
            }

            if (cnt == n) {
                ret = sum;
                break;
            }
        }

    }

    static int count(int a, int b) {
        if (v[a] && v[b])
            return 2;
        else if (!v[a] && !v[b])
            return 0;
        return 1;
    }

    static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA > rootB) {
            int tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        }

        parent[rootA] = rootB;
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
        int start;
        int end;
        int cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }
}
