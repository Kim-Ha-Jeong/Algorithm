import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_3176 {
    static int n, m = 0;
    static int[] depth;
    static int[][] max, min, parent;
    static ArrayList<Node>[] list;
    static int minVal, maxVal;

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

        int a, b, c;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        getLog();
        parent = new int[m + 1][n + 1];
        min = new int[m + 1][n + 1];
        max = new int[m + 1][n + 1];
        bfs(1);
        setParents();

        int k = Integer.parseInt(br.readLine());
        int d, e;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            d = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            LCA(d, e);
            sb.append(minVal).append(" ").append(maxVal).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        depth[start] = 1;
        q.add(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (Node nextNode : list[now]) {
                int next = nextNode.num;
                int cost = nextNode.cost;

                if (depth[next] != 0)
                    continue;

                depth[next] = depth[now] + 1;
                parent[0][next] = now;
                min[0][next] = max[0][next] = cost;
                q.add(next);
            }
        }
    }

    static void setParents() {
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
                min[i][j] = Math.min(min[i - 1][j], min[i - 1][parent[i - 1][j]]);
                max[i][j] = Math.max(max[i - 1][j], max[i - 1][parent[i - 1][j]]);
            }
        }
    }

    static int LCA(int a, int b) {
        minVal = 1000001;
        maxVal = 0;

        if (depth[b] > depth[a])
            return LCA(b, a);

        for (int i = 0; i < m + 1; i++) {
            // (2^i) 칸만큼 올려라 처음 1.. 2.. 4.. 8.. 이런식
            // & 연산을 통해 (depth[a] - depth[b])로 이루어진 2의제곱식을 찾아냄
            // ex. 19 = 10010 --> 가능한 것 : i = 1, i = 5
            if (((depth[a] - depth[b]) & (1 << i)) >= 1) {
                minVal = Math.min(minVal, min[i][a]);
                maxVal = Math.max(maxVal, max[i][a]);
                a = parent[i][a];
            }
        }

        if (a == b)
            return a;

        for (int i = m; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                minVal = Math.min(minVal, Math.min(min[i][a], min[i][b]));
                maxVal = Math.max(maxVal, Math.max(max[i][a], max[i][b]));
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        minVal = Math.min(minVal, Math.min(min[0][a], min[0][b]));
        maxVal = Math.max(maxVal, Math.max(max[0][a], max[0][b]));

        return parent[0][a];
    }

    static void getLog() {
        for (int i = 1; i < n; i *= 2) {
            m++;
        }
    }

    static class Node {
        int num;
        int cost;

        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}