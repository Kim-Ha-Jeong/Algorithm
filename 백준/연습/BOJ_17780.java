import java.util.*;
import java.io.*;

class BOJ_17780 {
    static LinkedList<Integer>[][] map;
    static int[][] color;
    static int n, k, ans = -1;
    static Horse[] horse;
    static int BLUE = 2;
    static int RED = 1;
    static int WHITE = 0;
    static int[] dx = { 0, 0, 0, -1, 1 };
    static int[] dy = { 0, 1, -1, 0, 0 };
    static int[] change = { 0, 2, 1, 4, 3 };

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new LinkedList[n + 1][n + 1];
        horse = new Horse[k + 1];
        color = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                map[i][j] = new LinkedList<>();
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < k + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            horse[i] = new Horse(x, y, d);
            map[x][y].add(i);
        }

        solve();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void solve() {
        boolean flag = true;
        int cnt = 0;

        while (flag) {
            if (cnt > 1000)
                break;

            for (int i = 1; i < k + 1; i++) {
                Horse now = horse[i];

                int x = now.x;
                int y = now.y;
                int d = now.d;

                if (map[x][y].get(0) != i)
                    continue;

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!outOfBound(nx, ny) || color[nx][ny] == BLUE) {
                    horse[i].d = change[d];
                    nx = x + dx[horse[i].d];
                    ny = y + dy[horse[i].d];
                }

                if (!outOfBound(nx, ny) || color[nx][ny] == BLUE) {
                    continue;
                } else if (color[nx][ny] == RED) {
                    for (int j = map[x][y].size() - 1; j >= 0; j--) {
                        int tmp = map[x][y].get(j);
                        map[nx][ny].add(tmp);
                        horse[tmp].x = nx;
                        horse[tmp].y = ny;
                    }
                    map[x][y].clear();
                } else if (color[nx][ny] == WHITE) {
                    for (int j = 0; j < map[x][y].size(); j++) {
                        int tmp = map[x][y].get(j);
                        map[nx][ny].add(tmp);
                        horse[tmp].x = nx;
                        horse[tmp].y = ny;
                    }
                    map[x][y].clear();
                }

                if (map[nx][ny].size() >= 4) {
                    flag = false;
                    break;
                }

            }
            cnt++;
        }

        if (flag)
            ans = -1;
        else
            ans = cnt;
    }

    static boolean outOfBound(int x, int y) {
        if (x < 1 || x >= n + 1 || y < 1 || y >= n + 1)
            return false;
        return true;
    }

    static class Horse {
        int x;
        int y;
        int d;

        Horse(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}