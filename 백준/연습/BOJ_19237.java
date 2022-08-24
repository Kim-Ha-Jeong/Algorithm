import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
public class BOJ_19237 {
    static Deque<Node>[] q;
    static int n, m, k;
    static int[][] map;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int[][][] dir;
    static HashMap<Integer, Shark> shark;
    static int cnt = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        q = new ArrayDeque[m + 1];
        shark = new HashMap<>();

        for (int i = 1; i < m + 1; i++) {
            q[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    shark.put(map[i][j], new Shark(map[i][j], i, j));
                    q[map[i][j]].add(new Node(i, j));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < m + 1; i++) {
            Shark s = shark.get(i);
            s.d = Integer.parseInt(st.nextToken()) - 1;
        }

        dir = new int[m + 1][4][4];
        for (int i = 1; i < m + 1; i++) { // 상어 번호
            for (int j = 0; j < 4; j++) { // 상어의 현재 이동 방향
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) { // 우선순위에 따른 방향
                    dir[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        while (cnt <= 1000) {
            for (Shark s : shark.values()) {
                moveShark(s);
            }

            afterMove();
            if (shark.size() == 1)
                break;
            cnt++;
        }

        if (cnt > 1000)
            cnt = -1;
        bw.write(sb.append(cnt).toString());
        bw.flush();
        bw.close();
    }

    static void moveShark(Shark s) {
        map[s.x][s.y] = (-1) * s.num;
        int num = s.num;
        int d = s.d;

        boolean flag = false;
        int x = -1, y = -1;
        for (int i = 0; i < 4; i++) {
            int nd = dir[num][s.d][i];

            int nx = s.x + dx[nd];
            int ny = s.y + dy[nd];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;

            if (map[nx][ny] == 0 || (map[nx][ny] == num * (-1) && x == -1 && y == -1)) {
                x = nx;
                y = ny;
                d = nd;
                if (map[nx][ny] == 0) {
                    flag = true;
                    break;
                }
            }
        }

        if (x != -1 && y != -1) {
            Node now = new Node(x, y);
            if (!flag) {
                for (Node prev : q[s.num]) {
                    if (prev.x == x && prev.y == y) {
                        prev.flag = false;
                    }
                }
            }
            now.cnt = cnt;
            q[num].add(now);
            s.x = x;
            s.y = y;
            s.d = d;
        }
    }

    static void afterMove() {
        for (int i = 1; i < m + 1; i++) {
            if (q[i].size() == 0)
                continue;
            Node now = q[i].peekFirst();

            if (cnt - now.cnt >= k) {
                now = q[i].pollFirst();
                if (now.flag) {
                    map[now.x][now.y] = 0;
                }
            }

            if (shark.get(i) == null)
                continue;

            now = q[i].peekLast();
            if (map[now.x][now.y] > 0) {
                q[i].pollLast();
                shark.remove(i);
                continue;
            }
            map[now.x][now.y] = i;
        }

    }

    static class Node {
        int x;
        int y;
        int cnt = 0;
        boolean flag = true;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Shark {
        int x;
        int y;
        int num;
        int d;

        Shark(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

}
