import java.util.*;
import java.io.*;

public class BOJ_16933 {
    static int n, m, k, ans = -1;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][k + 1];

        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = c[j] - '0';
            }
        }

        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == n - 1 && now.y == m - 1) {
                ans = now.cnt + 1;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (map[nx][ny] == 0 && !visited[nx][ny][now.k]) {
                    visited[nx][ny][now.k] = true;
                    q.add(new Node(nx, ny, now.k, now.cnt + 1));
                }

                if (map[nx][ny] == 1 && now.k < k) {
                    if (now.cnt % 2 == 0 && !visited[nx][ny][now.k + 1]) {
                        visited[nx][ny][now.k + 1] = true;
                        q.add(new Node(nx, ny, now.k + 1, now.cnt + 1));
                    } else if (now.cnt % 2 == 1 && !visited[nx][ny][now.k + 1]) {
                        q.add(new Node(now.x, now.y, now.k, now.cnt + 1));
                    }
                }

            }
        }
    }

    static class Node {
        int x;
        int y;
        int cnt;
        int k;

        Node(int x, int y, int k, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
        }
    }

}
