import java.util.*;
import java.io.*;

public class BOJ_14502 {
    static int n, m;
    static int[][] map;
    static int[][] copyMap;
    static boolean[][] visited;
    static Node[] choice = new Node[3];
    static Queue<Node> q;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int ans, max = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);

        bw.write(sb.append(max).toString());
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int idx) {
        if (depth == 3) {
            init();
            makeWall();
            infection();
            max = Math.max(ans, max);
            return;
        }

        for (int i = idx; i < n * m; i++) {
            int x = i / m;
            int y = i % m;
            if (map[x][y] != 0)
                continue;
            choice[depth] = new Node(x, y);
            combination(depth + 1, i + 1);
        }
    }

    static void init() {
        copyMap = new int[n][m];
        q = new LinkedList<>();
        visited = new boolean[n][m];
        ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyMap[i][j] = map[i][j];
                if (copyMap[i][j] == 2)
                    q.add(new Node(i, j));
                else if (copyMap[i][j] == 0)
                    ans++;
            }
        }

        ans -= 3;
    }

    static void makeWall() {
        for (int i = 0; i < 3; i++) {
            int x = choice[i].x;
            int y = choice[i].y;

            copyMap[x][y] = 1;
        }
    }

    static void infection() {
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (copyMap[nx][ny] != 0 || visited[nx][ny])
                    continue;

                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
                ans--;
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
