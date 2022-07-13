import java.io.*;
import java.util.*;

public class BOJ_16197 {
    static int n, m;
    static int[][] map;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static boolean[][][][] visited;
    static Queue<Node> q = new LinkedList<Node>();
    static int ans = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m][n][m];

        int num = 0;
        int aX = 0, aY = 0, bX = 0, bY = 0;
        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (c[j] == 'o') {
                    map[i][j] = 2;
                    if (num == 0) {
                        aX = i;
                        aY = j;
                    } else {
                        bX = i;
                        bY = j;
                    }
                    num++;
                } else if (c[j] == '#')
                    map[i][j] = 1;
            }
        }

        q.add(new Node(aX, aY, bX, bY, 0));
        visited[aX][aY][bX][bY] = true;
        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.cnt > 10) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nxa = now.ax + dx[i];
                int nya = now.ay + dy[i];

                int nxb = now.bx + dx[i];
                int nyb = now.by + dy[i];

                if (!canMove(nxa, nya)) {
                    nxa = now.ax;
                    nya = now.ay;
                }

                if (!canMove(nxb, nyb)) {
                    nxb = now.bx;
                    nyb = now.by;
                }

                boolean cur = check(nxa, nya);
                boolean side = check(nxb, nyb);
                if (!(cur && side)) {
                    if (cur == false && side == false) {
                        continue;
                    } else {
                        ans = now.cnt + 1;
                        return;
                    }
                }

                if (!visited[nxa][nya][nxb][nyb]) {
                    q.add(new Node(nxa, nya, nxb, nyb, now.cnt + 1));
                    visited[nxa][nya][nxb][nyb] = true;
                }
            }
        }
    }

    static boolean check(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m)
            return false;
        return true;
    }

    static boolean canMove(int x, int y) {
        if (x >= 0 && y >= 0 && x < n && y < m && map[x][y] == 1)
            return false;
        return true;
    }

    static class Node {
        int ax;
        int ay;
        int bx;
        int by;
        int cnt;

        Node(int ax, int ay, int bx, int by, int cnt) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

}
