import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14391 {
    static int[][] arr;
    static boolean[][] visited;
    static int n, m;
    static int ans = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        String[] s;
        for (int i = 0; i < n; i++) {
            s = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }

        dfs(0, 0);

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int row, int col) {
        if (row >= n) {
            sum();
            return;
        }

        if (col >= m) {
            dfs(row + 1, 0);
            return;
        }

        visited[row][col] = true;
        dfs(row, col + 1);

        visited[row][col] = false;
        dfs(row, col + 1);
    }

    static void sum() {
        int ret = 0;

        for (int i = 0; i < n; i++) {
            int tmp = 0;
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    tmp *= 10;
                    tmp += arr[i][j];
                } else {
                    ret += tmp;
                    tmp = 0;
                }
            }
            ret += tmp;
        }

        for (int i = 0; i < m; i++) {
            int tmp = 0;
            for (int j = 0; j < n; j++) {
                if (!visited[j][i]) {
                    tmp *= 10;
                    tmp += arr[j][i];
                } else {
                    ret += tmp;
                    tmp = 0;
                }
            }
            ret += tmp;
        }
        ans = Math.max(ans, ret);
    }
}
