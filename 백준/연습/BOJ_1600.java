import java.util.*;
import java.io.*;

public class BOJ_1600 {
    static boolean[][][] visited;
    static int[][] map;
    static int w, h, ans = -1;
    static int k;
    static int[] hdx = { -1, 1, -2, 2, -2, 2, -1, 1 };
    static int[] hdy = { 2, 2, 1, 1, -1, -1, -2, -2 };
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        visited = new boolean[h][w][k + 1];
        map = new int[h][w];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            int x = now.x;
            int y = now.y;

            if (x == h - 1 && y == w - 1) {
                ans = now.cnt;
                return;
            }

            if (now.horse < k) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + hdx[i];
                    int ny = y + hdy[i];

                    if (!check(nx, ny, now.horse + 1))
                        continue;

                    q.add(new Node(nx, ny, now.cnt + 1, now.horse + 1));
                    visited[nx][ny][now.horse + 1] = true;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!check(nx, ny, now.horse))
                    continue;

                q.add(new Node(nx, ny, now.cnt + 1, now.horse));
                visited[nx][ny][now.horse] = true;
            }
        }
    }

    static boolean check(int nx, int ny, int horse) {
        if (nx < 0 || nx >= h || ny < 0 || ny >= w)
            return false;
        if (visited[nx][ny][horse] || map[nx][ny] == 1)
            return false;
        return true;
    }

    static class Node {
        int x;
        int y;
        int cnt;
        int horse;

        Node(int x, int y, int cnt, int horse) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.horse = horse;
        }
    }
}