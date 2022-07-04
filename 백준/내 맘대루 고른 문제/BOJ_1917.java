import java.io.*;

public class BOJ_1917 {
    static boolean[] visited;
    static int n;
    static StringBuffer sb = new StringBuffer();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];

        dfs(0, "");

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void dfs(int depth, String str) {
        if (depth == n) {
            sb.append(str).append("\n");
            return;
        }

        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, str + i + " ");
                visited[i] = false;
            }
        }
    }

}
