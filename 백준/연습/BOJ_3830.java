import java.util.*;
import java.io.*;

public class BOJ_3830 {
    static int n, m;
    static ArrayList<Integer> list[];
    static int[] weight;
    static int[] parent;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            list = new ArrayList[n + 1];
            weight = new int[n + 1];
            parent = new int[n + 1];

            for (int i = 1; i < n + 1; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                char cmd = st.nextToken().charAt(0);

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (cmd == '!') {
                    int w = Integer.parseInt(st.nextToken());
                    union(a, b, w);
                } else {
                    if (find(a) == find(b))
                        sb.append(weight[b] - weight[a]);
                    else
                        sb.append("UNKNOWN");
                    sb.append("\n");
                }
            }

            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }
    }

    static void union(int a, int b, int w) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB)
            return;

        weight[rootB] = weight[a] - weight[b] + w;
        parent[rootB] = rootA;
    }

    static int find(int x) {
        if (x == parent[x])
            return x;
        int parentIdx = find(parent[x]);
        weight[x] += weight[parent[x]];
        return parent[x] = parentIdx;
    }

}
