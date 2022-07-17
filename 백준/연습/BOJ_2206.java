import java.util.*;
import java.io.*;

public class BOJ_2206 {
    static int n, m;
    static int[][] map;
    static boolean[][][] v;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int ans = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        v = new boolean[n + 1][m + 1][2];

        for (int i = 1; i < n + 1; i++) {
            String[] s = br.readLine().split("");
            for (int j = 1; j < m + 1; j++) {
                map[i][j] = Integer.parseInt(s[j - 1]);
            }
        }

        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1, 1, 0, 0));
        v[1][1][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == n && now.y == m) {
                ans = now.cnt + 1;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 1 || nx >= n + 1 || ny < 1 || ny >= m + 1)
                    continue;

                if (now.k == 0 && map[nx][ny] == 1) {
                    if (!v[nx][ny][1]) {
                        q.add(new Node(nx, ny, now.cnt + 1, 1));
                        v[nx][ny][1] = true;
                    }
                }

                if (map[nx][ny] == 0 && !v[nx][ny][now.k]) {
                    q.add(new Node(nx, ny, now.cnt + 1, now.k));
                    v[nx][ny][now.k] = true;
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int cnt;
        int k;

        Node(int x, int y, int cnt, int k) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
        }
    }

}
