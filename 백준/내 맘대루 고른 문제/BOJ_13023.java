import java.util.*;
import java.io.*;

public class BOJ_13023 {
    static int n, m;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int ans = 0;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            if (ans == 1)
                break;
            dfs(0, i);
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int now) {
        if (depth == 4) {
            ans = 1;
            return;
        }

        visited[now] = true;
        for (int next : list[now]) {
            if (visited[next])
                continue;
            dfs(depth + 1, next);
        }
        visited[now] = false;
    }

}
