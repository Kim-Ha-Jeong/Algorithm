import java.util.*;
import java.io.*;

public class BOJ_15652 {
    static int n, m;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dfs(0, "", 1);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, String str, int idx) {
        if (depth == m) {
            sb.append(str).append("\n");
            return;
        }

        for (int i = idx; i < n + 1; i++) {
            dfs(depth + 1, str + i + " ", i);
        }
    }

}
