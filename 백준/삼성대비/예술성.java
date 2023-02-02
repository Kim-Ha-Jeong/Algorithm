import java.util.*;
import java.io.*;

public class 예술성 {
    static int n;
    static int[][] map;
    static int[][] group;
    static HashMap<Integer, Group> groupMap;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static boolean[][] v;
    static boolean[][] visited;
    static int[][] newMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        groupMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int art = 0;
        for (int round = 0; round < 4; round++) {
            v = new boolean[n][n];
            group = new int[n][n];
            newMap = new int[n][n];
            int g = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (v[i][j])
                        continue;
                    makeGroup(i, j, g++, map[i][j]);
                }
            }

            visited = new boolean[g][g];
            v = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (v[i][j])
                        continue;
                    art += cal(i, j, map[i][j], group[i][j], g);
                }
            }

            int half = n / 2;
            rotateCross(half);
            rotate(0, half, 0, half);
            rotate(0, half, half + 1, n);
            rotate(half + 1, n, 0, half);
            rotate(half + 1, n, half + 1, n);

            map = newMap;
        }

        bw.write(sb.append(art).toString());
        bw.flush();
        bw.close();
    }

    static void rotate(int sX, int eX, int sY, int eY) {
        int y = eY - 1;
        for (int i = sX; i < eX; i++) {
            int x = sX;
            for (int j = sY; j < eY; j++) {
                newMap[x][y] = map[i][j];
                x++;
            }
            y--;
        }
    }

    static void rotateCross(int half) {
        for (int i = 0; i < n; i++) {
            newMap[half][i] = map[i][half];
            newMap[n - i - 1][half] = map[half][i];
        }
    }

    static int cal(int x, int y, int num, int g, int total) {
        Queue<Node> q = new LinkedList<>();
        int[] cnt = new int[total];
        q.add(new Node(x, y));
        v[x][y] = true;

        HashSet<Integer> set = new HashSet<>();

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;
                if (num != map[nx][ny]) {
                    set.add(group[nx][ny]);
                    cnt[group[nx][ny]]++;
                }
                if (num == map[nx][ny] && !v[nx][ny]) {
                    q.add(new Node(nx, ny));
                    v[nx][ny] = true;
                }
            }
        }

        int ret = 0;
        Group a = groupMap.get(g);
        for (int i : set) {
            if (visited[g][i])
                continue;
            Group b = groupMap.get(i);
            ret += (a.cnt + b.cnt) * a.num * b.num * cnt[i];
            visited[g][i] = visited[i][g] = true;
        }

        return ret;
    }

    static void makeGroup(int x, int y, int g, int num) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        v[x][y] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            cnt++;
            group[now.x][now.y] = g;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;
                if (num != map[nx][ny] || v[nx][ny])
                    continue;

                q.add(new Node(nx, ny));
                v[nx][ny] = true;
            }
        }

        groupMap.put(g, new Group(g, num, cnt));
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Group {
        int cnt;
        int num;
        int id;

        Group(int id, int num, int cnt) {
            this.cnt = cnt;
            this.id = id;
            this.num = num;
        }
    }
}
