import java.util.*;
import java.io.*;

public class BOJ_16929 {
    static int n, m, startX, startY;
    static char color;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = c[j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                startX = i;
                startY = j;
                color = map[i][j];
                visited[i][j] = true;
                dfs(i, j, 1);
                visited[i][j] = false;
            }
        }

        bw.write(sb.append("No").toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int x, int y, int depth) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                continue;

            if (map[nx][ny] != color)
                continue;

            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1);
                visited[nx][ny] = false;
            } else {
                if (nx == startX && ny == startY && depth >= 4) {
                    System.out.print("Yes");
                    System.exit(0);
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

}
