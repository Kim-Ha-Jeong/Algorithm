import java.io.*;
import java.util.*;

public class BOJ_2234 {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { -1, 0, 1, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int room = 0;
        int ans = -1;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j])
                    continue;
                ans = Math.max(bfs(i, j), ans);
                room++;
            }
        }

        int ans2 = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited = new boolean[n][m];
                for (int k = 1; k <= 8; k *= 2) {
                    if ((map[i][j] & k) != k)
                        continue;
                    visited[i][j] = true;
                    map[i][j] = map[i][j] - k;
                    ans2 = Math.max(ans2, bfs(i, j));
                    map[i][j] = map[i][j] + k;
                }
            }
        }

        sb.append(room).append("\n");
        sb.append(ans).append("\n");
        sb.append(ans2);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();
            cnt++;
            int num = map[now.x][now.y];

            for (int i = 3; i >= 0; i--) {
                boolean flag = true;
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                int k = (int) Math.pow(2, i);
                if ((num & k) == k) {
                    flag = false;
                }

                if (!flag || nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny])
                    continue;
                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }

        return cnt;
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
