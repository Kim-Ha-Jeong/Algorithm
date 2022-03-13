import java.util.*;
import java.io.*;

public class BOJ_14500_2 {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int[][] dfx = { { 0, 0, 0, -1 }, { 0, 1, 2, 1 }, { 0, 0, 0, 1 }, { 0, -1, 0, 1 } };
    static int[][] dfy = { { 0, 1, 2, 1 }, { 0, 0, 0, 1 }, { 0, 1, 2, 1 }, { 0, 1, 1, 1 } };
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                fuck(i, j);
                visited[i][j] = false;
            }
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny])
                continue;

            visited[nx][ny] = true;
            dfs(nx, ny, depth + 1, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    static void fuck(int x, int y) {
        for (int i = 0; i < 4; i++) {
            boolean flag = true;
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                int nx = x + dfx[i][j];
                int ny = y + dfy[i][j];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    flag = false;
                    break;
                }
                sum += map[nx][ny];
            }

            if (flag)
                ans = Math.max(sum, ans);
        }
    }

}
