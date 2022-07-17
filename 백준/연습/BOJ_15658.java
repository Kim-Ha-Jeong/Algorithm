import java.util.*;
import java.io.*;

public class BOJ_15658 {
    static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] arr;
    static int[] op;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        op = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, arr[0]);

        sb.append(max).append("\n");
        sb.append(min);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int sum) {
        if (depth == n - 1) {
            max = Math.max(sum, max);
            min = Math.min(sum, min);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] <= 0)
                continue;
            op[i]--;
            if (i == 0) {
                dfs(depth + 1, sum + arr[depth + 1]);
            } else if (i == 1) {
                dfs(depth + 1, sum - arr[depth + 1]);
            } else if (i == 2) {
                dfs(depth + 1, sum * arr[depth + 1]);
            } else {
                dfs(depth + 1, sum / arr[depth + 1]);
            }
            op[i]++;
        }
    }
}
