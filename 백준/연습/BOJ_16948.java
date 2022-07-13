import java.util.*;
import java.io.*;

public class BOJ_16948 {
    static int[] dx = { -2, -2, 0, 2, 2, 0 };
    static int[] dy = { -1, 1, 2, 1, -1, -2 };
    static int n, startX, startY, endX, endY;
    static int[][] map;
    static int ans = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());

        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], -1);
        }
        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY));
        map[startX][startY] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == endX && now.y == endY) {
                ans = map[now.x][now.y];
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;
                if (map[nx][ny] != -1)
                    continue;

                q.add(new Node(nx, ny));
                map[nx][ny] = map[now.x][now.y] + 1;
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
