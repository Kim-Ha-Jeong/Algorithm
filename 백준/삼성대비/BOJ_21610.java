import java.util.*;
import java.io.*;

public class BOJ_21610 {
    static int n, m;
    static int[][] map;
    static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static ArrayList<Cloud> cloud;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        cloud = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cloud.add(new Cloud(n - 1, 0));
        cloud.add(new Cloud(n - 1, 1));
        cloud.add(new Cloud(n - 2, 0));
        cloud.add(new Cloud(n - 2, 1));
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken()) % n;
            move(d, s);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += map[i][j];
            }
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void move(int d, int s) {
        boolean[][] v = new boolean[n][n];
        ArrayList<Cloud> tmp = new ArrayList<>();

        for (Cloud c : cloud) {
            int nx = c.x + dx[d] * s;
            int ny = c.y + dy[d] * s;

            if (nx < 0) {
                nx = n - (Math.abs(nx) % n);
            }
            if (ny < 0) {
                ny = n - (Math.abs(ny) % n);
            }
            if (nx >= n) {
                nx %= n;
            }
            if (ny >= n) {
                ny %= n;
            }

            v[nx][ny] = true;
            map[nx][ny] += 1;
            tmp.add(new Cloud(nx, ny));
        }

        cloud = new ArrayList<>();

        for (Cloud c : tmp) {
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int cd = 2 * i + 1;
                int nx = c.x + dx[cd];
                int ny = c.y + dy[cd];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;
                if (map[nx][ny] == 0)
                    continue;
                cnt++;
            }
            map[c.x][c.y] += cnt;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (v[i][j])
                    continue;
                if (map[i][j] >= 2) {
                    cloud.add(new Cloud(i, j));
                    map[i][j] -= 2;
                }
            }
        }
    }

    static class Cloud {
        int x;
        int y;

        Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
