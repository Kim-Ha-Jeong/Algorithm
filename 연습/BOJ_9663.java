import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_9663 {
    static int n;
    static int[] visited;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        visited = new int[n];

        dfs(0);

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth) {
        if (depth == n) {
            ans++;
            return;
        }

        for (int i = 0; i < n; i++) {
            visited[depth] = i;
            if (check(depth))
                dfs(depth + 1);
        }
    }

    static boolean check(int idx) {
        for (int i = 0; i < idx; i++) {
            if (visited[i] == visited[idx])
                return false;
            else if (Math.abs(visited[i] - visited[idx]) == Math.abs(i - idx))
                return false;
        }

        return true;
    }

}
