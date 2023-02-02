import java.util.*;
import java.io.*;

public class 나무박멸 {
    static int n, m, k, c, ans = 0;
    static int[][] map, empCnt, herb;
    static boolean[][][] empty;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int[] cdx = { -1, -1, 1, 1 };
    static int[] cdy = { -1, 1, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken()) + 1;

        map = new int[n][n];
        herb = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int year = 1; year < m + 1; year++) {
            empty = new boolean[n][n][4];
            empCnt = new int[n][n];
            growUp();
            birth();
            poisoning();
            deleteHerb();
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();

    }

    static void deleteHerb() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (herb[i][j] > 0) {
                    herb[i][j]--;
                }
            }
        }
    }

    static void poisoning() {
        int max = 0;
        int maxX = 0;
        int maxY = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= 0)
                    continue;
                int cnt = map[i][j];

                for (int d = 0; d < 4; d++) {
                    int nx = i;
                    int ny = j;

                    for (int x = 0; x < k; x++) {
                        nx += cdx[d];
                        ny += cdy[d];
                        if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                            continue;
                        if (map[nx][ny] <= 0)
                            break;
                        cnt += map[nx][ny];
                    }
                }

                if (max < cnt) {
                    max = cnt;
                    maxX = i;
                    maxY = j;
                }
            }
        }

        ans += max;

        if (map[maxX][maxY] > 0) {
            map[maxX][maxY] = 0;
            herb[maxX][maxY] = c;
            for (int d = 0; d < 4; d++) {
                int nx = maxX;
                int ny = maxY;

                for (int x = 0; x < k; x++) {
                    nx += cdx[d];
                    ny += cdy[d];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                        continue;
                    if (map[nx][ny] < 0)
                        break;
                    if (map[nx][ny] == 0) {
                        herb[nx][ny] = c;
                        break;
                    }

                    map[nx][ny] = 0;
                    herb[nx][ny] = c;
                }
            }
        }
    }

    static void birth() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= 0)
                    continue;
                if (empCnt[i][j] == 0)
                    continue;

                int tree = map[i][j] / empCnt[i][j];
                for (int l = 0; l < 4; l++) {
                    if (!empty[i][j][l])
                        continue;

                    int nx = i + dx[l];
                    int ny = j + dy[l];

                    map[nx][ny] += tree;
                }
            }
        }
    }

    static void growUp() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= 0)
                    continue;

                int cnt = 0;
                int emp = 0;
                for (int l = 0; l < 4; l++) {
                    int nx = i + dx[l];
                    int ny = j + dy[l];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                        continue;
                    if (map[nx][ny] > 0) {
                        cnt++;
                    } else if (map[nx][ny] == 0 && herb[nx][ny] == 0) {
                        emp++;
                        empty[i][j][l] = true;
                    }
                }
                map[i][j] += cnt;
                empCnt[i][j] = emp;
            }
        }
    }

    static class Node {
        int x;
        int y;
        int year = -1;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}