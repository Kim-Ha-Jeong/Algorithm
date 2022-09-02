import java.util.*;
import java.io.*;

public class BOJ_5827 {
    static int n, m;
    static char[][] map;
    static int[][][] dist;
    static int startX, startY, endX, endY;
    static int ans = -1;
    static int[] dy = { -1, 1 };
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        dist = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = ch[j];
                if (ch[j] == 'C') {
                    startX = i;
                    startY = j;
                }

                if (ch[j] == 'D') {
                    endX = i;
                    endY = j;
                }
            }
        }

        init();
        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void init() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 2; k++) {
                    dist[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
    }

    static void bfs() {
        pq = new PriorityQueue<>();
        dist[startX][startY][0] = 0;
        pq.add(new Node(startX, startY, 0, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int x = now.x;
            int y = now.y;
            int g = now.gravity;

            if (x == endX && y == endY) {
                ans = now.cnt;
                return;
            }

            int nx = fall(g, x, y);
            if (nx == -1)
                continue;
            if (nx == -100) {
                ans = now.cnt;
                return;
            }

            if (dist[nx][y][g] > now.cnt) {
                pq.add(new Node(nx, y, now.cnt, g));
                dist[nx][y][g] = now.cnt;
            }

            int ng = g == 0 ? 1 : 0;
            if (dist[nx][y][ng] > now.cnt + 1) {
                pq.add(new Node(nx, y, now.cnt + 1, ng));
                dist[nx][y][ng] = now.cnt + 1;
            }

            for (int i = 0; i < 2; i++) {
                int ny = y + dy[i];

                if (ny < 0 || ny >= m)
                    continue;
                if (map[nx][ny] == '#' || map[nx][ny] == 'C')
                    continue;

                if (dist[nx][ny][g] > now.cnt) {
                    pq.add(new Node(nx, ny, now.cnt, g));
                    dist[nx][ny][g] = now.cnt;
                }
            }
        }
    }

    static int fall(int g, int x, int y) {
        int ret = 0;

        if (g == 1) {
            for (int i = x - 1; i >= 0; i--) {
                if (map[i][y] == '#') {
                    ret = i + 1;
                    break;
                }

                if (map[i][y] == 'D') {
                    return -100;
                }

                if (i == 0) {
                    return -1;
                }
            }
        } else {
            for (int i = x + 1; i < n; i++) {
                if (map[i][y] == '#') {
                    ret = i - 1;
                    break;
                }

                if (map[i][y] == 'D') {
                    return -100;
                }

                if (i == n - 1) {
                    return -1;
                }
            }
        }

        return ret;
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cnt;
        int gravity;

        Node(int x, int y, int cnt, int gravity) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.gravity = gravity;
        }

        @Override
        public int compareTo(Node n) {
            return this.cnt - n.cnt;
        }
    }

}
