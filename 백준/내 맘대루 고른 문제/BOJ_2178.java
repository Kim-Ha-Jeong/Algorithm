import java.util.*;
import java.io.*;

public class BOJ_2178 {
    static int[] dx = { 0, 1, -1, 0 };
    static int[] dy = { 1, 0, 0, -1 };
    static int[][] map;
    static boolean[][] visited;
    static int n, m, ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            String[] str = br.readLine().split("");
            for (int j = 1; j < m + 1; j++) {
                map[i][j] = Integer.parseInt(str[j - 1]);
            }
        }

        bfs();

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1, 1, 1));
        visited[1][1] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == n && now.y == m) {
                ans = now.min;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 1 || nx > n || ny < 1 || ny > m || visited[nx][ny])
                    continue;

                if (map[nx][ny] == 0)
                    continue;

                q.add(new Node(nx, ny, now.min + 1));
                visited[nx][ny] = true;
            }
        }
    }

    static class Node {
        int x;
        int y;
        int min;

        Node(int x, int y, int min) {
            this.x = x;
            this.y = y;
            this.min = min;
        }
    }

}
