import java.util.*;
import java.io.*;

public class BOJ_1182 {
    static int[] arr;
    static int n;
    static long s;
    static int ans = 0;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Long.parseLong(st.nextToken());

        arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n + 1; i++) {
            dfs(0, i, 1, 0);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int m, int idx, int sum) {
        if (depth == m) {
            if (sum == s)
                ans++;
            return;
        }

        for (int i = idx; i < n + 1; i++) {
            dfs(depth + 1, m, i + 1, sum + arr[i]);
        }

    }
}