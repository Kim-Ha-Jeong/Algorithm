import java.util.*;
import java.io.*;

public class BOJ_6087 {
    static int h, w;
    static char[][] map;
    static Node start, end;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { -1, 1, 0, 0 };
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new char[h][w];

        boolean flag = false;
        for (int i = 0; i < h; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                map[i][j] = c[j];
                if (map[i][j] == 'C') {
                    if (!flag) {
                        start = new Node(i, j);
                        flag = true;
                    } else
                        end = new Node(i, j);
                }
            }
        }

        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][] dist = new int[h][w];
        pq.add(start);

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int d = now.d;

            if (now.x == end.x && now.y == end.y) {
                ans = now.cnt - 1;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (outOfbound(nx, ny))
                    continue;
                if (map[nx][ny] == '*')
                    continue;

                int cnt = d != i ? now.cnt + 1 : now.cnt;
                if (dist[nx][ny] == 0 || dist[nx][ny] >= cnt) {
                    dist[nx][ny] = cnt;
                    Node next = new Node(nx, ny);
                    next.cnt = cnt;
                    next.d = i;
                    pq.add(next);
                }
            }
        }
    }

    static boolean outOfbound(int x, int y) {
        if (x < 0 || x >= h || y < 0 || y >= w)
            return true;
        return false;
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int d = -1;
        int cnt = 0;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node n) {
            return this.cnt - n.cnt;
        }

    }

}
