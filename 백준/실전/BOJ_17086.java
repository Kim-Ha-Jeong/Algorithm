import java.util.*;
import java.io.*;

public class BOJ_17086 {
    static int n, m, ans = 0;
    static int[][] map;
    static int[][] dist;
    static ArrayList<Shark> shark;
    static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
    static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        shark = new ArrayList<>();

        map = new int[n][m];
        dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    shark.add(new Shark(i, j));
                }
            }
        }

        bfs();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(dist[i][j], ans);
            }
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Queue<Shark> q = new LinkedList<>();

        for (Shark s : shark) {
            q.add(s);
            dist[s.x][s.y] = 0;
        }

        while (!q.isEmpty()) {
            Shark now = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (dist[nx][ny] > dist[now.x][now.y] + 1) {
                    q.add(new Shark(nx, ny));
                    dist[nx][ny] = dist[now.x][now.y] + 1;
                }
            }
        }
    }

    static class Shark {
        int x;
        int y;

        Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
