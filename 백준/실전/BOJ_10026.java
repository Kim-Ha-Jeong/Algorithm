import java.util.*;
import java.io.*;

public class BOJ_10026 {
    static int n;
    static boolean[][] v;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int RED = 1, GREEN = 2, BLUE = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        int[][][] color = new int[2][n][n];

        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                int x;
                if (c[j] == 'R') {
                    x = RED;
                } else if (c[j] == 'G') {
                    x = GREEN;
                } else {
                    x = BLUE;
                }

                color[0][i][j] = x;
                color[1][i][j] = x == GREEN ? RED : x;
            }
        }

        int[] section = { 0, 0 };
        for (int k = 0; k < 2; k++) {
            v = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (v[i][j])
                        continue;
                    bfs(i, j, color[k]);
                    section[k]++;
                }
            }
        }

        sb.append(section[0]).append(" ").append(section[1]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs(int x, int y, int[][] arr) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;
                if (v[nx][ny] || arr[nx][ny] != arr[now.x][now.y])
                    continue;

                v[nx][ny] = true;
                q.add(new Node(nx, ny));
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
