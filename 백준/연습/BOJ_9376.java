import java.util.*;
import java.io.*;

public class BOJ_9376 {
    static char[][] map;
    static int[][] visited;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int h, w;
    static int[][] start = new int[2][2];
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken()) + 2;
            w = Integer.parseInt(st.nextToken()) + 2;

            map = new char[h][w];
            int num = 0;
            for (int i = 1; i < h - 1; i++) {
                map[i][0] = '.';
                map[i][h - 1] = '.';
                char[] c = br.readLine().toCharArray();
                for (int j = 1; j < w - 1; j++) {
                    map[i][j] = c[j - 1];
                    if (map[i][j] == '$') {
                        start[num][0] = i;
                        start[num][1] = j;
                        num++;
                    }
                }
            }

            for (int i = 0; i < w; i++) {
                map[0][i] = map[h - 1][i] = '.';
            }

            int[][] dist1 = bfs(start[0][0], start[0][1]);
            int[][] dist2 = bfs(start[1][0], start[1][1]);
            int[][] dist3 = bfs(0, 0);

            int ans = Integer.MAX_VALUE;
            int sum = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '*')
                        continue;
                    if (dist1[i][j] != -1 && dist2[i][j] != -1 && dist3[i][j] != -1) {
                        sum = dist1[i][j] + dist2[i][j] + dist3[i][j];
                    }
                    if (map[i][j] == '#')
                        sum -= 2;
                    ans = Math.min(sum, ans);
                }
            }

            sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int[][] bfs(int a, int b) {
        int[][] visited = new int[h][w];
        init(visited, -1);
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(a, b, 0));
        visited[a][b] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int cnt = now.cnt;

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                    continue;
                }

                if (map[nx][ny] == '*')
                    continue;

                if (map[nx][ny] == '#') {
                    cnt++;
                }

                if (visited[nx][ny] == -1 || visited[nx][ny] > cnt) {
                    visited[nx][ny] = cnt;
                    q.add(new Node(nx, ny, cnt));
                }

            }
        }

        return visited;
    }

    static void init(int[][] visited, int num) {
        for (int i = 0; i < h; i++) {
            Arrays.fill(visited[i], num);
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
