import java.util.*;
import java.io.*;

public class BOJ_13460_2 {
    static int n, m, ans = -1;
    static char[][] map;
    static boolean[][][][] visited;
    static Marble red, blue;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = c[j];
                if (c[j] == 'R')
                    red = new Marble(i, j, 0);
                else if (c[j] == 'B')
                    blue = new Marble(i, j, 0);
            }
        }

        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Marble> redQ = new LinkedList<>();
        Queue<Marble> blueQ = new LinkedList<>();

        redQ.add(red);
        blueQ.add(blue);
        visited[red.x][red.y][blue.x][blue.y] = true;

        while (!redQ.isEmpty() && !blueQ.isEmpty()) {
            Marble curR = redQ.poll();
            Marble curB = blueQ.poll();

            if (map[curB.x][curB.y] == 'O')
                continue;

            if (map[curR.x][curR.y] == 'O') {
                ans = curR.cnt;
                return;
            }

            if (curR.cnt > 10) {
                ans = -1;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int[] nextR = move(curR.x, curR.y, i);
                int[] nextB = move(curB.x, curB.y, i);

                int rx = nextR[0];
                int ry = nextR[1];
                int bx = nextB[0];
                int by = nextB[1];

                if (rx == bx && ry == by && map[rx][ry] != 'O') {
                    int distR = Math.abs(rx - curR.x) + Math.abs(ry - curR.y);
                    int distB = Math.abs(bx - curB.x) + Math.abs(by - curB.y);

                    if (distR > distB) {
                        rx -= dx[i];
                        ry -= dy[i];
                    } else {
                        bx -= dx[i];
                        by -= dy[i];
                    }
                }

                if (!visited[rx][ry][bx][by]) {
                    visited[rx][ry][bx][by] = true;

                    redQ.add(new Marble(rx, ry, curR.cnt + 1));
                    blueQ.add(new Marble(bx, by, curB.cnt + 1));
                }
            }
        }

    }

    static int[] move(int x, int y, int d) {
        int[] next = new int[2];

        while (true) {
            x += dx[d];
            y += dy[d];

            if (map[x][y] == 'O')
                break;
            if (map[x][y] == '#') {
                x -= dx[d];
                y -= dy[d];
                break;
            }

        }

        next[0] = x;
        next[1] = y;

        return next;
    }

    static class Marble {
        int x;
        int y;
        int cnt;

        Marble(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

}
