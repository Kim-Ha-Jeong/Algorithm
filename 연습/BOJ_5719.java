import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class BOJ_5719 {
    static int n, m, s, e;
    static ArrayList<Node> list[];
    static int[] dist;
    static boolean[][] check; // check[a][b] = true, a -> b 로 가는 간선이 최단거리
    static ArrayList<Integer> parent[];
    static int INF = Integer.MAX_VALUE;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0)
                break;

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            list = new ArrayList[n];
            dist = new int[n];
            check = new boolean[n][n];
            parent = new ArrayList[n];

            for (int i = 0; i < n; i++) {
                list[i] = new ArrayList<>();
                parent[i] = new ArrayList<>();
            }

            int a, b, c;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                list[a].add(new Node(b, c));
            }

            dijkstra(s);
            if (dist[e] == INF) {
                sb.append(-1).append("\n");
                continue;
            }

            backTracking(s, e);
            dijkstra(s);

            if (dist[e] == INF)
                sb.append(-1);
            else
                sb.append(dist[e]);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void backTracking(int start, int now) {
        if (start == now) // 재귀 탈출 조건 마지막 점에 도착하면 끝내기..
            return;

        for (int next : parent[now]) {
            if (!check[next][now]) {
                check[next][now] = true; // 최단 경로
                backTracking(start, next);
            }
        }
    }

    static void dijkstra(int start) {
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node nowN = pq.poll();
            int now = nowN.end;

            if (nowN.cost > dist[now])
                continue;

            for (Node nextN : list[now]) {
                int next = nextN.end;
                int cost = nextN.cost;

                if (check[now][next]) // 최단 경로에 속함
                    continue;

                if (dist[next] == dist[now] + cost) // 같으면 트래킹 정보에 추가해야함
                    parent[next].add(now); // 왜냐면 최단 거리 경로가 2개 이상 나올 수도 있기 때문에

                if (dist[next] > dist[now] + cost) {
                    dist[next] = dist[now] + cost;
                    parent[next].clear();
                    parent[next].add(now);
                    pq.add(new Node(next, dist[next]));
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
