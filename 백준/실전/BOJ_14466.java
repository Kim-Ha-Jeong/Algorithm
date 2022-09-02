import java.util.*;
import java.io.*;

public class BOJ_14466 {
    static int n, k, r;
    static int[][] map;
    static ArrayList<Integer>[] bridges;
    static Node[] cows;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int ans = 0;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        bridges = new ArrayList[n * n + n + 1];
        cows = new Node[k];

        for (int i = n + 1; i < n * n + n + 1; i++) {
            bridges[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int nx = Integer.parseInt(st.nextToken());
            int ny = Integer.parseInt(st.nextToken());

            int a = x * n + y;
            int b = nx * n + ny;

            bridges[a].add(b);
            bridges[b].add(a);
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            cows[i] = new Node(x, y);
            map[x][y] = 1;
        }

        for (int i = 0; i < k; i++) {
            bfs(i);
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs(int num) {
        Node start = cows[num];

        boolean[][] v = new boolean[n + 1][n + 1];
        boolean[][] contact = new boolean[k][k];
        Queue<Node> q = new LinkedList<>();

        q.add(start);
        v[start.x][start.y] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            int a = now.x * n + now.y;

            if (map[now.x][now.y] == 1) {
                for (int i = num + 1; i < k; i++) {
                    Node next = cows[i];

                    if (next.x == now.x && next.y == now.y) {
                        contact[num][i] = true;
                        break;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int b = nx * n + ny;

                if (nx < 1 || nx >= n + 1 || ny < 1 | ny >= n + 1)
                    continue;
                if (v[nx][ny] || bridges[a].contains(b))
                    continue;

                v[nx][ny] = true;
                q.add(new Node(nx, ny));
            }
        }

        for (int i = num + 1; i < k; i++) {
            if (!contact[num][i])
                ans++;
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