import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class BOJ_9663 {
    static int[] visit;
    static int n, cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        visit = new int[n];

        dfs(0);

        bw.write(sb.append(cnt).toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth) {
        if (depth == n) {
            cnt++;
            return;
        }

        for (int i = 0; i < n; i++) {
            visit[depth] = i;
            if (check(depth)) {
                dfs(depth + 1);
            }
        }
    }

    static boolean check(int row) {
        for (int i = 0; i < row; i++) {
            if (visit[i] == visit[row])
                return false;

            if (Math.abs(i - row) == Math.abs(visit[i] - visit[row]))
                return false;
        }

        return true;
    }

}
