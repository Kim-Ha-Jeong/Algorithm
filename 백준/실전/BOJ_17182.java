import java.util.*;
import java.io.*;

public class BOJ_17182 {
    static int n, k;
    static int[][] dist;
    static boolean[] v;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dist = new int[n][n];
        v = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int m = 0; m < n; m++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    if (s == e)
                        continue;
                    dist[s][e] = Math.min(dist[s][e], dist[s][m] + dist[m][e]);
                }
            }
        }

        v[k] = true;
        dfs(k, 0, 0);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int start, int depth, int sum) {
        if (depth == n - 1) {
            ans = Math.min(sum, ans);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (v[i])
                continue;
            v[i] = true;
            dfs(i, depth + 1, sum + dist[start][i]);
            v[i] = false;
        }
    }

}
