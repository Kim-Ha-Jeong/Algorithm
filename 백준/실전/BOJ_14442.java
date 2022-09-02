import java.util.*;
import java.io.*;

public class BOJ_14442 {
    static int n, m, k, ans = -1;
    static int[][] map;
    static boolean[][][] v;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        v = new boolean[n][m][k + 1];

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
        v[0][0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == n - 1 && now.y == m - 1) {
                ans = now.dist + 1;
                return;
            }

            int nd = now.dist + 1;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (map[nx][ny] == 0) {
                    if (v[nx][ny][now.k])
                        continue;
                    v[nx][ny][now.k] = true;
                    q.add(new Node(nx, ny, nd, now.k));
                } else {
                    if (now.k < k) {
                        if (v[nx][ny][now.k + 1])
                            continue;
                        v[nx][ny][now.k + 1] = true;
                        q.add(new Node(nx, ny, nd, now.k + 1));
                    }
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int dist;
        int k;

        Node(int x, int y, int dist, int k) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.k = k;
        }
    }
}
