import java.util.*;
import java.io.*;

public class BOJ_17144 {
    static int n, m, t;
    static int[][] map;
    static int condition;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1)
                    condition = i - 1;
            }
        }

        while (t-- > 0) {
            diffusion();
            operation();
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != -1) {
                    ans += map[i][j];
                }
            }
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();

    }

    static void operation() {
        up();
        down();
    }

    static void up() {
        for (int i = condition - 2; i >= 0; i--) {
            map[i + 1][0] = map[i][0];
        }

        for (int i = 0; i < m - 1; i++) {
            map[0][i] = map[0][i + 1];
        }

        for (int i = 0; i < condition; i++) {
            map[i][m - 1] = map[i + 1][m - 1];
        }

        for (int i = m - 2; i >= 1; i--) {
            map[condition][i + 1] = map[condition][i];
        }

        map[condition][1] = 0;
    }

    static void down() {
        for (int i = condition + 2; i < n - 1; i++) {
            map[i][0] = map[i + 1][0];
        }

        for (int i = 0; i < m - 1; i++) {
            map[n - 1][i] = map[n - 1][i + 1];
        }

        for (int i = n - 2; i >= condition + 1; i--) {
            map[i + 1][m - 1] = map[i][m - 1];
        }

        for (int i = m - 2; i >= 1; i--) {
            map[condition + 1][i + 1] = map[condition + 1][i];
        }

        map[condition + 1][1] = 0;
    }

    static void diffusion() {
        Queue<Dust> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && map[i][j] != -1) {
                    q.add(new Dust(i, j, map[i][j]));
                }
            }
        }

        while (!q.isEmpty()) {
            Dust now = q.poll();

            int cnt = 0;
            int tmp = now.amount / 5;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (map[nx][ny] == -1)
                    continue;

                map[nx][ny] += tmp;
                cnt++;
            }

            map[now.x][now.y] -= (tmp * cnt);
        }
    }

    static class Dust {
        int x;
        int y;
        int amount;

        Dust(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }
}
