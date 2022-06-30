import java.io.*;
import java.util.*;

public class BOJ_15654 {
    static boolean[] visited;
    static int[] arr;
    static int n, m;
    static StringBuffer sb = new StringBuffer();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
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

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, str + arr[i] + " ");
                visited[i] = false;
            }
        }
    }

}
