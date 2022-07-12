import java.util.*;
import java.io.*;

public class BOJ_14225 {
    static int n;
    static int[] arr;
    static boolean[] visited = new boolean[2000001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            dfs(0, i, 0, 0);
        }

        int ans = 1;
        while (true) {
            if (!visited[ans]) {
                break;
            }
            ans++;
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int m, int sum, int idx) {
        if (depth == m) {
            visited[sum] = true;
            return;
        }

        for (int i = idx; i < n; i++) {
            dfs(depth + 1, m, sum + arr[i], i + 1);
        }
    }

}
