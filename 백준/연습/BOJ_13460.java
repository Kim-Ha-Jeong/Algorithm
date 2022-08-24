import java.util.*;
import java.io.*;

public class BOJ_13460 {
    static int n, m;
    static boolean[][][][] v;
    static Marble first;
    static int[] dx = { 0, 0, -1, 1 }; // 왼 오 위 아래
    static int[] dy = { -1, 1, 0, 0 };
    static int ans = -1;
    static int rx = -1, ry = -1, bx = -1, by = -1;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        v = new boolean[n][m][n][m];

        first = new Marble(-1, -1, -1, -1, 1);
        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = c[j];
                if (map[i][j] == 'R') {
                    first.rx = i;
                    first.ry = j;
                }
                if (map[i][j] == 'B') {
                    first.bx = i;
                    first.by = j;
                }
            }
        }

        first.map = map;
        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Marble> q = new LinkedList<>();
        q.add(first);
        v[first.rx][first.ry][first.bx][first.by] = true;

        while (!q.isEmpty()) {
            Marble now = q.poll();
            boolean r = true, b = true;

            if (now.cnt >= 11) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                map = copy(now.map);
                if (i == 0) {
                    if (now.ry < now.by) {
                        r = moveLeft(now.rx, now.ry, 'R');
                        b = moveLeft(now.bx, now.by, 'B');
                    } else {
                        b = moveLeft(now.bx, now.by, 'B');
                        r = moveLeft(now.rx, now.ry, 'R');
                    }
                } else if (i == 1) {
                    if (now.ry < now.by) {
                        b = moveRight(now.bx, now.by, 'B');
                        r = moveRight(now.rx, now.ry, 'R');
                    } else {
                        r = moveRight(now.rx, now.ry, 'R');
                        b = moveRight(now.bx, now.by, 'B');
                    }

                } else if (i == 2) {
                    if (now.rx < now.bx) {
                        r = moveUp(now.rx, now.ry, 'R');
                        b = moveUp(now.bx, now.by, 'B');
                    } else {
                        b = moveUp(now.bx, now.by, 'B');
                        r = moveUp(now.rx, now.ry, 'R');
                    }
                } else {
                    if (now.rx < now.bx) {
                        b = moveDown(now.bx, now.by, 'B');
                        r = moveDown(now.rx, now.ry, 'R');
                    } else {
                        r = moveDown(now.rx, now.ry, 'R');
                        b = moveDown(now.bx, now.by, 'B');
                    }
                }

                if (!r && b) {
                    ans = now.cnt;
                    return;
                } else if (r && b) {
                    if (v[rx][ry][bx][by])
                        continue;
                    v[rx][ry][bx][by] = true;
                    Marble next = new Marble(rx, ry, bx, by, now.cnt + 1);
                    next.map = map;
                    q.add(next);
                }
            }

        }
    }

    static char[][] copy(char[][] map) {
        char[][] tmp = new char[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        return tmp;
    }

    static boolean moveDown(int x, int y, char marble) {
        map[x][y] = '.';
        if (marble == 'R')
            ry = y;
        else
            by = y;

        for (int i = x + 1; i < n; i++) {
            if (map[i][y] == 'O')
                return false;

            if (map[i][y] != '.') {
                map[i - 1][y] = marble;
                if (marble == 'R') {
                    rx = i - 1;
                } else {
                    bx = i - 1;
                }
                break;
            }

            if (i == n - 1) {
                map[i][y] = marble;
                if (marble == 'R')
                    rx = i;
                else
                    bx = i;
            }
        }

        return true;
    }

    static boolean moveUp(int x, int y, char marble) {
        map[x][y] = '.';
        if (marble == 'R') {
            ry = y;
        } else {
            by = y;
        }

        for (int i = x - 1; i >= 0; i--) {
            if (map[i][y] == 'O')
                return false;

            if (map[i][y] != '.') {
                map[i + 1][y] = marble;
                if (marble == 'R') {
                    rx = i + 1;
                } else {
                    bx = i + 1;
                }
                break;
            }

            if (i == 0) {
                map[i][y] = marble;
                if (marble == 'R')
                    rx = i;
                else
                    bx = i;
            }
        }

        return true;
    }

    static boolean moveRight(int x, int y, char marble) {
        map[x][y] = '.';
        if (marble == 'R') {
            rx = x;
        } else {
            bx = x;
        }

        for (int i = y + 1; i < m; i++) {
            if (map[x][i] == 'O')
                return false;

            if (map[x][i] != '.') {
                map[x][i - 1] = marble;
                if (marble == 'R') {
                    ry = i - 1;
                } else {
                    by = i - 1;
                }
                break;
            }

            if (i == m - 1) {
                map[x][i] = marble;
                if (marble == 'R')
                    ry = i;
                else
                    by = i;
            }
        }

        return true;
    }

    static boolean moveLeft(int x, int y, char marble) {
        map[x][y] = '.';
        if (marble == 'R') {
            rx = x;
        } else {
            bx = x;
        }

        for (int i = y - 1; i >= 0; i--) {
            if (map[x][i] == 'O') {
                return false;
            }

            if (map[x][i] != '.') {
                map[x][i + 1] = marble;
                if (marble == 'R')
                    ry = i + 1;
                else
                    by = i + 1;
                break;
            }

            if (i == 0) {
                map[x][i] = marble;
                if (marble == 'R')
                    ry = 0;
                else
                    by = 0;
            }
        }

        return true;
    }

    static class Marble {
        int rx;
        int ry;
        int bx;
        int by;
        int cnt;
        char[][] map;

        Marble(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

}
