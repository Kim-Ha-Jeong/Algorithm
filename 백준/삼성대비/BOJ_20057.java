import java.util.*;
import java.io.*;

public class BOJ_20057 {
    static int n, k, ans = 0;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };
    static int[][][] sand = {
            { { -2, 0, 2 }, { -1, -1, 10 }, { -1, 0, 7 }, { -1, 1, 1 }, { 0, -2, 5 }, { 1, -1, 10 }, { 1, 0, 7 },
                    { 1, 1, 1 }, { 2, 0, 2 } },
            { { -1, -1, 1 }, { 0, -1, 7 }, { 1, -1, 10 }, { 0, -2, 2 }, { 2, 0, 5 }, { -1, 1, 1 }, { 0, 1, 7 },
                    { 1, 1, 10 }, { 0, 2, 2 } },
            { { -2, 0, 2 }, { -1, -1, 1 }, { -1, 0, 7 }, { -1, 1, 10 }, { 0, 2, 5 }, { 1, -1, 1 }, { 1, 0, 7 },
                    { 1, 1, 10 }, { 2, 0, 2 } },
            { { -2, 0, 5 }, { -1, -1, 10 }, { 0, -1, 7 }, { 1, -1, 1 }, { 0, -2, 2 }, { -1, 1, 10 }, { 0, 1, 7 },
                    { 1, 1, 1 }, { 0, 2, 2 } } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = 2 * (n - 1) + 1;
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(n / 2, n / 2);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void move(int x, int y) {
        int cnt = 1;
        int d = 0;
        for (int i = 0; i < k; i++) {
            int[] arr = moveDetail(x, y, cnt, d);
            x = arr[0];
            y = arr[1];
            d = (d + 1) % 4;
            if (i % 2 == 1 && i != k - 2) {
                cnt++;
            }
        }
    }

    static int[] moveDetail(int x, int y, int cnt, int d) {
        for (int i = 0; i < cnt; i++) {
            x += dx[d];
            y += dy[d];
            int origin = map[x][y];
            if (origin == 0)
                continue;
            if (origin >= 10) {
                for (int l = 0; l < 9; l++) {
                    int nx = x + sand[d][l][0];
                    int ny = y + sand[d][l][1];
                    int part = (int) (sand[d][l][2] / 100.0 * origin);
                    map[x][y] -= part;

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        ans += part;
                        continue;
                    }

                    map[nx][ny] += part;
                }
            }
            int ax = x + dx[d];
            int ay = y + dy[d];

            if (ax < 0 || ax >= n || ay < 0 || ay >= n) {
                ans += map[x][y];
            } else {
                map[ax][ay] += map[x][y];
            }
            map[x][y] = 0;
        }

        int[] ret = { x, y };
        return ret;
    }

}
