import java.util.*;
import java.io.*;

public class BOJ_1717 {
    static int n, m;
    static int[] parent;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                int retB = find(b);
                int retC = find(c);
                if (retB == retC)
                    sb.append("YES");
                else
                    sb.append("NO");
                sb.append("\n");
            } else {
                union(b, c);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB)
            return;

        if (rootA > rootB) {
            int tmp = rootB;
            rootB = rootA;
            rootA = tmp;
        }

        parent[rootA] = rootB;
    }
}
