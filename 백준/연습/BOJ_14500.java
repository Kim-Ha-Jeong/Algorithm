import java.util.*;
import java.io.*;

public class BOJ_14500 {
    static int n, m, ans = -1;
    static int[][] map;
    static int[][] exceptMap;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        exceptMap = new int[n + 2][m + 2];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                exceptMap[i + 1][j + 1] = map[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(0, i, j, 0);
                except(i + 1, j + 1);
                visited[i][j] = false;
            }
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void except(int x, int y) {
        int sum = exceptMap[x][y];
        int ret = -1;
        int[] wing = new int[4];

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            wing[i] = exceptMap[nx][ny];
            sum += wing[i];
        }

        for (int i = 0; i < 4; i++) {
            ret = Math.max(ret, sum - wing[i]);
        }

        ans = Math.max(ret, ans);
    }

    static void dfs(int depth, int x, int y, int sum) {
        if (depth == 4) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;
            if (visited[nx][ny])
                continue;

            visited[nx][ny] = true;
            dfs(depth + 1, nx, ny, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

}
