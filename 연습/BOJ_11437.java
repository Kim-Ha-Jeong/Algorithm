import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ_11437 {

    static int n, m, k = 0;
    static ArrayList<Integer>[] list;
    static int[][] parent;
    static int[] depth;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        list = new ArrayList[n + 1];
        depth = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        getLog();
        parent = new int[k + 1][n + 1];
        bfs(1);
        setParent();

        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            sb.append(LCA(a, b)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void getLog() {
        for (int i = 1; i < n; i *= 2)
            k++;
    }

    static void setParent() {
        for (int i = 1; i < k + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        depth[start] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : list[now]) {
                if (depth[next] != 0)
                    continue;

                depth[next] = depth[now] + 1;
                parent[0][next] = now;
                q.add(next);
            }
        }
    }

    static int LCA(int a, int b) {
        if (depth[b] > depth[a])
            return LCA(b, a);

        for (int i = k; i >= 0; i--) {
            if (depth[a] - depth[b] >= (1 << i)) {
                a = parent[i][a];
            }
        }

        if (a == b)
            return a;

        for (int i = k; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }
}
