import java.util.*;
import java.io.*;

public class BOJ_1175 {
    static int n, m, ans = -1;
    static boolean[][][][] v;
    static char[][] map;
    static Node start, end[];
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        v = new boolean[n][m][4][3];
        end = new Node[2];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'S')
                    start = new Node(i, j, -1, 0, 0);
                if (map[i][j] == 'C')
                    end[idx++] = new Node(i, j, -1, -1, -1);
            }
        }

        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        int a = start.x, b = start.y;
        v[a][b][0][0] = v[a][b][1][0] = v[a][b][2][0] = v[a][b][3][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (end[0].x == now.x && end[0].y == now.y) {
                if (now.cnt != 1)
                    now.cnt++;
            } else if (end[1].x == now.x && end[1].y == now.y) {
                if (now.cnt <= 1)
                    now.cnt += 2;
            }

            if (now.cnt == 3) {
                ans = now.time;
                return;
            }

            for (int i = 0; i < 4; i++) {
                if (i == now.d)
                    continue;

                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (map[nx][ny] == '#')
                    continue;
                if (v[nx][ny][i][now.cnt])
                    continue;

                v[nx][ny][i][now.cnt] = true;
                q.add(new Node(nx, ny, i, now.time + 1, now.cnt));
            }
        }
    }

    static class Node {
        int x;
        int y;
        int d;
        int time;
        int cnt;

        Node(int x, int y, int d, int time, int cnt) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.time = time;
            this.cnt = cnt;
        }
    }
}
