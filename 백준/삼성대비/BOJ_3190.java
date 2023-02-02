import java.util.*;
import java.io.*;

public class BOJ_3190 {
    static int n, k, l, apple = 100, s = -1, d = 0, second = -1;
    static int[][] map;
    static ArrayDeque<Node> snake;
    static boolean flag = true;
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        snake = new ArrayDeque<>();
        map = new int[n][n];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = apple;
        }

        l = Integer.parseInt(br.readLine());
        Dir[] change = new Dir[l];

        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int se = Integer.parseInt(st.nextToken());
            int dir = st.nextToken().charAt(0) == 'D' ? -1 : 1;
            change[i] = new Dir(se, dir);
        }
        map[0][0] = s;
        snake.add(new Node(0, 0));
        int idx = 0;

        while (flag) {
            second++;
            if (idx < l && second == change[idx].second) {
                int f = change[idx].d;
                if (f == -1) {
                    d = d == 0 ? 3 : d - 1;
                } else {
                    d = (d + 1) % 4;
                }
                idx++;
            }
            move();
        }

        bw.write(sb.append(second + 1).toString());
        bw.flush();
        bw.close();
    }

    static void move() {
        Node now = snake.peekFirst();

        int nx = now.x + dx[d];
        int ny = now.y + dy[d];

        if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == s) {
            flag = false;
            return;
        }

        if (map[nx][ny] != apple) {
            Node last = snake.pollLast();
            map[last.x][last.y] = 0;
        }
        snake.addFirst(new Node(nx, ny));
        map[nx][ny] = s;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Dir {
        int second;
        int d;

        Dir(int second, int d) {
            this.second = second;
            this.d = d;
        }
    }
}
