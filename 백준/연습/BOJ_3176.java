import java.util.*;
import java.io.*;

public class BOJ_3176 {
    static int n, h = 0, k;
    static int[][] dist;
    static ArrayList<Node>[] list;
    static int[] depth;
    static int[][] min, max, parent;
    static int minVal, maxVal;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        for (int i = 1; i < n; i *= 2) {
            h++;
        }

        parent = new int[h + 1][n + 1];
        min = new int[h + 1][n + 1];
        max = new int[h + 1][n + 1];
        depth = new int[n + 1];

        makeTree();
        setParent();

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            LCA(a, b);

            sb.append(minVal).append(" ").append(maxVal).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int LCA(int a, int b) {
        minVal = Integer.MAX_VALUE;
        maxVal = Integer.MIN_VALUE;

        if (depth[a] < depth[b])
            return LCA(b, a);

        for (int i = 0; i < h + 1; i++) {
            if (((depth[a] - depth[b]) & (1 << i)) >= 1) {
                minVal = Math.min(minVal, min[i][a]);
                maxVal = Math.max(maxVal, max[i][a]);
                a = parent[i][a];
            }
        }

        if (a == b)
            return a;

        for (int i = h; i >= 0; i--) {
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

    static void setParent() {
        for (int i = 1; i < h + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
                min[i][j] = Math.min(min[i - 1][j], min[i - 1][parent[i - 1][j]]);
                max[i][j] = Math.max(max[i - 1][j], max[i - 1][parent[i - 1][j]]);
            }
        }
    }

    static void makeTree() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        depth[1] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (Node nextNode : list[now]) {
                int next = nextNode.num;

                if (depth[next] != 0)
                    continue;

                depth[next] = depth[now] + 1;
                parent[0][next] = now;
                min[0][next] = max[0][next] = nextNode.dist;
                q.add(next);
            }
        }
    }

    static class Node {
        int num;
        int dist;

        Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }

}
