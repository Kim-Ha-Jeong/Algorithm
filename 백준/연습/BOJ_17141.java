import java.util.*;
import java.io.*;

class BOJ_17141 {
    static int n, m, empty;
    static int[][] map;
    static int ans = Integer.MAX_VALUE;
    static ArrayList<Node> virusLocation;
    static ArrayList<Node> virus;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        virusLocation = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2)
                    virusLocation.add(new Node(i, j, 0));
                else if (map[i][j] == 0)
                    empty++;
            }
        }

        empty += (virusLocation.size() - m);

        int[] tmp = new int[m];
        combination(0, 0, tmp);

        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int idx, int[] tmp) {
        if (depth == m) {
            virus = new ArrayList<>();
            for (int i : tmp) {
                virus.add(virusLocation.get(i));
            }
            bfs();
            return;
        }

        for (int i = idx; i < virusLocation.size(); i++) {
            tmp[depth] = i;
            combination(depth + 1, i + 1, tmp);
        }
    }

    static void bfs() {
        // int[][] dist = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        Queue<Node> q = new LinkedList<>();

        for (Node v : virus) {
            q.add(v);
            visited[v.x][v.y] = true;
        }

        int cnt = 0;
        int tmp = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (cnt == empty) {
                ans = Math.min(tmp, ans);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                if (visited[nx][ny])
                    continue;

                if (map[nx][ny] == 1)
                    continue;

                q.add(new Node(nx, ny, now.cnt + 1));
                visited[nx][ny] = true;
                // dist[nx][ny] = dist[now.x][now.y] + 1;
                tmp = Math.max(now.cnt + 1, tmp);
                cnt++;
            }
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