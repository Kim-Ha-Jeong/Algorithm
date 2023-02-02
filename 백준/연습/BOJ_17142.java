import java.util.*;
import java.io.*;

public class BOJ_17142 {
    static int n, m, empty = 0, ans = Integer.MAX_VALUE;
    static int[][] map;
    static ArrayList<Virus> virus;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        virus = new ArrayList<>();

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0)
                    empty++;
                if (map[i][j] == 2)
                    virus.add(new Virus(i, j));
            }
        }

        int[] tmp = new int[m];
        combination(0, 0, tmp);

        ans = ans == Integer.MAX_VALUE ? -1 : ans;

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int idx, int[] result) {
        if (depth == m) {
            bfs(result);
            return;
        }

        for (int i = idx; i < virus.size(); i++) {
            result[depth] = i;
            combination(depth + 1, i + 1, result);
        }
    }

    static void bfs(int[] active) {
        Queue<Virus> q = new LinkedList<>();
        int[][] dist = new int[n][n];
        int e = 0;

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int i : active) {
            Virus v = virus.get(i);
            q.add(v);
            dist[v.x][v.y] = 0;
        }

        int max = 0;
        while (!q.isEmpty()) {
            Virus now = q.poll();

            if (e == empty) {
                ans = Math.min(ans, max);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;
                if (map[nx][ny] == 1)
                    continue;

                if (dist[nx][ny] > dist[now.x][now.y] + 1) {
                    if (map[nx][ny] == 0)
                        e++;

                    dist[nx][ny] = dist[now.x][now.y] + 1;
                    max = Math.max(dist[nx][ny], max);
                    Virus next = new Virus(nx, ny);
                    q.add(next);
                }

            }
        }
    }

    static class Virus {
        int x;
        int y;

        Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}