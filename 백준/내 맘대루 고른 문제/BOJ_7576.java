import java.io.*;
import java.util.*;

public class BOJ_7576 {
    static int m, n;
    static int[][] arr;
    static int[] dx = { 0, 1, -1, 0 };
    static int[] dy = { 1, 0, 0, -1 };
    static int cnt = 0;
    static Queue<Tomato> q = new LinkedList<>();
    static int ans = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0)
                    cnt++;
                if (arr[i][j] == 1)
                    q.add(new Tomato(i, j, 0));
            }
        }

        StringBuffer sb = new StringBuffer();
        if (cnt == 0)
            sb.append(0);
        else {
            bfs();
            sb.append(ans);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void bfs() {
        while (!q.isEmpty()) {
            Tomato t = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (arr[nx][ny] == 0) {
                    cnt--;
                    q.add(new Tomato(nx, ny, t.day + 1));
                    arr[nx][ny] = 1;

                    if (cnt == 0) {
                        ans = t.day + 1;
                        return;
                    }
                }
            }
        }
    }

    static class Tomato {
        int x;
        int y;
        int day;

        Tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

}
