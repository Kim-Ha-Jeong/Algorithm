import java.util.*;
import java.io.*;

public class BOJ_11048 {
    static int n, m;
    static int[][] map;
    static int[][] dist;
    static int[] dx = { 1, 0, 1 };
    static int[] dy = { 0, 1, 1 };
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        dist[0][0] = map[0][0];

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == n - 1 && now.y == m - 1) {
                ans = Math.max(dist[n - 1][m - 1], ans);
                continue;
            }

            for (int i = 0; i < 3; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                int candy = dist[now.x][now.y] + map[nx][ny];
                if (dist[nx][ny] == -1 || dist[nx][ny] < candy) {
                    dist[nx][ny] = candy;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
