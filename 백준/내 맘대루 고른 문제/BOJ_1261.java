import java.util.*;
import java.io.*;

public class BOJ_1261 {
    static int n, m, ans = -1;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }

        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0, arr[0][0]));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            int cnt = now.cnt;

            if (now.x == n - 1 && now.y == m - 1) {
                ans = cnt;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= n || nx < 0 || ny >= m || ny < 0)
                    continue;

                if (visited[nx][ny])
                    continue;

                q.add(new Node(nx, ny, cnt + arr[nx][ny]));
                visited[nx][ny] = true;

            }
        }
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n) {
            return this.cnt - n.cnt;
        }
    }

}
