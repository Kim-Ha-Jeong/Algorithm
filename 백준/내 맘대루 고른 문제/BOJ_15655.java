import java.io.*;
import java.util.*;

public class BOJ_15655 {
    static int n, m;
    static int[] arr;
    static boolean[] visited;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
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

        for (int i = idx; i < n; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            dfs(depth + 1, str + arr[i] + " ", i + 1);
            visited[i] = false;
        }
    }

}
