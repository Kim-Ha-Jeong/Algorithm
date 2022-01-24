import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11657 {
    static int n, m;
    static Edge[] list;
    static long[] dist;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new long[n + 1];
        list = new Edge[m];

        for (int i = 1; i < n + 1; i++) {
            dist[i] = INF;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[i] = new Edge(start, end, cost);
        }

        if (solve(1)) {
            sb.append(-1);
        } else {
            for (int i = 2; i < n + 1; i++) {
                if (dist[i] == INF)
                    sb.append(-1);
                else
                    sb.append(dist[i]);
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean solve(int start) {
        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int cur = list[j].start;
                int next = list[j].end;
                int cost = list[j].cost;

                if (dist[cur] == INF)
                    continue;

                if (dist[next] > dist[cur] + cost) {
                    dist[next] = dist[cur] + cost;

                    if (i == n - 1)
                        return true;
                }

            }
        }

        return false;

    }

    static class Edge {
        int start;
        int end;
        int cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

}
