import java.util.*;
import java.io.*;

public class BOJ_15650 {
    static int n, m;
    static boolean[] visited;
    static StringBuffer sb = new StringBuffer();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];

        dfs(0, "", 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, String str, int idx) {
        if (depth == m) {
            sb.append(str).append("\n");
            return;
        }

        for (int i = idx + 1; i < n + 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, str + i + " ", i);
                visited[i] = false;
            }
        }
    }

}
