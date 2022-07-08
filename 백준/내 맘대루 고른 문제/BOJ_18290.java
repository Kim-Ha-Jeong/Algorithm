import java.util.*;
import java.io.*;

public class BOJ_18290 {
    static int n, m, k;
    static long ans = Long.MIN_VALUE;
    static int[][] map;
    static int[][] visited;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], -1);
        }

        combination(0, 0, 0);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int idx, int sum) {
        if (depth == k) {
            ans = Math.max(ans, sum);
            return;
        }

        int x = idx / m;
        int y = idx % m;
        for (int i = x; i < n; i++) {
            if (i != x) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j] != -1)
                        continue;

                    int tmp = i * m + j;
                    visited[i][j] = tmp;
                    changeT(tmp, i, j);
                    combination(depth + 1, tmp + 1, sum + map[i][j]);
                    changeF(tmp, i, j);
                    visited[i][j] = -1;
                }
            } else {
                for (int j = y; j < m; j++) {
                    if (visited[i][j] != -1)
                        continue;

                    int tmp = i * m + j;
                    visited[i][j] = tmp;
                    changeT(tmp, i, j);
                    combination(depth + 1, tmp + 1, sum + map[i][j]);
                    changeF(tmp, i, j);
                    visited[i][j] = -1;
                }
            }
        }

    }

    static void changeT(int idx, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] != -1)
                continue;

            visited[nx][ny] = idx;
        }
    }

    static void changeF(int idx, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                continue;

            if (visited[nx][ny] != idx)
                continue;

            visited[nx][ny] = -1;
        }
    }

}
