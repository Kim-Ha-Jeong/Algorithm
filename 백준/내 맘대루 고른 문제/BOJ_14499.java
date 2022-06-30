import java.util.*;
import java.io.*;

public class BOJ_14499 {
    static int n, m, r, c, k;
    static int[][] map;
    static int[] dr = { 0, 0, 0, -1, 1 };
    static int[] dc = { 0, 1, -1, 0, 0 };
    static int[] dice = { 0, 0, 0, 0, 0, 0, 0 };

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        int down = 6, dir, nr = r, nc = c;
        for (int i = 0; i < k; i++) {
            dir = Integer.parseInt(st.nextToken());
            nr += dr[dir];
            nc += dc[dir];

            if (nr >= n || nr < 0 || nc >= m || nc < 0) {
                nr -= dr[dir];
                nc -= dc[dir];
                continue;
            }

            roll(dir);
            if (map[nr][nc] == 0) {
                map[nr][nc] = dice[down];
            } else {
                dice[down] = map[nr][nc];
                map[nr][nc] = 0;
            }

            sb.append(dice[1]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void roll(int dir) {
        int[] prev = new int[7];
        System.arraycopy(dice, 0, prev, 0, dice.length);

        if (dir == 1) {
            dice[3] = prev[1];
            dice[6] = prev[3];
            dice[4] = prev[6];
            dice[1] = prev[4];
        } else if (dir == 2) {
            dice[3] = prev[6];
            dice[6] = prev[4];
            dice[4] = prev[1];
            dice[1] = prev[3];
        } else if (dir == 3) {
            dice[2] = prev[1];
            dice[1] = prev[5];
            dice[5] = prev[6];
            dice[6] = prev[2];
        } else {
            dice[2] = prev[6];
            dice[6] = prev[5];
            dice[5] = prev[1];
            dice[1] = prev[2];
        }
    }

}
