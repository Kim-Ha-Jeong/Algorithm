import java.util.*;
import java.io.*;

public class BOJ_12946 {
    static char[][] map;
    static int n;
    static ArrayList<Node> fill;
    static int[][] color;
    static int cnt = 1;
    static int[] dx = { -1, -1, 0, 0, 1, 1 };
    static int[] dy = { 0, 1, -1, 1, -1, 0 };
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        color = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = c[j];
                if (c[j] == 'X') {
                    color[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (color[i][j] == -1) {
                    ans = Math.max(ans, bfs(i, j));
                }
            }
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static int bfs(int x, int y) {
        int cnt = 1;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y, 1));
        color[x][y] = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 6; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                if (color[nx][ny] > 0) {
                    if (now.prevX == -1)
                        continue;
                    if (now.prevX == nx && now.prevY == ny)
                        continue;
                    if (color[now.prevX][now.prevY] != color[nx][ny]) {
                        return 3;
                    }
                    continue;
                }

                if (map[nx][ny] == 'X') {
                    color[nx][ny] = now.color != 1 ? now.color - 1 : now.color + 1;
                    Node next = new Node(nx, ny, color[nx][ny]);
                    next.prevX = now.x;
                    next.prevY = now.y;
                    cnt = Math.max(cnt, color[nx][ny]);
                    q.add(next);
                }
            }
        }

        return cnt;
    }

    static class Node {
        int x;
        int y;
        int prevX = -1;
        int prevY = -1;
        int color;

        Node(int x, int y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

}
