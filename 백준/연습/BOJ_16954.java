import java.util.*;
import java.io.*;

public class BOJ_16954 {
    static int n = 8, ans = 0;
    static int[][] map;
    static ArrayList<Node> list;
    static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1, 0 };
    static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        map = new int[n][n];
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char[] c = st.nextToken().toCharArray();
            for (int j = 0; j < n; j++) {
                if (c[j] == '.')
                    map[i][j] = 0;
                else if (c[j] == '#') {
                    map[i][j] = 1;
                    list.add(new Node(i, j, 0));
                }
            }
        }

        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();

    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(n - 1, 0, 0));

        int cnt = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == 0 && now.y == n - 1 || now.cnt >= 8 || list.size() == 0) {
                ans = 1;
                return;
            }

            if (cnt != now.cnt) {
                move();
                cnt = now.cnt;
            }

            if (map[now.x][now.y] == 1) {
                continue;
            }

            int tmp = 0;
            for (int i = 0; i < 9; i++) {
                if (i == 0 && tmp > 0)
                    continue;
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;

                if (map[nx][ny] == 1)
                    continue;

                q.add(new Node(nx, ny, now.cnt + 1));
                tmp++;
            }

        }
    }

    static void move() {
        ArrayList<Integer> remove = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            Node now = list.get(i);
            if (now.x + 1 >= n) {
                remove.add(i);
                map[now.x][now.y] = 0;
            } else {
                map[now.x][now.y] = 0;
                map[now.x + 1][now.y] = 1;
                now.x++;
            }
        }

        for (int i = 0; i < remove.size(); i++) {
            int a = remove.get(i);
            list.remove(a);
        }
    }

    static class Node {
        int x;
        int y;
        int cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

}
