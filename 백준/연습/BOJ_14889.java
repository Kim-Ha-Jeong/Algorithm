import java.util.*;
import java.io.*;

public class BOJ_14889 {
    static int n, ans = Integer.MAX_VALUE;
    static int[][] S;
    static boolean[] visited;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        arr = new int[n / 2];
        S = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int idx) {
        if (depth == n / 2) {
            ans = Math.min(cal(), ans);
            return;
        }

        for (int i = idx; i < n; i++) {
            visited[i] = true;
            arr[depth] = i;
            dfs(depth + 1, i + 1);
            visited[i] = false;
        }
    }

    static int cal() {
        int sum = 0;
        int sum2 = 0;

        int[] arr2 = new int[n / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                arr2[idx++] = i;
        }

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                if (i == j)
                    continue;
                sum += S[arr[i]][arr[j]];
                sum2 += S[arr2[i]][arr2[j]];
            }
        }

        return Math.abs(sum2 - sum);
    }
}
