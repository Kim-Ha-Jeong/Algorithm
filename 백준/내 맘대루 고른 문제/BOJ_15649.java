import java.util.*;
import java.io.*;

public class BOJ_15649 {
    static boolean[] v;
    static int[] arr;
    static int n, m;
    static StringBuffer sb = new StringBuffer();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        v = new boolean[n + 1];
        arr = new int[n + 1];

        for (int i = 1; i < n; i++) {
            arr[i] = i;
        }

        dfs(0, "");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, String str) {
        if (depth == m) {
            sb.append(str).append("\n");
            return;
        }

        for (int i = 1; i < n + 1; i++) {
            if (!v[i]) {
                v[i] = true;
                dfs(depth + 1, str + i + " ");
                v[i] = false;
            }
        }
    }

}
