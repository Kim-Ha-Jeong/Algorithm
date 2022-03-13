import java.util.*;
import java.io.*;

public class BOJ_14503_2 {
    static int[][] map;
    static int r, c;
    static int[] dc = { 0, 1, 0, -1 };
    static int[] dr = { -1, 0, 1, 0 };
    static int CLEAN = 9;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r][c];

        st = new StringTokenizer(br.readLine());
        Node start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(start);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs(Node start) {
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        map[start.r][start.c] = CLEAN;
        ans++;

        while (!q.isEmpty()) {
            Node now = q.poll();
            boolean flag = false;
            int nd = now.d;

            for (int i = 0; i < 4; i++) {
                nd = nd == 0 ? 3 : nd - 1;
                int nr = now.r + dr[nd];
                int nc = now.c + dc[nd];

                if (nr >= r || nr < 0 || nc >= c || nc < 0 || map[nr][nc] != 0)
                    continue;

                flag = true;
                map[nr][nc] = CLEAN;
                ans++;
                q.add(new Node(nr, nc, nd));
                break;
            }

            if (!flag) {
                int tmp = (now.d + 2) % 4;
                int nr = now.r + dr[tmp];
                int nc = now.c + dc[tmp];

                if (nr >= r || nr < 0 || nc >= c || nc < 0 || map[nr][nc] == 1)
                    break;

                q.add(new Node(nr, nc, now.d));
            }

        }
    }

    static class Node {
        int r;
        int c;
        int d;

        Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}
