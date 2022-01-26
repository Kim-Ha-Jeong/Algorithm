import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_3830 {
    static int n, m;
    static int[] parent;
    static int[] weight;

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

            parent = new int[n + 1];
            weight = new int[n + 1];

            for (int i = 1; i < n + 1; i++) {
                parent[i] = i;
            }

            int a, b, c;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();

                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                if (str.equals("!")) {
                    c = Integer.parseInt(st.nextToken());
                    union(a, b, c);
                } else {
                    if (find(a) == find(b)) {
                        sb.append(weight[b] - weight[a]);
                    } else {
                        sb.append("UNKNOWN");
                    }

                    sb.append("\n");
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void union(int a, int b, int diff) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB)
            return;

        weight[rootB] = weight[a] - weight[b] + diff;
        parent[rootB] = rootA;
    }

    static int find(int a) {
        if (parent[a] == a)
            return a;

        int parentIdx = find(parent[a]);
        weight[a] += weight[parent[a]];
        return parent[a] = parentIdx;
    }
}