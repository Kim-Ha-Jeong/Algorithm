import java.util.*;
import java.io.*;

public class BOJ_21609 {
    static int n, m;
    static int[][] map;
    static int[][] g;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static ArrayList<Group> all;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            all = new ArrayList<>();
            g = new int[n][n];
            int num = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (g[i][j] != 0 || map[i][j] <= 0 || map[i][j] == 100)
                        continue;
                    bfs(i, j, num++);
                }
            }

            if (all.size() == 0)
                break;
            Collections.sort(all);
            Group choice = all.get(0);
            ans += choice.cnt * choice.cnt;
            remove(choice.element);
            move();
            rotate();
            move();
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void rotate() {
        int[][] tmp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = map[j][n - 1 - i];
            }
        }
        map = tmp;
    }

    static void move() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 2; j >= 0; j--) {
                if (map[j][i] == -1 || map[j][i] == 100)
                    continue;
                int idx = j;
                int tmp = map[j][i];
                map[j][i] = 100;
                for (int k = j + 1; k < n; k++) {
                    if (map[k][i] == 100) {
                        idx = k;
                    }
                    if (map[k][i] == -1)
                        break;
                }
                map[idx][i] = tmp;
            }
        }
    }

    static void remove(ArrayList<Node> tmp) {
        for (Node now : tmp) {
            map[now.x][now.y] = 100;
        }
    }

    static void bfs(int x, int y, int gNum) {
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> rain = new ArrayList<>();
        ArrayList<Node> element = new ArrayList<>();

        q.add(new Node(x, y));
        g[x][y] = gNum;
        int num = map[x][y];
        int cnt = 0, rainbow = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (map[now.x][now.y] == 0) {
                rainbow++;
            }
            cnt++;
            element.add(now);

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;
                if (g[nx][ny] != 0 || map[nx][ny] == -1 || map[nx][ny] == 100)
                    continue;
                if (map[nx][ny] == 0 || map[nx][ny] == num) {
                    if (map[nx][ny] == 0) {
                        rain.add(new Node(nx, ny));
                    }
                    q.add(new Node(nx, ny));
                    g[nx][ny] = gNum;
                }
            }
        }

        for (Node bow : rain) {
            g[bow.x][bow.y] = 0;
        }

        if (cnt >= 2) {
            all.add(new Group(gNum, x, y, cnt, rainbow, element));
        }
    }

    static class Group implements Comparable<Group> {
        int num;
        int cnt;
        int x;
        int y;
        int rainbow;
        ArrayList<Node> element;

        Group(int num, int x, int y, int cnt, int rainbow, ArrayList<Node> element) {
            this.num = num;
            this.cnt = cnt;
            this.x = x;
            this.y = y;
            this.rainbow = rainbow;
            this.element = element;
        }

        @Override
        public int compareTo(Group g) {
            if (this.cnt == g.cnt) {
                if (this.rainbow == g.rainbow) {
                    if (this.x == g.x) {
                        return g.y - this.y;
                    }
                    return g.x - this.x;
                }
                return g.rainbow - this.rainbow;
            }
            return g.cnt - this.cnt;
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
