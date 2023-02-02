import java.util.*;
import java.io.*;

public class BOJ_23288 {
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int[] dice = { 2, 1, 5, 6, 4, 3 };
    static int n, m, k, d = 0, score = 0;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int nx = 0, ny = 0;
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int round = 0; round < k; round++) {
            int tmpX = nx + dx[d];
            int tmpY = ny + dy[d];

            if (tmpX < 0 || tmpX >= n || tmpY < 0 || tmpY >= m) {
                d = d >= 2 ? d - 2 : d + 2;
                nx += dx[d];
                ny += dy[d];
            } else {
                nx = tmpX;
                ny = tmpY;
            }

            rotate();
            int b = map[nx][ny];
            score += b * bfs(nx, ny, b);
            compare(dice[3], b);
        }

        bw.write(sb.append(score).toString());
        bw.flush();
        bw.close();
    }

    static void compare(int a, int b) {
        if (a > b) {
            d = (d + 1) % 4;
        } else if (a < b) {
            d = d == 0 ? 3 : d - 1;
        }
    }

    static int bfs(int x, int y, int num) {
        boolean[][] v = new boolean[n][m];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        v[x][y] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (v[nx][ny] || map[nx][ny] != num)
                    continue;

                v[nx][ny] = true;
                q.add(new Node(nx, ny));
            }
        }

        return cnt;
    }

    static void rotate() {
        int tmp;
        if (d == 0) {
            tmp = dice[4];
            dice[4] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[1];
            dice[1] = tmp;
        } else if (d == 1) {
            tmp = dice[3];
            for (int i = 2; i >= 0; i--) {
                dice[i + 1] = dice[i];
            }
            dice[0] = tmp;
        } else if (d == 2) {
            tmp = dice[5];
            dice[5] = dice[3];
            dice[3] = dice[4];
            dice[4] = dice[1];
            dice[1] = tmp;
        } else {
            tmp = dice[0];
            for (int i = 0; i < 3; i++) {
                dice[i] = dice[i + 1];
            }
            dice[3] = tmp;
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
