import java.util.*;
import java.io.*;

class BOJ_11438 {
    static int n, m, h = 0;
    static int[] depth;
    static int[][] parent;
    static ArrayList<Integer>[] list;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());

        getHeight();

        list = new ArrayList[n + 1];
        depth = new int[n + 1];
        parent = new int[h + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        makeTree();
        setParent();

        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(LCA(a, b)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void makeTree() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        depth[1] = 1;

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

    static void setParent() {
        for (int i = 1; i < h + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
    }

    static int LCA(int a, int b) {
        if (depth[a] < depth[b])
            return LCA(b, a);

        for (int i = h; i >= 0; i--) {
            if ((depth[a] - depth[b]) >= (1 << i)) {
                a = parent[i][a];
            }
        }

        if (a == b)
            return a;

        for (int i = h; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }

    static void getHeight() {
        for (int i = 1; i < n; i *= 2) {
            h++;
        }
    }
}