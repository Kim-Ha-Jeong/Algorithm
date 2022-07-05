import java.util.*;
import java.io.*;

public class BOJ_15657 {
    static int n, m;
    static int[] arr;
    static StringBuffer sb = new StringBuffer();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0, 1, "");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int idx, String str) {
        if (depth == m) {
            sb.append(str).append("\n");
            return;
        }

        for (int i = idx - 1; i < n; i++) {
            dfs(depth + 1, i + 1, str + arr[i] + " ");
        }
    }

}
