import java.util.*;
import java.io.*;

public class BOJ_20058 {
    static int n, q, l, ans = 0;
    static int[][] map;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static boolean[][] v;
    static int[] variable = { 1, 2, 4, 8, 16, 32, 64 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        n = variable[k];
        map = new int[n][n];
        v = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int turn = 0; turn < q; turn++) {
            l = variable[Integer.parseInt(st.nextToken())];
            if (l != 0) {
                int[][] tmp = new int[n][n];
                for (int i = 0; i < n; i += l) {
                    for (int j = 0; j < n; j += l) {
                        rotate(i, j, i + l, j + l, tmp);
                    }
                }
                map = tmp;
            }

            find();
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 0)
                    sum += map[i][j];
                if (v[i][j] || map[i][j] <= 0)
                    continue;
                findGroup(i, j);
            }
        }

        sb.append(sum).append("\n");
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void findGroup(int x, int y) {
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

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;
                if (map[nx][ny] <= 0 || v[nx][ny])
                    continue;

                q.add(new Node(nx, ny));
                v[nx][ny] = true;
            }
        }

        ans = Math.max(ans, cnt);
    }

    static int cal() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= 0)
                    continue;
                sum += map[i][j];
            }
        }
        return sum;
    }

    static void find() {
        int[][] tmp = copy();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                        continue;
                    if (map[nx][ny] <= 0)
                        continue;
                    cnt++;
                }
                if (cnt < 3)
                    tmp[i][j] -= 1;
            }
        }
        map = tmp;
    }

    static int[][] copy() {
        int[][] tmp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }

    static void rotate(int startX, int startY, int endX, int endY, int[][] tmp) {
        int idx = endY - 1;
        for (int i = startX; i < endX; i++) {
            int row = startX;
            for (int j = startY; j < endY; j++) {
                tmp[row][idx] = map[i][j];
                row++;
            }
            idx--;
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
