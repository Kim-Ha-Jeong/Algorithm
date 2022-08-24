import java.util.*;
import java.io.*;

public class BOJ_4195 {
    static int[] parent, level;
    static int sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map;

        for (int t = 1; t <= tc; t++) {
            map = new HashMap<>();
            int n = Integer.parseInt(br.readLine());

            int cnt = 0;
            parent = new int[n * 2 + 1];
            level = new int[n * 2 + 1];

            for (int i = 0; i < n * 2; i++) {
                parent[i] = i;
                level[i] = 1;
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                String[] name = { st.nextToken(), st.nextToken() };
                int[] tmp = new int[2];

                for (int j = 0; j < 2; j++) {
                    if (map.containsKey(name[j])) {
                        tmp[j] = map.get(name[j]);
                    } else {
                        tmp[j] = cnt;
                        map.put(name[j], cnt++);
                    }
                }

                int a = tmp[0];
                int b = tmp[1];

                sb.append(union(a, b)).append("\n");
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

    static int union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA < rootB) {
            int tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        }

        if (rootA != rootB) {
            parent[rootA] = rootB;
            level[rootB] += level[rootA];
        }

        return level[rootB];
    }

}
